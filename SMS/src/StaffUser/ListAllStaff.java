package StaffUser;
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
public class ListAllStaff extends Application{

		public static void main(String[] args) {
		    launch(args);
		}

		@Override
		public void start(Stage primaryStage) throws Exception {
			
			TableView<StaffUser> table1 = new TableView<>();
			table1.setPrefWidth(1050);
			table1.setPrefHeight(250);
			
			//Table columns Name
			TableColumn<StaffUser, Integer> colStaffIdentity = new TableColumn<>("StaffId");
			colStaffIdentity.setCellValueFactory(new PropertyValueFactory<>("staffIdentity"));
			colStaffIdentity.setMinWidth(50);
			
			TableColumn<StaffUser, Integer> colFirstName = new TableColumn<>("FirstName");
			colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
			colFirstName.setMinWidth(150);
			
			TableColumn<StaffUser, Integer> colSurName = new TableColumn<>("SurName");
			colSurName.setCellValueFactory(new PropertyValueFactory<>("surName"));
			colSurName.setMinWidth(150);
			
			TableColumn<StaffUser, Integer> colAddress = new TableColumn<>("Address");
			colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
			colAddress.setMinWidth(150);
			
			TableColumn<StaffUser, Integer> colEmail = new TableColumn<>("Email");
			colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
			colEmail.setMinWidth(150);
			
			TableColumn<StaffUser, Integer> colGender = new TableColumn<>("Gender");
			colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
			colGender.setMinWidth(75);
			
			TableColumn<StaffUser, Integer> colPhoneNumber= new TableColumn<>("PhoneNumber");
			colPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
			colPhoneNumber.setMinWidth(50);
			
			TableColumn<StaffUser, Integer> colDateOfBirth = new TableColumn<>("DateOfBirth");
			colDateOfBirth.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
			colDateOfBirth.setMinWidth(75);
			
			TableColumn<StaffUser, Integer> colUserType = new TableColumn<>("UserType");
			colUserType.setCellValueFactory(new PropertyValueFactory<>("userType"));
			colUserType.setMinWidth(50);
			
			TableColumn<StaffUser, Integer> colUserAlias = new TableColumn<>("UserAlias");
			colUserAlias.setCellValueFactory(new PropertyValueFactory<>("userAlias"));
			colUserAlias.setMinWidth(50);
			
			TableColumn<StaffUser, Integer> colCredential = new TableColumn<>("Credential");
			colCredential.setCellValueFactory(new PropertyValueFactory<>("credential"));
			colCredential.setMinWidth(50);
			
			table1.getColumns().addAll(colStaffIdentity, colFirstName, colSurName, colAddress, colEmail,
					colGender, colPhoneNumber, colDateOfBirth, colUserType, colUserAlias, colCredential);
			
			
			Button btnClose= new Button("Close");
			btnClose.setOnAction((event)->{
				primaryStage.close();
			});
			
			ArrayList staff = allRecords();
			//set person to table1
			 table1.getItems().addAll(staff);
			 
		        
		        VBox vbox = new VBox(); // Create a VBox layout
		        vbox.getChildren().addAll(table1, btnClose); 
		        
		        Scene scene = new Scene(vbox);
			 

			primaryStage.setScene(scene);
			primaryStage.setTitle("List all staff");
			primaryStage.setHeight(350);
			primaryStage.setWidth(1200);
			primaryStage.setResizable(false);
			primaryStage.show();
			
		}
			
		public ArrayList allRecords() {
			
			  
			  ArrayList<StaffUser> staff = new ArrayList();
			  String DRIVER = "com.mysql.cj.jdbc.Driver";
			  String HOST = "localhost";
			  int PORT = 3306;
			  String DATABASE = "SMS";
			  String DBUSER = "root";
			  String DBPASS ="neera@12";
			  String URL ="jdbc:mysql://"+HOST+":"+PORT+"/"+DATABASE;
			  String sql ="SELECT * FROM StaffUsers";
			  try {
				  Class.forName(DRIVER);  //loading driver
				  Connection conn = DriverManager.getConnection(URL, DBUSER, DBPASS);
				  PreparedStatement pstat = conn.prepareStatement(sql);
				  ResultSet rs = pstat.executeQuery();
				  while(rs.next()){
					  int staffIdentity = rs.getInt("staffIdentity");
					  String firstName = rs.getString("firstName");  
						String surName = rs.getString("surName");  
						String address = rs.getString("address");
						String email = rs.getString("email");
						String gender = rs.getString("gender");
						String phoneNumber = rs.getString("phoneNumber");  
						String dateOfBirth = rs.getString("dateOfBirth");
						String userType = rs.getString("userType");
						String userAlias = rs.getString("userAlias");
						String credential = rs.getString("credential");
						StaffUser staffs = new StaffUser(staffIdentity, firstName, surName, address, email,
			    				gender, phoneNumber, dateOfBirth, userType, userAlias, credential);
						staff.add(staffs);
					  }
				  pstat.close();
				  conn.close();
			
				  
			  
		  } catch (ClassNotFoundException e) {
		        e.printStackTrace(); // Handle class loading errors
		    } catch (SQLException e) {
		        e.printStackTrace(); // Handle database errors
		    }
			  return staff;
		}
		 
		}

