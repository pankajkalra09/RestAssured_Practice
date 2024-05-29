package POJO;

import java.util.List;

public class Courses {
	
//	"courses": {
//	    "webAutomation": [
//	      {
//	        "courseTitle": "Selenium Webdriver Java",
//	        "price": "50"
//	      },
//	      {
//	        "courseTitle": "Cypress",
//	        "price": "40"
//	      },
//	      {
//	        "courseTitle": "Protractor",
//	        "price": "40"
//	      }
//	    ],
//	    "api": [
//	      {
//	        "courseTitle": "Rest Assured Automation using Java",
//	        "price": "50"
//	      },
//	      {
//	        "courseTitle": "SoapUI Webservices testing",
//	        "price": "40"
//	      }
//	    ],
//	    "mobile": [
//	      {
//	        "courseTitle": "Appium-Mobile Automation using Java",
//	        "price": "50"
//	      }
//	    ]
//	  },
	
	//For courses its a nested json as there are 3 more mini jsons inside this and the nested json is an array.
		//For nested parent json courses which has 3 subjsons, we will create courses as a seperate POJO class.
	//In parent GetCourseDetails class we have to define the return tyope of courses is not string, but a class.
	private List<WebAutomation> webAutomation;//as we are having webautomation as an array with 3 elements so we mention List of indexes which means we are saying to expect a list of json.
	private List<Api> api;
	private List<Mobile> mobile;
	
	public List<WebAutomation> getWebAutomation() {
		return webAutomation;
	}
	public void setWebAutomation(List<WebAutomation> webAutomation) { 
		this.webAutomation = webAutomation;
	}
	public List<Api> getApi() {
		return api;
	}
	public void setApi(List<Api> api) {
		this.api = api;
	}
	public List<Mobile> getMobile() {
		return mobile;
	}
	public void setMobile(List<Mobile> mobile) {
		this.mobile = mobile;
	}
	
}
