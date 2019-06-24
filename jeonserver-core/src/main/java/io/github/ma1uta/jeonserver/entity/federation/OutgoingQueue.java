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
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Outgoing queue.
 */
@Entity
@Table(
    name = "outgoing_queue",
    uniqueConstraints = {
        @UniqueConstraint(name = "outcome_queue_constr_event_target", columnNames = {"event_id", "target"})
    }
)
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class OutgoingQueue implements Serializable {

    @Id
    @SequenceGenerator(name = "pk_sequence_outgoing_queue", sequenceName = "outgoing_queue_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence_outgoing_queue")
    private Long id;

    @OneToOne
    @JoinColumn(name = "event_id", foreignKey = @ForeignKey(name = "outcome_queue_fk_event"))
    private Event event;

    private String target;

    private LocalDateTime created;

    @Column(name = "sent_to_transaction")
    private LocalDateTime sentToTransaction;

    @OneToOne
    @JoinColumn(name = "transaction_id", foreignKey = @ForeignKey(name = "outcome_queue_fk_trans"))
    private OutgoingTransaction transaction;

    @PrePersist
    private void create() {
        created = LocalDateTime.now();
    }
}