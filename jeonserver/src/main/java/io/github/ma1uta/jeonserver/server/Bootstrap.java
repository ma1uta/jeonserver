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

package io.github.ma1uta.jeonserver.server;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigException;
import com.typesafe.config.ConfigFactory;
import picocli.CommandLine;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Server bootstrap.
 */
@CommandLine.Command(
    name = "java -jar jeonserver.jar",
    description = "Start JeonServer.",
    versionProvider = VersionProvider.class
)
public class Bootstrap {

    @CommandLine.Option(names = {"-v", "--verbose"}, description = "be verbose.")
    private boolean verbose = false;

    @CommandLine.Option(names = {"-h", "--help"}, usageHelp = true, description = "display this help message.")
    private boolean help = false;

    @CommandLine.Option(names = {"--version"}, versionHelp = true, description = "display version info.")
    private boolean version = false;

    @CommandLine.Option(names = {"-c", "--config"}, description = "specify configuration file.")
    private List<File> files = new ArrayList<>();

    @CommandLine.Option(names = {"-u", "--url"}, description = "specify configuration url.")
    private List<URL> urls = new ArrayList<>();

    /**
     * Main entry point.
     *
     * @param args program arguments.
     */
    public static void main(String[] args) {
        Bootstrap bootstrap = new Bootstrap();
        CommandLine commandLine = new CommandLine(bootstrap);
        commandLine.parse(args);
        if (commandLine.isVersionHelpRequested()) {
            commandLine.printVersionHelp(System.out);
            return;
        } else if (commandLine.isUsageHelpRequested()) {
            commandLine.usage(System.out);
            return;
        }

        bootstrap.entry();
    }

    /**
     * Start main program.
     */
    public void entry() {
        Config config = ConfigFactory.load().withFallback(ConfigFactory.load("application.conf"));

        for (URL url : urls) {
            try {
                config = ConfigFactory.parseURL(url).withFallback(config);
            } catch (ConfigException e) {
                System.err.printf("Unable to read configuration from '%s'", url);
            }
        }

        for (File file : files) {
            try {
                config = ConfigFactory.parseFile(file).withFallback(config);
            } catch (ConfigException e) {
                System.err.printf("Unable to read file '%s'", file.getAbsolutePath());
            }
        }
    }
}

