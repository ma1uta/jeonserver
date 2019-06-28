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

package io.github.ma1uta.jeonserver.core.entity.filter;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Room filter.
 */
@Entity
@Table(name = "room_filter")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class RoomFilter implements Serializable {

    @Id
    @SequenceGenerator(name = "pk_sequence_room_filter", sequenceName = "room_filter_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence_room_filter")
    private Long id;

    private Long limit;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "room_filter_not_rooms",
        joinColumns = @JoinColumn(name = "id", foreignKey = @ForeignKey(name = "room_filter_fk_not_rooms")))
    @Column(name = "not_rooms")
    private Set<String> notRooms;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "room_filter_rooms",
        joinColumns = @JoinColumn(name = "id", foreignKey = @ForeignKey(name = "room_filter_fk_rooms")))
    @Column(name = "rooms")
    private Set<String> rooms;

    @OneToOne
    @JoinColumn(name = "ephemeral_id", foreignKey = @ForeignKey(name = "room_filter_fk_ephemeral"))
    private RoomEventFilter ephemeral;

    @Column(name = "include_leave")
    private Boolean includeLeave;

    @OneToOne
    @JoinColumn(name = "state_id", foreignKey = @ForeignKey(name = "room_filter_fk_state"))
    private StateFilter state;

    @OneToOne
    @JoinColumn(name = "timeline_id", foreignKey = @ForeignKey(name = "room_filter_fk_timeline"))
    private RoomEventFilter timeline;

    @OneToOne
    @JoinColumn(name = "account_data_id", foreignKey = @ForeignKey(name = "room_filter_fk_account_data"))
    private RoomEventFilter accountData;
}
