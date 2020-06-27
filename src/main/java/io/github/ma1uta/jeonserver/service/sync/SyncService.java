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

package io.github.ma1uta.jeonserver.service.sync;

import static java.lang.Boolean.TRUE;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.ma1uta.jeonserver.event.SyncEvent;
import io.github.ma1uta.jeonserver.persistence.entity.Membership;
import io.github.ma1uta.jeonserver.persistence.entity.PersistentDataUnit;
import io.github.ma1uta.jeonserver.persistence.repository.MembershipRepository;
import io.github.ma1uta.jeonserver.persistence.repository.PersistentDataUnitRepository;
import io.github.ma1uta.jeonserver.service.AbstractService;
import io.github.ma1uta.jeonserver.service.EventTypeMap;
import io.github.ma1uta.matrix.client.model.sync.InviteState;
import io.github.ma1uta.matrix.client.model.sync.InvitedRoom;
import io.github.ma1uta.matrix.client.model.sync.JoinedRoom;
import io.github.ma1uta.matrix.client.model.sync.Rooms;
import io.github.ma1uta.matrix.client.model.sync.SyncResponse;
import io.github.ma1uta.matrix.client.model.sync.Timeline;
import io.github.ma1uta.matrix.event.Event;
import io.github.ma1uta.matrix.event.RoomEvent;
import io.github.ma1uta.matrix.event.RoomMember;
import io.github.ma1uta.matrix.event.StateEvent;
import io.github.ma1uta.matrix.event.content.EventContent;
import io.github.ma1uta.matrix.event.content.RoomMemberContent;
import io.quarkus.vertx.ConsumeEvent;
import org.eclipse.microprofile.context.ManagedExecutor;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SyncService implements AbstractService<SyncEvent, SyncResponse> {

    private final Logger logger;

    private final ManagedExecutor managedExecutor;

    private final PersistentDataUnitRepository pduRepository;

    private final MembershipRepository membershipRepository;

    private final ObjectMapper mapper;

    public SyncService(
        Logger logger,
        ManagedExecutor managedExecutor,
        PersistentDataUnitRepository pduRepository,
        MembershipRepository membershipRepository,
        ObjectMapper mapper
    ) {
        this.logger = logger;
        this.managedExecutor = managedExecutor;
        this.pduRepository = pduRepository;
        this.membershipRepository = membershipRepository;
        this.mapper = mapper;
    }

    @ConsumeEvent("sync")
    public void sync(SyncEvent syncEvent) {
        consumeEvent(syncEvent);
    }

    @Override
    public ManagedExecutor getManagedExecutor() {
        return managedExecutor;
    }

    @Override
    public SyncResponse action(SyncEvent event) {
        var sender = event.getSecurityContext().getUserPrincipal().getName();
        var response = new SyncResponse();

        var rooms = new Rooms();

        for (Membership senderRoom : membershipRepository.findRooms(sender)) {
            switch (senderRoom.getMembership()) {
                case RoomMemberContent.INVITE:
                    invited(rooms, senderRoom);
                    break;
                case RoomMemberContent.JOIN:
                    joined(rooms, senderRoom, TRUE.equals(event.getFullState()), event.getRequest());
                    break;
                case RoomMemberContent.BAN:
                case RoomMemberContent.KNOCK:
                default:
                    logger.warn("ignored membership: {}", senderRoom.getMembership());
            }
        }

        response.setRooms(rooms);

        return response;
    }

    private void invited(Rooms rooms, Membership membership) {
        var room = membership.getId().getRoom();
        var invites = rooms.getInvite();
        if (invites == null) {
            invites = new HashMap<>();
            rooms.setInvite(invites);
        }
        try {
            var invitePdu = pduRepository.findById(membership.getEventId());

            var inviteState = new InviteState();
            inviteState.setEvents(List.of(fillRoomEvent(new RoomMember(), invitePdu, RoomMemberContent.class)));

            var inviteRoom = new InvitedRoom();
            inviteRoom.setInviteState(inviteState);

            invites.put(room.getRoomId(), inviteRoom);
        } catch (Exception e) {
            logger.error("Unable to get invite event", e);
        }
    }

    private void joined(Rooms rooms, Membership membership, boolean fullState, String since) {
        var join = rooms.getJoin();
        if (join == null) {
            join = new HashMap<>();
            rooms.setJoin(join);
        }

        //if (fullState) {
        //}
        var timeline = new Timeline();
        timeline.setLimited(false);
        var pduTimeline = pduRepository.timeline(membership.getId().getRoom().getRoomId());
        var events = new ArrayList<Event>(pduTimeline.size());
        for (PersistentDataUnit timelineEvent : pduTimeline) {
            Event<?> event = toEvent(timelineEvent);
            if (event != null) {
                events.add(event);
            }
        }
        timeline.setEvents(events);

        var joinedRoom = new JoinedRoom();
        joinedRoom.setTimeline(timeline);

        var room = membership.getId().getRoom();
        join.put(room.getRoomId(), joinedRoom);
    }

    private Event<?> toEvent(PersistentDataUnit pdu) {
        try {
            var typeMap = EventTypeMap.getInstance();
            var eventPair = typeMap.getEvent(pdu.getType());
            return fillEvent(eventPair.getEventType().getDeclaredConstructor().newInstance(), pdu, eventPair.getContentType());
        } catch (Exception e) {
            logger.error("Unable to map pdu: " + pdu.getEventId(), e);
        }
        return null;
    }

    private Event<?> fillEvent(Event<?> event, PersistentDataUnit pdu, Class<? extends EventContent> contentClass) throws
        JsonProcessingException {
        if (event instanceof RoomEvent) {
            fillRoomEvent((RoomEvent) event, pdu, contentClass);
        }
        if (event instanceof StateEvent) {
            fillStateEvent((StateEvent) event, pdu, contentClass);
        }
        return event;
    }

    private <C extends EventContent, E extends RoomEvent<C>> Event<?> fillRoomEvent(
        E event,
        PersistentDataUnit pdu,
        Class<C> contentClass
    ) throws JsonProcessingException {
        event.setEventId(pdu.getEventId());
        event.setRoomId(pdu.getRoomId());
        event.setOriginServerTs(pdu.getOriginServerTs());
        event.setSender(pdu.getSender());
        event.setContent(mapper.readValue(pdu.getContent(), contentClass));
        return event;
    }

    private <C extends EventContent, E extends StateEvent<C>> Event<?> fillStateEvent(
        E event,
        PersistentDataUnit pdu,
        Class<C> contentClass
    ) {
        event.setStateKey(pdu.getStateKey());
        return event;
    }
}
