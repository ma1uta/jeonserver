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

package io.github.ma1uta.jeonserver.persistence.entity.room;

import io.github.ma1uta.jeonserver.persistence.entity.PersistentDataUnit;
import io.github.ma1uta.jeonserver.persistence.jsonb.JsonType;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * PDU for room version v4.
 */
@Entity
@DiscriminatorValue("v4")
public class PersistentDataUnitV4 extends PersistentDataUnit {

    @Type(
        type = "jsonb",
        parameters = {@Parameter(name = JsonType.TYPE, value = "java.util.ArrayList")}
    )
    @Column(name = "prev_events", columnDefinition = "jsonb")
    private List<String> prevEvents;

    @Type(
        type = "jsonb",
        parameters = {@Parameter(name = JsonType.TYPE, value = "java.util.ArrayList")}
    )
    @Column(name = "auth_events", columnDefinition = "jsonb")
    private List<String> authEvents;

    public List<String> getPrevEvents() {
        return prevEvents;
    }

    public void setPrevEvents(List<String> prevEvents) {
        this.prevEvents = prevEvents;
    }

    public List<String> getAuthEvents() {
        return authEvents;
    }

    public void setAuthEvents(List<String> authEvents) {
        this.authEvents = authEvents;
    }
}

