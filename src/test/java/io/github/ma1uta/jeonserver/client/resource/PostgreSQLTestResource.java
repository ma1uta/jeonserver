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

package io.github.ma1uta.jeonserver.client.resource;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.Collections;
import java.util.Map;

public class PostgreSQLTestResource implements QuarkusTestResourceLifecycleManager {

    private PostgreSQLContainer<?> container;

    @Override
    public Map<String, String> start() {
        container = new PostgreSQLContainer<>()
            .withDatabaseName("jeonserver")
            .withUsername("jeonserver")
            .withPassword("jeonserver")
            .withExposedPorts(55555);
        container.start();

        System.setProperty("quarkus.datasource.url", container.getJdbcUrl());
        System.setProperty("quarkus.datasource.username", container.getUsername());
        System.setProperty("quarkus.datasource.password", container.getPassword());
        return Collections.emptyMap();
    }

    @Override
    public void stop() {
        container.stop();
    }
}
