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
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Device key.
 */
@Entity
@Table(
    name = "device_key",
    uniqueConstraints = {
        @UniqueConstraint(name = "device_key_constr_device_user_algorithm", columnNames = {"device_id", "user_id", "algorithm"})
    }
)
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class DeviceKey implements Serializable {

    @Id
    @SequenceGenerator(name = "pk_sequence_device_key", sequenceName = "device_key_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence_device_key")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "device_id", foreignKey = @ForeignKey(name = "device_key_fk_device"))
    private Device device;

    @ManyToOne
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "device_key_fk_user"))
    private User user;

    private String algorithm;

    private LocalDateTime created;

    private String key;

    private String signature;

    @PrePersist
    private void create() {
        created = LocalDateTime.now();
    }
}