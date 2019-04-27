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
import javax.persistence.Table;

/**
 * State filter.
 */
@Entity
@Table(name = "state_filter")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class StateFilter implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private Long limit;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> notSenders;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> notTypes;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> senders;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> types;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> notRooms;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> rooms;

    @Column(name = "contains_url")
    private Boolean containsUrl;

    @Column(name = "lazy_load_members")
    private Boolean lazyLoadMembers;

    @Column(name = "include_redundant_members")
    private Boolean includeRedundantMembers;
}
