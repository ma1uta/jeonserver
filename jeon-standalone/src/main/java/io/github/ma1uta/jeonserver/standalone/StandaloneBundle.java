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
import com.google.inject.Inject;
import com.google.inject.Provides;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import io.github.ma1uta.jeonserver.Server;
import picocli.CommandLine;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Standalone module.
 */
public class StandaloneBundle extends AbstractModule implements Bundle {

    @CommandLine.Option(names = {"-f", "--file"}, description = "specify configuration file.")
    private List<File> files = new ArrayList<>();

    @CommandLine.Option(names = {"-u", "--url"}, description = "specify configuration url.")
    private List<URL> urls = new ArrayList<>();

    private Config config;

    @Override
    public String getName() {
        return "standalone";
    }

    @Override
    public void restart() {
    }

    @Override
    public ParseResult parseCommandLine() throws Exception {
        return ParseResult.NEXT;
    }

    @Override
    public void init() throws Exception {
        initConfig();
    }

    private void initConfig() throws Exception {
        Config config = ConfigFactory.load().withFallback(ConfigFactory.load("application.conf"));
        for (URL url : urls) {
            config = ConfigFactory.parseURL(url).withFallback(config);
        }

        for (File file : files) {
            config = ConfigFactory.parseFile(file).withFallback(config);
        }

        this.config = config;
    }

    @Override
    protected void configure() {
        Map<String, String> persistProperties = new HashMap<>();
        persistProperties.put("hibernate.connection.provider_class", "com.zaxxer.hikari.hibernate.HikariConnectionProvider");
        persistProperties.put("hibernate.hikari.dataSourceClassName", config.getString("db.dataSource"));
        persistProperties.put("hibernate.hikari.dataSource.user", config.getString("db.user"));
        persistProperties.put("hibernate.hikari.dataSource.password", config.getString("db.password"));
        persistProperties.put("hibernate.hikari.dataSource.url", config.getString("db.url"));
        persistProperties.put("hibernate.dialect", config.getString("db.dialect"));

        JpaPersistModule persistModule = new JpaPersistModule("jeonserver");
        persistModule.properties(persistProperties);

        install(persistModule);

        bind(Server.class).to(StandaloneServer.class).asEagerSingleton();
        bind(Initializer.class).asEagerSingleton();
    }

    @Override
    public void close() throws Exception {

    }

    /**
     * Configuration.
     *
     * @return configuration.
     */
    @Provides
    @Inject
    public Config config(/*@Nullable Set<ConfigurationProvider> providers*/) {
        Config config = this.config;
        /*if (providers != null) {
            for (ConfigurationProvider provider : providers) {
                config = provider.config().withFallback(config);
            }
        }*/
        return config;
    }
}
