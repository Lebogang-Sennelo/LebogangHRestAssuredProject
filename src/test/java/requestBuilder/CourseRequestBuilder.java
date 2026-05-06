package requestBuilder;

import commons.Paths;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CourseRequestBuilder {

    public static Response getCourses() {
        return given()
                .baseUri(Paths.BASE_URL)
                .queryParam("level", "Beginner")
                .queryParam("category", "automation")
                .log().all()
                .when()
                .get(Paths.GET_COURSES);
    }
}