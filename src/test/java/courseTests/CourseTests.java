package courseTests;

import commons.Paths;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import requestBuilder.CourseRequestBuilder;

public class CourseTests {

    @Test
    public void getCoursesTest() {
        Response response = CourseRequestBuilder.getCourses();

        response.then()
                .log().all()
                .statusCode(200);

        Assert.assertFalse(response.asString().isEmpty(), "Response body should not be empty");
    }

    // Negative test
    @Test
    public void getCoursesInvalidCategory() {
        Response response = given()
                .baseUri(Paths.BASE_URL)
                .log().all()
                .queryParam("level", "Beginner")
                .queryParam("category", "invalid")
                .when()
                .get(Paths.GET_COURSES);

        response.then().log().all();

        int status = response.getStatusCode();


        Assert.assertTrue(
                status == 400 || status == 200,
                "Expected 400 or 200 but got: " + status
        );


        if (status == 200) {
            String body = response.asString();
            Assert.assertTrue(
                    body.contains("[]") || body.contains("\"data\":[]") || !body.contains("automation"),
                    "Invalid category should return no matching results"
            );
        }
    }
}