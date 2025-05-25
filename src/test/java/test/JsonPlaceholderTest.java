package test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class JsonPlaceholderTest {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    @Test
    public void createNewPost() {
        String requestBody = "{\n" +
                "  \"title\": \"Learn API Testing\",\n" +
                "  \"body\": \"Practicing API testing with JSONPlaceholder\",\n" +
                "  \"userId\": 101\n" +
                "}";

        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/posts");

        response.then().statusCode(201);

        // ✅ Verify response matches request
        response.then().body("title", equalTo("Learn API Testing"));
        response.then().body("body", equalTo("Practicing API testing with JSONPlaceholder"));
        response.then().body("userId", equalTo(101));

        // ✅ Validate JSON Schema
        response.then().body(matchesJsonSchemaInClasspath("post-schema.json"));
    }

    @Test
    public void retrievePostsAndVerifyIds() {
        Response response = RestAssured
                .given()
                .when()
                .get("/posts");

        response.then().statusCode(200);

        List<Map<String, Object>> posts = response.jsonPath().getList("");

        // ✅ Check each post has non-null ID
        for (Map<String, Object> post : posts) {
            assertThat(post.get("id"), is(notNullValue()));
        }

        // ✅ Validate JSON Schema for the entire array
        response.then().body(matchesJsonSchemaInClasspath("posts-array-schema.json"));
    }

    @Test
    public void deletePost() {
        Response response = RestAssured
                .given()
                .when()
                .delete("/posts/1");

        response.then().statusCode(200);

        // ✅ JSONPlaceholder returns empty object on delete
        assertThat(response.getBody().asString().trim(), equalTo("{}"));

        // ✅ Validate JSON Schema for the empty object
        response.then().body(matchesJsonSchemaInClasspath("empty-object-schema.json"));
    }

    @Test
    public void updatePost() {
        String requestBody = "{\n" +
                "  \"id\": 1,\n" +
                "  \"title\": \"Updated Post Title\",\n" +
                "  \"body\": \"This is the updated body content.\",\n" +
                "  \"userId\": 99\n" +
                "}";

        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .put("/posts/1");

        response.then().statusCode(200);

        response.then().body("title", equalTo("Updated Post Title"));
        response.then().body("body", equalTo("This is the updated body content."));
        response.then().body("userId", equalTo(99));

        // ✅ Validate JSON Schema
        response.then().body(matchesJsonSchemaInClasspath("post-schema.json"));
    }
}