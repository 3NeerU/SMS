package Client;

import java.sql.Date;

public class Client {
    private int clientIdentity;
    private String firstName;
    private String surName;
    private String address;
    private String email;
    private String gender;
    private String phoneNumber;
    private String dateOfBirth;
    private String userType;
    private String alias;
    private String credential;
	public Client(int clientIdentity, String firstName, String surName, String address, String email, String gender,
			String phoneNumber, String dateOfBirth, String userType, String alias, String credential) {
		super();
		this.clientIdentity = clientIdentity;
		this.firstName = firstName;
		this.surName = surName;
		this.address = address;
		this.email = email;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
		this.dateOfBirth = dateOfBirth;
		this.userType = userType;
		this.alias = alias;
		this.credential = credential;
	}
	public Client(int clientIdentity2, String firstName2, String surName2, String address2, String email2,
			String gender2, String phoneNumber2, String dateOfBirth2, String alias2) {
		super();
		this.clientIdentity = clientIdentity2;
		this.firstName = firstName2;
		this.surName = surName2;
		this.address = address2;
		this.email = email2;
		this.gender = gender2;
		this.phoneNumber = phoneNumber2;
		this.dateOfBirth = dateOfBirth2;
		this.alias = alias2;
	}
	public Client(String firstName, String surName, String address, String email, String gender,
			String phoneNumber, String dateOfBirth, String userType, String alias, String credential) {
		this.firstName = firstName;
		this.surName = surName;
		this.address = address;
		this.email = email;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
		this.dateOfBirth = dateOfBirth;
		this.userType = userType;
		this.alias = alias;
		this.credential = credential;
		}
	// getter setter
	public int getClientIdentity() {
		return clientIdentity;
	}
	public void setClientIdentity(int clientIdentity) {
		this.clientIdentity = clientIdentity;
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
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getCredential() {
		return credential;
	}
	public void setCredential(String credential) {
		this.credential = credential;
	}
	@Override
	public String toString() {
		return "Client [clientIdentity=" + clientIdentity + ", firstName=" + firstName + ", surName=" + surName
				+ ", address=" + address + ", email=" + email + ", gender=" + gender + ", phoneNumber=" + phoneNumber
				+ ", dateOfBirth=" + dateOfBirth + ", user=" + userType + ", alias=" + alias + ", credential=" + credential
				+ "]";
	}
	
    
}
