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

import com.google.inject.Guice;
import io.github.ma1uta.jeonserver.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(Bootstrap.class);

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
        LOGGER.info("Preparing JeonServer.");

        Bootstrap bootstrap = new Bootstrap();
        CommandLine commandLine = new CommandLine(bootstrap);

        Map<String, Bundle> bundles = new HashMap<>();
        for (Bundle bundle : ServiceLoader.load(Bundle.class)) {
            LOGGER.info(String.format("Found bundle: %s", bundle.getName()));
            bundles.put(bundle.getName(), bundle);
            commandLine.addMixin(bundle.getName(), bundle);
        }

        LOGGER.info("Parse command line.");
        commandLine.parse(args);
        if (commandLine.isVersionHelpRequested()) {
            commandLine.printVersionHelp(System.out);
            return;
        } else if (commandLine.isUsageHelpRequested()) {
            commandLine.usage(System.out);
            return;
        }

        LOGGER.info("Starting JeonServer.");
        bootstrap.entry(bundles);
    }

    /**
     * Start main program.
     *
     * @param bundles loaded bundles.
     */
    public void entry(Map<String, Bundle> bundles) {
        LOGGER.debug("Check commands.");
        for (Bundle bundle : bundles.values()) {
            try {
                LOGGER.debug("Check bundle: {}", bundle.getName());
                switch (bundle.parseCommandLine()) {
                    case STOP:
                        LOGGER.info("Exit.");
                        return;
                    case NEXT:
                    default:
                        LOGGER.debug("Next bundle.");
                }
            } catch (Exception e) {
                LOGGER.error("Unable to run commands.", e);
                System.exit(1);
            }
        }

        LOGGER.info("Initialize bundles.");
        for (Bundle bundle : bundles.values()) {
            try {
                LOGGER.info("Initialize bundle: {}", bundle.getName());
                bundle.init();
            } catch (Exception e) {
                LOGGER.error("Unable to initialize bundle", e);
                System.exit(1);
            }
        }

        LOGGER.info("Run Server.");
        Guice.createInjector(new ArrayList<>(bundles.values())).getInstance(Server.class).run();
    }
}

