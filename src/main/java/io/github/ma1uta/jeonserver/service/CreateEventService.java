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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.ma1uta.jeonserver.persistence.entity.PersistentDataUnit;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;

import java.time.ZonedDateTime;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class CreateEventService {

    @ConfigProperty(name = "jeon.domain")
    String domain;

    @Inject
    ObjectMapper objectMapper;

    @Inject
    Logger logger;

    public PersistentDataUnit createPdu(String roomVersion, long creationTimestamp, String creator, String roomId, ZonedDateTime createdAt,
                                        String type, Object content, Long initialDepth) {
        PersistentDataUnit pdu = new PersistentDataUnit();
        pdu.setVersion(roomVersion);
        pdu.setOriginServerTs(creationTimestamp);
        pdu.setOrigin(domain);
        pdu.setDepth(initialDepth == null ? 0L : initialDepth + 1);
        pdu.setCreatedAt(createdAt);
        pdu.setType(type);
        pdu.setSender(creator);
        pdu.setRoomId(roomId);
        if (content != null) {
            try {
                pdu.setContent(objectMapper.writeValueAsString(content));
            } catch (JsonProcessingException e) {
                logger.error("Unable to serialize room creation content", e);
                throw new RuntimeException(e);
            }
        }
        return pdu;
    }
}
