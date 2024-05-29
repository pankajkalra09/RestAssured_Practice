package OnBoardingRestAssured;

public class LoginResponse {
	
	private String access_token;
	private UserPojoClass user;

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public UserPojoClass getUser() {
		return user;
	}

	public void setUser(UserPojoClass user) {
		this.user = user;
	}
	

}
