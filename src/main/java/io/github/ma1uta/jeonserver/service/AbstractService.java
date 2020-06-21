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

package io.github.ma1uta.jeonserver.service;

import io.github.ma1uta.jeonserver.event.AbstractEvent;
import org.eclipse.microprofile.context.ManagedExecutor;

import javax.ws.rs.container.AsyncResponse;

public interface AbstractService<E extends AbstractEvent<?>, R> {

    default void consumeEvent(E event) {
        getManagedExecutor().supplyAsync(() -> action(event)).whenComplete((result, exception) -> {
            AsyncResponse asyncResponse = event.getAsyncResponse();
            if (exception != null) {
                asyncResponse.resume(exception);
            } else {
                asyncResponse.resume(result);
            }
        });
    }

    ManagedExecutor getManagedExecutor();

    R action(E event);
}
