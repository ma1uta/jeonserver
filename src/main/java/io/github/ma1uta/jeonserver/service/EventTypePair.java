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

package io.github.ma1uta.jeonserver.service;

import io.github.ma1uta.matrix.event.Event;
import io.github.ma1uta.matrix.event.content.EventContent;

import java.lang.reflect.ParameterizedType;

public class EventTypePair<C extends EventContent, E extends Event<C>> {

    private final Class<E> eventType;
    private final Class<C> contentType;

    @SuppressWarnings("unchecked")
    EventTypePair(Class<E> eventType) {
        this(eventType, (Class<C>) ((ParameterizedType) eventType.getGenericSuperclass()).getActualTypeArguments()[0]);
    }

    EventTypePair(Class<E> eventType, Class<C> contentType) {
        this.eventType = eventType;
        this.contentType = contentType;
    }

    public Class<E> getEventType() {
        return eventType;
    }

    public Class<C> getContentType() {
        return contentType;
    }
}
