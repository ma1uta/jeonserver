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
import com.google.inject.Module;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import io.github.ma1uta.jeonserver.Bundle;
import io.github.ma1uta.jeonserver.Server;
import io.github.ma1uta.jeonserver.VersionProvider;
import picocli.CommandLine;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.ServiceLoader;

/**
 * Server bootstrap.
 */
@CommandLine.Command(
    name = "java -jar <server app>.jar",
    description = "Run JeonServer.",
    versionProvider = VersionProvider.class
)
public class Bootstrap {

    @CommandLine.Option(names = {"-v", "--verbose"}, description = "be verbose.")
    private boolean verbose = false;

    @CommandLine.Option(names = {"-h", "--help"}, usageHelp = true, description = "display this help message.")
    private boolean help = false;

    @CommandLine.Option(names = {"--version"}, versionHelp = true, description = "display version info.")
    private boolean version = false;

    /**
     * Main entry point.
     *
     * @param args program arguments.
     */
    public static void main(String[] args) {
        Bootstrap bootstrap = new Bootstrap();
        CommandLine commandLine = new CommandLine(bootstrap);

        Map<String, Bundle> bundles = new HashMap<>();
        for (Bundle bundle : ServiceLoader.load(Bundle.class)) {
            bundles.put(bundle.getName(), bundle);
            commandLine.addMixin(bundle.getName(), bundle);
        }

        commandLine.parse(args);
        if (commandLine.isVersionHelpRequested()) {
            commandLine.printVersionHelp(System.out);
            return;
        } else if (commandLine.isUsageHelpRequested()) {
            commandLine.usage(System.out);
            return;
        }

        bootstrap.entry(bundles);
    }

    /**
     * Start main program.
     *
     * @param bundles loaded bundles.
     */
    public void entry(Map<String, Bundle> bundles) {
        Config config = ConfigFactory.load().withFallback(ConfigFactory.load("application.conf"));

        for (Bundle bundle : bundles.values()) {
            Optional<Config> optionalConfig = bundle.init();
            if (optionalConfig.isPresent()) {
                config = optionalConfig.get().withFallback(config);
            }
        }

        Collection<Module> modules = new ArrayList<>(bundles.values());

        Config preparedConfig = config;
        modules.add(new AbstractModule() {
            @Override
            protected void configure() {
                bind(Config.class).toInstance(preparedConfig);
                bind(Server.class).toInstance(new Server());
            }
        });
        Guice.createInjector(modules).getInstance(Server.class).run();
    }
}

