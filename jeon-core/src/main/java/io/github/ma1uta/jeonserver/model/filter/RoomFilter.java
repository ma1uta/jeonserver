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

package io.github.ma1uta.jeonserver.model.filter;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private Long limit;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> notRooms;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> rooms;

    @OneToOne
    private RoomEventFilter ephemeral;

    @Column(name = "include_leave")
    private Boolean includeLeave;

    @OneToOne
    private StateFilter state;

    @OneToOne
    private RoomEventFilter timeline;

    @OneToOne
    private RoomEventFilter accountData;
}
