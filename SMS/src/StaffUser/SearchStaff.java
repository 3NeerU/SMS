package StaffUser;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

public class SearchStaff extends Application {
			 public static void main(String[] args) {
				  launch(args);
			  }  // main Method
			
			@Override
			public void start(Stage primaryStage) throws Exception {
				
				  Label lblStaffIdentity = new Label("Staff Identity: ");
			        Label lblFirstName = new Label("First Name: ");
			        Label lblSurName = new Label("Surname: ");
			        Label lblAddress = new Label("Address: ");
			        Label lblEmail = new Label("Email: ");
			        Label lblGender = new Label("Gender: ");
			        Label lblPhoneNumber = new Label("Phone Number: ");
			        Label lblDateOfBirth = new Label("Date of Birth: ");
			        Label lblUserType = new Label("User: ");
			        Label lblUserAlias = new Label("User Alias: ");
			        Label lblCredential = new Label("Credential: ");
			        
			        TextField txtStaffIdentity = new TextField();
			        TextField txtFirstName = new TextField();
			        TextField txtSurName = new TextField();
			        TextArea txtAddress = new TextArea();
			        TextField txtEmail = new TextField();
			        
			        RadioButton rbMale = new RadioButton("Male");
			        RadioButton rbFemale = new RadioButton("Female");
			        rbMale.setSelected(true); // default value
			        ToggleGroup genderGroup = new ToggleGroup();
			        rbMale.setToggleGroup(genderGroup);
			        rbFemale.setToggleGroup(genderGroup);
			        
			        TextField txtPhoneNumber = new TextField();
			        
			        DatePicker datePicker = new DatePicker();
			    	
			    	ComboBox cmbUserType = new ComboBox();  // gives a drop down arrow list
			    	List lstUser = new ArrayList(); 
			    	lstUser.add("Staff");
			    	lstUser.add("Client");
			    	lstUser.add("Worker");
			    	cmbUserType.getItems().addAll(lstUser);
			        
			        TextField txtUserAlias = new TextField();
			        PasswordField txtCredential = new PasswordField(); //hides the content
			        
			        Button btnSearch = new Button("Search");
			    	Button btnClose= new Button("Close");
			    	btnClose.setOnAction((event)->{
			    		primaryStage.close();
			    	});
			    	btnSearch.setOnAction((event)->{
			    		//Reading values from UI
			    		int staffIdentity = Integer.parseInt(txtStaffIdentity.getText());  //string->INT
			    		StaffUser staff =  searchRecord(staffIdentity);
			    		
			    		if(staff!=null) {
			    			txtFirstName.setText(staff.getFirstName());
			    			txtSurName.setText(staff.getSurName());
			    			txtAddress.setText(staff.getAddress());
			    			txtEmail.setText(staff.getEmail());
			    			
			    			//Gender
			    			if(staff.getGender().equals("Male")) {
			    				rbMale.setSelected(true);
			    			}
			    			else {
			    				rbFemale.setSelected(true);
			    			}
			    			txtPhoneNumber.setText(staff.getPhoneNumber());
			    			datePicker.setValue(LocalDate.parse(staff.getDateOfBirth()));
			    			 cmbUserType.setValue(staff.getUserType());
			    			 
			                txtUserAlias.setText(staff.getUserAlias());
			                txtCredential.setText(staff.getCredential());
			    			System.out.println("Record Found !");		
			    		}
			    		else {
			    			System.out.println("Error to Find Record");
			    		}
			    	});
			    	GridPane pane = new GridPane();
			    	pane.setConstraints(lblStaffIdentity, 0, 0); //col, row
			    	pane.setConstraints(txtStaffIdentity, 1, 0);
			    	
			    	pane.setConstraints(lblFirstName, 0, 1); //col, row
			    	pane.setConstraints(txtFirstName, 1, 1);
			    	
			    	pane.setConstraints(lblSurName, 0, 2); //col, row
			    	pane.setConstraints(txtSurName, 1, 2);
			    	
			    	pane.setConstraints(lblAddress, 0, 3);
			    	pane.setConstraints(txtAddress, 1, 3);
			    	
			    	pane.setConstraints(lblEmail, 0, 4);
			    	pane.setConstraints(txtEmail, 1, 4);
			    	
			    	pane.setConstraints(lblGender, 0, 5);
			    	pane.setConstraints(rbMale, 1, 5);
			    	pane.setConstraints(rbFemale, 1, 6);
			    	
			    	pane.setConstraints(lblPhoneNumber, 0, 7);
			    	pane.setConstraints(txtPhoneNumber, 1, 7);
			    	
			    	pane.setConstraints(lblDateOfBirth, 0, 8);
			    	pane.setConstraints(datePicker, 1, 8);
			    	
			    	pane.setConstraints(lblUserType, 0, 9);
			    	pane.setConstraints(cmbUserType, 1, 9);
			    	
			       	pane.setConstraints(lblUserAlias, 0, 10);
			    	pane.setConstraints(txtUserAlias, 1, 10);
			    	
			    	pane.setConstraints(lblCredential, 0, 11);
			    	pane.setConstraints(txtCredential, 1, 11);
			    	
			    	pane.setConstraints(btnSearch, 0, 12);
			    	pane.setConstraints(btnClose, 1, 12);
			    	
			    	pane.getChildren().add(lblStaffIdentity);
			    	pane.getChildren().add(txtStaffIdentity); 
			    	pane.getChildren().add(lblFirstName);
			    	pane.getChildren().add(txtFirstName);
			    	pane.getChildren().add(lblSurName);
			    	pane.getChildren().add(txtSurName);
			    	pane.getChildren().add(lblAddress);
			    	pane.getChildren().add(txtAddress);
			    	pane.getChildren().add(lblEmail);
			    	pane.getChildren().add(txtEmail);
			    	pane.getChildren().add(lblGender);
			    	pane.getChildren().add(rbMale);
			    	pane.getChildren().add(rbFemale);
			    	pane.getChildren().add(lblPhoneNumber);
			    	pane.getChildren().add(txtPhoneNumber);
			    	pane.getChildren().add(lblDateOfBirth);
			    	pane.getChildren().add(datePicker);
			    	pane.getChildren().add(lblUserType);
			    	pane.getChildren().add(cmbUserType);
			    	pane.getChildren().add(lblUserAlias);
			    	pane.getChildren().add(txtUserAlias);
			    	pane.getChildren().add(lblCredential);
			    	pane.getChildren().add(txtCredential);
			    	pane.getChildren().add(btnSearch);
			    	pane.getChildren().add(btnClose);


			    	
			    	Scene scene = new Scene(pane);
			    	primaryStage.setScene(scene);
			    	
			    	primaryStage.setTitle("Search Staff's Info");
			    	primaryStage.setWidth(600);
			    	primaryStage.setHeight(500);
			    	primaryStage.setResizable(false);
			    	
			    	primaryStage.show();
			    	
			}

			public StaffUser searchRecord(int staffIdentity) {
				  StaffUser staff = null;
				  String DRIVER = "com.mysql.cj.jdbc.Driver";
				  String HOST = "localhost";
				  int PORT = 3306;
				  String DATABASE = "SMS";
				  String DBUSER = "root";
				  String DBPASS ="neera@12";
				  String URL ="jdbc:mysql://"+HOST+":"+PORT+"/"+DATABASE;
				  String sql = "SELECT * FROM StaffUsers WHERE staffIdentity =?";
				  try {
					  Class.forName(DRIVER);  //loading driver
					  Connection conn = DriverManager.getConnection(URL, DBUSER, DBPASS);
					  PreparedStatement pstat = conn.prepareStatement(sql);
					  pstat.setInt(1, staffIdentity);
					  ResultSet rs = pstat.executeQuery();
					  while(rs.next()){
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
							staff = new StaffUser(staffIdentity, firstName, surName, address, email,
				    				gender, phoneNumber, dateOfBirth, userType, userAlias, credential);
							
						  }
						  pstat.close();
						  conn.close();
					
						  
					  }
					  catch(Exception ex){
						  System.out.println("Error");
					  }
					  return staff;
				  }
				} 



