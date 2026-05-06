package requestBuilder;

import commons.Paths;
import io.restassured.response.Response;
import payloadBuilder.TestimonialPayload;

import static io.restassured.RestAssured.given;

public class TestimonialRequestBuilder {

    public static Response createTestimonial(String token) {
        return given()
                .baseUri(Paths.BASE_URL)
                .header("Authorization", "Bearer " + token)
                .contentType("application/json") // 🔥 recommended
                .body(TestimonialPayload.createTestimonialPayload())
                .log().all()
                .when()
                .post(Paths.CREATE_TESTIMONIAL);
    }

    public static Response updateTestimonial(String token, String id) {
        return given()
                .baseUri(Paths.BASE_URL)
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .body(TestimonialPayload.updateTestimonialPayload())
                .pathParam("id", id)
                .log().all()
                .when()
                .put(Paths.UPDATE_TESTIMONIAL);
    }

    public static Response deleteTestimonial(String token, String id) {
        return given()
                .baseUri(Paths.BASE_URL)
                .header("Authorization", "Bearer " + token)
                .pathParam("id", id)
                .log().all()
                .when()
                .delete(Paths.DELETE_TESTIMONIAL);
    }
}