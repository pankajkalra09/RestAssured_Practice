package LibraryTest;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import data.Paylod;
import data.Reusable;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;

public class DynamicJson {
	
	@Test(dataProvider="BookData")
	public void addBook(String isbn, String aisle) 
	{
		RestAssured.baseURI = "http://216.10.245.166";
		
		//Dyanamically build json paylod with external data inputs in method addbookmethod
		//instead of hard coded vale in body, we are passing dynamic values using addbookmethod directly.
		String addbookresponse = given().header("Content-Type", "application/json").body(Paylod.addbookmethod(isbn, aisle))
		.when().post("Library/Addbook.php").
		then().assertThat().statusCode(200).extract().response().asString();
		//System.out.println("Response is: "+addbookresponse);
		
		JsonPath js = Reusable.rawToJson(addbookresponse);
		String bookID = js.get("ID");
		
		System.out.println("Book ID is: "+bookID);
	}
	
	//Parameterize the API test with multiple data sets using Data Provider
	
	@DataProvider(name="BookData")
	public Object[][] getdata() 
	{
		//array - collection of elements
		//multidimensional array = collection of array
		
		return new Object[][] 
				{
			{"bookone", "123"}, 
			{"booktwo", "234"}, 
			{"bookthree", "345"}
			};
	}

}
