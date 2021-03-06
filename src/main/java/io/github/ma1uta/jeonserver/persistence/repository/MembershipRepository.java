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

import io.github.ma1uta.jeonserver.persistence.entity.Membership;
import io.github.ma1uta.jeonserver.persistence.entity.MembershipId;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import java.util.List;
import java.util.Map;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MembershipRepository implements PanacheRepositoryBase<Membership, MembershipId> {

    public List<Membership> findRooms(String mxid) {
        return list("#Membership.findRooms", Map.of("mxid", mxid));
    }
}
