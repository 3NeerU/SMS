package Service;

public class Service {
	private int serviceIdentity;
    private String serviceName;
    private String serviceCategory;
    private String serviceDetail;
    private double serviceCost;
    private String serviceStatus;

	public Service(int serviceIdentity, String serviceName, 
			String serviceCategory, String serviceDetail, double serviceCost, String serviceStatus) {
		super();
		this.serviceIdentity = serviceIdentity;
		this.serviceName = serviceName;
		this.serviceDetail = serviceDetail;
		this.serviceCost = serviceCost;
		this.serviceCategory = serviceCategory;
		this.serviceStatus = serviceStatus;
	}
	public int getServiceIdentity() {
		return serviceIdentity;
	}
	public void setServiceIdentity(int serviceIdentity) {
		this.serviceIdentity = serviceIdentity;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getServiceDetail() {
		return serviceDetail;
	}
	public void setServiceDetail(String serviceDetail) {
		this.serviceDetail = serviceDetail;
	}
	public double getServiceCost() {
		return serviceCost;
	}
	public void setServiceCost(double serviceCost) {
		this.serviceCost = serviceCost;
	}
	public String getServiceCategory() {
		return serviceCategory;
	}
	public void setServiceCategory(String serviceCategory) {
		this.serviceCategory = serviceCategory;
	}
	public String getServiceStatus() {
		return serviceStatus;
	}
	public void setServiceStatus(String serviceStatus) {
		this.serviceStatus = serviceStatus;
	}
	@Override
	public String toString() {
		return "Service [serviceIdentity=" + serviceIdentity + ", serviceName=" + serviceName + ", serviceCategory=" + serviceCategory+", serviceDetail="
							+ serviceDetail + ",serviceCost=" + serviceCost + 
				", serviceStatus=" + serviceStatus + "]";
	}
    
}
