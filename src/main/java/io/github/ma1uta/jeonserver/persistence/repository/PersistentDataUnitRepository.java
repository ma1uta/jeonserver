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

package io.github.ma1uta.jeonserver.persistence.repository;

import io.github.ma1uta.jeonserver.persistence.entity.PersistentDataUnit;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.reactivex.annotations.NonNull;

import java.util.List;
import java.util.Map;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PersistentDataUnitRepository implements PanacheRepositoryBase<PersistentDataUnit, String> {

    private static final int DEFAULT_PAGE_SIZE = 20;

    public List<PersistentDataUnit> timeline(@NonNull String roomId) {
        return find("#pdu.timeline", Map.of("roomId", roomId)).range(0, DEFAULT_PAGE_SIZE).list();
    }
}
