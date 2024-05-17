package POJO;

public class GetCourseDetails {
	
	//We can converting the response received from oAuthNew.java class and using getters/setters we will parse it further. Response is as under:
//	{
//		  "instructor": "RahulShetty",
//		  "url": "rahulshettycademy.com",
//		  "services": "projectSupport",
//		  "expertise": "Automation",
//		  "courses": {
//		    "webAutomation": [
//		      {
//		        "courseTitle": "Selenium Webdriver Java",
//		        "price": "50"
//		      },
//		      {
//		        "courseTitle": "Cypress",
//		        "price": "40"
//		      },
//		      {
//		        "courseTitle": "Protractor",
//		        "price": "40"
//		      }
//		    ],
//		    "api": [
//		      {
//		        "courseTitle": "Rest Assured Automation using Java",
//		        "price": "50"
//		      },
//		      {
//		        "courseTitle": "SoapUI Webservices testing",
//		        "price": "40"
//		      }
//		    ],
//		    "mobile": [
//		      {
//		        "courseTitle": "Appium-Mobile Automation using Java",
//		        "price": "50"
//		      }
//		    ]
//		  },
//		  "linkedIn": "https://www.linkedin.com/in/rahul-shetty-trainer/"
//		}
	
	//create private variables for all the attributes we got as a response.
	
	private String instructor;
	private String url;
	private String services;
	private String expertise;
	private String linkedIn;
	private Courses courses; //this means the courses is an object of class Courses.
	//use shortcut key Alt+Shift+s to create getter/setters after selecting the variables.
	//here value of all other attributes are simple like key value, but not for courses.
	//For courses its a nested json as there are 3 more mini jsons inside this and the nested json is an array.
	//For nested parent json courses which has 3 subjsons, we will create courses as a separate POJO class.
	//we have to define the return type of courses is not string, but a class.
	
	//now when our pojo class trying to build/read the response and it will see String for instructor variable and understands a string is for that variable.
	//BUt for courses, it will see that the return type is a class Courses, so it go to the Courses.java, execute the code of that class
	//and bring back the mini json to our parent class. In this case the mini json got inducted into parent json.
	//getter/setter method of courses is not returning string, instead it returns object of Courses class.
	
	//but still issue is not fully resolved as the the attributes of the Courses json are arrays.
	//For example: for Webautomation we have array of 3 items which itself is a sub json and each item has its own json file.
	// now we have to create 3 more pojo classes for webautomation, api and mobile and injuct it into courses.java.
	
	public String getInstructor() {
		return instructor;
	}
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getServices() {
		return services;
	}
	public void setServices(String services) {
		this.services = services;
	}
	public String getExpertise() {
		return expertise;
	}
	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}
	public String getLinkedIn() {
		return linkedIn;
	}
	public void setLinkedIn(String linkedIn) {
		this.linkedIn = linkedIn;
	}
	public Courses getCourses() {
		return courses;
	}
	public void setCourses(Courses courses) {
		this.courses = courses;
	}
	
	
}
