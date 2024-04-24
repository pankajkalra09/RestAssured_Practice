package data;

import org.testng.annotations.DataProvider;

import io.restassured.path.json.JsonPath;

public class Reusable 
{

	public static JsonPath rawToJson(String response) 
	{
		
		JsonPath js1 = new JsonPath(response);
		return js1;
	}
	
	//Parameterize the API test with multiple data sets using Data Provider
	@DataProvider(name="BookData")
	public Object[][] getdata() 
	{
		//array - collection of elements. multidimensional array = collection of array
		return new Object[][] 
				{{"booanee", "12544"}, {"boodoso", "24455"}, {"booksree", "35464"}	};
	}
		
}
