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

import io.github.ma1uta.jeonserver.event.MessageEvent;
import io.github.ma1uta.matrix.client.api.EventApi;
import io.github.ma1uta.matrix.client.model.event.RedactRequest;
import io.github.ma1uta.matrix.event.content.EventContent;
import io.vertx.mutiny.core.eventbus.EventBus;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

@ApplicationScoped
@Path("/_matrix/client/r0/rooms")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EventResource implements EventApi {

    private final EventBus eventBus;

    public EventResource(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void roomEvent(String roomId, String eventId, UriInfo uriInfo, HttpHeaders httpHeaders, AsyncResponse asyncResponse,
                          SecurityContext securityContext) {

    }

    @Override
    public void roomEventWithTypeAndState(String roomId, String eventType, String stateKey, UriInfo uriInfo, HttpHeaders httpHeaders,
                                          AsyncResponse asyncResponse, SecurityContext securityContext) {

    }

    @Override
    public void roomState(String roomId, UriInfo uriInfo, HttpHeaders httpHeaders, AsyncResponse asyncResponse,
                          SecurityContext securityContext) {

    }

    @Override
    public void members(String roomId, UriInfo uriInfo, HttpHeaders httpHeaders, AsyncResponse asyncResponse,
                        SecurityContext securityContext) {

    }

    @Override
    public void joinedMembers(String roomId, UriInfo uriInfo, HttpHeaders httpHeaders, AsyncResponse asyncResponse,
                              SecurityContext securityContext) {

    }

    @Override
    public void messages(String roomId, String from, String to, String dir, Integer limit, String filter, UriInfo uriInfo,
                         HttpHeaders httpHeaders, AsyncResponse asyncResponse, SecurityContext securityContext) {

    }

    @Override
    public void sendEventWithTypeAndState(String roomId, String eventType, String stateKey, EventContent event, UriInfo uriInfo,
                                          HttpHeaders httpHeaders, AsyncResponse asyncResponse, SecurityContext securityContext) {

    }

    @Override
    public void sendEventWithType(String roomId, String eventType, EventContent event, UriInfo uriInfo, HttpHeaders httpHeaders,
                                  AsyncResponse asyncResponse, SecurityContext securityContext) {

    }

    @PUT
    @Path("/{roomId}/send/{eventType}/{txnId}")
    @RolesAllowed( {"USER"})
    @Override
    public void sendEvent(
        @PathParam("roomId") String roomId,
        @PathParam("eventType") String eventType,
        @PathParam("txnId") String txnId,
        EventContent event,
        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse,
        @Context SecurityContext securityContext
    ) {
        eventBus.publish(
            "sendEvent",
            new MessageEvent(
                asyncResponse,
                event,
                roomId,
                eventType,
                txnId,
                securityContext.getUserPrincipal().getName()
            )
        );
    }

    @Override
    public void redact(String roomId, String eventId, String txnId, RedactRequest event, UriInfo uriInfo, HttpHeaders httpHeaders,
                       AsyncResponse asyncResponse, SecurityContext securityContext) {

    }
}
