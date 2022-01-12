package com.danny.mobileappws.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class UserEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6816468982464987170L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@Column(nullable=false)
	private String userId;
	@Column(nullable=false,length = 100)
	private String firstName;
	@Column(nullable=false,length = 100)
	private String lastName;
	@Column(nullable=false,length = 100,unique = true)
	private String email;
	@Column(nullable=false,length = 100)
	private String password;
	@Column(nullable=false,length = 100)
	private String encriptedPassword;
	private String emailVerificationToken;
	@Column(nullable=false,columnDefinition = "Boolean default false")
	private boolean emailVerificationStatus;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEncriptedPassword() {
		return encriptedPassword;
	}
	public void setEncriptedPassword(String encriptedPassword) {
		this.encriptedPassword = encriptedPassword;
	}
	public String getEmailVerificationToken() {
		return emailVerificationToken;
	}
	public void setEmailVerificationToken(String emailVerificationToken) {
		this.emailVerificationToken = emailVerificationToken;
	}
	public boolean isEmailVerificationStatus() {
		return emailVerificationStatus;
	}
	public void setEmailVerificationStatus(boolean emailVerificationStatus) {
		this.emailVerificationStatus = emailVerificationStatus;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
