package basicTests;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserRegistrationTests {

    String baseURL = "https://ndosiautomation.co.za/APIDEV";
    String registeredUserId;
    public static String token;

    static Faker faker = new Faker();
    static String email;
    static String password = "@1234567";

    @BeforeClass
    public static void setUp() {
        email = "Group2" + faker.internet().emailAddress();
    }

    @Test(priority = 1)
    public void userRegistrationTest() {

        String userRegistrationPayload = "{\n" +
                "  \"firstName\": \"Leano\",\n" +
                "  \"lastName\": \"Zulu\",\n" +
                "  \"email\": \"" + email + "\",\n" +
                "  \"password\": \"" + password + "\",\n" +
                "  \"confirmPassword\": \"" + password + "\",\n" +
                "  \"groupId\": \"b0664443-c8a4-4a36-a87c-6ac33c6058bc\"\n" +
                "}";

        Response response = RestAssured.given()
                .baseUri(baseURL)
                .basePath("/register")
                .header("Content-Type", "application/json")
                .body(userRegistrationPayload)
                .log().all()
                .post()
                .then().extract().response();

        int responseStatusCode = response.getStatusCode();
        System.out.println("Status Code: " + responseStatusCode);
        System.out.println("Response Body: " + response.getBody().asString());
        Assert.assertEquals(responseStatusCode, 201, "Expected status code 201");

        registeredUserId = response.jsonPath().getString("data.id");
    }

    @Test(priority = 2)
    public void adminLoginTest() {

        String adminLoginPayload = "{\n" +
                "  \"email\": \"admin@gmail.com\",\n" +
                "  \"password\": \"@12345678\"\n" +
                "}";

        Response response = RestAssured.given()
                .baseUri(baseURL)
                .basePath("/login")
                .header("Content-Type", "application/json")
                .body(adminLoginPayload)
                .log().all()
                .post()
                .then().extract().response();

        int responseStatusCode = response.getStatusCode();
        System.out.println("Status Code: " + responseStatusCode);
        System.out.println("Response Body: " + response.getBody().asString());
        Assert.assertEquals(responseStatusCode, 200, "Expected status code 200");

        token = response.jsonPath().getString("data.token");
    }

    @Test(priority = 3)
    public void approveUser() {

        Response response = RestAssured.given()
                .baseUri(baseURL)
                .basePath("/admin/users/" + registeredUserId + "/approve")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .log().all()
                .put()
                .then().extract().response();

        System.out.println("Response Body: " + response.getBody().asString());
        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200");
    }
}



