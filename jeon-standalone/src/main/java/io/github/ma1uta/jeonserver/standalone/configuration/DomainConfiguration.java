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

package io.github.ma1uta.jeonserver.standalone.configuration;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Domain configuration.
 */
@Getter
@Setter
public class DomainConfiguration {

    private String name;

    private Ratelimit ratelimit;

    private Federation federation;

    private Preview preview;

    private Blacklist blacklist;

    private List<Registration> registration;

    private Turn turn;

    /**
     * Rate limit configuration.
     */
    @Getter
    @Setter
    public static class Ratelimit {

        private Integer messagesPerSecond;
    }

    /**
     * Federation configuration.
     */
    @Getter
    @Setter
    public static class Federation {

        private Integer delay;

        private Integer workerPool;
    }

    /**
     * Preview configuration.
     */
    @Getter
    @Setter
    public static class Preview {

        private Integer maxSize;

        private List<String> blacklist;
    }

    /**
     * Media configuration.
     */
    @Getter
    @Setter
    public static class Media {

        private String path;

        private String maxUploadSize;

        private Thumbnails thumbnails;
    }

    /**
     * Thumbnails configuration.
     */
    @Getter
    @Setter
    public static class Thumbnails {

        private Boolean dynamic;

        private List<PrecalcSize> precalcSizes;
    }

    /**
     * Precalculated size configuration.
     */
    @Getter
    @Setter
    public static class PrecalcSize {

        private Integer width;

        private Integer height;

        private String method;
    }

    /**
     * Blacklist configuration.
     */
    @Getter
    @Setter
    public static class Blacklist {

        private List<String> usernames;

        private List<String> servers;
    }

    /**
     * Registration.
     */
    public abstract static class Registration {

        /**
         * Registration type.
         *
         * @return registration type.
         */
        public abstract String getType();
    }

    /**
     * Internal registration.
     */
    @Getter
    @Setter
    private static class InternalRegistration extends Registration {

        @Override
        public String getType() {
            return "internal";
        }

        private Boolean enable;

        private Boolean allowGuests;

        private String sharedSecret;

        private Rechaptcha rechaptcha;
    }

    /**
     * Recaptcha configuration.
     */
    @Getter
    @Setter
    public static class Rechaptcha {

        private String publicKey;

        private String privateKey;

        private String verifyApi;
    }

    /**
     * LDAP registration.
     */
    @Getter
    @Setter
    public static class LdapRegistration extends Registration {


        @Override
        public String getType() {
            return "ldap";
        }

        private String base;

        private String username;

        private String password;

        private String query;
    }

    /**
     * Turn configuration.
     */
    @Getter
    @Setter
    public static class Turn {

        private String username;

        private String password;

        private List<String> uris;

        private Boolean allowGuest;
    }
}
