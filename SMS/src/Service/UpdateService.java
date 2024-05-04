package Service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Worker.WorkerUser;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
public class UpdateService extends Application {

			 public static void main(String[] args) {
				  launch(args);
			  }  // main Method
			
			@Override
			public void start(Stage primaryStage) throws Exception {
				
				 Label lblServiceIdentity = new Label("Service Identity: ");
				  Label lblServiceName = new Label("Service Name: ");
				  Label lblServiceCategory = new Label("Service Category: ");
				  Label lblServiceDetail = new Label("Service Detail: ");
				  Label lblServiceCost = new Label("Service Cost: ");
				  Label lblServiceStatus = new Label("Service Status: ");

			        TextField txtServiceIdentity = new TextField();
			        TextField txtServiceName = new TextField();
			        
			    	ComboBox cmbServiceCategory = new ComboBox();  // gives a drop down arrow list
			    	List lstServiceCategory = new ArrayList(); 
			    	lstServiceCategory.add("Plumbing");
			    	lstServiceCategory.add("Electrical");
			    	lstServiceCategory.add("Cleaning");
			    	lstServiceCategory.add("Gardening");
			    	cmbServiceCategory.getItems().addAll(lstServiceCategory);
			    	
			        TextArea txtServiceDetail = new TextArea();
			        
			        TextField txtServiceCost = new TextField();
			  
			    	ComboBox cmbServiceStatus = new ComboBox();  // gives a drop down arrow list
			    	List lstServiceStatus = new ArrayList(); 
			    	lstServiceStatus.add("Available");
			    	lstServiceStatus.add("Under Maintenance");
			    	cmbServiceStatus.getItems().addAll(lstServiceStatus);
			        
			        Button btnSearch = new Button("Search");
			    	btnSearch.setOnAction((event)->{
			    		//Reading values from UI
			    		int serviceIdentity = Integer.parseInt(txtServiceIdentity.getText());  //string->INT
			    		Service service =  searchRecord(serviceIdentity);

			    		if(service!=null) {
			    			txtServiceName.setText(service.getServiceName());
			    			cmbServiceCategory.setValue(service.getServiceCategory());
			    			txtServiceDetail.setText(service.getServiceDetail());
			                txtServiceCost.setText(String.valueOf(service.getServiceCost()));
			    			cmbServiceStatus.setValue(service.getServiceStatus());

			    			
			    			System.out.println("Record Found!");
			    		}
			    		else {
			    			System.out.println("Error to Find Record");
			    		}
			    	});
			    		Button btnUpdate=new Button("Update");
						btnUpdate.setOnAction((event)->{
							//Reading values from UI
				    		int serviceIdentity = Integer.parseInt(txtServiceIdentity.getText());  //string->INT 
				    		String serviceName = txtServiceName.getText();
				    		String serviceCategory =  cmbServiceCategory.getValue().toString();
				    		String serviceDetail = txtServiceDetail.getText();
				    		double serviceCost =  Double.parseDouble(txtServiceCost.getText());
				    		String serviceStatus = cmbServiceStatus.getValue().toString();
							 
							
				    			
							Service service = new Service(serviceIdentity, serviceName, serviceCategory,serviceDetail, serviceCost, serviceStatus);

					    		boolean res = UpdateRecord(service); //callMethod
					    		if(res==true) {
					    			System.out.println("Record Updated!");
					    		}
					    		else {
					    			System.out.println("Error to Update Record");
					    		}
						});
						Button btnClose= new Button("Close");
				    	btnClose.setOnAction((event)->{
				    		primaryStage.close();
			    	});

			    	GridPane pane = new GridPane();
			    	pane.setConstraints(lblServiceIdentity, 0, 0); //col, row
			    	pane.setConstraints(txtServiceIdentity, 1, 0);
			    	
			    	pane.setConstraints(lblServiceName, 0, 1); //col, row
			    	pane.setConstraints(txtServiceName, 1, 1);
			    	
			    	pane.setConstraints(lblServiceCategory, 0, 2); //col, row
			    	pane.setConstraints(cmbServiceCategory, 1, 2);
			    	
			    	pane.setConstraints(lblServiceDetail, 0, 3);
			    	pane.setConstraints(txtServiceDetail, 1, 3);
			    	
			    	pane.setConstraints(lblServiceCost, 0, 4);
			    	pane.setConstraints(txtServiceCost, 1, 4);
			    	
			    	pane.setConstraints(lblServiceStatus, 0, 5);
			    	pane.setConstraints(cmbServiceStatus, 1, 5);
			    	
			    	pane.setConstraints(btnSearch, 0, 18);
			     	pane.setConstraints(btnUpdate, 1, 18);
			    	pane.setConstraints(btnClose, 2, 18);
			    	
			    	pane.getChildren().add(lblServiceIdentity);
			    	pane.getChildren().add(txtServiceIdentity);
			    	pane.getChildren().add(lblServiceName);
			    	pane.getChildren().add(txtServiceName);
			    	pane.getChildren().add(lblServiceCategory);
			    	pane.getChildren().add(cmbServiceCategory);
			    	pane.getChildren().add(lblServiceDetail);
			    	pane.getChildren().add(txtServiceDetail);
			    	pane.getChildren().add(lblServiceCost);
			    	pane.getChildren().add(txtServiceCost);
			    	pane.getChildren().add(lblServiceStatus);
			    	pane.getChildren().add(cmbServiceStatus);
			    	pane.getChildren().add(btnSearch);
			    	pane.getChildren().add(btnUpdate);
			    	pane.getChildren().add(btnClose);


			    	
			    	Scene scene = new Scene(pane);
			    	primaryStage.setScene(scene);
			    	
			    	primaryStage.setTitle("Update Service's Info");
			    	primaryStage.setWidth(600);
			    	primaryStage.setHeight(500);
			    	primaryStage.setResizable(false);
			    	
			    	primaryStage.show();
			    	
			}

			public Service searchRecord(int serviceIdentity) {
				  Service service = null;
				  String DRIVER = "com.mysql.cj.jdbc.Driver";
				  String HOST = "localhost";
				  int PORT = 3306;
				  String DATABASE = "SMS";
				  String DBUSER = "root";
				  String DBPASS ="neera@12";
				  String URL ="jdbc:mysql://"+HOST+":"+PORT+"/"+DATABASE;
				  String sql = "SELECT * FROM Services WHERE serviceIdentity =?";
				  try {
					  Class.forName(DRIVER);  //loading driver
					  Connection conn = DriverManager.getConnection(URL, DBUSER, DBPASS);
					  PreparedStatement pstat = conn.prepareStatement(sql);
					  pstat.setInt(1,  serviceIdentity);			
					  ResultSet rs = pstat.executeQuery();
					  while(rs.next()){
							String serviceName = rs.getString("serviceName");  
							String serviceCategory = rs.getString("serviceCategory");  
							String serviceDetail = rs.getString("serviceDetail");  
							double serviceCost = rs.getDouble("serviceCost"); 
							String serviceStatus = rs.getString("serviceStatus");  
							
							service = new Service(serviceIdentity, serviceName, serviceCategory,serviceDetail, serviceCost, serviceStatus);
							
						  }
						  pstat.close();
						  conn.close();
					
						  
					  }
					  catch(Exception ex){
						  System.out.println("Error");
					  }
					  return service;
				  }
			public boolean UpdateRecord(Service service) {
				
				boolean result = false;
				String DRIVER ="com.mysql.cj.jdbc.Driver";
				String HOST ="localhost";
				int PORT=3306;
				String DATABASE ="SMS";
				String DBUSER="root";
				String DBPASS="neera@12";
				String URL = "jdbc:mysql://"+HOST+":"+PORT+"/"+DATABASE;
				String sql = "UPDATE Services SET serviceName=?, serviceCategory=?, serviceDetail=?, serviceCost=?, serviceStatus=? WHERE serviceIdentity=?";
	
				try {
					Class.forName(DRIVER); //loading driver
					Connection conn = DriverManager.getConnection(URL, DBUSER, DBPASS);//connection with database server
					PreparedStatement pstat = conn.prepareStatement(sql);			
					
					  
					  pstat.setString(1, service.getServiceName());
					  pstat.setString(2, service.getServiceCategory());
					  pstat.setString(3, service.getServiceDetail());
					  pstat.setDouble(4, service.getServiceCost());
					  pstat.setString(5, service.getServiceStatus());
					  pstat.setInt(6,  service.getServiceIdentity());
					
					  pstat.executeUpdate();
					  pstat.close();
					  conn.close();
					  result = true;
				}
				catch(Exception ex) {
					System.out.println("Error : "+ex.getMessage());
				}
				return result;
			}
}

