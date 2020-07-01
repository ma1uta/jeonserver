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

import io.github.ma1uta.matrix.event.content.EventContent;

import javax.ws.rs.container.AsyncResponse;

public class MessageEvent extends AbstractEvent<EventContent> {

    private final String roomId;
    private final String eventType;
    private final String transactionId;
    private final String sender;

    public MessageEvent(
        AsyncResponse asyncResponse,
        EventContent request,
        String roomId,
        String eventType,
        String transactionId,
        String sender
    ) {
        super(asyncResponse, request);
        this.roomId = roomId;
        this.eventType = eventType;
        this.transactionId = transactionId;
        this.sender = sender;
    }

    public String getRoomId() {
        return roomId;
    }

    public String getEventType() {
        return eventType;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getSender() {
        return sender;
    }
}
