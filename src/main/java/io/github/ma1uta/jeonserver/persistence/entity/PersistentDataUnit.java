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

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * Persistent data unit.
 */
@Entity
@Table(name = "pdu")
public class PersistentDataUnit implements Serializable {

    @Id
    @Column(name = "event_id", nullable = false)
    private String eventId;

    @Column(name = "room_id", nullable = false)
    private String roomId;

    @Column(name = "version", nullable = false)
    private String version;

    @Column(name = "sender", nullable = false)
    private String sender;

    @Column(name = "origin", nullable = false)
    private String origin;

    @Column(name = "origin_server_ts", nullable = false)
    private Long originServerTs;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "local_ts", nullable = false)
    private Long localTs;

    @Column(name = "state_key")
    private String stateKey;

    @Column(name = "content")
    private String content;

    @Column(name = "depth", nullable = false)
    private Long depth;

    @Column(name = "redacts")
    private String redacts;

    @Embedded
    private Unsigned unsigned;

    @Embedded
    private EventHash hashes;

    @Column(name = "signatures", nullable = false)
    private String signatures;

    @Column(name = "created_at", nullable = false)
    private ZonedDateTime createdAt;

    @ManyToMany
    @JoinTable(
        name = "pdu_prev_event",
        joinColumns = @JoinColumn(name = "event_id"),
        inverseJoinColumns = @JoinColumn(name = "prev_event_id")
    )
    private List<PersistentDataUnit> prevEvents;

    @ManyToMany
    @JoinTable(
        name = "pdu_auth_event",
        joinColumns = @JoinColumn(name = "event_id"),
        inverseJoinColumns = @JoinColumn(name = "auth_id")
    )
    private List<PersistentDataUnit> authEvents;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
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

    public String getSignatures() {
        return signatures;
    }

    public void setSignatures(String signatures) {
        this.signatures = signatures;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<PersistentDataUnit> getPrevEvents() {
        return prevEvents;
    }

    public void setPrevEvents(List<PersistentDataUnit> prevEvents) {
        this.prevEvents = prevEvents;
    }

    public List<PersistentDataUnit> getAuthEvents() {
        return authEvents;
    }

    public void setAuthEvents(List<PersistentDataUnit> authEvents) {
        this.authEvents = authEvents;
    }

    public Long getLocalTs() {
        return localTs;
    }

    public void setLocalTs(Long localTs) {
        this.localTs = localTs;
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
