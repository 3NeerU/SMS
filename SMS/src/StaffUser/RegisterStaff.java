package StaffUser;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import Client.Client;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class RegisterStaff extends Application {

	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		   Label lblTitle = new Label("Staff Registration Form");
	        lblTitle.getStyleClass().add("label-title");
	        
		 Label lblStaffIdentity = new Label("Staff Identity: ");
	        Label lblFirstName = new Label("First Name: ");
	        Label lblSurName = new Label("Surname: ");
	        Label lblAddress = new Label("Address: ");
	        Label lblEmail = new Label("Email: ");
	        Label lblGender = new Label("Gender: ");
	        Label lblPhoneNumber = new Label("Phone Number: ");
	        Label lblDateOfBirth = new Label("Date of Birth: ");
	        Label lblUser = new Label("User: ");
	        Label lblAlias = new Label("User Alias: ");
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
	        
	     	ComboBox cmbUser = new ComboBox();  // gives a drop down arrow list
	    	List lstUser = new ArrayList(); 
	    	lstUser.add("Staff");
	    	lstUser.add("Client");
	    	lstUser.add("Worker");
	    	cmbUser.getItems().addAll(lstUser);
	    	
	        TextField txtAlias = new TextField();
	        PasswordField txtCredential = new PasswordField(); //hides the content
	        
	        Button btnSave = new Button("Save");
	        btnSave.getStyleClass().add("save-button");
	    	Button btnClose= new Button("Close");
	    	btnClose.getStyleClass().add("close-button");
	    	btnClose.setOnAction((event)->{
	    		primaryStage.close();
	    	});
	    	btnSave.setOnAction((event)->{
	    		//Reading values from UI
	    		int clientIdentity = Integer.parseInt(txtStaffIdentity.getText());  //string->INT
	    		String firstName = txtFirstName.getText();
	    		String surName = txtSurName.getText();
	    		String address = txtAddress.getText();
	    		String email = txtEmail.getText();
	    		if (!email.toLowerCase().endsWith("@gmail.com")) {
    		        showDialog("Write email like: example@gmail.com", Alert.AlertType.ERROR);
    		        return;
    		    }
	    		String gender = "Female";
	    		if(rbMale.isSelected()==true) {
	    			gender="Male";
	    		}
	    		String phoneNumber = txtPhoneNumber.getText();
	    		String dateOfBirth = datePicker.getValue() != null ? datePicker.getValue().toString() : "";    	
	    		String user = cmbUser.getValue().toString();
	    		String alias = txtAlias.getText();
	             String credential = txtCredential.getText();
	             if (phoneNumber.length() != 10) {
	                 showDialog("Write 10 digit Phone Number",Alert.AlertType.INFORMATION);
	                 return;
	             }
	    		StaffUser staff =  new StaffUser(clientIdentity, firstName, surName, address, email,
	    				gender, phoneNumber, dateOfBirth, user, alias , credential);
	    		boolean res = saveRecord(staff); //callMethod
	    		if(res==true) {
	    			showDialog(" Staff Registered ", primaryStage);
	    		
	    			System.out.println("Record Inserted!");
	    		}
	    		else {
	    			System.out.println("Error to Insert Record");
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
	    	
	    	pane.setConstraints(lblUser, 0, 9);
	    	pane.setConstraints(cmbUser, 1, 9);
	    	
	    	pane.setConstraints(lblAlias, 0, 10);
	    	pane.setConstraints(txtAlias, 1, 10);
	    	
	    	pane.setConstraints(lblCredential, 0, 11);
	    	pane.setConstraints(txtCredential, 1, 11);
	  
	    	
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
	    	pane.getChildren().add(lblUser);
	    	pane.getChildren().add(cmbUser);
	    	pane.getChildren().add(lblAlias);
	    	pane.getChildren().add(txtAlias);
	    	pane.getChildren().add(lblCredential);
	    	pane.getChildren().add(txtCredential);
	    	
	    	 BorderPane bPane = new BorderPane();
	         bPane.setTop(lblTitle);
	         BorderPane.setAlignment(lblTitle, Pos.CENTER); 
	         bPane.setCenter(pane); 

	         
	         HBox button = new HBox(10); 
	         button.setAlignment(Pos.CENTER); 
	         button.getChildren().addAll(btnSave, btnClose); 
	         
	         bPane.setStyle("-fx-background-color: #CCCCCC;");

	         bPane.setBottom(button); 

	    	Scene scene = new Scene(bPane);
	    	primaryStage.setScene(scene);
	    	
	    
	    	String LoginPath = getClass().getResource("/Login/style.css").toExternalForm();
	    	scene.getStylesheets().add(LoginPath);

	    	primaryStage.setTitle("Insert Staff's Info");
	    	primaryStage.setWidth(700);
	    	primaryStage.setHeight(600);
	    	primaryStage.setResizable(false);
	    	
	    	primaryStage.show();
	}
	public boolean saveRecord(StaffUser staff) {
		  boolean result = false;
		  String DRIVER = "com.mysql.cj.jdbc.Driver";
		  String HOST = "localhost";
		  int PORT = 3306;
		  String DATABASE = "SMS";
		  String DBUSER = "root";
		  String DBPASS ="neera@12";
		  String URL ="jdbc:mysql://"+HOST+":"+PORT+"/"+DATABASE;
		  String sql = "INSERT INTO StaffUsers (staffIdentity, firstName, surName, address, email, gender, phoneNumber, dateOfBirth, userType,  userAlias, credential) " +
		             "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		  try {
			  Class.forName(DRIVER);  //loading driver
			  Connection conn = DriverManager.getConnection(URL, DBUSER, DBPASS);
			  PreparedStatement pstat = conn.prepareStatement(sql);
			  pstat.setInt(1,  staff.getStaffIdentity());
			  pstat.setString(2, staff.getFirstName());
			  pstat.setString(3, staff.getSurName());
			  pstat.setString(4, staff.getAddress());
			  pstat.setString(5, staff.getEmail());
			  pstat.setString(6, staff.getGender());
			  pstat.setString(7, staff.getPhoneNumber());
			  pstat.setDate(8,Date.valueOf(staff.getDateOfBirth()));
			  pstat.setString(9, staff.getUserType());
			  pstat.setString(10, staff.getUserAlias());
			  pstat.setString(11, staff.getCredential());
			  pstat.executeUpdate();
			  pstat.close();
			  conn.close();
			  result = true;
			  
		  }
		  catch(Exception ex){
			  System.out.println("Error");
		  }
		  return result;
	  }
	  public void showDialog(String message, Stage primaryStage) {
		   	Alert alert = new Alert(Alert.AlertType.INFORMATION);
		   	alert.setTitle("Staff");
		   	alert.setHeaderText(null);
		   	alert.setContentText(message);
		   	alert.setOnCloseRequest(event->{
		   		primaryStage.close();
		   	
		   	});
		   	alert.showAndWait();
	   }
	  public void showDialog(String message, Alert.AlertType type) {
	        Alert alert = new Alert(type);
	        alert.setTitle("Client");
	        alert.setHeaderText(null);
	        alert.setContentText(message);
	        alert.showAndWait();
	    }
}
