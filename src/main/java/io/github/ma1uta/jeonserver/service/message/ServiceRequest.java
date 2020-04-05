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

import javax.validation.constraints.NotNull;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

public class ServiceRequest<T> {

    @NotNull
    private final T request;
    private final SecurityContext securityContext;
    private final UriInfo uriInfo;
    private final HttpHeaders httpHeaders;

    public ServiceRequest(T request, SecurityContext securityContext, UriInfo uriInfo, HttpHeaders httpHeaders) {
        this.request = request;
        this.securityContext = securityContext;
        this.uriInfo = uriInfo;
        this.httpHeaders = httpHeaders;
    }

    public T getRequest() {
        return request;
    }

    public SecurityContext getSecurityContext() {
        return securityContext;
    }

    public UriInfo getUriInfo() {
        return uriInfo;
    }

    public HttpHeaders getHttpHeaders() {
        return httpHeaders;
    }
}
