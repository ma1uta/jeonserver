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

package io.github.ma1uta.jeonserver.configuration;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import io.github.ma1uta.jeonserver.service.EventIdCreator;
import io.github.ma1uta.jeonserver.service.NewRoomVersion;
import io.github.ma1uta.jeonserver.service.RoomIdCreator;
import io.github.ma1uta.matrix.event.Event;
import io.github.ma1uta.matrix.event.content.RoomEncryptedContent;
import io.github.ma1uta.matrix.event.content.RoomMessageContent;
import io.github.ma1uta.matrix.event.nested.ReceiptTs;
import io.github.ma1uta.matrix.support.jackson.EventDeserializer;
import io.github.ma1uta.matrix.support.jackson.RoomEncryptedContentDeserializer;
import io.github.ma1uta.matrix.support.jackson.RoomMessageContentDeserializer;
import io.github.ma1uta.matrix.support.jackson.workaround.ReceiptTsDeserialized4898;
import io.quarkus.jackson.ObjectMapperCustomizer;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Clock;
import java.util.UUID;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

/**
 * Common application configuration.
 */
@ApplicationScoped
public class ApplicationConfiguration {

    @ConfigProperty(name = "jeon.domain")
    String domain;

    /**
     * Application clock.
     *
     * @return clock.
     */
    @Produces
    public Clock clock() {
        return Clock.systemDefaultZone();
    }

    /**
     * Application logger.
     *
     * @param injectionPoint point to inject.
     * @return logger.l
     */
    @Produces
    public Logger logger(InjectionPoint injectionPoint) {
        return LoggerFactory.getLogger(injectionPoint.getMember().getDeclaringClass());
    }

    @Produces
    public RoomIdCreator roomIdCreator() {
        return () -> String.format("!%s:%s", UUID.randomUUID().toString().replaceAll("-", ""), domain);
    }

    @Produces
    public EventIdCreator eventIdCreator() {
        return () -> String.format("$%s:%s", UUID.randomUUID().toString().replaceAll("-", ""), domain);
    }

    @Produces
    public NewRoomVersion newRoomVersion() {
        return () -> "6";
    }

    @Produces
    public ObjectMapperCustomizer mapperCustomizer() {
        return objectMapper -> {
            objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
            objectMapper.enable(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY);

            SimpleModule eventModule = new SimpleModule("Jackson Matrix Module");
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
            eventModule.addDeserializer(Event.class, new EventDeserializer());
            eventModule.addDeserializer(RoomEncryptedContent.class, new RoomEncryptedContentDeserializer());
            eventModule.addDeserializer(RoomMessageContent.class, new RoomMessageContentDeserializer());
            eventModule.addDeserializer(ReceiptTs.class, new ReceiptTsDeserialized4898());

            objectMapper.registerModule(eventModule);
        };
    }
}
