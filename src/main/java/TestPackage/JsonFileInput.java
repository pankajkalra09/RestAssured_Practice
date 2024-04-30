package TestPackage;


import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.Test;

import files.Reusable;

public class JsonFileInput {

	static String myPlaceid = null;

	@Test (priority=1)
	public void CreatePlaceID() throws IOException {
		// TODO Auto-generated method stub
		// Validate if Add prospect is working fine.

		// given = all input details
		// when = submit the api - Respurce and HTTP method will go here
		// then = validate the response

		// pass static json file as a body instead of the string.
		// body method accepts string. We have json in a file so our goal is to convert
		// the content of the file to string. To do that there is a way in java where content is converted into Byte first which is a datatype in java.
		// Once we have byte data than we can convert byte data into string.
		// Let's do IT

		// In java there is a static package called Files and has a method called
		// readAllBytes which will read all the content of the path we passed
		// where json file is present and converts the content into bytes.

		// to convert byte into string we need to create a string object using new String and parameterized the byte stuff.

		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body(new String(Files.readAllBytes(
						Paths.get(System.getProperty("user.dir") + "//src//main/resources//InputData//AddPlace.json"))))
				.when().post("/maps/api/place/add/json").then().assertThat().statusCode(200).extract().response()
				.asString();

		System.out.println(response);

		// now we have responce in string and if we want to extract 1 value from the
		// response for further use, we have to parse the response.
		// For that we will be using JsonPath class that has many method through which
		// we can extract the values.
		// JsonPath class takes String as a parameter and we have string here as
		// response.

		JsonPath js = new JsonPath(response);
		String placeID = js.get("place_id");
		System.out.println("Place ID is: " + placeID);
		myPlaceid = placeID;
		// return placeID;
		System.out.printf("myPlaceid {}", myPlaceid);

	}

	@Test(priority=2)
	public void updatePlaceID() {
		String newaddress = "73 winter walk, USA";
		// placeID = CreatePlaceID();
		System.out.println("placeID from CreatePlaceID() " + myPlaceid);

		given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body("{\r\n" + "\"place_id\":\"" + myPlaceid + "\",\r\n" + "\"address\":\"" + newaddress + "\",\r\n"
						+ "\"key\":\"qaclick123\"\r\n" + "}")
				.when().put("/maps/api/place/update/json").then().assertThat().statusCode(200);
	}

	// Get Place

	@Test(priority=3)
	public void getPlaceID() {
		// getplaceID = CreatePlaceID();
		System.out.println("placeID from CreatePlaceID() " + myPlaceid);

		String getPlaceResponse = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", myPlaceid)
				.when().get("/maps/api/place/get/json").then().assertThat().statusCode(200).extract().response()
				.asString();

		JsonPath js1 = Reusable.rawToJson(getPlaceResponse);
		String actualResult = js1.getString("address");
		System.out.println("Actual Result is: " + actualResult);

	}

}
