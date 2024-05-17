package TestPackage;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;

public class oAuthNew {
	@Test
	//Generate access token using post method.
	public void getaccesstoken() {
		String response = given()
		.formParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.formParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
		.formParam("grant_type", "client_credentials")
		.formParam("scope", "trust")
		.when().post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token").asString();
		System.out.println(response);
		JsonPath jsonPath = new JsonPath(response);
	String accessToken = jsonPath.getString("access_token");
	//Using the same access token we are getting the data.
	String response2 = given()
	.queryParam("access_token", accessToken)
	.when()
	.get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").asString();
	System.out.println("Response is: "+response2);	
	}
}
