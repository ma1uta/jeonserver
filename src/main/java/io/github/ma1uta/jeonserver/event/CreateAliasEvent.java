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

package io.github.ma1uta.jeonserver.event;

import io.github.ma1uta.matrix.client.model.room.RoomId;

import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.core.SecurityContext;

public class CreateAliasEvent extends AbstractEvent<RoomId> {

    private final SecurityContext securityContext;

    private final String alias;

    public CreateAliasEvent(RoomId roomId, SecurityContext securityContext, AsyncResponse asyncResponse, String alias) {
        super(asyncResponse, roomId);
        this.securityContext = securityContext;
        this.alias = alias;
    }

    public SecurityContext getSecurityContext() {
        return securityContext;
    }

    public String getAlias() {
        return alias;
    }
}
