package Quote;

public class Quote {
	 int quoteIdentity;
	private int clientIdentity;
    private int serviceIdentity;
	    private String serviceCategory;
	    private double serviceCost;
	    private String preferredDate;
	    private String status;
	    private String workerMastery;
	    private double totalPrice;
	    
		public Quote(int quoteIdentity, int clientIdentity, String serviceCategory,
				double serviceCost, String preferredDate, String status, String workerMastery, double totalPrice) {
			super();
			this.quoteIdentity = quoteIdentity;
			this.clientIdentity = clientIdentity;
			this.serviceCategory = serviceCategory;
			this.serviceCost = serviceCost;
			this.preferredDate = preferredDate;
			this.status = status;
			this.workerMastery = workerMastery;
			this.totalPrice = totalPrice;
		}
		public Quote(int clientIdentity, String serviceCategory, String preferredDate, Double serviceCost, String status,
				String workerMastery, double totalPrice) {
			super();
			this.quoteIdentity = 0;
			this.clientIdentity = clientIdentity;
			this.serviceCategory = serviceCategory;
			this.preferredDate = preferredDate;
			this.serviceCost = serviceCost;
			this.status = status;
			this.workerMastery = workerMastery;
			this.totalPrice = totalPrice;
		}
		public Quote(String serviceCategory, String preferredDate, String status, String workerMastery,
				double totalPrice) {
		
			this.serviceCategory = serviceCategory;
			this.preferredDate = preferredDate;
			this.status = status;
			this.workerMastery = workerMastery;
			this.totalPrice = totalPrice;
		}
		public Quote(int clientIdentity, int serviceIdentity,String serviceCategory, String preferredDate, double serviceCost, String status,
				String workerMastery, double totalPrice) {
	
			this.clientIdentity = clientIdentity;
			this.serviceIdentity = serviceIdentity;
			this.serviceCategory = serviceCategory;
			this.preferredDate = preferredDate;
			this.serviceCost = serviceCost;
			this.status = status;
			this.workerMastery = workerMastery;
			this.totalPrice = totalPrice;
		}
		public Quote(int quoteIdentity, int clientIdentity, String serviceCategory) {
			this.quoteIdentity = quoteIdentity;
			this.clientIdentity = clientIdentity;
			this.serviceCategory = serviceCategory;

		}
	
		public Quote(int quoteIdentity, int serviceIdentity, int clientIdentity, String serviceCategory,
				String preferredDate, double serviceCost, String status, String workerMastery, double totalPrice) {
			this.quoteIdentity = quoteIdentity;
			this.serviceIdentity = serviceIdentity;
			this.clientIdentity = clientIdentity;
			this.serviceCategory = serviceCategory;
			this.preferredDate = preferredDate;
			this.serviceCost = serviceCost;
			this.status = status;
			this.workerMastery = workerMastery;
			this.totalPrice = totalPrice;
		}
		public Quote(String serviceCategory, int totalPrice) {
			this.serviceCategory = serviceCategory;
			this.totalPrice = totalPrice;
		}
		public String getServiceCategory() {
			return serviceCategory;
		}
		public void setServiceCategory(String serviceCategory) {
			this.serviceCategory = serviceCategory;
		}
		public double getServiceCost() {
			return serviceCost;
		}
		public void setServiceCost(double serviceCost) {
			this.serviceCost = serviceCost;
		}
		public String getPreferredDate() {
			return preferredDate;
		}
		public void setPreferredDate(String preferredDate) {
			this.preferredDate = preferredDate;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getWorkerMastery() {
			return workerMastery;
		}
		public void setWorkerMastery(String workerMastery) {
			this.workerMastery = workerMastery;
		}
		public double getTotalPrice() {
			return totalPrice;
		}
		public void setTotalPrice(double totalPrice) {
			this.totalPrice = totalPrice;
		}
		public int getQuoteIdentity() {
			
			return quoteIdentity;
		}
		public void setQuoteIdentity(int quoteID) {
			this.quoteIdentity = quoteID;
		}
		public int getClientIdentity() {
			
			return clientIdentity;
		}
		public void setClientIdentity(int clientIdentity) {
			this.clientIdentity = clientIdentity;
		}
		public int getServiceIdentity() {
			
			return clientIdentity;
		}
	
		public void setServiceIdentity(int serviceIdentity) {
			this.serviceIdentity = serviceIdentity;
		}
		@Override
		public String toString() {
			return "Quote [quoteIdentity=" + quoteIdentity + ", clientIdentity=" + clientIdentity + ", serviceIdentity="
					+ serviceIdentity + ", serviceCategory=" + serviceCategory + ", serviceCost=" + serviceCost
					+ ", preferredDate=" + preferredDate + ", status=" + status + ", workerMastery=" + workerMastery
					+ ", totalPrice=" + totalPrice + "]";
		}
		public char[] getEarning() {
			// TODO Auto-generated method stub
			return null;
		}
		public char[] getTotalEarning() {
			// TODO Auto-generated method stub
			return null;
		}
	
		
		

		
		
		
		
	    
}