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

import io.github.ma1uta.matrix.client.api.RoomApi;
import io.github.ma1uta.matrix.client.model.room.CreateRoomRequest;
import io.github.ma1uta.matrix.client.model.room.InviteRequest;
import io.github.ma1uta.matrix.client.model.room.JoinRequest;
import io.github.ma1uta.matrix.client.model.room.KickRequest;
import io.github.ma1uta.matrix.client.model.room.NewVersion;
import io.github.ma1uta.matrix.client.model.room.PublicRoomsRequest;
import io.github.ma1uta.matrix.client.model.room.RoomId;
import io.github.ma1uta.matrix.client.model.room.RoomVisibility;
import io.github.ma1uta.matrix.client.model.room.UnbanRequest;
import org.slf4j.Logger;

import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

/**
 * {@inheritDoc}
 */
@ApplicationScoped
@Path("/_matrix/client/r0")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RoomResource implements RoomApi {

    @Inject
    Logger logger;

    @POST
    @Path("/createRoom")
    @Override
    public void create(
        CreateRoomRequest createRoomRequest,
        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse,
        @Context SecurityContext securityContext
    ) {
    }

    @Override
    public void createAlias(
        String roomAlias,
        RoomId roomId,
        UriInfo uriInfo,
        HttpHeaders httpHeaders,
        AsyncResponse asyncResponse,
        SecurityContext securityContext
    ) {

    }

    @Override
    public void resolveAlias(String roomAlias, UriInfo uriInfo, HttpHeaders httpHeaders, AsyncResponse asyncResponse) {

    }

    @Override
    public void deleteAlias(String roomAlias, UriInfo uriInfo, HttpHeaders httpHeaders, AsyncResponse asyncResponse,
                            SecurityContext securityContext) {

    }

    @Override
    public void joinedRooms(UriInfo uriInfo, HttpHeaders httpHeaders, AsyncResponse asyncResponse, SecurityContext securityContext) {

    }

    @Override
    public void invite(String roomId, InviteRequest inviteRequest, UriInfo uriInfo, HttpHeaders httpHeaders, AsyncResponse asyncResponse,
                       SecurityContext securityContext) {

    }

    @Override
    public void joinById(String roomId, JoinRequest joinRequest, UriInfo uriInfo, HttpHeaders httpHeaders, AsyncResponse asyncResponse,
                         SecurityContext securityContext) {

    }

    @Override
    public void joinByIdOrAlias(String roomIdOrAlias, List<String> serverName, JoinRequest joinRequest, UriInfo uriInfo,
                                HttpHeaders httpHeaders, AsyncResponse asyncResponse, SecurityContext securityContext) {

    }

    @Override
    public void leave(String roomId, UriInfo uriInfo, HttpHeaders httpHeaders, AsyncResponse asyncResponse,
                      SecurityContext securityContext) {

    }

    @Override
    public void forget(String roomId, UriInfo uriInfo, HttpHeaders httpHeaders, AsyncResponse asyncResponse,
                       SecurityContext securityContext) {

    }

    @Override
    public void kick(String roomId, KickRequest kickRequest, UriInfo uriInfo, HttpHeaders httpHeaders, AsyncResponse asyncResponse,
                     SecurityContext securityContext) {

    }

    @Override
    public void ban(String roomId, KickRequest banRequest, UriInfo uriInfo, HttpHeaders httpHeaders, AsyncResponse asyncResponse,
                    SecurityContext securityContext) {

    }

    @Override
    public void unban(String roomId, UnbanRequest unbanRequest, UriInfo uriInfo, HttpHeaders httpHeaders, AsyncResponse asyncResponse,
                      SecurityContext securityContext) {

    }

    @Override
    public void getVisibility(String roomId, UriInfo uriInfo, HttpHeaders httpHeaders, AsyncResponse asyncResponse) {

    }

    @Override
    public void setVisibility(String roomId, RoomVisibility visibility, UriInfo uriInfo, HttpHeaders httpHeaders,
                              AsyncResponse asyncResponse, SecurityContext securityContext) {

    }

    @Override
    public void showPublicRooms(Long limit, String since, String server, UriInfo uriInfo, HttpHeaders httpHeaders,
                                AsyncResponse asyncResponse) {

    }

    @Override
    public void searchPublicRooms(String server, PublicRoomsRequest publicRoomsRequest, UriInfo uriInfo, HttpHeaders httpHeaders,
                                  AsyncResponse asyncResponse, SecurityContext securityContext) {

    }

    @Override
    public void upgrade(String roomId, NewVersion newVersion, UriInfo uriInfo, HttpHeaders httpHeaders, AsyncResponse asyncResponse,
                        SecurityContext securityContext) {

    }
}
