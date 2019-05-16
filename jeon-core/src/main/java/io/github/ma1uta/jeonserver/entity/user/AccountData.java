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
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * User account data.
 */
@Entity
@Table(
    name = "account_data",
    uniqueConstraints = {
        @UniqueConstraint(name = "account_data_constr_room_user_type", columnNames = {"room", "user_id", "type"})
    }
)
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class AccountData implements Serializable {

    @Id
    @SequenceGenerator(name = "pk_sequence", sequenceName = "account_data_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
    private Long id;

    private String room;

    @ManyToOne
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "account_data_fk_user"))
    private User user;

    private String type;

    private String content;
}
