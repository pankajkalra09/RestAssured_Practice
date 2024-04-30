package TestPackage;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.Paylod;
import files.Reusable;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;
public class DynamicJson {
	
	
	
	@Test(dataProvider = "BookData", dataProviderClass = Reusable.class, priority=1)
	public void addBook(String isbn, String aisle) 
	{
		RestAssured.baseURI = "http://216.10.245.166";
		//Dyanamically build json paylod with external data inputs in method addbookmethod
		//instead of hard coded vale in body, we are passing dynamic values using addbookmethod directly.
		String addbookresponse = given().header("Content-Type", "application/json").body(Paylod.addbookmethod(isbn, aisle))
		.when().post("Library/Addbook.php").
		then().assertThat().statusCode(200).extract().response().asString();
		JsonPath js = Reusable.rawToJson(addbookresponse);
		String bookID = js.get("ID");
		System.out.println("Book ID is: "+bookID);
		//return bookID;
	}
	
	@Test(dataProvider = "BookData", dataProviderClass = Reusable.class, priority=2)
	public void getbookID(String isbn, String aisle) {
		RestAssured.baseURI = "http://216.10.245.166";
		String bookIDCreated = given().queryParam("ID", ""+isbn+aisle+"" ).when().get("/Library/GetBook.php")
		.then().assertThat().statusCode(200).extract().response().asString();
		JsonPath js = Reusable.rawToJson(bookIDCreated);
		String bookName = js.getString("book_name");
		System.out.println("Book name is: "+bookName);
		String bookisbn = js.getString("isbn");
		System.out.println("Book isbn is: "+bookisbn);
		String bookaisle = js.getString("aisle");
		System.out.println("Book aisle is: "+bookaisle);
		String bookauthor = js.getString("author");
		System.out.println("Book aisle is: "+bookauthor);
	}
	
	@Test(dataProvider = "BookData", dataProviderClass = Reusable.class, priority=3)
	public void deleteBook(String isbn, String aisle) {
		RestAssured.baseURI = "http://216.10.245.166";
	String deleteResponse = 	given().header("Content-Type", "application/json").body("{\r\n"
			+ "\"ID\": \""+isbn+aisle+"\" \r\n"
			+ "} \r\n"
			+ "").when()
		.post("Library/DeleteBook.php").then().assertThat().statusCode(200).extract().response().asString();
	JsonPath js1 = Reusable.rawToJson(deleteResponse);
	//System.out.println(js1.getString());
		String message = js1.getString("msg");
		System.out.println(message);
		
	}

}
