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
import io.github.ma1uta.jeonserver.config.IdGenerator;
import io.github.ma1uta.jeonserver.entity.core.Event;
import io.github.ma1uta.jeonserver.entity.room.Room;
import io.github.ma1uta.jeonserver.entity.room.RoomState;
import io.github.ma1uta.jeonserver.entity.room.RoomStateSnapshot;
import io.github.ma1uta.matrix.client.model.room.CreateRoomRequest;
import io.github.ma1uta.matrix.client.model.room.RoomId;
import io.github.ma1uta.matrix.event.content.RoomCreateContent;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.microprofile.config.Config;

import java.time.Clock;
import java.time.LocalDateTime;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

/**
 * Room service.
 */
@Transactional
@ApplicationScoped
public class RoomService {

    @Inject
    private EntityManager em;

    @Inject
    private Config config;

    @Inject
    private Clock clock;

    @Inject
    private IdGenerator idGenerator;

    /**
     * Create a new room.
     *
     * @param roomParams new room parameters.
     * @return room id.
     */
    public RoomId createRoom(CreateRoomRequest roomParams) {
        Room room = new Room();
        room.setRoomId(idGenerator.roomId());
        room.setCreated(LocalDateTime.now(clock));

        RoomCreateContent roomContent = roomParams.getCreationContent();

        room.setVersion(roomParams.getRoomVersion() != null
            ? roomParams.getRoomVersion()
            : config.getValue("server.room.version.default", String.class));

        RoomStateSnapshot snapshot = new RoomStateSnapshot();

        if (StringUtils.isNoneBlank(roomParams.getTopic())) {
            Event event = new Event();
            event.setRoom(room);

            RoomState topicState = new RoomState();
            topicState.setRoomState(snapshot);
            topicState.setEvent(event);

            em.persist(event);
            em.persist(topicState);
        }


        em.persist(snapshot);
        em.persist(room);

        return new RoomIdWrapper(room.getRoomId());
    }
}

