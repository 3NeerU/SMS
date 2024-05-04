package Worker;

import java.sql.Date;
import java.time.LocalDate;

import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

public class WorkerUser {
	  private int workerIdentity;
	    private String firstName;
	    private String surName;
	    private String address;
	    private String email;
	    private String gender;
	    private String phoneNumber;
	    private String dateOfBirth;
	    private String department;
	    private String duty;
	    private String userType;
	    private String shift;
	    private int novice;
	    private int intermediate;
	    private int expert;
	    private String userAlias;
	    private String credential;
		public WorkerUser(int workerIdentity, String firstName, String surName, String address, String email,
				String gender, String phoneNumber, String dateOfBirth, String department, String duty, String userType,
				String shift, int novice, int intermediate, int expert, String userAlias, String credential) {
			super();
			
			this.workerIdentity = workerIdentity;
			this.firstName = firstName;
			this.surName = surName;
			this.address = address;
			this.email = email;
			this.gender = gender;
			this.phoneNumber = phoneNumber;
			this.dateOfBirth = dateOfBirth;
			this.department = department;
			this.duty = duty;
			this.userType = userType;
			this.shift = shift;
			this.novice = novice;
			this.intermediate = intermediate;
			this.expert = expert;
			this.userAlias = userAlias;
			this.credential = credential;
		}
		public WorkerUser(int workerIdentity, String firstName, String surName, String address, String email,
				String gender, String phoneNumber, String dateOfBirth, String department, String duty,
				String userType, String shift, int novice, int intermediate, int expert, String userAlias) {
			this.workerIdentity = workerIdentity;
			this.firstName = firstName;
			this.surName = surName;
			this.address = address;
			this.email = email;
			this.gender = gender;
			this.phoneNumber = phoneNumber;
			this.dateOfBirth = dateOfBirth;
			this.department = department;
			this.duty = duty;
			this.userType = userType;
			this.shift = shift;
			this.novice = novice;
			this.intermediate = intermediate;
			this.expert = expert;
			this.userAlias = userAlias;		}
		public int getWorkerIdentity() {
			return workerIdentity;
		}
		public void setWorkerIdentity(int workerIdentity) {
			this.workerIdentity = workerIdentity;
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
		public String getDepartment() {
			return department;
		}
		public void setDepartment(String department) {
			this.department = department;
		}
		public String getDuty() {
			return duty;
		}
		public void setDuty(String duty) {
			this.duty = duty;
		}
		public String getUserType() {
			return userType;
		}
		public void setUserType(String userType) {
			this.userType = userType;
		}
		public String getShift() {
			return shift;
		}
		public void setShift(String shift) {
			this.shift = shift;
		}
		public int getNovice() {
			return novice;
		}
		public void setNovice(int novice) {
			this.novice = novice;
		}
		public int getIntermediate() {
			return intermediate;
		}
		public void setIntermediate(int intermediate) {
			this.intermediate = intermediate;
		}
		public int getExpert() {
			return expert;
		}
		public void setExpert(int expert) {
			this.expert = expert;
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
			return "WorkerUser [workerIdentity=" + workerIdentity + ", firstName=" + firstName + ", surName=" + surName
					+ ", address=" + address + ", email=" + email + ", gender=" + gender + ", phoneNumber="
					+ phoneNumber + ", dateOfBirth=" + dateOfBirth + ", department=" + department + ", duty=" + duty
					+ ", user=" + userType + ", shift=" + shift + ", novice=" + novice + ", intermediate=" + intermediate
					+ ", expert=" + expert + ", userAlias=" + userAlias + ", credential=" + credential + "]";
		}
		
		
}