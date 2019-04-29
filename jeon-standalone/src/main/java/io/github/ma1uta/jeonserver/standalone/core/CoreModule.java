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

import com.google.inject.AbstractModule;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigValue;
import io.github.ma1uta.jeonserver.Server;
import io.github.ma1uta.jeonserver.standalone.StandaloneServer;

import java.util.HashMap;
import java.util.Map;

/**
 * Core Guice's module.
 */
public class CoreModule extends AbstractModule {

    private final Config config;

    public CoreModule(Config config) {
        this.config = config;
    }

    @Override
    protected void configure() {
        Map<String, String> persistProperties = new HashMap<>();
        persistProperties.put("hibernate.hikari.dataSourceClassName", config.getString("db.dataSource"));
        persistProperties.put("hibernate.hikari.dataSource.user",     config.getString("db.user"));
        persistProperties.put("hibernate.hikari.dataSource.password", config.getString("db.password"));
        persistProperties.put("hibernate.hikari.dataSource.url",      config.getString("db.url"));
        persistProperties.put("hibernate.dialect",                    config.getString("db.dialect"));

        if (config.hasPath("service.persist")) {
            for (Map.Entry<String, ConfigValue> entry : config.getConfig("service.persist").entrySet()) {
                persistProperties.put(entry.getKey(), entry.getValue().unwrapped().toString());
            }
        }

        JpaPersistModule persistModule = new JpaPersistModule("jeonserver");
        persistModule.properties(persistProperties);

        install(persistModule);

        bind(CoreInitializer.class);
        bind(Server.class).to(StandaloneServer.class).asEagerSingleton();
    }
}
