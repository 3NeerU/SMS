package StaffUser;

import java.sql.Date;

public class StaffUser {
    private int staffIdentity;
    private String firstName;
    private String surName;
    private String address;
    private String email;
    private String gender;
    private String phoneNumber;
    private String dateOfBirth;
    private String userType;
    public String userAlias;
    private String credential;
	public StaffUser(int staffIdentity, String firstName, String surName, String address, String email, String gender,
			String phoneNumber, String dateOfBirth, String userType, String userAlias, String credential) {
		super();
		this.staffIdentity = staffIdentity;
		this.firstName = firstName;
		this.surName = surName;
		this.address = address;
		this.email = email;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
		this.dateOfBirth = dateOfBirth;
		this.userType = userType;
		this.userAlias = userAlias;
		this.credential = credential;
	}
	public StaffUser() {
		
	}
	public int getStaffIdentity() {
		return staffIdentity;
	}
	public void setStaffIdentity(int staffIdentity) {
		this.staffIdentity = staffIdentity;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getSurName() {
		return surName;
	}
	public void setSurName(String surName) {
		this.surName = surName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getUserAlias() {
		return userAlias;
	}
	public void setUserAlias(String userAlias) {
		this.userAlias = userAlias;
	}
	public String getCredential() {
		return credential;
	}
	public void setCredential(String credential) {
		this.credential = credential;
	}
	@Override
	public String toString() {
		return "StaffUser [staffIdentity=" + staffIdentity + ", firstName=" + firstName + ", surName=" + surName
				+ ", address=" + address + ", email=" + email + ", gender=" + gender + ", phoneNumber=" + phoneNumber
				+ ", dateOfBirth=" + dateOfBirth + ", user=" + userType + ", userAlias=" + userAlias + ", credential="
				+ credential + "]";
	}



}

