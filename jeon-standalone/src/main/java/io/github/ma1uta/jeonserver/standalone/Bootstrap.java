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
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.Provides;
import com.typesafe.config.Config;
import io.github.ma1uta.jeonserver.Server;
import org.pf4j.DefaultExtensionFinder;
import org.pf4j.DefaultPluginManager;
import org.pf4j.ExtensionFinder;
import org.pf4j.PluginManager;
import org.pf4j.PluginWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

/**
 * Server bootstrap.
 */
@CommandLine.Command(
    name = "java -jar <server app>.jar",
    description = "Run JeonServer.",
    versionProvider = VersionProvider.class
)
public class Bootstrap {

    private static final Logger LOGGER = LoggerFactory.getLogger(Bootstrap.class);

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
        LOGGER.info("Boot JeonServer.");

        Bootstrap bootstrap = new Bootstrap();
        CommandLine commandLine = new CommandLine(bootstrap);

        commandLine.parse(args);
        bootstrap.boot(args);
    }

    /**
     * Start main program.
     *
     * @param args program arguments.
     */
    public void boot(String[] args) {
        if (verbose) {
            Logger logger = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
            if (logger instanceof ch.qos.logback.classic.Logger) {
                ((ch.qos.logback.classic.Logger) logger).setLevel(ch.qos.logback.classic.Level.ALL);
            }
            LOGGER.info("Be verbose.");
        }

        DefaultPluginManager pluginManager = new DefaultPluginManager(pluginFolder) {
            @Override
            protected ExtensionFinder createExtensionFinder() {
                DefaultExtensionFinder finder = (DefaultExtensionFinder) super.createExtensionFinder();
                finder.addServiceProviderExtensionFinder();
                return finder;
            }
        };

        pluginManager.loadPlugins();
        pluginManager.startPlugins();
        boolean skipPlugins = pluginManager.getPlugins().isEmpty();
        for (PluginWrapper plugin : pluginManager.getPlugins()) {
            LOGGER.info("Found plugin: {}", plugin.getPluginId());
        }

        Map<String, Bundle> bundles = new HashMap<>();
        for (Bundle bundle : ServiceLoader.load(Bundle.class)) {
            bundles.put(bundle.name(), bundle);
        }
        if (!skipPlugins) {
            for (Bundle bundle : pluginManager.getExtensions(Bundle.class)) {
                bundles.put(bundle.name(), bundle);
            }
        }
        if (LOGGER.isInfoEnabled()) {
            for (String name : bundles.keySet()) {
                LOGGER.info("Found bundle: {}", name);
            }
        }

        CommandLine cli = new CommandLine(this);
        for (Map.Entry<String, Bundle> bundleEntry : bundles.entrySet()) {
            cli.addMixin(bundleEntry.getKey(), bundleEntry.getValue().cli());
        }
        cli.parse(args);

        if (cli.isVersionHelpRequested()) {
            cli.printVersionHelp(System.out);
            return;
        } else if (cli.isUsageHelpRequested()) {
            cli.usage(System.out);
            return;
        }

        for (Bundle bundle : bundles.values()) {
            if (bundle.cli().invokeCommand()) {
                LOGGER.info("Stop JeonServer.");
                return;
            }
        }

        List<ConfigurationModule> configModules = bundles.values()
            .stream()
            .map(Bundle::configurationModule)
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        if (LOGGER.isWarnEnabled()) {
            for (ConfigurationModule module : configModules) {
                LOGGER.warn("Found configuration: {}", module.getClass().getName());
            }
        }

        Injector rootInjector = Guice.createInjector(configModules);
        Config config = rootInjector.getInstance(Config.class);

        if (config == null) {
            LOGGER.error("Unable to build configuration.");
            System.exit(1);
        }

        List<Module> modules = new ArrayList<>(bundles.size() + 1);
        modules.add(new AbstractModule() {

            @Provides
            PluginManager pluginManager() {
                return pluginManager;
            }
        });
        for (Bundle bundle : bundles.values()) {
            try {
                modules.addAll(bundle.init(config, pluginManager));
            } catch (Exception e) {
                LOGGER.error("Unable to initialize bundle", e);
                System.exit(1);
            }
        }

        LOGGER.info("Run JeonServer.");
        rootInjector.createChildInjector(modules).getInstance(Server.class).run();
    }
}

