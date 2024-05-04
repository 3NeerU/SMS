package Worker;
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
public class ListAllWorkers extends Application{
	public static void main(String[] args) {
	    launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		TableView<WorkerUser> table1 = new TableView<>();
		table1.setPrefWidth(1050);
		table1.setPrefHeight(250);
		
		//Table columns Name
		TableColumn<WorkerUser, Integer> colWorkerIdentity = new TableColumn<>("WorkerId");
		colWorkerIdentity.setCellValueFactory(new PropertyValueFactory<>("workerIdentity"));
		colWorkerIdentity.setMinWidth(50);
		
		TableColumn<WorkerUser, Integer> colFirstName = new TableColumn<>("FirstName");
		colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		colFirstName.setMinWidth(150);
		
		TableColumn<WorkerUser, Integer> colSurName = new TableColumn<>("SurName");
		colSurName.setCellValueFactory(new PropertyValueFactory<>("surName"));
		colSurName.setMinWidth(150);
		
		TableColumn<WorkerUser, Integer> colAddress = new TableColumn<>("Address");
		colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
		colAddress.setMinWidth(150);
		
		TableColumn<WorkerUser, Integer> colEmail = new TableColumn<>("Email");
		colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
		colEmail.setMinWidth(150);
		
		TableColumn<WorkerUser, Integer> colGender = new TableColumn<>("Gender");
		colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
		colGender.setMinWidth(75);
		
		TableColumn<WorkerUser, Integer> colPhoneNumber= new TableColumn<>("PhoneNumber");
		colPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
		colPhoneNumber.setMinWidth(50);
		
		TableColumn<WorkerUser, Integer> colDateOfBirth = new TableColumn<>("DateOfBirth");
		colDateOfBirth.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
		colDateOfBirth.setMinWidth(75);
		
		TableColumn<WorkerUser, Integer> colDepartment = new TableColumn<>("Department");
		colDepartment.setCellValueFactory(new PropertyValueFactory<>("department"));
		colDepartment.setMinWidth(50);
		
		TableColumn<WorkerUser, Integer> colDuty = new TableColumn<>("Duty");
		colDuty.setCellValueFactory(new PropertyValueFactory<>("duty"));
		colDuty.setMinWidth(50);
		
		TableColumn<WorkerUser, Integer> colUserType = new TableColumn<>("UserType");
		colUserType.setCellValueFactory(new PropertyValueFactory<>("userType"));
		colUserType.setMinWidth(50);
		
		TableColumn<WorkerUser, Integer> colShift = new TableColumn<>("Shift");
		colShift.setCellValueFactory(new PropertyValueFactory<>("shift"));
		colShift.setMinWidth(120);
		
		TableColumn<WorkerUser, Integer> colNovice = new TableColumn<>("Novice");
		colNovice.setCellValueFactory(new PropertyValueFactory<>("novice"));
		colNovice.setMinWidth(50);
		
		TableColumn<WorkerUser, Integer> colIntermediate = new TableColumn<>("Intermediate");
		colIntermediate.setCellValueFactory(new PropertyValueFactory<>("intermediate"));
		colIntermediate.setMinWidth(50);
		
		TableColumn<WorkerUser, Integer> colExpert = new TableColumn<>("Expert");
		colExpert.setCellValueFactory(new PropertyValueFactory<>("expert"));
		colExpert.setMinWidth(50);
		
		TableColumn<WorkerUser, Integer> colUserAlias = new TableColumn<>("UserAlias");
		colUserAlias.setCellValueFactory(new PropertyValueFactory<>("userAlias"));
		colUserAlias.setMinWidth(50);
		
		//TableColumn<WorkerUser, Integer> colCredential = new TableColumn<>("Credential");
	//	colCredential.setCellValueFactory(new PropertyValueFactory<>("credential"));
		//colCredential.setMinWidth(50);
		
		table1.getColumns().addAll(colWorkerIdentity, colFirstName, colSurName, colAddress, colEmail,
				colGender, colPhoneNumber, colDateOfBirth, colDepartment, colDuty, colUserType, colShift,
				colNovice, colIntermediate, colExpert, colUserAlias);
		
		
		Button btnClose= new Button("Close");
		btnClose.setOnAction((event)->{
			primaryStage.close();
		});
		
		ArrayList worker = allRecords();
		//set person to table1
		 table1.getItems().addAll(worker);
		 
	        
	        VBox vbox = new VBox(); // Create a VBox layout
	        vbox.getChildren().addAll(table1, btnClose); 
	        
	        Scene scene = new Scene(vbox);
		 

		primaryStage.setScene(scene);
		primaryStage.setTitle("List all workers");
		primaryStage.setHeight(350);
		primaryStage.setWidth(1200);
		primaryStage.setResizable(false);
		primaryStage.show();
		
	}
		
	public ArrayList allRecords() {
		
		  
		  ArrayList<WorkerUser> worker = new ArrayList();
		  String DRIVER = "com.mysql.cj.jdbc.Driver";
		  String HOST = "localhost";
		  int PORT = 3306;
		  String DATABASE = "SMS";
		  String DBUSER = "root";
		  String DBPASS ="neera@12";
		  String URL ="jdbc:mysql://"+HOST+":"+PORT+"/"+DATABASE;
		  String sql ="SELECT * FROM WorkerUsers";
		  try {
			  Class.forName(DRIVER);  //loading driver
			  Connection conn = DriverManager.getConnection(URL, DBUSER, DBPASS);
			  PreparedStatement pstat = conn.prepareStatement(sql);
			  ResultSet rs = pstat.executeQuery();
			  while(rs.next()){
				  int workerIdentity = rs.getInt("workerIdentity");
				  String firstName = rs.getString("firstName");  
					String surName = rs.getString("surName");  
					String address = rs.getString("address");
					String email = rs.getString("email");
					String gender = rs.getString("gender");
					String phoneNumber = rs.getString("phoneNumber");  
					String dateOfBirth = rs.getString("dateOfBirth");
					String department = rs.getString("department");
					String duty = rs.getString("duty");
					String userType = rs.getString("userType");
					String shift = rs.getString("shift");
					int novice = rs.getInt("novice");
					int intermediate = rs.getInt("intermediate");
					int expert = rs.getInt("expert");
					String userAlias = rs.getString("userAlias");
					//String credential = rs.getString("credential");
					WorkerUser workers = new WorkerUser(workerIdentity, firstName, surName, address, email,
		    				gender, phoneNumber, dateOfBirth, department, duty, userType, shift,
		    				novice, intermediate, expert, userAlias);
					worker.add(workers);
				  }
			  pstat.close();
			  conn.close();
		
			  
		  
	  } catch (ClassNotFoundException e) {
	        e.printStackTrace(); // Handle class loading errors
	    } catch (SQLException e) {
	        e.printStackTrace(); // Handle database errors
	    }
		  return worker;
	}
	 
	}


