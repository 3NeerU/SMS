package Worker;


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

public class SearchWorkerRecord extends Application{
		 public static void main(String[] args) {
			  launch(args);
		  }  // main Method
		
		@Override
		public void start(Stage primaryStage) throws Exception {
			
			  Label lblWorkerIdentity = new Label("Worker Identity: ");
		        Label lblFirstName = new Label("First Name: ");
		        Label lblSurName = new Label("Surname: ");
		        Label lblAddress = new Label("Address: ");
		        Label lblEmail = new Label("Email: ");
		        Label lblGender = new Label("Gender: ");
		        Label lblPhoneNumber = new Label("Phone Number: ");
		        Label lblDateOfBirth = new Label("Date of Birth: ");
		        Label lblDepartment = new Label("Department: ");
		        Label lblDuty = new Label("Duty: ");
		        Label lblUserType = new Label("User: ");
		        Label lblShift = new Label("Shift:");
		        Label lblMastery = new Label("Mastery: ");
		        Label lblAlias = new Label("User Alias: ");
		        Label lblCredential = new Label("Credential: ");
		        
		        TextField txtWorkerIdentity = new TextField();
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
		        
		    	ComboBox cmbDepartment = new ComboBox();  // gives a drop down arrow list
		    	List lstDepartment = new ArrayList(); 
		    	lstDepartment.add("Plumbing Department");
		    	lstDepartment.add("Electrical Department");
		    	lstDepartment.add("Cleaning Department");
		    	lstDepartment.add("Gardening Department");
		    	cmbDepartment.getItems().addAll(lstDepartment);
		    	
		    	
		    	ComboBox cmbDuty = new ComboBox();  
		    	List<String> lstDuty = new ArrayList<>(); 
		    	lstDuty.add("Plumber");
		    	lstDuty.add("Electrician");
		    	lstDuty.add("Gardener"); 
		    	lstDuty.add("Cleaner");
		    	cmbDuty.getItems().addAll(lstDuty);
		    	
		    	ComboBox cmbUserType = new ComboBox();  // gives a drop down arrow list
		    	List lstUser = new ArrayList(); 
		    	lstUser.add("Staff");
		    	lstUser.add("Client");
		    	lstUser.add("Worker");
		    	cmbUserType.getItems().addAll(lstUser);

		        
		    	ListView<String> lstShift = new ListView<>();  // shows the list of the time of shift
		    	lstShift.getItems().addAll("8am to 12pm", "12pm to 4pm", "4pm to 8pm");

		    	CheckBox chkNovice = new CheckBox("Novice");
		        CheckBox chkIntermediate = new CheckBox("Intermediate");
		        CheckBox chkExpert = new CheckBox("Expert");
		        chkNovice.setSelected(true);
		        
		        TextField txtAlias = new TextField();
		        PasswordField txtCredential = new PasswordField(); //hides the content
		        
		        Button btnSearch = new Button("Search");
		    	Button btnClose= new Button("Close");
		    	btnClose.setOnAction((event)->{
		    		primaryStage.close();
		    	});
		    	btnSearch.setOnAction((event)->{
		    		//Reading values from UI
		    		int workerIdentity = Integer.parseInt(txtWorkerIdentity.getText());  //string->INT
		    		WorkerUser worker =  searchRecord(workerIdentity);
		    		
		    		if(worker!=null) {
		    			txtFirstName.setText(worker.getFirstName());
		    			txtSurName.setText(worker.getSurName());
		    			txtAddress.setText(worker.getAddress());
		    			txtEmail.setText(worker.getEmail());
		    			
		    			//Gender
		    			if(worker.getGender().equals("Male")) {
		    				rbMale.setSelected(true);
		    			}
		    			else {
		    				rbFemale.setSelected(true);
		    			}
		    			datePicker.setValue(LocalDate.parse(worker.getDateOfBirth()));

		    			txtPhoneNumber.setText(worker.getPhoneNumber());
		    			

		                cmbDepartment.setValue(worker.getDepartment());
		                cmbDuty.setValue(worker.getDuty());
		                cmbUserType.setValue(worker.getUserType());

		                lstShift.getItems().forEach(item -> {
		                    if (item.equals(worker.getShift())) {
		                        lstShift.getSelectionModel().select(item);
		                    }	
		                });
		            	int novice = worker.getNovice();
		    			int intermediate = worker.getIntermediate();
		    			int expert = worker.getExpert();
		    			
		    			chkNovice.setSelected(false);
		    			if(novice==1) {
		    				chkNovice.setSelected(true);
		    			}
		    			
		    			chkIntermediate.setSelected(false);
		    			if(intermediate==1) {
		    				chkIntermediate.setSelected(true);
		    			}
		    			
		    			chkExpert.setSelected(false);
		    			if(expert==1) {
		    				chkExpert.setSelected(true);
		    			}
		    			

		                txtAlias.setText(worker.getUserAlias());
		                txtCredential.setText(worker.getCredential());
		    			System.out.println("Record Found !");		
		    		}
		    		else {
		    			System.out.println("Error to Find Record");
		    		}
		    	});
		    	GridPane pane = new GridPane();
		    	pane.setConstraints(lblWorkerIdentity, 0, 0); //col, row
		    	pane.setConstraints(txtWorkerIdentity, 1, 0);
		    	
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
		    	
		    	pane.setConstraints(lblDepartment, 0, 9);
		    	pane.setConstraints(cmbDepartment, 1, 9);
		    	
		    	pane.setConstraints(lblDuty, 0, 10);
		    	pane.setConstraints(cmbDuty, 1, 10);
		    	
		    	pane.setConstraints(lblUserType, 0, 11);
		    	pane.setConstraints(cmbUserType, 1, 11);
		    	
		    	pane.setConstraints(lblShift, 0, 12);
		    	pane.setConstraints(lstShift, 1, 12);
		    	
		    	pane.setConstraints(lblMastery, 0, 13);
		    	pane.setConstraints(chkNovice, 1, 13);
		    	pane.setConstraints(chkIntermediate, 1, 14);
		    	pane.setConstraints(chkExpert, 1, 15);
		    	
		    	pane.setConstraints(lblAlias, 0, 16);
		    	pane.setConstraints(txtAlias, 1, 16);
		    	
		    	pane.setConstraints(lblCredential, 0, 17);
		    	pane.setConstraints(txtCredential, 1, 17);
		    	
		    	pane.setConstraints(btnSearch, 0, 18);
		    	pane.setConstraints(btnClose, 1, 18);
		    	
		    	pane.getChildren().add(lblWorkerIdentity);
		    	pane.getChildren().add(txtWorkerIdentity);
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
		    	pane.getChildren().add(lblDepartment);
		    	pane.getChildren().add(cmbDepartment);
		    	pane.getChildren().add(lblDuty);
		    	pane.getChildren().add(cmbDuty);
		    	pane.getChildren().add(lblUserType);
		    	pane.getChildren().add(cmbUserType);
		    	pane.getChildren().add(lblShift);
		    	pane.getChildren().add(lstShift);
		    	pane.getChildren().add(lblMastery);
		    	pane.getChildren().add(chkNovice);
		    	pane.getChildren().add(chkIntermediate);
		    	pane.getChildren().add(chkExpert);
		    	pane.getChildren().add(lblAlias);
		    	pane.getChildren().add(txtAlias);
		    	pane.getChildren().add(lblCredential);
		    	pane.getChildren().add(txtCredential);
		    	pane.getChildren().add(btnSearch);
		    	pane.getChildren().add(btnClose);


		    	
		    	Scene scene = new Scene(pane);
		    	primaryStage.setScene(scene);
		    	
		    	primaryStage.setTitle("Search Workers Info");
		    	primaryStage.setWidth(600);
		    	primaryStage.setHeight(500);
		    	primaryStage.setResizable(false);
		    	
		    	primaryStage.show();
		    	
		}

		public WorkerUser searchRecord(int workerIdentity) {
			  WorkerUser worker = null;
			  String DRIVER = "com.mysql.cj.jdbc.Driver";
			  String HOST = "localhost";
			  int PORT = 3306;
			  String DATABASE = "SMS";
			  String DBUSER = "root";
			  String DBPASS ="neera@12";
			  String URL ="jdbc:mysql://"+HOST+":"+PORT+"/"+DATABASE;
			  String sql = "SELECT firstName, surName, address, email, gender, phoneNumber, dateOfBirth, department, duty, userType, shift, novice, intermediate, expert, userAlias, credential FROM WorkerUsers WHERE workerIdentity =?";
			  try {
				  Class.forName(DRIVER);  //loading driver
				  Connection conn = DriverManager.getConnection(URL, DBUSER, DBPASS);
				  PreparedStatement pstat = conn.prepareStatement(sql);
				  pstat.setInt(1, workerIdentity);
				  ResultSet rs = pstat.executeQuery();
				  while(rs.next()){
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
						String credential = rs.getString("credential");
						worker = new WorkerUser(workerIdentity, firstName, surName, address, email,
			    				gender, phoneNumber, dateOfBirth, department, duty, shift, userType, 
			    				novice, intermediate, expert, userAlias, credential);
						
					  }
					  pstat.close();
					  conn.close();
				
					  
				  }
				  catch(Exception ex){
					  System.out.println("Error");
				  }
				  return worker;
			  }
			} 

