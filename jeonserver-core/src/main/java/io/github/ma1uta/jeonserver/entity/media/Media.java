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

package io.github.ma1uta.jeonserver.entity.media;

import io.github.ma1uta.jeonserver.entity.core.Domain;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Media.
 */
@Entity
@Table(
    name = "media",
    uniqueConstraints = {
        @UniqueConstraint(name = "media_constr_media_domain", columnNames = {"media_id", "domain_id"})
    }
)
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Media implements Serializable {

    @Id
    @SequenceGenerator(name = "pk_sequence_media", sequenceName = "media_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence_media")
    private Long id;

    @Column(name = "media_id")
    private String mediaId;

    @ManyToOne
    @JoinColumn(name = "domain_id", foreignKey = @ForeignKey(name = "media_fk_domain"))
    private Domain domain;

    private String filename;

    private String path;

    private LocalDateTime created;

    @Column(name = "allow_remote")
    private Boolean allowRemote;

    private Long size;

    @OneToMany(mappedBy = "media")
    private Set<Thumbnail> thumbnails;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "media_remote_addresses",
        joinColumns = @JoinColumn(name = "id", foreignKey = @ForeignKey(name = "media_fk_remote_addresses")))
    @Column(name = "remote_address")
    private Set<String> remoteAddresses;
}
