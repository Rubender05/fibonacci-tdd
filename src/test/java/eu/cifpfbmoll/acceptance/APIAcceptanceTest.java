package eu.cifpfbmoll.acceptance;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@DisplayName("API Acceptance Tests")
public class APIAcceptanceTest {

    @LocalServerPort
    private int port;

    @BeforeAll
    static void setup() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    @DisplayName("Should be able to create and retrieve a user")
    void shouldCreateAndRetrieveUser() {
        // Create user
        Long userId = given()
                .baseUri("http://localhost:" + port)
                .contentType(ContentType.JSON)
                .body("{\"name\": \"AcceptanceTest User\", \"email\": \"acceptance@test.com\"}")
                .when()
                .post("/api/users")
                .then()
                .statusCode(201)
                .extract()
                .path("id");

        // Retrieve user
        given()
                .baseUri("http://localhost:" + port)
                .when()
                .get("/api/users/" + userId)
                .then()
                .statusCode(200)
                .body("name", equalTo("AcceptanceTest User"))
                .body("email", equalTo("acceptance@test.com"));
    }

    @Test
    @DisplayName("Should calculate Fibonacci and store result")
    void shouldCalculateFibonacciAndStore() {
        given()
                .baseUri("http://localhost:" + port)
                .when()
                .get("/api/fibonacci/calculate/10")
                .then()
                .statusCode(200)
                .body("n", equalTo(10))
                .body("result", equalTo(55))
                .body("recordId", notNullValue());
    }

    @Test
    @DisplayName("Should manage categories")
    void shouldManageCategories() {
        // Create category
        Long categoryId = given()
                .baseUri("http://localhost:" + port)
                .contentType(ContentType.JSON)
                .body("{\"categoryName\": \"Science\"}")
                .when()
                .post("/api/categories")
                .then()
                .statusCode(201)
                .extract()
                .path("id");

        // Get all categories
        given()
                .baseUri("http://localhost:" + port)
                .when()
                .get("/api/categories")
                .then()
                .statusCode(200)
                .body("$", hasSize(greaterThan(0)))
                .body("categoryName", hasItem("Science"));
    }

    @Test
    @DisplayName("Should handle API errors gracefully")
    void shouldHandleErrors() {
        // Try to create user with invalid data
        given()
                .baseUri("http://localhost:" + port)
                .contentType(ContentType.JSON)
                .body("{\"name\": \"\", \"email\": \"test@test.com\"}")
                .when()
                .post("/api/users")
                .then()
                .statusCode(400);

        // Try to get non-existent user
        given()
                .baseUri("http://localhost:" + port)
                .when()
                .get("/api/users/99999")
                .then()
                .statusCode(404);
    }

    @Test
    @DisplayName("Should list all Fibonacci records")
    void shouldListFibonacciRecords() {
        // Calculate a few Fibonacci values
        given().baseUri("http://localhost:" + port)
                .when().get("/api/fibonacci/calculate/3").then().statusCode(200);
        given().baseUri("http://localhost:" + port)
                .when().get("/api/fibonacci/calculate/4").then().statusCode(200);

        // List all records
        given()
                .baseUri("http://localhost:" + port)
                .when()
                .get("/api/fibonacci/records")
                .then()
                .statusCode(200)
                .body("$", hasSize(greaterThan(0)));
    }
}
