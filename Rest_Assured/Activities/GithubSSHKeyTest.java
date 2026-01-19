package Project;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GitHubSSHKeyTest {

    // Request specification
    private RequestSpecification requestSpec;

    // SSH key and key id variables
    private String sshKey = "ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAAAg....";
    private int keyId;

    @BeforeClass
    public void setup() {

        requestSpec = new RequestSpecBuilder()
                .setBaseUri("https://api.github.com")
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", "token YOUR_GITHUB_ACCESS_TOKEN")
                .build();
    }

    @Test(priority = 1)
    public void addSSHKey() {

        String requestBody = "{\n" +
                "  \"title\": \"TestAPIKey\",\n" +
                "  \"key\": \"" + sshKey + "\"\n" +
                "}";

        Response response =
                given()
                        .spec(requestSpec)
                        .body(requestBody)
                .when()
                        .post("/user/keys")
                .then()
                        .extract()
                        .response();

        // Extract key id
        keyId = response.jsonPath().getInt("id");

        Reporter.log("POST Response: " + response.asString(), true);

        // Assertions
        assertThat(response.getStatusCode(), equalTo(201));
        assertThat(keyId, notNullValue());
    }

    @Test(priority = 2)
    public void getSSHKey() {

        Response response =
                given()
                        .spec(requestSpec)
                        .pathParam("keyId", keyId)
                .when()
                        .get("/user/keys/{keyId}")
                .then()
                        .extract()
                        .response();

        Reporter.log("GET Response: " + response.asString(), true);

        // Assertions
        assertThat(response.getStatusCode(), equalTo(200));
        assertThat(response.jsonPath().getString("title"), equalTo("TestAPIKey"));
    }

    @Test(priority = 3)
    public void deleteSSHKey() {

        Response response =
                given()
                        .spec(requestSpec)
                        .pathParam("keyId", keyId)
                .when()
                        .delete("/user/keys/{keyId}")
                .then()
                        .extract()
                        .response();

        Reporter.log("DELETE Response Status: " + response.getStatusCode(), true);

        // Assertions
        assertThat(response.getStatusCode(), equalTo(204));
    }
}