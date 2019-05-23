/*
 * Copyright JeonServer sablintolya@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.ma1uta.jeonserver.standalone;

import com.google.inject.AbstractModule;
import com.google.inject.Binding;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.Provides;
import com.google.inject.Scopes;
import com.typesafe.config.Config;
import io.github.ma1uta.jeonserver.Server;
import lombok.extern.slf4j.Slf4j;
import org.pf4j.DefaultExtensionFinder;
import org.pf4j.DefaultPluginManager;
import org.pf4j.ExtensionFinder;
import org.pf4j.PluginManager;
import org.pf4j.PluginWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine;

import java.lang.reflect.Method;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.ServiceLoader;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.transaction.Transactional;

/**
 * Server bootstrap.
 */
@CommandLine.Command(
    name = "java -jar <server app>.jar",
    description = "Run JeonServer.",
    versionProvider = VersionProvider.class
)
@Slf4j
public class Bootstrap {

    @CommandLine.Option(names = {"-v", "--verbose"}, description = "be verbose.")
    private boolean verbose = false;

    @CommandLine.Option(names = {"-h", "--help"}, usageHelp = true, description = "display this help message.")
    private boolean help = false;

    @CommandLine.Option(names = {"--version"}, versionHelp = true, description = "display version info.")
    private boolean version = false;

    @CommandLine.Option(names = {"-p", "--plugins"}, description = "plugin folder.")
    private Path pluginFolder;

    @CommandLine.Unmatched
    private List<String> unmatched;

    /**
     * Main entry point.
     *
     * @param args program arguments.
     */
    public static void main(String[] args) {
        log.info("Boot JeonServer.");

        Bootstrap bootstrap = new Bootstrap();
        CommandLine commandLine = new CommandLine(bootstrap);

        commandLine.parse(args);
        try {
            bootstrap.boot(args);
        } catch (Exception e) {
            log.error("Shutdown JeonServer.", e);
            System.exit(1);
        }
    }

    /**
     * Start main program.
     *
     * @param args program arguments.
     * @throws BootstrapException when unable to start JeonServer.
     */
    public void boot(String[] args) throws BootstrapException {
        if (verbose) {
            Logger logger = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
            if (logger instanceof ch.qos.logback.classic.Logger) {
                ((ch.qos.logback.classic.Logger) logger).setLevel(ch.qos.logback.classic.Level.ALL);
            }
            log.info("Be verbose.");
        }

        DefaultPluginManager pluginManager = initPluginManager();

        Map<String, Bundle> bundles = loadBundles(pluginManager);

        if (parseCommandLine(args, bundles)) {
            return;
        }

        Injector rootInjector = Guice.createInjector(loadConfigurationModules(bundles));

        List<Module> modules = loadModules(pluginManager, bundles, rootInjector);

        log.info("Configure JeonServer.");
        Injector childInjector = rootInjector.createChildInjector(modules);
        childInjector.getInstance(InitializerRunner.class).init();

        postConstruct(childInjector);

        log.info("Run JeonServer.");
        childInjector.getInstance(Server.class).run();
    }

    /**
     * Provides the {@link PostConstruct} logic.
     *
     * @param childInjector service injector.
     * @throws BootstrapException when unable construct service.
     */
    private void postConstruct(Injector childInjector) throws BootstrapException {
        EntityManager entityManager = childInjector.getInstance(EntityManager.class);
        for (Binding<?> binding : childInjector.getAllBindings().values()) {
            if (Scopes.isSingleton(binding)) {
                Object instance = binding.getProvider().get();
                Class<?> clazz = instance.getClass();
                for (Method method : clazz.getDeclaredMethods()) {
                    if (method.isAnnotationPresent(PostConstruct.class)) {
                        boolean accessible = method.isAccessible();
                        log.debug("{}#{} annotated with @PostConstruct", clazz.getCanonicalName(), method.getName());
                        EntityTransaction tx = method.isAnnotationPresent(Transactional.class) ? entityManager.getTransaction() : null;
                        if (tx != null) {
                            log.debug("Begin transaction.");
                            tx.begin();
                        } else {
                            log.debug("Without transaction.");
                        }
                        try {
                            method.setAccessible(true);
                            log.debug("Invoke.");
                            method.invoke(instance);
                        } catch (Throwable e) {
                            log.error("Unable to run @PostConstruct method.", e);
                            if (tx != null) {
                                tx.setRollbackOnly();
                            }
                            throw new BootstrapException(String.format("Unable to construct singleton: %s", clazz.getCanonicalName()), e);
                        } finally {
                            method.setAccessible(accessible);
                            if (tx != null) {
                                if (tx.getRollbackOnly()) {
                                    log.debug("Rollback transaction.");
                                    tx.rollback();
                                } else {
                                    log.debug("Commit transaction.");
                                    tx.commit();
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private List<ConfigurationModule> loadConfigurationModules(Map<String, Bundle> bundles) {
        List<ConfigurationModule> configModules = bundles.values()
            .stream()
            .map(Bundle::configurationModule)
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        if (log.isTraceEnabled()) {
            for (ConfigurationModule module : configModules) {
                log.trace("Found configuration: {}", module.getClass().getName());
            }
        }
        return configModules;
    }

    private List<Module> loadModules(DefaultPluginManager pluginManager, Map<String, Bundle> bundles, Injector rootInjector)
        throws BootstrapException {
        List<Module> modules = new ArrayList<>(bundles.size() + 1);
        modules.add(new AbstractModule() {

            @Provides
            PluginManager pluginManager() {
                return pluginManager;
            }
        });
        Config config = loadConfig(rootInjector);
        for (Bundle bundle : bundles.values()) {
            try {
                modules.addAll(bundle.init(config, pluginManager));
            } catch (Exception e) {
                throw new BootstrapException(String.format("Unable to initialize bundle: %s", bundle.getClass().getName()), e);
            }
        }
        if (log.isTraceEnabled()) {
            for (Module module : modules) {
                log.trace("Found module: {}", module.getClass().getName());
            }
        }
        return modules;
    }

    private Config loadConfig(Injector rootInjector) throws BootstrapException {
        Config config = rootInjector.getInstance(Config.class);
        if (config == null) {
            throw new BootstrapException("Unable to build configuration. Config not found.");
        }
        return config;
    }

    private boolean parseCommandLine(String[] args, Map<String, Bundle> bundles) {
        CommandLine cli = new CommandLine(this);
        for (Map.Entry<String, Bundle> bundleEntry : bundles.entrySet()) {
            cli.addMixin(bundleEntry.getKey(), bundleEntry.getValue().cli());
        }
        cli.parse(args);

        if (cli.isVersionHelpRequested()) {
            cli.printVersionHelp(System.out);
            return true;
        } else if (cli.isUsageHelpRequested()) {
            cli.usage(System.out);
            return true;
        }

        for (Bundle bundle : bundles.values()) {
            if (bundle.cli().invokeCommand()) {
                log.info("Stop JeonServer.");
                return true;
            }
        }
        return false;
    }

    private Map<String, Bundle> loadBundles(DefaultPluginManager pluginManager) {
        pluginManager.loadPlugins();
        pluginManager.startPlugins();

        for (PluginWrapper plugin : pluginManager.getPlugins()) {
            log.info("Found plugin: {}", plugin.getPluginId());
        }

        Map<String, Bundle> bundles = new HashMap<>();
        for (Bundle bundle : ServiceLoader.load(Bundle.class)) {
            bundles.put(bundle.name(), bundle);
        }
        if (!pluginManager.getPlugins().isEmpty()) {
            for (Bundle bundle : pluginManager.getExtensions(Bundle.class)) {
                bundles.put(bundle.name(), bundle);
            }
        }
        if (log.isInfoEnabled()) {
            for (String name : bundles.keySet()) {
                log.info("Found bundle: {}", name);
            }
        }
        return bundles;
    }

    private DefaultPluginManager initPluginManager() {
        return new DefaultPluginManager(pluginFolder) {
            @Override
            protected ExtensionFinder createExtensionFinder() {
                DefaultExtensionFinder finder = (DefaultExtensionFinder) super.createExtensionFinder();
                finder.addServiceProviderExtensionFinder();
                return finder;
            }
        };
    }
}

