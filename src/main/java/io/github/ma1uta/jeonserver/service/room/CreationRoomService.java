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

package io.github.ma1uta.jeonserver.service.room;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.ma1uta.jeonserver.client.model.room.RoomIdWrapper;
import io.github.ma1uta.jeonserver.configuration.JeonConfig;
import io.github.ma1uta.jeonserver.event.CreateRoomEvent;
import io.github.ma1uta.jeonserver.persistence.entity.Membership;
import io.github.ma1uta.jeonserver.persistence.entity.MembershipId;
import io.github.ma1uta.jeonserver.persistence.entity.PersistentDataUnit;
import io.github.ma1uta.jeonserver.persistence.entity.Room;
import io.github.ma1uta.jeonserver.persistence.repository.MembershipRepository;
import io.github.ma1uta.jeonserver.persistence.repository.PersistentDataUnitRepository;
import io.github.ma1uta.jeonserver.persistence.repository.RoomRepository;
import io.github.ma1uta.jeonserver.service.AbstractService;
import io.github.ma1uta.jeonserver.service.EventIdCreator;
import io.github.ma1uta.jeonserver.service.NewRoomVersion;
import io.github.ma1uta.jeonserver.service.RoomIdCreator;
import io.github.ma1uta.matrix.Id;
import io.github.ma1uta.matrix.client.model.room.CreateRoomRequest;
import io.github.ma1uta.matrix.client.model.room.RoomId;
import io.github.ma1uta.matrix.event.RoomAliases;
import io.github.ma1uta.matrix.event.RoomCreate;
import io.github.ma1uta.matrix.event.RoomHistoryVisibility;
import io.github.ma1uta.matrix.event.RoomMember;
import io.github.ma1uta.matrix.event.RoomName;
import io.github.ma1uta.matrix.event.RoomPowerLevels;
import io.github.ma1uta.matrix.event.RoomTopic;
import io.github.ma1uta.matrix.event.content.RoomAliasesContent;
import io.github.ma1uta.matrix.event.content.RoomCreateContent;
import io.github.ma1uta.matrix.event.content.RoomHistoryVisibilityContent;
import io.github.ma1uta.matrix.event.content.RoomMemberContent;
import io.github.ma1uta.matrix.event.content.RoomNameContent;
import io.github.ma1uta.matrix.event.content.RoomPowerLevelsContent;
import io.github.ma1uta.matrix.event.content.RoomTopicContent;
import io.quarkus.vertx.ConsumeEvent;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.microprofile.context.ManagedExecutor;
import org.slf4j.Logger;

import java.time.Clock;
import java.time.Instant;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.InternalServerErrorException;

@ApplicationScoped
public class CreationRoomService implements AbstractService<CreateRoomEvent, RoomId> {

    private final PersistentDataUnitRepository pduRepository;

    private final MembershipRepository membershipRepository;

    private final RoomRepository roomRepository;

    private final Clock clock;

    private final RoomIdCreator roomIdCreator;

    private final EventIdCreator eventIdCreator;

    private final NewRoomVersion newRoomVersion;

    private final Logger logger;

    private final ManagedExecutor managedExecutor;

    private final ObjectMapper mapper;

    private final JeonConfig config;

    public CreationRoomService(
        MembershipRepository membershipRepository,
        RoomRepository roomRepository,
        Logger logger,
        ManagedExecutor managedExecutor,
        PersistentDataUnitRepository pduRepository,
        Clock clock,
        RoomIdCreator roomIdCreator,
        EventIdCreator eventIdCreator,
        NewRoomVersion newRoomVersion,
        ObjectMapper mapper,
        JeonConfig config
    ) {
        this.membershipRepository = membershipRepository;
        this.roomRepository = roomRepository;
        this.pduRepository = pduRepository;
        this.clock = clock;
        this.roomIdCreator = roomIdCreator;
        this.eventIdCreator = eventIdCreator;
        this.logger = logger;
        this.managedExecutor = managedExecutor;
        this.newRoomVersion = newRoomVersion;
        this.mapper = mapper;
        this.config = config;
    }

    @Override
    public ManagedExecutor getManagedExecutor() {
        return managedExecutor;
    }

    @ConsumeEvent("createRoom")
    public void createRoom(CreateRoomEvent event) {
        consumeEvent(event);
    }

    @Override
    @Transactional
    public RoomId action(CreateRoomEvent createRoomEvent) {
        try {
            var roomId = roomIdCreator.createId();
            var request = createRoomEvent.getRequest();
            var content = request.getCreationContent();
            var roomVersion = request.getRoomVersion() != null ? request.getRoomVersion() : newRoomVersion.version();

            Room room = room(roomId, request, content, roomVersion);

            var sender = String.format("%s%s:%s", Id.Sigil.USER, createRoomEvent.getSender(), config.getDomain());
            var depth = 0L;
            var roomCreate = roomCreate(content, room, sender, depth++);
            var powerLevel = roomPowerLevel(room, request, sender, depth++, roomCreate);
            var ownerMember = roomMember(room, sender, depth++, roomCreate, powerLevel);
            roomName(room, request, sender, depth++, roomCreate, List.of(roomCreate, powerLevel, ownerMember));
            roomTopic(room, request, sender, depth++, roomCreate, List.of(roomCreate, powerLevel, ownerMember));
            roomAliases(room, request, sender, depth++, roomCreate, List.of(roomCreate, powerLevel, ownerMember));
            roomVisibility(room, request, sender, depth++, roomCreate, List.of(roomCreate, powerLevel, ownerMember));
            roomInvites(room, request, sender, depth, roomCreate, List.of(roomCreate, powerLevel, ownerMember));

            return new RoomIdWrapper(roomId);
        } catch (Exception e) {
            logger.error("Unable to create room", e);
            throw new InternalServerErrorException(e);
        }
    }

    private Room room(String roomId, CreateRoomRequest request, RoomCreateContent content, String roomVersion) {
        var ts = clock.millis();
        var createdAt = Instant.ofEpochMilli(ts).atZone(clock.getZone());

        var room = new Room();
        room.setTs(ts);
        room.setCreatedAt(createdAt);
        room.setRoomId(roomId);
        room.setVersion(roomVersion);
        room.setDirect(request.getDirect());
        room.setName(request.getName());
        room.setFederated(content != null ? content.getFederate() : false);
        room.setVisibility(StringUtils.isNotBlank(request.getVisibility()) ? request.getVisibility() : "private");
        if (request.getCreationContent() != null) {
            var predecessor = request.getCreationContent().getPredecessor();
            if (predecessor != null) {
                room.setPrevRoomId(predecessor.getRoomId());
                room.setPrevEventId(predecessor.getEventId());
            }
        }
        roomRepository.persist(room);
        return room;
    }

    private PersistentDataUnit roomPowerLevel(
        Room room,
        CreateRoomRequest request,
        String sender,
        long depth,
        PersistentDataUnit roomCreate
    ) throws JsonProcessingException {
        var roomPowerLevelsContent = new RoomPowerLevelsContent();
        var powerLevelOverride = request.getPowerLevelContentOverride();
        if (powerLevelOverride != null) {
            roomPowerLevelsContent.setEvents(powerLevelOverride.getEvents());
            roomPowerLevelsContent.setUsers(powerLevelOverride.getUsers());
            roomPowerLevelsContent.setNotifications(powerLevelOverride.getNotifications());
            if (powerLevelOverride.getBan() != null) {
                roomPowerLevelsContent.setBan(powerLevelOverride.getBan());
            }
            if (powerLevelOverride.getEventsDefault() != null) {
                roomPowerLevelsContent.setEventsDefault(powerLevelOverride.getEventsDefault());
            }
            if (powerLevelOverride.getInvite() != null) {
                roomPowerLevelsContent.setInvite(powerLevelOverride.getInvite());
            }
            if (powerLevelOverride.getKick() != null) {
                roomPowerLevelsContent.setKick(powerLevelOverride.getKick());
            }
            if (powerLevelOverride.getRedact() != null) {
                roomPowerLevelsContent.setRedact(powerLevelOverride.getRedact());
            }
            if (powerLevelOverride.getUsersDefault() != null) {
                roomPowerLevelsContent.setUsersDefault(powerLevelOverride.getUsersDefault());
            }
        }
        PersistentDataUnit event = createEvent(
            room,
            sender,
            RoomPowerLevels.TYPE,
            roomPowerLevelsContent,
            depth,
            roomCreate,
            List.of(roomCreate)
        );
        pduRepository.persist(event);
        return event;
    }

    private PersistentDataUnit roomMember(
        Room room,
        String sender,
        long depth,
        PersistentDataUnit roomCreate,
        PersistentDataUnit powerLevel
    ) throws JsonProcessingException {
        var roomMemberContent = new RoomMemberContent();
        roomMemberContent.setMembership(RoomMemberContent.INVITE);
        roomMemberContent.setDirect(room.getDirect());
        PersistentDataUnit event = createEvent(
            room,
            sender,
            RoomMember.TYPE,
            roomMemberContent,
            sender,
            depth,
            roomCreate,
            List.of(roomCreate, powerLevel)
        );
        pduRepository.persist(event);

        var membership = new Membership();
        var membershipId = new MembershipId();
        membershipId.setRoom(room);
        membershipId.setMxid(sender);
        membership.setId(membershipId);
        membership.setCreatedAt(room.getCreatedAt());
        membership.setEventId(event.getEventId());
        membership.setMembership(RoomMemberContent.INVITE);
        membershipRepository.persist(membership);

        return event;
    }

    private void roomTopic(
        Room room,
        CreateRoomRequest request,
        String sender,
        long depth,
        PersistentDataUnit prevEvents,
        List<PersistentDataUnit> authEvents
    ) throws JsonProcessingException {
        if (StringUtils.isNotBlank(request.getTopic())) {
            var roomTopicContent = new RoomTopicContent();
            roomTopicContent.setTopic(request.getTopic());
            pduRepository.persist(createEvent(room, sender, RoomTopic.TYPE, roomTopicContent, depth, prevEvents, authEvents));
        }
    }

    private void roomName(
        Room room,
        CreateRoomRequest request,
        String sender,
        long depth,
        PersistentDataUnit prevEvent,
        List<PersistentDataUnit> authEvents
    ) throws JsonProcessingException {
        if (StringUtils.isNotBlank(request.getName())) {
            var roomNameContent = new RoomNameContent();
            roomNameContent.setName(request.getName());
            pduRepository.persist(createEvent(room, sender, RoomName.TYPE, roomNameContent, depth, prevEvent, authEvents));
        }
    }

    private PersistentDataUnit roomCreate(RoomCreateContent content, Room room, String sender, long depth) throws JsonProcessingException {
        var roomCreateContent = new RoomCreateContent();
        roomCreateContent.setCreator(sender);
        roomCreateContent.setFederate(room.getFederated());
        roomCreateContent.setRoomVersion(room.getVersion());
        if (content != null && content.getPredecessor() != null) {
            roomCreateContent.setPredecessor(content.getPredecessor());
        }
        PersistentDataUnit event = createEvent(room, sender, RoomCreate.TYPE, roomCreateContent, depth, null, null);
        pduRepository.persist(event);
        return event;
    }

    private void roomAliases(
        Room room,
        CreateRoomRequest request,
        String sender,
        long depth,
        PersistentDataUnit prevEvent,
        List<PersistentDataUnit> authEvents
    ) throws JsonProcessingException {
        if (StringUtils.isNotBlank(request.getRoomAliasName())) {
            var roomAliasesContent = new RoomAliasesContent();
            roomAliasesContent.setAliases(List.of(request.getRoomAliasName()));
            pduRepository.persist(createEvent(room, sender, RoomAliases.TYPE, roomAliasesContent, depth, prevEvent, authEvents));
        }
    }

    private void roomVisibility(
        Room room,
        CreateRoomRequest request,
        String sender,
        long depth,
        PersistentDataUnit prevEvent,
        List<PersistentDataUnit> authEvents
    ) throws JsonProcessingException {
        if (StringUtils.isNotBlank(request.getVisibility())) {
            var visibilityContent = new RoomHistoryVisibilityContent();
            visibilityContent.setHistoryVisibility(RoomHistoryVisibilityContent.INVITED);
            pduRepository.persist(createEvent(room, sender, RoomHistoryVisibility.TYPE, visibilityContent, depth, prevEvent, authEvents));
        }
    }

    private void roomInvites(
        Room room,
        CreateRoomRequest request,
        String sender,
        long depth,
        PersistentDataUnit prevEvent,
        List<PersistentDataUnit> authEvents
    ) throws JsonProcessingException {
        if (request.getInvite() != null && !request.getInvite().isEmpty()) {
            for (String invitee : request.getInvite()) {
                var roomMemberContent = new RoomMemberContent();
                roomMemberContent.setMembership(RoomMemberContent.INVITE);
                pduRepository.persist(createEvent(room, sender, RoomMember.TYPE, roomMemberContent, invitee, depth, prevEvent, authEvents));
            }
        }
    }

    private PersistentDataUnit createEvent(
        Room room,
        String sender,
        String type,
        Object content,
        long depth,
        PersistentDataUnit prevEvent,
        List<PersistentDataUnit> authEvent
    ) throws JsonProcessingException {
        return createEvent(room, sender, type, content, null, depth, prevEvent, authEvent);
    }

    private PersistentDataUnit createEvent(
        Room room,
        String sender,
        String type,
        Object content,
        String stateKey,
        long depth,
        PersistentDataUnit prevEvent,
        List<PersistentDataUnit> authEvent
    ) throws JsonProcessingException {
        var pdu = new PersistentDataUnit();
        pdu.setEventId(eventIdCreator.createId());
        pdu.setRoomId(room.getRoomId());
        pdu.setType(type);
        pdu.setCreatedAt(room.getCreatedAt());
        pdu.setDepth(depth);
        pdu.setVersion(room.getVersion());
        pdu.setSender(sender);
        pdu.setOrigin(config.getDomain());
        pdu.setOriginServerTs(room.getTs());
        pdu.setLocalTs(room.getTs());
        pdu.setSignatures("");
        if (content != null) {
            pdu.setContent(mapper.writeValueAsString(content));
        }
        if (stateKey != null) {
            pdu.setStateKey(stateKey);
        }
        if (prevEvent != null) {
            pdu.setPrevEvents(List.of(prevEvent));
        }
        if (authEvent != null) {
            pdu.setAuthEvents(authEvent);
        }
        return pdu;
    }
}
