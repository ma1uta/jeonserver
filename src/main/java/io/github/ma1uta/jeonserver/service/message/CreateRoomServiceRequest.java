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

package io.github.ma1uta.jeonserver.service.message;

import io.github.ma1uta.matrix.client.model.room.CreateRoomRequest;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

public class CreateRoomServiceRequest extends ServiceRequest<CreateRoomRequest> {

    public CreateRoomServiceRequest(CreateRoomRequest request, SecurityContext securityContext, UriInfo uriInfo, HttpHeaders httpHeaders) {
        super(request, securityContext, uriInfo, httpHeaders);
    }
}
