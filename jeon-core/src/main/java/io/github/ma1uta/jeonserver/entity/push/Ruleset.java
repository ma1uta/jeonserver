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

package io.github.ma1uta.jeonserver.entity.push;

import io.github.ma1uta.jeonserver.entity.user.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Ruleset.
 */
@Entity
@Table(name = "ruleset")
@Getter
@Setter
@EqualsAndHashCode(of = {"id", "user"})
@IdClass(RulesetId.class)
public class Ruleset implements Serializable {

    @Id
    @SequenceGenerator(name = "pk_sequence", sequenceName = "ruleset_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
    private Long id;

    @Id
    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "ruleset")
    private Set<PushRule> content;

    @OneToMany(mappedBy = "ruleset")
    private Set<PushRule> override;

    @OneToMany(mappedBy = "ruleset")
    private Set<PushRule> room;

    @OneToMany(mappedBy = "ruleset")
    private Set<PushRule> sender;

    @OneToMany(mappedBy = "ruleset")
    private Set<PushRule> underride;
}
