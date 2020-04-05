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

package io.github.ma1uta.jeonserver.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "room_info")
public class RoomInfo {

    @Id
    @Column(name = "room_id")
    private String roomId;

    @Column(name = "version")
    private String version;

    @ManyToOne
    @JoinColumn(name = "create_room_id")
    private PersistentDataUnit createPdu;

    @ManyToOne
    @JoinColumn(name = "power_level_id")
    private PersistentDataUnit powerLevels;

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public PersistentDataUnit getCreatePdu() {
        return createPdu;
    }

    public void setCreatePdu(PersistentDataUnit createPdu) {
        this.createPdu = createPdu;
    }
}
