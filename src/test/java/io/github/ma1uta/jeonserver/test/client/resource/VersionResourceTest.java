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

package io.github.ma1uta.jeonserver.test.client.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItem;

import io.github.ma1uta.jeonserver.test.PostgreSQLTestResource;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.parsing.Parser;
import org.junit.jupiter.api.Test;

@QuarkusTest
@QuarkusTestResource(PostgreSQLTestResource.class)
public class VersionResourceTest {

    @Test
    public void versionTest() {
        given().when().get("/_matrix/client/versions").then().statusCode(200).defaultParser(Parser.JSON)
            .body("versions", hasItem("r0.5.0"));
    }
}
