package RestAssured_Practive_Test;
import org.testng.Assert;
import org.testng.annotations.Test;

import data.Paylod;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {
	@Test
	public void JsonParse() 
	
	{
		//Print No of courses returned by API
		//Print Purchase Amount
		//Print Title of the first course
		//Print All course titles and their respective Prices
		//Print no of copies sold by RPA Course
		//test
		//Verify if Sum of all Course prices matches with Purchase Amount
		int total = 0;
		JsonPath js = new JsonPath(Paylod.CoursePrice());
		System.out.println("Print No of courses returned by API");
		
		int count = js.getInt("courses.size()");
		System.out.println("No of courses are: "+count);
		
		System.out.println("Print Purchase Amount");
		
		int amount = js.getInt("dashboard.purchaseAmount");
		System.out.println("Purchase Amount is: "+amount);
		
		System.out.println("Print Title of the first course");
		String firstCourse = js.getString("courses[0].title");
		System.out.println("Title of the first course is: "+firstCourse);
		
		System.out.println("Print All course titles and their respective Prices");
		for (int i=0;i<count;i++) {
			String title = js.get("courses["+i+"].title");
			int price=js.getInt("courses["+i+"].price");
			System.out.println(title);
			System.out.println(price);			
		}
		
		System.out.println("Print no of copies sold by RPA Course");
		for (int i=0;i<count;i++) 
		{
			String title = js.get("courses["+i+"].title");
			if (title.equalsIgnoreCase("RPA")) 
			{
				int copies = js.get("courses["+i+"].copies");
				System.out.println(copies);
				break;
				
			}
		
	    }
		
		System.out.println("Verify if Sum of all Course prices matches with Purchase Amount");
		int sum=0;
		
		for (int i=0;i<count;i++) 
		{
			int copies = js.get("courses["+i+"].copies");
			int price=js.getInt("courses["+i+"].price");
			String title = js.get("courses["+i+"].title");
			total = copies * price;
			System.out.println("Total of course titled " +title +"is: " +total  );
			
			sum=sum+total;
			
		}
		System.out.println("Total sum of all the courses is: "+sum);
		int purchaseAmount = js.getInt("dashboard.purchaseAmount");
		Assert.assertEquals(sum, purchaseAmount);
		

	}
}
