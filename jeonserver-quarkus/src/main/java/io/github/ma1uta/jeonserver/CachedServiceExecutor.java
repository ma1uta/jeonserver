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

package io.github.ma1uta.jeonserver;

import org.slf4j.Logger;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.core.UriInfo;

/**
 * Service executor based on cached thread pool.
 */
@ApplicationScoped
public class CachedServiceExecutor implements ServiceExecutor {

    private final Executor executor = Executors.newCachedThreadPool();

    @Inject
    Logger logger;

    @Override
    public void execute(Callable<?> runnable, AsyncResponse asyncResponse, UriInfo uriInfo) {
        executor.execute(() -> {
            try {
                asyncResponse.resume(runnable.call());
            } catch (Exception e) {
                logger.error(String.format("Unable to invoke command. Request: %s", uriInfo.getPath()), e);
                asyncResponse.resume(e);
            }
        });
    }
}
