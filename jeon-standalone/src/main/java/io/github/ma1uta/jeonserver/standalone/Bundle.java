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

import com.google.inject.Module;
import com.typesafe.config.Config;
import org.pf4j.ExtensionPoint;
import org.pf4j.PluginManager;

import java.util.Set;

/**
 * JeonServer's bundle.
 */
public interface Bundle extends ExtensionPoint {

    /**
     * Bundle name.
     *
     * @return bundle name.
     */
    String name();

    /**
     * Prepare bundle for boot.
     *
     * @param config        bootstrap config.
     * @param pluginManager plugin manager.
     * @return Guice modules for server.
     * @throws Exception when initialization was failed.
     */
    Set<Module> init(Config config, PluginManager pluginManager) throws Exception;

    /**
     * Command line mixin.
     *
     * @return command line mixin.
     */
    CommandLineExtension cli();

    /**
     * Configuration module.
     *
     * @return configuration module.
     */
    ConfigurationModule configurationModule();
}
