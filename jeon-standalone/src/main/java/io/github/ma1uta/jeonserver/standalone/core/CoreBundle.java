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

package io.github.ma1uta.jeonserver.standalone.core;

import com.google.inject.Module;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import io.github.ma1uta.jeonserver.standalone.Bundle;
import io.github.ma1uta.jeonserver.standalone.CommandLineExtension;
import io.github.ma1uta.jeonserver.standalone.ConfigurationModule;
import org.pf4j.PluginManager;
import picocli.CommandLine;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Core bundle.
 * <br>
 * Used to provide common services.
 */
public class CoreBundle implements CommandLineExtension, Bundle {

    @CommandLine.Option(names = {"-f", "--file"}, description = "specify configuration file.")
    private List<File> files = new ArrayList<>();

    @CommandLine.Option(names = {"-u", "--url"}, description = "specify configuration url.")
    private List<URL> urls = new ArrayList<>();

    @Override
    public String name() {
        return "core";
    }

    @Override
    public Set<Module> init(Config config, PluginManager pluginManager) throws Exception {
        return Collections.singleton(new CoreModule(config));
    }

    @Override
    public CommandLineExtension cli() {
        return this;
    }

    @Override
    public ConfigurationModule configurationModule() {
        var config = ConfigFactory.load().withFallback(ConfigFactory.load("application.conf"));
        for (URL url : urls) {
            config = ConfigFactory.parseURL(url).withFallback(config);
        }

        for (File file : files) {
            config = ConfigFactory.parseFile(file).withFallback(config);
        }
        return new CoreConfigurationModule(config);
    }

    @Override
    public boolean invokeCommand() {
        return false;
    }
}
