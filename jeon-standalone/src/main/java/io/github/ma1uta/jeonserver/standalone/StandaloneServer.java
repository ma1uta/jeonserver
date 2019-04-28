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

package io.github.ma1uta.jeonserver.standalone;

import com.google.inject.Inject;
import com.google.inject.persist.PersistService;
import io.github.ma1uta.jeonserver.Server;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 * Standalone server.
 */
public class StandaloneServer implements Server {

    @Inject
    private EntityManager em;

    @Inject
    private PersistService persistService;

    @Override
    public void run() {
        persistService.start();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.createQuery("select e from Event e");
        tx.rollback();
        persistService.stop();
    }
}
