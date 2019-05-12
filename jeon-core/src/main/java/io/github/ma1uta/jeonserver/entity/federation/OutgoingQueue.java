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

package io.github.ma1uta.jeonserver.entity.federation;

import io.github.ma1uta.jeonserver.entity.core.Event;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

/**
 * Outgoing queue.
 */
@Entity
@Table(name = "outgoing_queue")
@Getter
@Setter
@EqualsAndHashCode(of = {"event", "target"})
@IdClass(OutgoingQueueId.class)
public class OutgoingQueue implements Serializable {

    @Id
    @OneToOne
    private Event event;

    @Id
    private String target;

    private LocalDateTime created;

    @Column(name = "sent_to_transaction")
    private LocalDateTime sentToTransaction;

    @PrePersist
    private void create() {
        created = LocalDateTime.now();
    }
}
