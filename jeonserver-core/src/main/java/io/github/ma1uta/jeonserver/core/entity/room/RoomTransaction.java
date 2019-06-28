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

package io.github.ma1uta.jeonserver.core.entity.room;

import io.github.ma1uta.jeonserver.core.entity.EventContent;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Room transaction.
 */
@Entity
@Table(
    name = "room_transaction",
    uniqueConstraints = {
        @UniqueConstraint(name = "room_trans_constr_txn_sender_room", columnNames = {"txn_id", "sender", "room_id"})
    }
)
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class RoomTransaction implements Serializable {

    @Id
    @SequenceGenerator(name = "pk_sequence_room_transaction", sequenceName = "room_transaction_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence_room_transaction")
    private Long id;

    @Column(name = "txn_id")
    private String txnId;

    private String sender;

    @ManyToOne
    @JoinColumn(name = "room_id", foreignKey = @ForeignKey(name = "room_trans_fk_room"))
    private Room room;

    @OneToOne
    @JoinColumn(name = "content_id", foreignKey = @ForeignKey(name = "room_trans_fk_content"))
    private EventContent content;

    private String type;

    private LocalDateTime created;

    @PrePersist
    private void create() {
        created = LocalDateTime.now();
    }
}
