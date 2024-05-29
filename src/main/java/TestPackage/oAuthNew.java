package TestPackage;

import org.testng.Assert;
import org.testng.annotations.Test;

import POJO.Api;
import POJO.GetCourseDetails;
import POJO.WebAutomation;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
	
	//Using the same access token we are getting the data and storing it in string.
	String response2 = given()
	.queryParam("access_token", accessToken)
	.when()
	.get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").asString();
	System.out.println("Response is: "+response2);	
	
	//Using POJO Classes
	//Now we don't want to convert the response into string instead we want to convert it into Java class for deserialization.
	//for that we need a supportive java class that is GetCourseDetails.java.
	//Explanation: When we get a JSON response from above get request, we are converting it into java object by taking the help of GetCourseDetails class.
	//So all the json data going to pojo class and while serialization, it will see the values of the variables.
	//For example: value of instructor from json response gets stored in the instructor variable.
	//we will use the getter method for instructor to get the value as the method itself is returing the instructor.
	
	GetCourseDetails gc = given()
			.queryParam("access_token", accessToken)
			.when()
			.get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").as(GetCourseDetails.class);
	
	System.out.println(gc.getInstructor());
	//Let's say we want to get price of Soap WebService testing course that is in course pojo class.
	
	//when we say gc.getCourses() which means the control goes back to courses.java class as it return object of courses class.
	//now we need to goto to api courses section that gives us the list of courses under API.
	//getApi() return the object of Api class which means we can access any method of Api class ut it has list of array.
	//now we have to explain which object of API class we want to access.
	
	//the logic should be like we can scan each array element in api and whereever we find soap webservice, we will get the price for the same.
	List<Api> apiCourses= gc.getCourses().getApi();
	for(int i=0;i<apiCourses.size();i++) 
	{	
		String courseName="SoapUI Webservices testing";
		if(apiCourses.get(i).getCourseTitle().equalsIgnoreCase(courseName)) {
		System.out.println("Price of "+courseName+" is: "+apiCourses.get(i).getPrice());
		}
	}
	
	//we want to print the title of all the courses present in WebAutomation and compare if correct values are showing up or not.
	//created an array of actual values.
	String[] WebAutomationActualCoursesName = {"Selenium Webdriver Java", "Cypress", "Protractor"};
	
	//Creating an arraylist to store the values we receive from the response as the size is not fixed.
	ArrayList<String> WebAutomationExpectedCoursesName = new ArrayList<String>();
	
	List<WebAutomation> WebAutmationCourses = gc.getCourses().getWebAutomation();
	
	for (int j=0;j<WebAutmationCourses.size();j++) {
		//storing the values in a different array.
		WebAutomationExpectedCoursesName.add(WebAutmationCourses.get(j).getCourseTitle());
	}
	//Converting actual array to arraylist so the comparison become easy between 2 arraylists.
	List<String> actualCourseName = Arrays.asList(WebAutomationActualCoursesName);
	Assert.assertTrue(actualCourseName.equals(WebAutomationExpectedCoursesName));
	
	
	
			
	
	}
	
}
