package Client;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.geometry.Pos;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
public class UpdateClient extends Application {

			 public static void main(String[] args) {
				  launch(args);
			  }  // main Method
			
			@Override
			public void start(Stage primaryStage) throws Exception {
				Label lblTitle = new Label("Update Details");
		        lblTitle.getStyleClass().add("label-title");
		        
				  Label lblClientIdentity = new Label("Client Identity: ");
			        Label lblFirstName = new Label("First Name: ");
			        Label lblSurName = new Label("Surname: ");
			        Label lblAddress = new Label("Address: ");
			        Label lblEmail = new Label("Email: ");
			        Label lblGender = new Label("Gender: ");
			        Label lblPhoneNumber = new Label("Phone Number: ");
			        Label lblDateOfBirth = new Label("Date of Birth: ");
			        Label lblUserType = new Label("User: ");
			        Label lblAlias = new Label("User Alias: ");
			        Label lblCredential = new Label("Credential: ");
			        
			        TextField txtClientIdentity = new TextField();
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
			        
			        TextField txtAlias = new TextField();
			        PasswordField txtCredential = new PasswordField(); //hides the content
			        
			        Button btnSearch = new Button("Search");
			        btnSearch.getStyleClass().add("button");

			    	btnSearch.setOnAction((event)->{
			    		//Reading values from UI
			    		int clientIdentity = Integer.parseInt(txtClientIdentity.getText());  //string->INT
			    		Client customer =  searchRecord(clientIdentity);
			    		
			    		if(customer!=null) {
			    			txtFirstName.setText(customer.getFirstName());
			    			txtSurName.setText(customer.getSurName());
			    			txtAddress.setText(customer.getAddress());
			    			txtEmail.setText(customer.getEmail());
			    			
			    			//Gender
			    			if(customer.getGender().equals("Male")) {
			    				rbMale.setSelected(true);
			    			}
			    			else {
			    				rbFemale.setSelected(true);
			    			}
			    			 txtPhoneNumber.setText(customer.getPhoneNumber());
			    			datePicker.setValue(LocalDate.parse(customer.getDateOfBirth()));
			    			
			                cmbUserType.setValue(customer.getUserType());

			                txtAlias.setText(customer.getAlias());
			                txtCredential.setText(customer.getCredential());
			    			System.out.println("Record Found !");		
			    		}
			    		else {
			    			System.out.println("Error to Find Record");
			    		}
			    	});
			    	Button btnUpdate=new Button("Update");
			    	btnUpdate.getStyleClass().add("button");

					btnUpdate.setOnAction((event)->{
						//Reading values from UI
			    		int clientIdentity = Integer.parseInt(txtClientIdentity.getText());  //string->INT
						String firstName = txtFirstName.getText();
			    		String surName = txtSurName.getText();
			    		String address = txtAddress.getText();
			    		String email = txtEmail.getText();
			    		String gender = "Female";
			    		if(rbMale.isSelected()==true) {
			    			gender="Male";
			    		}
			    		String phoneNumber = txtPhoneNumber.getText();
			    		String dateOfBirth = datePicker.getValue() != null ? datePicker.getValue().toString() : "";
			     		String userType = cmbUserType.getValue().toString();
			     		
			     		System.out.println("Selected User: " + userType); //debug
			            String userAlias = txtAlias.getText();
			             String credential = txtCredential.getText(); 		
						
			             Client customer =  new Client(clientIdentity, firstName, surName, address, email,
				    				gender, phoneNumber, dateOfBirth, userType,
				    			userAlias, credential);
				    		boolean res = UpdateRecord(customer); //callMethod
				    		if(res==true) {
				    			System.out.println("Record Updated!");
				    		}
				    		else {
				    			System.out.println("Error to Update Record");
				    		}
				    	});
			    	Button btnClose= new Button("Close");
			    	btnClose.getStyleClass().add("button");

			    	btnClose.setOnAction((event)->{
			    		primaryStage.close();
			    	});
			    	GridPane pane = new GridPane();
			    	pane.setConstraints(lblClientIdentity, 0, 0); //col, row
			    	pane.setConstraints(txtClientIdentity, 1, 0);
			    	
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
			    	
			    	pane.setConstraints(lblAlias, 0, 10);
			    	pane.setConstraints(txtAlias, 1, 10);
			    	
			    	pane.setConstraints(lblCredential, 0, 11);
			    	pane.setConstraints(txtCredential, 1, 11);
			    	
			    	pane.setConstraints(btnSearch, 0, 12);
			    	pane.setConstraints(btnUpdate, 1, 12);
			    	pane.setConstraints(btnClose, 2, 12);
			    	
			    	pane.getChildren().add(lblClientIdentity);
			    	pane.getChildren().add(txtClientIdentity);
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
			    	pane.getChildren().add(lblAlias);
			    	pane.getChildren().add(txtAlias);
			    	pane.getChildren().add(lblCredential);
			    	pane.getChildren().add(txtCredential);
			    	pane.getChildren().add(btnSearch);
			    	BorderPane bPane = new BorderPane();
			         bPane.setTop(lblTitle);
			         BorderPane.setAlignment(lblTitle, Pos.CENTER); 
			         bPane.setCenter(pane); 

			         
			         HBox button = new HBox(10); 
			         button.setAlignment(Pos.CENTER); 
			         button.getChildren().addAll(btnSearch,btnUpdate, btnClose); 
			         
			         bPane.setStyle("-fx-background-color: #CCCCCC;");

			         bPane.setBottom(button); 

			    	Scene scene = new Scene(bPane);
			    	primaryStage.setScene(scene);
			    	
			    	String LoginPath = getClass().getResource("/Login/style.css").toExternalForm();
			    	scene.getStylesheets().add(LoginPath);
			    	primaryStage.setTitle("Insert Client's Info");
			    	primaryStage.setWidth(600);
			    	primaryStage.setHeight(500);
			    	primaryStage.setResizable(false);
			    	
			    	primaryStage.show();
			}

			public Client searchRecord(int clientIdentity) {
				 Client customer = null;
				  String DRIVER = "com.mysql.cj.jdbc.Driver";
				  String HOST = "localhost";
				  int PORT = 3306;
				  String DATABASE = "SMS";
				  String DBUSER = "root";
				  String DBPASS ="neera@12";
				  String URL ="jdbc:mysql://"+HOST+":"+PORT+"/"+DATABASE;
				  String sql = "SELECT * FROM Clients WHERE clientIdentity =?";
				  try {
					  Class.forName(DRIVER);  //loading driver
					  Connection conn = DriverManager.getConnection(URL, DBUSER, DBPASS);
					  PreparedStatement pstat = conn.prepareStatement(sql);
					  pstat.setInt(1, clientIdentity);
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
							String alias = rs.getString("alias");
							String credential = rs.getString("credential");
							customer = new Client(clientIdentity, firstName, surName, address, email,
				    				gender, phoneNumber, dateOfBirth, userType, alias, credential);
							
						  }
						  pstat.close();
						  conn.close();
					
						  
					  }
					  catch(Exception ex){
						  System.out.println("Error");
					  }
					  return customer;
				  }
				  
			public boolean UpdateRecord(Client customer) {
				
				boolean result = false;
				String DRIVER ="com.mysql.cj.jdbc.Driver";
				String HOST ="localhost";
				int PORT=3306;
				String DATABASE ="SMS";
				String DBUSER="root";
				String DBPASS="neera@12";
				String URL = "jdbc:mysql://"+HOST+":"+PORT+"/"+DATABASE;
				String sql="UPDATE Clients SET firstName=?, surName=?, address=?, email=?, gender=?, phoneNumber=?, dateOfBirth=?, userType=?, alias=?, credential=? WHERE clientIdentity=?";

						try {
					Class.forName(DRIVER); //loading driver
					Connection conn = DriverManager.getConnection(URL, DBUSER, DBPASS);//connection with database server
					PreparedStatement pstat = conn.prepareStatement(sql);			
					
					  pstat.setString(1, customer.getFirstName());
					  pstat.setString(2, customer.getSurName());
					  pstat.setString(3, customer.getAddress());
					  pstat.setString(4, customer.getEmail());
					  pstat.setString(5, customer.getGender());
					  pstat.setString(6, customer.getPhoneNumber());
					  pstat.setDate(7,Date.valueOf(customer.getDateOfBirth()));
					  pstat.setString(8, customer.getUserType());
					  pstat.setString(9, customer.getAlias());
					  pstat.setString(10, customer.getCredential());
					  pstat.setInt(11, customer.getClientIdentity());
					
					  pstat.executeUpdate();
					  pstat.close();
					  conn.close();
					  result = true;
				}
				catch(Exception ex) {
					System.out.println("Error : "+ex.getMessage()); //describes the error message
				}
				return result;
			}
				} 
