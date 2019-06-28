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

package io.github.ma1uta.jeonserver.core.entity.push;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Push condition.
 */
@Entity
@Table(name = "push_condition")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class PushCondition implements Serializable {

    @Id
    @SequenceGenerator(name = "pk_sequence_push_condition", sequenceName = "push_condition_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence_push_condition")
    private Long id;

    @Enumerated(EnumType.STRING)
    private PushConditionKind kind;

    private String key;

    private String pattern;

    private String is;

    @ManyToOne
    @JoinColumn(name = "rule_id", foreignKey = @ForeignKey(name = "push_condition_fk_rule"))
    private PushRule rule;
}
