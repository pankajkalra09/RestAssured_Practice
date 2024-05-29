package OnBoardingRestAssured;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class ScheduleDemo {

	@Test
	public void scheduleDemoTest() {
		//First thing is to set baseurl so that we can make use of specbuilder. We will start with creating a requestspecbuilder object.
		//We are going to login first so that we can generate the token that we can use later on.
		
		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://onboarding.qa.unifyedx.com:3001/")
				.setContentType(ContentType.JSON).build();
		// now req object holds the information of baseuri and whatever data we pass that should be in json format.
		// we will use pojo class to pass the body. for that we will be creating a pojo class "LoginPojoClass".
		//now we will create the object of pojo class the use it to pass the body information.
		LoginPojoClass loginpojoobject = new LoginPojoClass();
		loginpojoobject.setUserId("pankaj_kalra@unifyed.com");
		loginpojoobject.setotp(321713);
		//LoginResponse loginResponseObject = new LoginResponse();
		
		//let's create a subobject for the below statement
		
		RequestSpecification reqLogin = given().log().all().spec(req).body(loginpojoobject);
		//to get the response as string we will siply extract the response as string and user jsonpath class we can extract the accerss token.
		//but here we will be using the deserilization concept and create a new pojo class to get the access token.
		
		LoginResponse loginResponse = reqLogin.when().log().all().post("api/auth/prospect/login").then().log().all().extract().response().as(LoginResponse.class);
		System.out.println(loginResponse);
		System.out.println(loginResponse.getAccess_token());
		System.out.println(loginResponse.getUser().getFirstName());
	
		//System.out.println(loginResponseObject.getAccess_token());
	}
}
