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

package io.github.ma1uta.jeonserver.test.event;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.github.ma1uta.jeonserver.service.EventTypeMap;
import io.github.ma1uta.matrix.event.CallAnswer;
import io.github.ma1uta.matrix.event.CallCandidates;
import io.github.ma1uta.matrix.event.CallHangup;
import io.github.ma1uta.matrix.event.CallInvite;
import io.github.ma1uta.matrix.event.Direct;
import io.github.ma1uta.matrix.event.Dummy;
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
import io.github.ma1uta.matrix.event.content.CallAnswerContent;
import io.github.ma1uta.matrix.event.content.CallCandidatesContent;
import io.github.ma1uta.matrix.event.content.CallHangupContent;
import io.github.ma1uta.matrix.event.content.CallInviteContent;
import io.github.ma1uta.matrix.event.content.DirectContent;
import io.github.ma1uta.matrix.event.content.DummyContent;
import io.github.ma1uta.matrix.event.content.ForwardedRoomKeyContent;
import io.github.ma1uta.matrix.event.content.FullyReadContent;
import io.github.ma1uta.matrix.event.content.IgnoredUserListContent;
import io.github.ma1uta.matrix.event.content.KeyVerificationAcceptContent;
import io.github.ma1uta.matrix.event.content.KeyVerificationCancelContent;
import io.github.ma1uta.matrix.event.content.KeyVerificationKeyContent;
import io.github.ma1uta.matrix.event.content.KeyVerificationMacContent;
import io.github.ma1uta.matrix.event.content.KeyVerificationRequestContent;
import io.github.ma1uta.matrix.event.content.KeyVerificationStartContent;
import io.github.ma1uta.matrix.event.content.PolicyRuleRoomContent;
import io.github.ma1uta.matrix.event.content.PolicyRuleServerContent;
import io.github.ma1uta.matrix.event.content.PolicyRuleUserContent;
import io.github.ma1uta.matrix.event.content.PresenceContent;
import io.github.ma1uta.matrix.event.content.PushRulesContent;
import io.github.ma1uta.matrix.event.content.ReceiptContent;
import io.github.ma1uta.matrix.event.content.RoomAliasesContent;
import io.github.ma1uta.matrix.event.content.RoomAvatarContent;
import io.github.ma1uta.matrix.event.content.RoomCanonicalAliasContent;
import io.github.ma1uta.matrix.event.content.RoomCreateContent;
import io.github.ma1uta.matrix.event.content.RoomEncryptedContent;
import io.github.ma1uta.matrix.event.content.RoomEncryptionContent;
import io.github.ma1uta.matrix.event.content.RoomGuestAccessContent;
import io.github.ma1uta.matrix.event.content.RoomHistoryVisibilityContent;
import io.github.ma1uta.matrix.event.content.RoomJoinRulesContent;
import io.github.ma1uta.matrix.event.content.RoomKeyContent;
import io.github.ma1uta.matrix.event.content.RoomKeyRequestContent;
import io.github.ma1uta.matrix.event.content.RoomMemberContent;
import io.github.ma1uta.matrix.event.content.RoomMessageContent;
import io.github.ma1uta.matrix.event.content.RoomMessageFeedbackContent;
import io.github.ma1uta.matrix.event.content.RoomNameContent;
import io.github.ma1uta.matrix.event.content.RoomPinnedContent;
import io.github.ma1uta.matrix.event.content.RoomPowerLevelsContent;
import io.github.ma1uta.matrix.event.content.RoomRedactionContent;
import io.github.ma1uta.matrix.event.content.RoomServerAclContent;
import io.github.ma1uta.matrix.event.content.RoomThirdPartyInviteContent;
import io.github.ma1uta.matrix.event.content.RoomTopicContent;
import io.github.ma1uta.matrix.event.content.StickerContent;
import io.github.ma1uta.matrix.event.content.TagContent;
import io.github.ma1uta.matrix.event.content.TombstoneContent;
import io.github.ma1uta.matrix.event.content.TypingContent;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class EventTypeMapTest {

    public static Stream<Arguments> eventTypes() {
        return Stream.of(
            Arguments.of(CallAnswer.TYPE, CallAnswer.class, CallAnswerContent.class),
            Arguments.of(CallCandidates.TYPE, CallCandidates.class, CallCandidatesContent.class),
            Arguments.of(CallHangup.TYPE, CallHangup.class, CallHangupContent.class),
            Arguments.of(CallInvite.TYPE, CallInvite.class, CallInviteContent.class),
            Arguments.of(Direct.TYPE, Direct.class, DirectContent.class),
            Arguments.of(ForwardedRoomKey.TYPE, ForwardedRoomKey.class, ForwardedRoomKeyContent.class),
            Arguments.of(FullyRead.TYPE, FullyRead.class, FullyReadContent.class),
            Arguments.of(IgnoredUserList.TYPE, IgnoredUserList.class, IgnoredUserListContent.class),
            Arguments.of(Presence.TYPE, Presence.class, PresenceContent.class),
            Arguments.of(Receipt.TYPE, Receipt.class, ReceiptContent.class),
            Arguments.of(RoomAliases.TYPE, RoomAliases.class, RoomAliasesContent.class),
            Arguments.of(RoomAvatar.TYPE, RoomAvatar.class, RoomAvatarContent.class),
            Arguments.of(RoomCanonicalAlias.TYPE, RoomCanonicalAlias.class, RoomCanonicalAliasContent.class),
            Arguments.of(RoomCreate.TYPE, RoomCreate.class, RoomCreateContent.class),
            Arguments.of(RoomGuestAccess.TYPE, RoomGuestAccess.class, RoomGuestAccessContent.class),
            Arguments.of(RoomEncryption.TYPE, RoomEncryption.class, RoomEncryptionContent.class),
            Arguments.of(RoomEncrypted.TYPE, RoomEncrypted.class, RoomEncryptedContent.class),
            Arguments.of(RoomHistoryVisibility.TYPE, RoomHistoryVisibility.class, RoomHistoryVisibilityContent.class),
            Arguments.of(RoomJoinRules.TYPE, RoomJoinRules.class, RoomJoinRulesContent.class),
            Arguments.of(RoomKey.TYPE, RoomKey.class, RoomKeyContent.class),
            Arguments.of(RoomKeyRequest.TYPE, RoomKeyRequest.class, RoomKeyRequestContent.class),
            Arguments.of(RoomMember.TYPE, RoomMember.class, RoomMemberContent.class),
            Arguments.of(RoomMessage.TYPE, RoomMessage.class, RoomMessageContent.class),
            Arguments.of(RoomMessageFeedback.TYPE, RoomMessageFeedback.class, RoomMessageFeedbackContent.class),
            Arguments.of(RoomName.TYPE, RoomName.class, RoomNameContent.class),
            Arguments.of(RoomPinned.TYPE, RoomPinned.class, RoomPinnedContent.class),
            Arguments.of(RoomPowerLevels.TYPE, RoomPowerLevels.class, RoomPowerLevelsContent.class),
            Arguments.of(RoomRedaction.TYPE, RoomRedaction.class, RoomRedactionContent.class),
            Arguments.of(RoomThirdPartyInvite.TYPE, RoomThirdPartyInvite.class, RoomThirdPartyInviteContent.class),
            Arguments.of(RoomTopic.TYPE, RoomTopic.class, RoomTopicContent.class),
            Arguments.of(Sticker.TYPE, Sticker.class, StickerContent.class),
            Arguments.of(Tag.TYPE, Tag.class, TagContent.class),
            Arguments.of(Typing.TYPE, Typing.class, TypingContent.class),
            Arguments.of(RoomServerAcl.TYPE, RoomServerAcl.class, RoomServerAclContent.class),
            Arguments.of(PushRules.TYPE, PushRules.class, PushRulesContent.class),
            Arguments.of(RoomTombstone.TYPE, RoomTombstone.class, TombstoneContent.class),
            Arguments.of(Dummy.TYPE, Dummy.class, DummyContent.class),
            Arguments.of(KeyVerificationAccept.TYPE, KeyVerificationAccept.class, KeyVerificationAcceptContent.class),
            Arguments.of(KeyVerificationCancel.TYPE, KeyVerificationCancel.class, KeyVerificationCancelContent.class),
            Arguments.of(KeyVerificationKey.TYPE, KeyVerificationKey.class, KeyVerificationKeyContent.class),
            Arguments.of(KeyVerificationMac.TYPE, KeyVerificationMac.class, KeyVerificationMacContent.class),
            Arguments.of(KeyVerificationRequest.TYPE, KeyVerificationRequest.class, KeyVerificationRequestContent.class),
            Arguments.of(KeyVerificationStart.TYPE, KeyVerificationStart.class, KeyVerificationStartContent.class),
            Arguments.of(PolicyRuleRoom.TYPE, PolicyRuleRoom.class, PolicyRuleRoomContent.class),
            Arguments.of(PolicyRuleUser.TYPE, PolicyRuleUser.class, PolicyRuleUserContent.class),
            Arguments.of(PolicyRuleServer.TYPE, PolicyRuleServer.class, PolicyRuleServerContent.class)
        );
    }

    @ParameterizedTest
    @MethodSource("eventTypes")
    public void getEventTypeMap(String type, Class<?> eventClass, Class<?> contentClass) {
        var eventPair = EventTypeMap.getInstance().getEvent(type);
        assertEquals(eventPair.getEventType(), eventClass);
        assertEquals(eventPair.getContentType(), contentClass);
    }
}
