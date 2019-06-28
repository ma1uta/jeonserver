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
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Push rule.
 */
@Entity
@Table(name = "push_rule")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class PushRule implements Serializable {

    @Id
    @SequenceGenerator(name = "pk_sequence_push_rule", sequenceName = "push_rule_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence_push_rule")
    private Long id;

    @OneToMany
    @JoinTable(
        name = "push_rule_actions",
        foreignKey = @ForeignKey(name = "push_rule_fk_push_rule_actions"),
        joinColumns = @JoinColumn(
            name = "push_rule_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "push_rule_fk_push_rule")),
        inverseJoinColumns = @JoinColumn(
            name = "action_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "push_rule_fk_action"))
    )
    private Set<Action> actions;

    @Column(name = "default")
    private Boolean defaultRule;

    private Boolean enabled;

    @Column(name = "rule_id")
    private String ruleId;

    private String pattern;

    @OneToMany(mappedBy = "rule")
    private Set<PushCondition> conditions;

    @ManyToOne
    @JoinColumn(name = "ruleset_id", foreignKey = @ForeignKey(name = "push_rule_fk_ruleset"))
    private Ruleset ruleset;
}
