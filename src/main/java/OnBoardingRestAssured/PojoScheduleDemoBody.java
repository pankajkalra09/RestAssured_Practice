package OnBoardingRestAssured;

import java.util.List;

public class PojoScheduleDemoBody {
	
	private String summary;
	private String meetingDescription;
	private String timeZone;
	private String productName;
	private String startDateTime;
	private String endDateTime;
	private int duration;
	//private List<PojoGuestAttendee> guestAttendees;
	
	public String getSummary() {
		return summary;
	}
//	public List<PojoGuestAttendee> getGuestAttendees() {
//		return guestAttendees;
//	}
//	public void setGuestAttendees(List<PojoGuestAttendee> guestAttendees) {
//		this.guestAttendees = guestAttendees;
//	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getMeetingDescription() {
		return meetingDescription;
	}
	public void setMeetingDescription(String meetingDescription) {
		this.meetingDescription = meetingDescription;
	}
	public String getTimeZone() {
		return timeZone;
	}
	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public String getStartDateTime() {
		return startDateTime;
	}
	public void setStartDateTime(String startDateTime) {
		this.startDateTime = startDateTime;
	}
	public String getEndDateTime() {
		return endDateTime;
	}
	public void setEndDateTime(String endDateTime) {
		this.endDateTime = endDateTime;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	

}
