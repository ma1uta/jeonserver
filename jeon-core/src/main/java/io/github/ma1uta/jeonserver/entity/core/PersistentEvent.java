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

package io.github.ma1uta.jeonserver.entity.core;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Event edge.
 */
@Entity
@Table(name = "persistent_event")
@Getter
@Setter
@SequenceGenerator(name = "pk_sequence", sequenceName = "persistent_event_id_seq", allocationSize = 1)
@DiscriminatorValue("persistent_event")
public class PersistentEvent extends Event {

    private String creator;

    private String sender;

    @Column(name = "txn_id")
    private String txnId;
}