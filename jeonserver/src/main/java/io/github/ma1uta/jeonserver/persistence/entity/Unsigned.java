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

import java.util.Map;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Unsigned.
 */
@Embeddable
public class Unsigned {

    @Column(name = "age")
    private Long age;

    @Column(name = "replaces_state")
    private String replacesState;

    @Column(name = "prev_sender")
    private String prevSender;

    @Column(name = "prev_content", columnDefinition = "jsonb")
    @Type(
        type = "jsonb",
        parameters = {@Parameter(name = JsonType.TYPE, value = "java.util.HashMap")}
    )
    private Map<String, Object> prevContent;

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

    public Map<String, Object> getPrevContent() {
        return prevContent;
    }

    public void setPrevContent(Map<String, Object> prevContent) {
        this.prevContent = prevContent;
    }

    public String getRedactedBecause() {
        return redactedBecause;
    }

    public void setRedactedBecause(String redactedBecause) {
        this.redactedBecause = redactedBecause;
    }
}
