package com.danny.mobileappws.models;

public class UserResponse {
	String userId;
	String firstName;
	String lastName;
	String email;

	

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	@Override
	public String toString() {
		return "UserResponse [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				 + "]";
	}

}
