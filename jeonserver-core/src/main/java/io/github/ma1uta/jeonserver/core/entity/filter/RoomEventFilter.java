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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Room event filter.
 */
@Entity
@Table(name = "room_event_filter")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class RoomEventFilter implements Serializable {

    @Id
    @SequenceGenerator(name = "pk_sequence_room_event_filter", sequenceName = "room_event_filter_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence_room_event_filter")
    private Long id;

    private Long limit;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "room_event_filter_not_senders",
        joinColumns = @JoinColumn(name = "id", foreignKey = @ForeignKey(name = "room_event_filter_fk_not_senders")))
    @Column(name = "not_senders")
    private Set<String> notSenders;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "room_event_filter_not_types",
        joinColumns = @JoinColumn(name = "id", foreignKey = @ForeignKey(name = "room_event_filter_fk_not_types")))
    @Column(name = "not_types")
    private Set<String> notTypes;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "room_event_filter_senders",
        joinColumns = @JoinColumn(name = "id", foreignKey = @ForeignKey(name = "room_event_filter_fk_sender")))
    @Column(name = "senders")
    private Set<String> senders;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "room_event_filter_types",
        joinColumns = @JoinColumn(name = "id", foreignKey = @ForeignKey(name = "room_event_filter_fk_types")))
    @Column(name = "types")
    private Set<String> types;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "room_event_filter_not_rooms",
        joinColumns = @JoinColumn(name = "id", foreignKey = @ForeignKey(name = "room_event_filter_fk_not_rooms")))
    @Column(name = "not_rooms")
    private Set<String> notRooms;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "room_event_filter_rooms",
        joinColumns = @JoinColumn(name = "id", foreignKey = @ForeignKey(name = "room_event_filter_fk_rooms")))
    @Column(name = "rooms")
    private Set<String> rooms;

    @Column(name = "contains_url")
    private Boolean containsUrl;
}
