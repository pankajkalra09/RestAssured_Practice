package TestPackage;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import files.Paylod;
import files.Reusable;


public class Basics {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Validate if Add prospect is working fine.
		
		//given = all input details
		//when = submit the api - Respurce and HTTP method will go here
		//then = validate the response
		
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(Paylod.addPlace()).when().post("/maps/api/place/add/json").then().assertThat().statusCode(200).extract().response().asString();
		
		System.out.println(response);
		
		//now we have responce in string and if we want to extract 1 value from the response for further use, we have to parse the response.
		//For that we will be using JsonPath class that has many method through which we can extract the values. 
		//JsonPath class takes String as a parameter and we have string here as response.
		
		JsonPath js = new JsonPath(response);
		String placeID = js.get("place_id");
		System.out.println("Place ID is: " +placeID);
		
		//placeID is string extracted from response that we will be used further
		
		//Update Place
		
		String newaddress = "73 winter walk, USA";
		
		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body("{\r\n"
				+ "\"place_id\":\""+placeID+"\",\r\n"
				+ "\"address\":\""+newaddress+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}").when().put("/maps/api/place/update/json")
		.then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));
			
		// Get Place
		
		String getPlaceResponse = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeID)
		.when().get("/maps/api/place/get/json")
		.then().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js1=Reusable.rawToJson(getPlaceResponse);
		String actualResult = js1.getString("address");
		System.out.println("Actual Result is: "+actualResult);
		
		
		
	}
	
	// pass static json file as a body instead of the string.
	// We have to convert the content of the file to string as body method takes string as input.
	//for that content of the file convert in bytes  >  Bytes data to string.
	//so in body method we have static packege called Files and in that package we have a method called readAllBytes and we have to pass the path of the json file.

	
	
}
