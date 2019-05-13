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

package io.github.ma1uta.jeonserver.entity.user;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * One time key.
 */
@Entity
@Table(
    name = "one_time_key",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"key_id", "user_id", "algorithm"})
    }
)
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class OneTimeKey implements Serializable {

    @Id
    @SequenceGenerator(name = "pk_sequence", sequenceName = "one_time_key_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
    private Long id;

    @Column(name = "key_id")
    private String keyId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String algorithm;

    private LocalDateTime created;

    private String key;

    @OneToMany(mappedBy = "oneTimeKey")
    private Set<OneTimeKeySignature> signatures;

    @PrePersist
    private void create() {
        created = LocalDateTime.now();
    }
}
