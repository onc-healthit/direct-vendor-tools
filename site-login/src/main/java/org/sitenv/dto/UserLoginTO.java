package org.sitenv.dto;

public class UserLoginTO {

	private String userName;
	private String password;
	private boolean isUserAuthenticated;
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isUserAuthenticated() {
		return isUserAuthenticated;
	}
	public void setUserAuthenticated(boolean isUserAuthenticated) {
		this.isUserAuthenticated = isUserAuthenticated;
	}
	
}
