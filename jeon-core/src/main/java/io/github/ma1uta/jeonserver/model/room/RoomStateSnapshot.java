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

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Full room's state.
 */
@Entity
@Table(name = "room_state_snapshot")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class RoomStateSnapshot implements Serializable {

    @Id
    @SequenceGenerator(name = "pk_sequence", sequenceName = "room_state_snapshot_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
    private Long id;

    @OneToOne
    private RoomStateSnapshot prev;

    private LocalDateTime created;

    @ManyToOne
    private Room room;

    @OneToOne
    private RoomState initial;

    @OneToOne
    private RoomState name;

    @OneToOne
    private RoomState topic;

    @OneToOne
    private RoomState avatar;

    @OneToOne
    private RoomState permissions;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "roomState")
    private List<RoomState> members;

    @OneToMany(mappedBy = "roomState")
    private List<RoomState> aliases;

    @OneToOne
    @JoinColumn(name = "canonical_alias_id")
    private RoomState canonicalAlias;

    @OneToMany(mappedBy = "roomState")
    private List<RoomState> pinned;

    @OneToOne
    @JoinColumn(name = "server_acl_id")
    private RoomState serverAcl;

    @OneToOne
    @JoinColumn(name = "guest_access_id")
    private RoomState guestAccess;

    @OneToOne
    @JoinColumn(name = "history_visibility_id")
    private RoomState historyVisibility;

    @OneToOne
    @JoinColumn(name = "join_rules_id")
    private RoomState joinRules;

    @OneToOne
    private RoomState encryption;

    @OneToOne
    private RoomState tombstone;
}
