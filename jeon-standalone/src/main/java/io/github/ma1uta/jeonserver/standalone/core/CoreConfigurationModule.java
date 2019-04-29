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

import com.google.inject.Provides;
import com.typesafe.config.Config;
import io.github.ma1uta.jeonserver.ConfigurationProvider;
import io.github.ma1uta.jeonserver.standalone.ConfigurationModule;

import java.util.Iterator;
import java.util.Set;
import javax.inject.Inject;

/**
 * Core configuration module.
 */
public class CoreConfigurationModule extends ConfigurationModule {

    private final Config config;

    public CoreConfigurationModule(Config config) {
        this.config = config;
    }

    @Override
    protected Config config() {
        return config;
    }

    /**
     * Configuration.
     *
     * @param providers config providers.
     * @return configuration.
     */
    @Provides
    @Inject
    public Config config(Set<ConfigurationProvider> providers) {
        Iterator<ConfigurationProvider> iterator = providers.iterator();
        Config config = iterator.next().config();
        while (iterator.hasNext()) {
            config = iterator.next().config().withFallback(config);
        }
        return config;
    }
}
