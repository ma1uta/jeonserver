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

package io.github.ma1uta.jeonserver.entity.filter;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Filter.
 */
@Entity
@Table(name = "filter")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Filter implements Serializable {

    @Id
    @SequenceGenerator(name = "pk_sequence", sequenceName = "filter_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
    private Long id;

    @ElementCollection
    @CollectionTable(name = "filter_event_fields", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "event_fields")
    private Set<String> eventFields;

    @ElementCollection
    @CollectionTable(name = "filter_event_format", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "event_format")
    private Set<String> eventFormat;

    @OneToOne
    @JoinColumn(name = "presence_id")
    private EventFilter presence;

    @OneToOne
    @JoinColumn(name = "account_data_id")
    private EventFilter accountData;

    @OneToOne
    @JoinColumn(name = "room_id")
    private RoomFilter room;
}