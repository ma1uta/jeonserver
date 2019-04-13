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

package io.github.ma1uta.jeonserver;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import picocli.CommandLine;

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

        for (Module module : ServiceLoader.load(Module.class)) {
            commandLine.addMixin(module.getName(), module);
        }

        commandLine.parse(args);
        if (commandLine.isVersionHelpRequested()) {
            commandLine.printVersionHelp(System.out);
            return;
        } else if (commandLine.isUsageHelpRequested()) {
            commandLine.usage(System.out);
            return;
        }

        bootstrap.entry(commandLine);
    }

    /**
     * Start main program.
     *
     * @param commandLine command line.
     */
    public void entry(CommandLine commandLine) {
        Config config = ConfigFactory.load().withFallback(ConfigFactory.load("application.conf"));

        for (Object mixinItem : commandLine.getMixins().values()) {
            if (mixinItem instanceof Module) {
                Optional<Config> optionalConfig = ((Module) mixinItem).init();
                if (optionalConfig.isPresent()) {
                    config = optionalConfig.get().withFallback(config);
                }
            }
        }
    }
}

