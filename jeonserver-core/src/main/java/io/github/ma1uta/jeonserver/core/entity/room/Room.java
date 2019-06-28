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

import io.github.ma1uta.jeonserver.core.entity.Domain;
import io.github.ma1uta.jeonserver.core.entity.Event;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Room.
 */
@Entity
@Table(
    name = "room",
    uniqueConstraints = {
        @UniqueConstraint(name = "room_constr_room_domain", columnNames = {"room_id", "domain_id"})
    }
)
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Room implements Serializable {

    @Id
    @SequenceGenerator(name = "pk_sequence_room", sequenceName = "room_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence_room")
    private Long id;

    @Column(name = "room_id")
    private String roomId;

    @ManyToOne
    @JoinColumn(name = "domain_id", foreignKey = @ForeignKey(name = "room_fk_domain"))
    private Domain domain;

    private String version;

    private LocalDateTime created;

    private Boolean visible;

    @OneToOne
    @JoinColumn(name = "latest_state_id", foreignKey = @ForeignKey(name = "room_fk_last_state"))
    private RoomStateSnapshot latestState;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "room")
    @OrderColumn(name = "received")
    private List<Event> timeline;
}


