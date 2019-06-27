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

package io.github.ma1uta.jeonserver.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Clock;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

/**
 * Common application configuration.
 */
@ApplicationScoped
public class ApplicationConfiguration {

    private Executor executor = Executors.newCachedThreadPool();

    /**
     * Application executor.
     *
     * @return executor.
     */
    @Produces
    public Executor executor() {
        return executor;
    }

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
}
