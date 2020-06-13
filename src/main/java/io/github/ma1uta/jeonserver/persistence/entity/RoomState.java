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

import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
//import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
//import javax.persistence.Table;

//@Entity
//@Table(name = "room_state")
public class RoomState {

    @Id
    @Column(name = "room_id")
    private String roomId;

    @Column(name = "name")
    private String name;

    @Column(name = "topic")
    private String topic;

    @OneToOne
    private Image avatar;

    @ManyToOne
    private List<String> pinnedEvents;

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Image getAvatar() {
        return avatar;
    }

    public void setAvatar(Image avatar) {
        this.avatar = avatar;
    }

    public List<String> getPinnedEvents() {
        return pinnedEvents;
    }

    public void setPinnedEvents(List<String> pinnedEvents) {
        this.pinnedEvents = pinnedEvents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RoomState roomState = (RoomState) o;
        return Objects.equals(roomId, roomState.roomId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomId);
    }
}
