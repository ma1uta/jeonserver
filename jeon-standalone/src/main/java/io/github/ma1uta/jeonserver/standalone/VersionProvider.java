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

import picocli.CommandLine;

import java.util.Objects;
import java.util.Properties;

/**
 * JeonServer version provider.
 */
public class VersionProvider implements CommandLine.IVersionProvider {

    private String[] version;

    @Override
    public String[] getVersion() throws Exception {
        if (version == null) {
            Properties properties = new Properties();
            properties.load(Objects.requireNonNull(VersionProvider.class.getClassLoader().getResourceAsStream("build.properties")));

            version = new String[] {
                properties.getProperty("name") + " \"" + properties.getProperty("version") + "\"",
                "Revision: " + properties.getProperty("revision"),
                "Timestamp: " + properties.getProperty("timestamp")
            };
        }
        return version;
    }
}
