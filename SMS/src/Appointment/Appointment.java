package Appointment;

public class Appointment {
    private int appointmentIdentity;
      private int quoteIdentity;
    private int clientIdentity;
    private int workerIdentity;
    private String appointmentDate;
    private String appointmentTime;
    private String serviceCategory;
    private String status;
    private int staffIdentity;
    
	// parameterized Constructors
	public Appointment(int appointmentIdentity, int quoteIdentity, int clientIdentity, int workerIdentity,
			String appointmentDate, String appointmentTime, String serviceCategory, String status, int staffIdentity) {
		super();
		this.appointmentIdentity = appointmentIdentity;
		this.quoteIdentity = quoteIdentity;
		this.clientIdentity = clientIdentity;
		this.workerIdentity = workerIdentity;
		this.appointmentDate = appointmentDate;
		this.appointmentTime = appointmentTime;
		this.serviceCategory = serviceCategory;
		this.status = status;
		this.staffIdentity = staffIdentity;
	}
	public Appointment(int appointmentIdentity, String appointmentDate, String appointmentTime,
			String serviceCategory, int clientIdentity, int workerIdentity, String status) {
		this.appointmentIdentity = appointmentIdentity;
		this.clientIdentity = clientIdentity;
		this.workerIdentity = workerIdentity;
		this.appointmentDate = appointmentDate;
		this.appointmentTime = appointmentTime;
		this.serviceCategory = serviceCategory;
		this.status = status;}
	
	public Appointment(int appointmentIdentity, String appointmentDate, String appointmentTime,
			String serviceCategory, int clientIdentity, int workerIdentity) {
		this.appointmentIdentity = appointmentIdentity;
		this.clientIdentity = clientIdentity;
		this.workerIdentity = workerIdentity;
		this.appointmentDate = appointmentDate;
		this.appointmentTime = appointmentTime;
		this.serviceCategory = serviceCategory;
	}
	public int getAppointmentIdentity() {
		return appointmentIdentity;
	}
	public void setAppointmentIdentity(int appointmentIdentity) {
		this.appointmentIdentity = appointmentIdentity;
	}
	public int getQuoteIdentity() {
		return quoteIdentity;
	}
	public void setQuoteIdentity(int quoteIdentity) {
		this.quoteIdentity = quoteIdentity;
	}
	public int getClientIdentity() {
		return clientIdentity;
	}
	public void setClientIdentity(int clientIdentity) {
		this.clientIdentity = clientIdentity;
	}
	public int getWorkerIdentity() {
		return workerIdentity;
	}
	public void setWorkerIdentity(int workerIdentity) {
		this.workerIdentity = workerIdentity;
	}
	public String getAppointmentDate() {
		return appointmentDate;
	}
	public void setAppointmentDate(String appointmentDate) {
		this.appointmentDate = appointmentDate;
	}
	public String getAppointmentTime() {
		return appointmentTime;
	}
	public void setAppointmentTime(String appointmentTime) {
		this.appointmentTime = appointmentTime;
	}
	public String getServiceCategory() {
		return serviceCategory;
	}
	public void setServiceCategory(String serviceCategory) {
		this.serviceCategory = serviceCategory;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getStaffIdentity() {
		return staffIdentity;
	}
	public void setStaffIdentity(int staffIdentity) {
		this.staffIdentity = staffIdentity;
	}
	@Override
	public String toString() {
		return "Appointment [appointmentIdentity=" + appointmentIdentity + ", quoteIdentity=" + quoteIdentity
				+ ", clientIdentity=" + clientIdentity + ", workerIdentity=" + workerIdentity + ", appointmentDate="
				+ appointmentDate + ", appointmentTime=" + appointmentTime + ", serviceCategory=" + serviceCategory
				+ ", status=" + status + ", staffIdentity=" + staffIdentity + "]";
	}

	}