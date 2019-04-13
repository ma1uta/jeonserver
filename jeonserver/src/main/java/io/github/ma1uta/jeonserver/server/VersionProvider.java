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

import java.net.URL;
import java.util.Enumeration;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

/**
 * JeonServer version provider.
 */
public class VersionProvider implements CommandLine.IVersionProvider {

    private String[] version;

    private boolean isApplicableManifest(Manifest manifest) {
        Attributes attributes = manifest.getMainAttributes();
        return "jeonserver".equalsIgnoreCase(get(attributes, "Implementation-Title"));
    }

    private String get(Attributes attributes, String key) {
        return (String) attributes.get(new Attributes.Name(key));
    }

    @Override
    public String[] getVersion() throws Exception {
        if (version == null) {
            Enumeration<URL> resources = CommandLine.class.getClassLoader().getResources("META-INF/MANIFEST.MF");
            while (resources.hasMoreElements()) {
                URL url = resources.nextElement();
                Manifest manifest = new Manifest(url.openStream());
                if (isApplicableManifest(manifest)) {
                    Attributes attr = manifest.getMainAttributes();
                    version = new String[] {
                        get(attr, "Implementation-Title") + " \"" + get(attr, "Implementation-Version") + "\"",
                        "Author: " + get(attr, "Implementation-Vendor"),
                        "Copyright: Apache License 2.0"
                    };
                }
            }
        }
        return version;
    }
}
