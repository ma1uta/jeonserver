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

import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.core.SecurityContext;

public class SyncEvent extends AbstractEvent<String> {

    private final String filter;

    private final Boolean fullState;

    private final String setPresence;

    private final Long timeout;

    private final String sender;

    public SyncEvent(
        AsyncResponse asyncResponse,
        String since,
        String filter,
        Boolean fullState,
        String setPresence,
        Long timeout,
        String sender
    ) {
        super(asyncResponse, since);
        this.filter = filter;
        this.fullState = fullState;
        this.setPresence = setPresence;
        this.timeout = timeout;
        this.sender = sender;
    }

    public String getFilter() {
        return filter;
    }

    public Boolean getFullState() {
        return fullState;
    }

    public String getSetPresence() {
        return setPresence;
    }

    public Long getTimeout() {
        return timeout;
    }

    public String getSender() {
        return sender;
    }
}
