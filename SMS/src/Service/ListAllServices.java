package Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ListAllServices extends Application{

			public static void main(String[] args) {
			    launch(args);
			}

			@Override
			public void start(Stage primaryStage) throws Exception {
				
				TableView<Service> table1 = new TableView<>();
				table1.setPrefWidth(1050);
				table1.setPrefHeight(250);
				
				//Table columns Name
				TableColumn<Service, Integer> colServiceIdentity = new TableColumn<>("ServiceId");
				colServiceIdentity.setCellValueFactory(new PropertyValueFactory<>("serviceIdentity"));
				colServiceIdentity.setMinWidth(50);
				
				TableColumn<Service, Integer> colServiceName = new TableColumn<>("ServiceName");
				colServiceName.setCellValueFactory(new PropertyValueFactory<>("serviceName"));
				colServiceName.setMinWidth(150);
				
				TableColumn<Service, Integer> colServiceDetail =new TableColumn<>("Service Detail");
				colServiceDetail.setCellValueFactory(new PropertyValueFactory<>("serviceDetail"));
				colServiceDetail.setMinWidth(600);
				
				TableColumn<Service, Integer> colServiceCategory = new TableColumn<>("ServiceCategory");
				colServiceCategory.setCellValueFactory(new PropertyValueFactory<>("serviceCategory"));
				colServiceCategory.setMinWidth(150);
				
				TableColumn<Service, Integer> colServiceStatus = new TableColumn<>("ServiceStatus");
				colServiceStatus.setCellValueFactory(new PropertyValueFactory<>("serviceStatus"));
				colServiceStatus.setMinWidth(150);
				
				table1.getColumns().addAll(colServiceIdentity, colServiceName, colServiceDetail, colServiceCategory, colServiceStatus);
				
				
				Button btnClose= new Button("Close");
				btnClose.setOnAction((event)->{
					primaryStage.close();
				});
				
				ArrayList service = allRecords();
				//set person to table1
				 table1.getItems().addAll(service);
				 
			        
			        VBox vbox = new VBox(); // Create a VBox layout
			        vbox.getChildren().addAll(table1, btnClose); 
			        
			        Scene scene = new Scene(vbox);
				 

				primaryStage.setScene(scene);
				primaryStage.setTitle("List all clients");
				primaryStage.setHeight(350);
				primaryStage.setWidth(1200);
				primaryStage.setResizable(false);
				primaryStage.show();
				
			}
				
			public ArrayList allRecords() {
				
				  
				  ArrayList<Service> service = new ArrayList<>();
				  String DRIVER = "com.mysql.cj.jdbc.Driver";
				  String HOST = "localhost";
				  int PORT = 3306;
				  String DATABASE = "SMS";
				  String DBUSER = "root";
				  String DBPASS ="neera@12";
				  String URL ="jdbc:mysql://"+HOST+":"+PORT+"/"+DATABASE;
				  String sql ="SELECT * FROM Services";
				  try {
					  Class.forName(DRIVER);  //loading driver
					  Connection conn = DriverManager.getConnection(URL, DBUSER, DBPASS);
					  PreparedStatement pstat = conn.prepareStatement(sql);
					  ResultSet rs = pstat.executeQuery();
					  while(rs.next()){
						  int serviceIdentity = rs.getInt("serviceIdentity");
							String serviceName = rs.getString("serviceName");  
							String serviceCategory = rs.getString("serviceCategory");  
							String serviceDetail = rs.getString("serviceDetail");  
							double serviceCost = rs.getDouble("serviceCost"); 
							String serviceStatus = rs.getString("serviceStatus");  
							Service services = new Service(serviceIdentity, serviceName, serviceCategory,serviceDetail, serviceCost, serviceStatus);
							service.add(services);
						  }
					  pstat.close();
					  conn.close();
				
					  
				  
			  } catch (ClassNotFoundException e) {
			        e.printStackTrace(); // Handle class loading errors
			    } catch (SQLException e) {
			        e.printStackTrace(); // Handle database errors
			    }
				  return service;
			}
			 
			}


