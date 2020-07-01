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

package io.github.ma1uta.jeonserver.test.client.resource;

import static io.restassured.RestAssured.given;
import static java.net.HttpURLConnection.HTTP_OK;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.ma1uta.jeonserver.persistence.entity.PersistentDataUnit;
import io.github.ma1uta.jeonserver.persistence.repository.PersistentDataUnitRepository;
import io.github.ma1uta.jeonserver.persistence.repository.RoomRepository;
import io.github.ma1uta.jeonserver.service.NewRoomVersion;
import io.github.ma1uta.jeonserver.test.PostgreSQLTestResource;
import io.github.ma1uta.matrix.client.model.room.CreateRoomRequest;
import io.github.ma1uta.matrix.event.RoomCreate;
import io.github.ma1uta.matrix.event.RoomMember;
import io.github.ma1uta.matrix.event.RoomPowerLevels;
import io.github.ma1uta.matrix.event.content.RoomCreateContent;
import io.github.ma1uta.matrix.event.content.RoomMemberContent;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

@QuarkusTest
@QuarkusTestResource(PostgreSQLTestResource.class)
public class CreateRoomTest {

    @Inject
    RoomRepository roomRepository;

    @Inject
    PersistentDataUnitRepository pduRepository;

    @Inject
    ObjectMapper mapper;

    @Inject
    NewRoomVersion newRoomVersion;

    @ConfigProperty(name = "jeon.domain")
    String domain;

    @Test
    public void createRoom() throws Exception {
        var request = new CreateRoomRequest();
        var fullMXID = "@user:jeonserver.org";

        given()
            .when()
            .contentType(ContentType.JSON)
            .auth()
            .basic("user", "user")
            .body(request)
            .post("/_matrix/client/r0/createRoom")
            .then()
            .statusCode(HTTP_OK)
            .defaultParser(Parser.JSON)
            .body("room_id", is(roomRepository.listAll().get(0).getRoomId()));

        var rooms = roomRepository.listAll();
        assertEquals(1, rooms.size());
        var room = rooms.get(0);
        var roomId = room.getRoomId();
        assertEquals(newRoomVersion.version(), room.getVersion());
        assertTrue(room.getFederated() == null || !room.getFederated());
        assertTrue(room.getDirect() == null || !room.getDirect());

        var pdus = pduRepository.listAll();
        assertEquals(3, pdus.size());

        boolean hasCreateEvent = false;
        boolean hasOwnerEvent = false;
        boolean hasPowerLevelEvent = false;
        long roomCreateTs = -1L;
        long powerLevelTs = -1L;

        for (PersistentDataUnit pdu : pdus) {
            assertEquals(domain, pdu.getOrigin());
            assertEquals(roomId, pdu.getRoomId());
            assertEquals(fullMXID, pdu.getSender());

            switch (pdu.getType()) {
                case RoomCreate.TYPE:
                    var roomCreateContent = mapper.readValue(pdu.getContent(), RoomCreateContent.class);
                    assertEquals(fullMXID, roomCreateContent.getCreator());
                    assertEquals(newRoomVersion.version(), roomCreateContent.getRoomVersion());
                    assertTrue(roomCreateContent.getFederate() == null || !roomCreateContent.getFederate());
                    roomCreateTs = pdu.getOriginServerTs();
                    if (hasCreateEvent) {
                        fail("Duplicate event: " + RoomCreate.TYPE);
                    }
                    hasCreateEvent = true;
                    break;

                case RoomMember.TYPE:
                    assertEquals(fullMXID, pdu.getStateKey());
                    var roomMemberContent = mapper.readValue(pdu.getContent(), RoomMemberContent.class);
                    assertEquals(RoomMemberContent.INVITE, roomMemberContent.getMembership());
                    if (hasOwnerEvent) {
                        fail("Duplicate event: " + RoomMember.TYPE);
                    }
                    hasOwnerEvent = true;
                    break;

                case RoomPowerLevels.TYPE:
                    powerLevelTs = pdu.getOriginServerTs();
                    if (hasPowerLevelEvent) {
                        fail("Duplicate event: " + RoomPowerLevels.TYPE);
                    }
                    hasPowerLevelEvent = true;
                    break;
                default:
                    fail("Unexpected event" + pdu.getType());
            }
        }

        if (!hasCreateEvent) {
            fail("Missing event: " + RoomCreate.TYPE);
        }
        if (!hasOwnerEvent) {
            fail("Missing event: " + RoomMember.TYPE);
        }
        if (!hasPowerLevelEvent) {
            fail("Missing event: " + RoomPowerLevels.TYPE);
        }
        assertEquals(roomCreateTs, powerLevelTs);
    }
}
