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

import io.github.ma1uta.jeonserver.entity.user.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Filter.
 */
@Entity
@Table(
    name = "filter",
    uniqueConstraints = {
        @UniqueConstraint(name = "filter_constr_user_id", columnNames = {"user_id", "user_ordinal_id"})
    }
)
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Filter implements Serializable {

    @Id
    @SequenceGenerator(name = "pk_sequence", sequenceName = "filter_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "filter_fk_user"))
    private User user;

    @Column(name = "user_ordinal_id")
    private Long userOrdinalId;

    @ElementCollection
    @CollectionTable(name = "filter_event_fields",
        joinColumns = @JoinColumn(name = "id", foreignKey = @ForeignKey(name = "filter_fk_event_fields")))
    @Column(name = "event_fields")
    private Set<String> eventFields;

    @ElementCollection
    @CollectionTable(name = "filter_event_format",
        joinColumns = @JoinColumn(name = "id", foreignKey = @ForeignKey(name = "filter_fk_event_format")))
    @Column(name = "event_format")
    private Set<String> eventFormat;

    @OneToOne
    @JoinColumn(name = "presence_id", foreignKey = @ForeignKey(name = "filter_fk_presence"))
    private EventFilter presence;

    @OneToOne
    @JoinColumn(name = "account_data_id", foreignKey = @ForeignKey(name = "filter_fk_account_data"))
    private EventFilter accountData;

    @OneToOne
    @JoinColumn(name = "room_id", foreignKey = @ForeignKey(name = "filter_fk_room"))
    private RoomFilter room;
}
