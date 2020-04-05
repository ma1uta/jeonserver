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

import io.github.ma1uta.jeonserver.client.model.version.VersionResponseWrapper;
import io.github.ma1uta.matrix.client.api.VersionApi;

import java.util.Collections;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * {@inheritDoc}
 */
@ApplicationScoped
@Path("/_matrix/client/versions")
@Produces(MediaType.APPLICATION_JSON)
public class VersionResource implements VersionApi {

    private static final Response RESPONSE = Response.ok(new VersionResponseWrapper(Collections.singletonList("r0.5.0"))).build();

    @GET
    @Path("/")
    @Override
    public void versions(
        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse
    ) {
        asyncResponse.resume(RESPONSE);
    }
}
