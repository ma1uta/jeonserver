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
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Unsigned.
 */
@Embeddable
public class Unsigned implements Serializable {

    @Column(name = "age")
    private Long age;

    @Column(name = "replaces_state")
    private String replacesState;

    @Column(name = "prev_sender")
    private String prevSender;

    @Column(name = "prev_content")
    private String prevContent;

    @Column(name = "redacted_because")
    private String redactedBecause;

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public String getReplacesState() {
        return replacesState;
    }

    public void setReplacesState(String replacesState) {
        this.replacesState = replacesState;
    }

    public String getPrevSender() {
        return prevSender;
    }

    public void setPrevSender(String prevSender) {
        this.prevSender = prevSender;
    }

    public String getPrevContent() {
        return prevContent;
    }

    public void setPrevContent(String prevContent) {
        this.prevContent = prevContent;
    }

    public String getRedactedBecause() {
        return redactedBecause;
    }

    public void setRedactedBecause(String redactedBecause) {
        this.redactedBecause = redactedBecause;
    }
}
