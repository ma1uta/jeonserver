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

import java.util.Objects;
import javax.persistence.Column;
//import javax.persistence.Entity;
import javax.persistence.Id;
//import javax.persistence.Table;

//@Entity
//@Table(name = "room_encryption")
public class RoomEncryption {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "algorithm")
    private String algorithm;

    @Column(name = "rotation_period_ms")
    private Long rotationPeriodMs;

    @Column(name = "rotation_period_msgs")
    private Long rotationPeriodMsgs;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public Long getRotationPeriodMs() {
        return rotationPeriodMs;
    }

    public void setRotationPeriodMs(Long rotationPeriodMs) {
        this.rotationPeriodMs = rotationPeriodMs;
    }

    public Long getRotationPeriodMsgs() {
        return rotationPeriodMsgs;
    }

    public void setRotationPeriodMsgs(Long rotationPeriodMsgs) {
        this.rotationPeriodMsgs = rotationPeriodMsgs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RoomEncryption that = (RoomEncryption) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
