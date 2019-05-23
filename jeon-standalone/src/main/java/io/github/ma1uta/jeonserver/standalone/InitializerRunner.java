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

package io.github.ma1uta.jeonserver.standalone;

import lombok.extern.slf4j.Slf4j;

import java.util.Set;
import javax.inject.Inject;

/**
 * Runner of the initializers.
 */
@Slf4j
public class InitializerRunner {

    private final Set<Initializer> initializers;

    @Inject
    public InitializerRunner(Set<Initializer> initializers) {
        this.initializers = initializers;
    }

    /**
     * Run initializers.
     */
    public void init() {
        log.info("Initialize.");
        for (Initializer initializer : initializers) {
            log.debug("Run initializer: {}", initializer.getClass().getCanonicalName());
            try {
                initializer.init();
            } catch (Exception e) {
                log.error("Unable to initialize services.", e);
                throw new RuntimeException(e);
            }
        }
        log.info("done");
    }
}
