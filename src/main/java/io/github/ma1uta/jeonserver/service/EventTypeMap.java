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

import io.github.ma1uta.matrix.event.CallAnswer;
import io.github.ma1uta.matrix.event.CallCandidates;
import io.github.ma1uta.matrix.event.CallHangup;
import io.github.ma1uta.matrix.event.CallInvite;
import io.github.ma1uta.matrix.event.Direct;
import io.github.ma1uta.matrix.event.Dummy;
import io.github.ma1uta.matrix.event.Event;
import io.github.ma1uta.matrix.event.ForwardedRoomKey;
import io.github.ma1uta.matrix.event.FullyRead;
import io.github.ma1uta.matrix.event.IgnoredUserList;
import io.github.ma1uta.matrix.event.KeyVerificationAccept;
import io.github.ma1uta.matrix.event.KeyVerificationCancel;
import io.github.ma1uta.matrix.event.KeyVerificationKey;
import io.github.ma1uta.matrix.event.KeyVerificationMac;
import io.github.ma1uta.matrix.event.KeyVerificationRequest;
import io.github.ma1uta.matrix.event.KeyVerificationStart;
import io.github.ma1uta.matrix.event.PolicyRuleRoom;
import io.github.ma1uta.matrix.event.PolicyRuleServer;
import io.github.ma1uta.matrix.event.PolicyRuleUser;
import io.github.ma1uta.matrix.event.Presence;
import io.github.ma1uta.matrix.event.PushRules;
import io.github.ma1uta.matrix.event.Receipt;
import io.github.ma1uta.matrix.event.RoomAliases;
import io.github.ma1uta.matrix.event.RoomAvatar;
import io.github.ma1uta.matrix.event.RoomCanonicalAlias;
import io.github.ma1uta.matrix.event.RoomCreate;
import io.github.ma1uta.matrix.event.RoomEncrypted;
import io.github.ma1uta.matrix.event.RoomEncryption;
import io.github.ma1uta.matrix.event.RoomGuestAccess;
import io.github.ma1uta.matrix.event.RoomHistoryVisibility;
import io.github.ma1uta.matrix.event.RoomJoinRules;
import io.github.ma1uta.matrix.event.RoomKey;
import io.github.ma1uta.matrix.event.RoomKeyRequest;
import io.github.ma1uta.matrix.event.RoomMember;
import io.github.ma1uta.matrix.event.RoomMessage;
import io.github.ma1uta.matrix.event.RoomMessageFeedback;
import io.github.ma1uta.matrix.event.RoomName;
import io.github.ma1uta.matrix.event.RoomPinned;
import io.github.ma1uta.matrix.event.RoomPowerLevels;
import io.github.ma1uta.matrix.event.RoomRedaction;
import io.github.ma1uta.matrix.event.RoomServerAcl;
import io.github.ma1uta.matrix.event.RoomThirdPartyInvite;
import io.github.ma1uta.matrix.event.RoomTombstone;
import io.github.ma1uta.matrix.event.RoomTopic;
import io.github.ma1uta.matrix.event.Sticker;
import io.github.ma1uta.matrix.event.Tag;
import io.github.ma1uta.matrix.event.Typing;
import io.github.ma1uta.matrix.event.content.EventContent;
import io.github.ma1uta.matrix.event.content.RoomMessageContent;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventTypeMap {

    private final Map<String, EventTypePair<?, ?>> map = new HashMap<>();

    private static final Collection<Class<? extends Event>> EVENT_CLASSES = List.of(
        CallAnswer.class,
        CallCandidates.class,
        CallHangup.class,
        CallInvite.class,
        Direct.class,
        Dummy.class,
        ForwardedRoomKey.class,
        FullyRead.class,
        IgnoredUserList.class,
        KeyVerificationAccept.class,
        KeyVerificationCancel.class,
        KeyVerificationKey.class,
        KeyVerificationMac.class,
        KeyVerificationRequest.class,
        KeyVerificationStart.class,
        PolicyRuleRoom.class,
        PolicyRuleServer.class,
        PolicyRuleUser.class,
        Presence.class,
        PushRules.class,
        Receipt.class,
        RoomAliases.class,
        RoomAvatar.class,
        RoomCanonicalAlias.class,
        RoomCreate.class,
        RoomEncrypted.class,
        RoomEncryption.class,
        RoomGuestAccess.class,
        RoomHistoryVisibility.class,
        RoomJoinRules.class,
        RoomKey.class,
        RoomKeyRequest.class,
        RoomMember.class,
        RoomMessageFeedback.class,
        RoomName.class,
        RoomPinned.class,
        RoomPowerLevels.class,
        RoomRedaction.class,
        RoomServerAcl.class,
        RoomThirdPartyInvite.class,
        RoomTombstone.class,
        RoomTopic.class,
        Sticker.class,
        Tag.class,
        Typing.class
    );
    private static final EventTypeMap INSTANCE = new EventTypeMap();

    public static EventTypeMap getInstance() {
        return INSTANCE;
    }

    @SuppressWarnings("unchecked")
    private EventTypeMap() {
        map.put(RoomMessage.TYPE, new EventTypePair<>(RoomMessage.class, RoomMessageContent.class));
        for (Class<? extends Event> eventClass : EVENT_CLASSES) {
            try {
                map.put(eventClass.getDeclaredConstructor().newInstance().getType(), new EventTypePair(eventClass));
            } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
                LoggerFactory.getLogger(EventTypeMap.class)
                    .error("Unable to create an instance of the class: " + eventClass.getCanonicalName(), e);
                throw new RuntimeException(e);
            }
        }
    }

    @SuppressWarnings("unchecked")
    public <C extends EventContent, E extends Event<C>> EventTypePair<C, E> getEvent(String type) {
        return (EventTypePair<C, E>) map.get(type);
    }
}
