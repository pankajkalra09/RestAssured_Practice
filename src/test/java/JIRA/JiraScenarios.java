package JIRA;
import static io.restassured.RestAssured.*;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;

public class JiraScenarios {
	
	//RestAssured.baseURI="http://localhost:8080/";
	SessionFilter session = new SessionFilter();
	
	@Test
	public void createSession() {
		
		RestAssured.baseURI="http://localhost:8080/";
		String response = given().header("Content-Type","application/json").log().all().body("{ \"username\": \"philip\", \"password\": \"parker\" }").filter(session)
		.when().post("rest/auth/1/session").then().extract().response().asString();
//		JsonPath js = new JsonPath(response);
//		String key = js.get("session.value");
		//System.out.println(session);
		String issuecreate = given().header("Content-Type","application/json").log().all().body("{\r\n"
				+ "    \"fields\": {\r\n"
				+ "        \"project\": {\r\n"
				+ "            \"key\": \"TEST\"\r\n"
				+ "        },\r\n"
				+ "        \"summary\": \"Bug created from RestAssured to check comment new\",\r\n"
				+ "         \"issuetype\": {\r\n"
				+ "            \"name\": \"Bug\"\r\n"
				+ "        },\r\n"
				+ "        \"assignee\": {\r\n"
				+ "            \"name\": \"philip\"\r\n"
				+ "        },\r\n"
				+ "        \"reporter\": {\r\n"
				+ "            \"name\": \"philip\"\r\n"
				+ "        },\r\n"
				+ "        \"description\": \"Sample defect description created via RestAssured to check comment new.\"\r\n"
				+ "    }\r\n"
				+ "}").filter(session).when().post("rest/api/2/issue").then()
		.extract().response().asString();
		JsonPath js = new JsonPath(issuecreate);
		String issueID = js.get("id");
		
		
		given().header("Content-Type","application/json").pathParam("key", issueID).log().all().body("{\r\n"
				+ "    \"body\": \"This is the comment made from RestAssured Code.\",\r\n"
				+ "    \"visibility\": {\r\n"
				+ "        \"type\": \"role\",\r\n"
				+ "        \"value\": \"Administrators\"\r\n"
				+ "    }\r\n"
				+ "}").filter(session).when().post("rest/api/2/issue/{key}/comment").then().assertThat().statusCode(201);
		
		// Add Attachment
		//we have a curl command to do so that JIRA has provided. 
		//curl -D- -u admin:admin -X POST -H "X-Atlassian-Token: no-check" -F "file=@myfile.txt" http://myhost/rest/api/2/issue/TEST-123/attachments
//here D stands for different parameters, admin:admin is username, password, then X stands for which http method we are using, here we are using
		//POST, H stands for Header, w
		
		given().header("X-Atlassian-Token", "no-check").pathParam("key", issueID).filter(session).when().post("rest/api/2/issue/{key}/attachments")
		
	}

}
