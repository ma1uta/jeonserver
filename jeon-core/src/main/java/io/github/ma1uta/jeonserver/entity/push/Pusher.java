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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Pusher.
 */
@Entity
@Table(name = "pusher")
@Getter
@Setter
@EqualsAndHashCode(of = {"pushkey", "user"})
@IdClass(PusherId.class)
public class Pusher implements Serializable {

    @Id
    private String pushkey;

    @Id
    @ManyToOne
    private User user;

    private String kind;

    @Column(name = "app_id")
    private String appId;

    @Column(name = "app_display_name")
    private String appDisplayName;

    @Column(name = "device_display_name")
    private String deviceDisplayName;

    @Column(name = "profile_tag")
    private String profileTag;

    private String lang;

    @OneToMany(mappedBy = "pusher")
    private Set<PusherData> data;
}
