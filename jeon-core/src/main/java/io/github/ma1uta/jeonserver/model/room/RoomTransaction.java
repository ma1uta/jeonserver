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

package io.github.ma1uta.jeonserver.model.room;

import io.github.ma1uta.jeonserver.model.core.EventContent;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

/**
 * Room transaction.
 */
@Entity
@Table(name = "room_transaction")
@Getter
@Setter
@EqualsAndHashCode(of = {"txnId", "sender", "room"})
@IdClass(RoomTransactionId.class)
public class RoomTransaction implements Serializable {

    @Id
    @Column(name = "txn_id")
    private String txnId;

    @Id
    private String sender;

    @Id
    @ManyToOne
    private Room room;

    @OneToOne
    private EventContent content;

    private String type;

    private LocalDateTime created;

    @PrePersist
    private void create() {
        created = LocalDateTime.now();
    }
}
