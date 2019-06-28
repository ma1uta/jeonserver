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

package io.github.ma1uta.jeonserver.core.entity;

import io.github.ma1uta.jeonserver.entity.room.Room;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Server side event implementation.
 */
@Entity
@Table(
    name = "event",
    uniqueConstraints = {
        @UniqueConstraint(name = "event_constr_domain_event", columnNames = {"domain_id", "event_id"})
    }
)
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@DiscriminatorColumn
public class Event implements Serializable {

    @Id
    @SequenceGenerator(name = "pk_sequence_event", sequenceName = "event_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence_event")
    @Column(name = "id")
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "domain_id", foreignKey = @ForeignKey(name = "event_fk_domain"))
    private Domain domain;

    private String type;

    @Column(name = "event_id")
    private String eventId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "content_id", foreignKey = @ForeignKey(name = "event_fk_content"))
    private EventContent content;

    @OneToOne
    @JoinColumn(name = "redacts_id", foreignKey = @ForeignKey(name = "event_fk_redacts"))
    private Redacts redacts;

    @Column(name = "origin_server")
    private String originServer;

    private String sender;

    private LocalDateTime created;

    private LocalDateTime received;

    @ManyToOne
    @JoinColumn(name = "room_id", foreignKey = @ForeignKey(name = "event_fk_room"))
    private Room room;

    @ManyToMany
    @JoinTable(name = "event_graph",
        joinColumns = @JoinColumn(name = "parents_id", foreignKey = @ForeignKey(name = "event_fk_parents")),
        inverseJoinColumns = @JoinColumn(name = "children_id", foreignKey = @ForeignKey(name = "event_fk_children"))
    )
    private List<Event> parents;

    @ManyToMany(mappedBy = "parents")
    private List<Event> children;

    private String hash;

    @OneToMany(mappedBy = "event")
    private Set<Signature> signatures;

    private Long depth;

    @Column(name = "txn_id")
    private String txnId;
}
