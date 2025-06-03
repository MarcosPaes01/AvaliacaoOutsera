import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ApiTestsTest {

    static final String BASE_URL = "https://jsonplaceholder.typicode.com"; 

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    // GET
    @Test
    @Order(1)
    public void testGetPostById() {
        given()
            .when()
                .get("/posts/1")
            .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .header("Content-Type", containsString("application/json"))
                .body("id", equalTo(1))
                .body("userId", notNullValue());
    }

    // POST
    @Test
    @Order(2)
    public void testCreatePost() {
        String newPost = "{\"title\":\"foo\", \"body\":\"bar\", \"userId\":1}";

        given()
            .contentType(ContentType.JSON)
            .body(newPost)
        .when()
            .post("/posts")
        .then()
            .statusCode(201)
            .body("title", equalTo("foo"))
            .body("body", equalTo("bar"))
            .body("userId", equalTo(1));
    }

    // PUT
    @Test
    @Order(3)
    public void testUpdatePost() {
        String updatePost = "{\"id\":1, \"title\":\"updated\", \"body\":\"content\", \"userId\":1}";
 
        given()
            .contentType(ContentType.JSON)
            .body(updatePost)
        .when()
            .put("/posts/1")
        .then()
            .statusCode(200)
            .body("title", equalTo("updated"))
            .body("body", equalTo("content"));  

    }

    // DELETE
    @Test
    @Order(4)
    public void testDeletePost() {
        when()
            .delete("/posts/1")
        .then()
            .statusCode(anyOf(is(200), is(204)));
    }
}
