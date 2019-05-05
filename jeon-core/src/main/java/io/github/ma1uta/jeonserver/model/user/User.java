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

package io.github.ma1uta.jeonserver.model.user;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * User.
 */
@Entity
@Table(name = "user")
@Getter
@Setter
@EqualsAndHashCode(of = "username")
public class User implements Serializable {

    @Id
    private String username;

    private LocalDateTime created;

    private Boolean admin;

    private String presence;

    @Column(name = "last_active_ago")
    private LocalDateTime lastActiveAgo;

    @Column(name = "state_msg")
    private String status;

    @Column(name = "currently_active")
    private Boolean currentlyActive;

    @OneToMany(mappedBy = "user")
    private List<Tag> tags;
}
