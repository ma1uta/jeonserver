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

package io.github.ma1uta.jeonserver.entity.federation;

import io.github.ma1uta.jeonserver.entity.core.Domain;
import io.github.ma1uta.jeonserver.entity.core.Event;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * PDU.
 */
@Entity
@Table(
    name = "federated_transaction",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"txn_id", "origin_server", "domain_id"})
    }
)
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class FederatedTransaction implements Serializable {

    @Id
    @SequenceGenerator(name = "pk_sequence", sequenceName = "fed_transaction_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
    private Long id;

    @Column(name = "txn_id")
    private String txnId;

    @Column(name = "origin_server")
    private String originServer;

    @ManyToOne
    @JoinColumn(name = "domain_id")
    private Domain domain;

    @Column(name = "origin_server_ts")
    private LocalDateTime originServerTs;

    private LocalDateTime received;

    @OneToMany
    @JoinTable(
        name = "pdu",
        joinColumns = {
            @JoinColumn(name = "txn_id", referencedColumnName = "txn_id"),
            @JoinColumn(name = "origin_server", referencedColumnName = "origin_server"),
            @JoinColumn(name = "txn_domain", referencedColumnName = "domain_id")
        },
        inverseJoinColumns = {
            @JoinColumn(name = "event_id", referencedColumnName = "id"),
            @JoinColumn(name = "event_domain", referencedColumnName = "domain_id")
        })
    private Set<Event> pdus;

    @OneToMany
    @JoinTable(
        name = "edu",
        joinColumns = {
            @JoinColumn(name = "txn_id", referencedColumnName = "txn_id"),
            @JoinColumn(name = "origin_server", referencedColumnName = "origin_server"),
            @JoinColumn(name = "txn_domain", referencedColumnName = "domain_id")
        },
        inverseJoinColumns = {
            @JoinColumn(name = "event_id", referencedColumnName = "id"),
            @JoinColumn(name = "event_domain", referencedColumnName = "domain_id")
        })
    private Set<Event> edus;
}
