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

import com.google.inject.persist.PersistService;
import com.typesafe.config.Config;
import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.Flyway;

import javax.inject.Inject;

/**
 * Core Initializer.
 */
@Slf4j
public class CoreInitializer {

    @Inject
    public CoreInitializer(Config config, PersistService persistService) {

        updateSchema(config);

        Runtime.getRuntime().addShutdownHook(new Thread(persistService::stop));

        persistService.start();
    }

    private void updateSchema(Config config) {
        if (config.hasPath("db.schemaUpdate") && config.getBoolean("db.schemaUpdate")) {
            log.info("Updating database schema...");
            Flyway.configure().dataSource(config.getString("db.url"), config.getString("db.user"), config.getString("db.password")).load()
                .migrate();
            log.info("Updating database schema... Done.");
        } else {
            log.info("Updating database schema... Skip.");
        }
    }
}
