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

import io.github.ma1uta.jeonserver.persistence.jsonb.JsonType;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Map;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 * Persistent data unit.
 */
@Entity
@Table(name = "pdu")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "version")
@TypeDef(
    name = "jsonb",
    typeClass = JsonType.class
)
public class PersistentDataUnit implements Serializable {

    @Id
    @Column(name = "event_id")
    private String eventId;

    @Column(name = "room_id")
    private String roomId;

    @Column(name = "version", insertable = false, updatable = false)
    private String version;

    @Column(name = "sender")
    private String sender;

    @Column(name = "origin")
    private String origin;

    @Column(name = "origin_server_ts")
    private Long originServerTs;

    @Column(name = "type")
    private String type;

    @Column(name = "state_key")
    private String stateKey;

    @Column(name = "content", columnDefinition = "jsonb")
    @Type(
        type = "jsonb",
        parameters = {@Parameter(name = JsonType.TYPE, value = "java.util.HashMap")}
    )
    private Map<String, Object> content;

    @Column(name = "depth")
    private Long depth;

    @Column(name = "redacts")
    private String redacts;

    @Embedded
    private Unsigned unsigned;

    @Embedded
    private EventHash hashes;

    @Column(name = "signatures", columnDefinition = "jsonb")
    @Type(
        type = "jsonb",
        parameters = {@Parameter(name = JsonType.TYPE, value = "java.util.HashMap")}
    )
    private Map<String, Map<String, String>> signatures;

    @Column(name = "created_at")
    private ZonedDateTime createdAt;

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

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

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public Long getOriginServerTs() {
        return originServerTs;
    }

    public void setOriginServerTs(Long originServerTs) {
        this.originServerTs = originServerTs;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStateKey() {
        return stateKey;
    }

    public void setStateKey(String stateKey) {
        this.stateKey = stateKey;
    }

    public Map<String, Object> getContent() {
        return content;
    }

    public void setContent(Map<String, Object> content) {
        this.content = content;
    }

    public Long getDepth() {
        return depth;
    }

    public void setDepth(Long depth) {
        this.depth = depth;
    }

    public String getRedacts() {
        return redacts;
    }

    public void setRedacts(String redacts) {
        this.redacts = redacts;
    }

    public Unsigned getUnsigned() {
        return unsigned;
    }

    public void setUnsigned(Unsigned unsigned) {
        this.unsigned = unsigned;
    }

    public EventHash getHashes() {
        return hashes;
    }

    public void setHashes(EventHash hashes) {
        this.hashes = hashes;
    }

    public Map<String, Map<String, String>> getSignatures() {
        return signatures;
    }

    public void setSignatures(Map<String, Map<String, String>> signatures) {
        this.signatures = signatures;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PersistentDataUnit that = (PersistentDataUnit) o;
        return Objects.equals(eventId, that.eventId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId);
    }
}
