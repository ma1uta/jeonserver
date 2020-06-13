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

package io.github.ma1uta.jeonserver.client.resource;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.ma1uta.jeonserver.persistence.entity.PersistentDataUnit;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@QuarkusTest
@QuarkusTestResource(PostgreSQLTestResource.class)
public class PduTest {

    @Inject
    EntityManager em;

    @Inject
    ObjectMapper objectMapper;

    @Test
    @Transactional
    public void versionTest() throws Exception {
        PersistentDataUnit pdu = new PersistentDataUnit();

        Map<String, Object> content = new HashMap<>();
        content.put("qwe", "asd");

        pdu.setEventId(UUID.randomUUID().toString());
        pdu.setRoomId(UUID.randomUUID().toString());
        pdu.setSender("@test:test.org");
        pdu.setType("x.test");
        pdu.setCreatedAt(ZonedDateTime.now());
        pdu.setDepth(1L);
        pdu.setOrigin("test.org");
        pdu.setLocalTs(1L);
        pdu.setOriginServerTs(System.currentTimeMillis());
        pdu.setVersion("1");
        pdu.setSignatures("");
        pdu.setContent(objectMapper.writeValueAsString(content));

        em.persist(pdu);


        List<PersistentDataUnit> pdus = em.createQuery("select pdu from PersistentDataUnit pdu", PersistentDataUnit.class)
            .getResultList();
        assertNotNull(pdus);
        PersistentDataUnit savedPdu = pdus.get(0);
        Map<String, Object> contentModel = objectMapper.readValue(savedPdu.getContent(), new TypeReference<>() {
        });
        pdus.forEach(p -> System.out.println(contentModel.get("qwe")));
    }
}
