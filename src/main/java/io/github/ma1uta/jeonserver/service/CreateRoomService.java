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

import io.github.ma1uta.jeonserver.client.model.room.RoomIdWrapper;
import io.github.ma1uta.jeonserver.persistence.entity.PersistentDataUnit;
import io.github.ma1uta.jeonserver.persistence.repository.PersistentDataUnitRepository;
import io.github.ma1uta.jeonserver.service.message.CreateRoomServiceRequest;
import io.github.ma1uta.matrix.client.model.room.CreateRoomRequest;
import io.github.ma1uta.matrix.client.model.room.RoomId;
import io.github.ma1uta.matrix.event.RoomCreate;
import io.github.ma1uta.matrix.event.RoomPowerLevels;
import io.github.ma1uta.matrix.event.content.RoomCreateContent;
import io.quarkus.vertx.ConsumeEvent;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.time.ZonedDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@ApplicationScoped
public class CreateRoomService {

    @Inject
    PersistentDataUnitRepository persistentDataUnitRepository;

    @Inject
    CreateEventService createEventService;

    @Inject
    RoomIdCreator roomIdCreator;

    @ConfigProperty(name = "jeon.domain")
    String domain;

    @ConsumeEvent
    @Transactional
    public CompletionStage<RoomId> createRoom(CreateRoomServiceRequest createRoomServiceRequest) {
        CreateRoomRequest request = createRoomServiceRequest.getRequest();

        String roomVersion = request.getRoomVersion();
        long creationTimestamp = System.currentTimeMillis();
        RoomCreateContent creationContent = request.getCreationContent();
        String creator = creationContent.getCreator();
        String roomId = roomIdCreator.newId(domain);
        ZonedDateTime createdAt = ZonedDateTime.now();

        PersistentDataUnit createRoomPdu = createEventService.createPdu(
            roomVersion,
            creationTimestamp,
            creator,
            roomId,
            createdAt,
            RoomCreate.TYPE,
            creationContent,
            null
        );

        persistentDataUnitRepository.persist(createRoomPdu);

        PersistentDataUnit powerLevelPdu = createEventService
            .createPdu(
                roomVersion,
                creationTimestamp,
                creator,
                roomId,
                createdAt,
                RoomPowerLevels.TYPE,
                request.getPowerLevelContentOverride(),
                null
            );

        persistentDataUnitRepository.persist(powerLevelPdu);

        CompletableFuture<RoomId> result = new CompletableFuture<>();
        result.complete(new RoomIdWrapper(roomId));
        return result;
    }
}
