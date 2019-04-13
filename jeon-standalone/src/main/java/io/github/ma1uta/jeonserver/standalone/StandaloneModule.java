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

import com.typesafe.config.Config;
import com.typesafe.config.ConfigException;
import com.typesafe.config.ConfigFactory;
import io.github.ma1uta.jeonserver.Module;
import picocli.CommandLine;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Standalone module.
 */
public class StandaloneModule implements Module {

    @CommandLine.Option(names = {"-f", "--file"}, description = "specify configuration file.")
    private List<File> files = new ArrayList<>();

    @CommandLine.Option(names = {"-u", "--url"}, description = "specify configuration url.")
    private List<URL> urls = new ArrayList<>();

    @Override
    public String getName() {
        return "standalone";
    }

    @Override
    public Optional<Config> init() {
        Config config = null;
        for (URL url : urls) {
            try {
                if (config == null) {
                    config = ConfigFactory.parseURL(url);
                } else {
                    config = ConfigFactory.parseURL(url).withFallback(config);
                }
            } catch (ConfigException e) {
                System.err.printf("Unable to read configuration from '%s'", url);
            }
        }

        for (File file : files) {
            try {
                if (config == null) {
                    config = ConfigFactory.parseFile(file);
                } else {
                    config = ConfigFactory.parseFile(file).withFallback(config);
                }
            } catch (ConfigException e) {
                System.err.printf("Unable to read file '%s'", file.getAbsolutePath());
            }
        }
        return Optional.ofNullable(config);
    }

    @Override
    public void restart() {

    }

    @Override
    public void close() throws Exception {

    }
}
