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

package io.github.ma1uta.jeonserver.entity.core.converter;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * JPA JSONB mapper.
 */
public abstract class JsonbMapper {

    private static volatile JsonbMapper instance;

    /**
     * Provides mapper instance.
     *
     * @return mapper.
     */
    public static JsonbMapper getInstance() {
        if (instance == null) {
            synchronized (JsonbMapper.class) {
                if (instance == null) {
                    Iterator<JsonbMapper> iterator = ServiceLoader.load(JsonbMapper.class).iterator();
                    if (!iterator.hasNext()) {
                        throw new IllegalArgumentException("Missing jsonb converter.");
                    }
                    instance = iterator.next();
                }
            }
        }
        return instance;
    }

    /**
     * Convert from object to string.
     *
     * @param attribute object.
     * @return jsonb string.
     */
    public abstract String convertToString(Object attribute);

    /**
     * Convert from string to object.
     *
     * @param dbData jsonb string.
     * @return object.
     */
    public abstract Object convertToObject(String dbData);

}
