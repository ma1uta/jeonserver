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

import io.github.ma1uta.jeonserver.persistence.entity.PersistentDataUnit;
import io.github.ma1uta.jeonserver.persistence.entity.room.PersistentDataUnitV4;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@QuarkusTest
public class PduTest {

    @Inject
    EntityManager em;

    @Test
    @Transactional
    public void versionTest() {
        PersistentDataUnit pdu = new PersistentDataUnitV4();

        Map<String, Object> content = new HashMap<>();
        content.put("qwe", "asd");

        pdu.setEventId(UUID.randomUUID().toString());
        pdu.setContent(content);

        em.persist(pdu);


        List<PersistentDataUnit> pdus = em.createQuery("select pdu from PersistentDataUnit pdu", PersistentDataUnit.class)
            .getResultList();
        assertNotNull(pdus);
        pdus.forEach(p -> System.out.println(p.getContent().get("qwe")));
    }
}
