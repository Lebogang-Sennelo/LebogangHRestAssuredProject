package testimonialTests;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import requestBuilder.TestimonialRequestBuilder;
import registrationTests.RegistrationTests;

public class TestimonialTests {

    static String token;
    static String testimonialId;

    @Test(priority = 1)
    public void createTestimonialTest() {
        token = RegistrationTests.token;

        Response response = TestimonialRequestBuilder.createTestimonial(token);
        response.then()
                .log().all()
                .statusCode(201);

        testimonialId = response.jsonPath().getString("data.Id");

        Assert.assertNotNull(testimonialId, "Testimonial ID should not be null");
        Assert.assertFalse(testimonialId.isEmpty(), "Testimonial ID should not be empty");
    }

    @Test(priority = 2)
    public void updateTestimonialTest() {
        Response response = TestimonialRequestBuilder.updateTestimonial(token, testimonialId);
        response.then()
                .log().all()
                .statusCode(200);
    }

    @Test(priority = 3)
    public void deleteTestimonialTest() {
        Response response = TestimonialRequestBuilder.deleteTestimonial(token, testimonialId);
        response.then()
                .log().all()
                .statusCode(200);
    }

    @Test(priority = 4)
    public void deleteInvalidTestimonialTest() {
        Response response = TestimonialRequestBuilder.deleteTestimonial(token, "99999");
        response.then()
                .log().all()
                .statusCode(200);

        Assert.assertEquals(response.jsonPath().getBoolean("success"), false, "Success should be false for invalid ID");
        Assert.assertEquals(response.jsonPath().getString("error_code"), "TESTIMONIAL_NOT_FOUND");
    }
}


