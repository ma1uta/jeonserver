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

import io.github.ma1uta.jeonserver.entity.core.Event;
import io.github.ma1uta.jeonserver.entity.user.User;
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
 * Notification.
 */
@Entity
@Table(name = "notification")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Notification implements Serializable {

    @Id
    @SequenceGenerator(name = "pk_sequence", sequenceName = "notification_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "notif_fk_user"))
    private User user;

    @OneToMany
    @JoinTable(
        name = "notification_actions",
        joinColumns = {
            @JoinColumn(name = "notification_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "notif_fk_notif"))
        },
        inverseJoinColumns = {
            @JoinColumn(name = "action_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "notif_fk_actions"))
        }
    )
    private Set<Action> actions;

    @ManyToOne
    @JoinColumn(name = "event_id", foreignKey = @ForeignKey(name = "notif_fk_event"))
    private Event event;

    @Column(name = "profile_tag")
    private String profileTag;

    private Boolean read;

    @Column(name = "room_id")
    private String roomId;

    private Long ts;
}
