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

package io.github.ma1uta.jeonserver.service.event;

import io.github.ma1uta.jeonserver.configuration.JeonConfig;
import io.github.ma1uta.jeonserver.event.MessageEvent;
import io.github.ma1uta.jeonserver.persistence.entity.MembershipId;
import io.github.ma1uta.jeonserver.persistence.repository.MembershipRepository;
import io.github.ma1uta.jeonserver.persistence.repository.PersistentDataUnitRepository;
import io.github.ma1uta.jeonserver.persistence.repository.RoomRepository;
import io.github.ma1uta.jeonserver.service.AbstractService;
import io.github.ma1uta.matrix.Id;
import io.github.ma1uta.matrix.client.model.event.SendEventResponse;
import org.eclipse.microprofile.context.ManagedExecutor;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SendMessageService implements AbstractService<MessageEvent, SendEventResponse> {

    private final ManagedExecutor managedExecutor;

    private final PersistentDataUnitRepository pduRepository;

    private final RoomRepository roomRepository;

    private final MembershipRepository membershipRepository;

    private final JeonConfig config;

    public SendMessageService(
        ManagedExecutor managedExecutor,
        PersistentDataUnitRepository pduRepository,
        RoomRepository roomRepository,
        MembershipRepository membershipRepository,
        JeonConfig config
    ) {
        this.managedExecutor = managedExecutor;
        this.pduRepository = pduRepository;
        this.roomRepository = roomRepository;
        this.membershipRepository = membershipRepository;
        this.config = config;
    }

    @Override
    public ManagedExecutor getManagedExecutor() {
        return managedExecutor;
    }

    @Override
    public SendEventResponse action(MessageEvent event) {
        var room = roomRepository.findById(event.getRoomId());
        var sender = String.format("%s%s:%s", Id.Sigil.USER, event.getSender(), config.getDomain());
        var membership = membershipRepository.findById(new MembershipId(room, sender));
        return null;
    }
}
