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

import picocli.CommandLine;

/**
 * Server bootstrap.
 */
@CommandLine.Command(versionProvider = VersionProvider.class)
public class Bootstrap {

    @CommandLine.Option(names = {"-v", "--verbose"}, description = "be verbose.")
    private boolean verbose = false;

    @CommandLine.Option(names = {"-h", "--help"}, usageHelp = true, description = "display this help message.")
    private boolean help;

    @CommandLine.Option(names = {"-V", "--version"}, versionHelp = true, description = "display version info.")
    private boolean version;

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

        if (bootstrap.verbose) {
            System.out.println("13");
        }
    }
}
