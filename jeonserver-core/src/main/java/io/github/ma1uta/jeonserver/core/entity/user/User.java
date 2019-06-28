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

package io.github.ma1uta.jeonserver.core.entity.user;

import io.github.ma1uta.jeonserver.core.entity.Domain;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * User.
 */
@Entity
@Table(
    name = "user",
    uniqueConstraints = {
        @UniqueConstraint(name = "user_constr_username_domain", columnNames = {"username", "domain_id"})
    }
)
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class User implements Serializable {

    @Id
    @SequenceGenerator(name = "pk_sequence_user", sequenceName = "user_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence_user")
    private Long id;

    private String username;

    @ManyToOne
    @JoinColumn(name = "domain_id", foreignKey = @ForeignKey(name = "user_fk_domain"))
    private Domain domain;

    private LocalDateTime created;

    private Boolean admin;

    private String presence;

    @OneToOne
    @JoinColumn(name = "auth_id", foreignKey = @ForeignKey(name = "user_fk_auth"))
    private AbstractAuth auth;

    @Column(name = "last_active_ago")
    private LocalDateTime lastActiveAgo;

    @Column(name = "state_msg")
    private String status;

    @Column(name = "currently_active")
    private Boolean currentlyActive;

    @OneToMany(mappedBy = "user")
    private List<Tag> tags;

    @OneToMany(mappedBy = "user")
    private Set<Device> devices;

    @OneToMany(mappedBy = "user")
    private Set<Token> tokens;

    @OneToMany(mappedBy = "user")
    private Set<OneTimeKey> oneTimeKeys;

    @PrePersist
    private void create() {
        created = LocalDateTime.now();
    }
}
