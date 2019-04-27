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

import io.github.ma1uta.jeonserver.model.core.Event;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

/**
 * Room.
 */
@Entity
@Table(name = "room")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Room implements Serializable {

    @Id
    private String id;

    private String version;

    private LocalDateTime created;

    private Boolean visible;

    @OneToOne
    private RoomState name;

    @OneToOne
    private RoomState topic;

    @OneToOne
    private RoomState avatar;

    @OneToOne
    private RoomState permissions;

    @OneToMany(fetch = FetchType.LAZY)
    private List<RoomState> members;

    @OneToMany
    private List<RoomState> aliases;

    @OneToMany(fetch = FetchType.LAZY)
    @OrderColumn(name = "received")
    private List<Event> timeline;
}


