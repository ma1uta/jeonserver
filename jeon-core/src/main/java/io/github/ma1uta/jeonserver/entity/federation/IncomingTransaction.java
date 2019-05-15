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
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Incoming transaction.
 */
@Entity
@Table(
    name = "incoming_transaction",
    uniqueConstraints = {
        @UniqueConstraint(name = "income_trans_constr_trans_domain", columnNames = {"transaction_id", "domain_id"})
    }
)
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class IncomingTransaction implements Serializable {

    @Id
    @SequenceGenerator(name = "pk_sequence", sequenceName = "incoming_transaction_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
    private Long id;

    @OneToOne
    @JoinColumn(name = "transaction_id")
    private FederatedTransaction transaction;

    @ManyToOne
    @JoinColumn(name = "domain_id")
    private Domain domain;

    private LocalDateTime received;

    private LocalDateTime parsed;

    @PrePersist
    private void receive() {
        received = LocalDateTime.now();
    }
}
