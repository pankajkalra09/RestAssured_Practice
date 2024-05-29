package POJO;
import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import POJO.Serialization.AddPlace;
import POJO.Serialization.Location;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class SerializationExample {

	//we are using serialization concept where we are creating POJO classes to build the body and pass it to the test case.
	//Example of body we are using here is as under:
//	{
//		  "location": {
//		    "lat": -38.383494,
//		    "lng": 33.427362
//		  },
//		  "accuracy": 50,
//		  "name": "Frontline house",
//		  "phone_number": "(+91) 983 893 3937",
//		  "address": "29, side layout, cohen 09",
//		  "types": [
//		    "shoe park",
//		    "shop"
//		  ],
//		  "website": "http://google.com",
//		  "language": "French-IN"
//		}

	//We have created POJO classes AddPlace and Location. Please refer those first.
	
	@Test
	public void serialization() {
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		AddPlace ap = new AddPlace();
		ap.setAccuracy(50);
		ap.setAddress("29, side layout, cohen 09");
		ap.setLanguage("French-IN");
		ap.setName("Frontline house");
		ap.setPhone_number("(+91) 983 893 3937");
		ap.setWebsite("http://google.com");
		//setType is expecting list of string, so we will create list here first
		List<String> myList=new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		ap.setTypes(myList);
		//setLocation is expecting Location class object. so we will be creating object for the same first.
		Location loc = new Location();
		loc.setLat(-38.383494);
		loc.setLng(33.427362);
		ap.setLocation(loc);
		
		// we will pass the body using POJO classes.
		String Res = given().queryParam("key", "qaclick123").body(ap).when()
		.post("/maps/api/place/add/json").then().assertThat().statusCode(200).extract().response().asString();
		System.out.println(Res);
		
		
	}
}
