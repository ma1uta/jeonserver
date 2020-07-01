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

package io.github.ma1uta.jeonserver.client.resource;

import io.github.ma1uta.jeonserver.event.SyncEvent;
import io.github.ma1uta.matrix.client.api.SyncApi;
import io.vertx.mutiny.core.eventbus.EventBus;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

@ApplicationScoped
@Path("/_matrix/client/r0")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SyncResource implements SyncApi {

    private final EventBus eventBus;

    public SyncResource(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @GET
    @Path("/sync")
    @Override
    public void sync(
        @QueryParam("filter") String filter,
        @QueryParam("since") String since,
        @QueryParam("full_state") Boolean fullState,
        @QueryParam("set_presence") String setPresence,
        @QueryParam("timeout") Long timeout,
        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse,
        @Context SecurityContext securityContext
    ) {
        eventBus.publish(
            "sync",
            new SyncEvent(
                asyncResponse,
                since,
                filter,
                fullState,
                setPresence,
                timeout,
                securityContext.getUserPrincipal().getName()
            )
        );
    }

    @Override
    public void events(
        String from,
        Long timeout,
        String roomId,
        UriInfo uriInfo,
        HttpHeaders httpHeaders,
        AsyncResponse asyncResponse,
        SecurityContext securityContext
    ) {

    }
}
