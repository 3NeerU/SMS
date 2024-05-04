package Worker;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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


public class RegisterWorker extends Application{
	 public static void main(String[] args) {
		  launch(args);
	  }  // main Method
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		 Label lblTitle = new Label("Worker Registration Form");
	        lblTitle.getStyleClass().add("label-title");
	        
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
	        Label lblUserType = new Label("UserType: ");
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
	    	List lstUserType = new ArrayList(); 
	    	lstUserType.add("Staff");
	    	lstUserType.add("Client");
	    	lstUserType.add("Worker");
	    	cmbUserType.getItems().addAll(lstUserType);

	        
	    	ListView<String> lstShift = new ListView<>();  // shows the list of the time of shift
	    	lstShift.getItems().addAll("8am to 12pm", "12pm to 4pm", "4pm to 8pm");

	    	CheckBox chkNovice = new CheckBox("Novice");
	        CheckBox chkIntermediate = new CheckBox("Intermediate");
	        CheckBox chkExpert = new CheckBox("Expert");
	        chkNovice.setSelected(true);
	        
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
	    		int workerIdentity = Integer.parseInt(txtWorkerIdentity.getText());  //string->INT
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
	    		String department = cmbDepartment.getValue().toString();    	
	     		String duty = cmbDuty.getValue().toString();
	     		String userType = cmbUserType.getValue().toString();
	    	    String shift = lstShift.getSelectionModel().getSelectedItem();
	    	   
	    		int novice =0, intermediate=0, expert=0;  //checkbox
	    		if(chkNovice.isSelected()==true) {
	    			novice = 1;
	    		}
	    		if(chkIntermediate.isSelected()==true) {
	    			intermediate = 1;
	    		}
	    		if(chkExpert.isSelected()==true) {
	    			expert = 1;
	    		}
	    		
	            String userAlias = txtAlias.getText();
	             String credential = txtCredential.getText();
	             if (phoneNumber.length() != 10) {
	                 showDialog("Write 10 digit Phone Number",Alert.AlertType.ERROR);
	                 return;
	             }
	    		WorkerUser worker =  new WorkerUser(workerIdentity, firstName, surName, address, email,
	    				gender, phoneNumber, dateOfBirth, department, duty, userType, shift,
	    				novice, intermediate, expert, userAlias, credential);
	    		boolean res = saveRecord(worker); //callMethod
	    		if(res==true) {
	    			showDialog("Worker Registered", primaryStage);
	    			System.out.println("Record Inserted!");
	    		}
	    		else {
	    			System.out.println("Error to Insert Record");
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

	    	primaryStage.setTitle("Insert Worker's Info");
	    	primaryStage.setWidth(700);
	    	primaryStage.setHeight(600);
	    	primaryStage.setResizable(false);
	    	
	    	primaryStage.show();
	    	
	}

	public boolean saveRecord(WorkerUser worker) {
		  boolean result = false;
		  String DRIVER = "com.mysql.cj.jdbc.Driver";
		  String HOST = "localhost";
		  int PORT = 3306;
		  String DATABASE = "SMS";
		  String DBUSER = "root";
		  String DBPASS ="neera@12";
		  String URL ="jdbc:mysql://"+HOST+":"+PORT+"/"+DATABASE;
		  String sql = "INSERT INTO WorkerUsers (workerIdentity, firstName, surName, address, email, gender, phoneNumber, dateOfBirth, department, duty, userType, shift, novice, intermediate, expert, userAlias, credential) " +
		             "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		  try {
			  Class.forName(DRIVER);  //loading driver
			  Connection conn = DriverManager.getConnection(URL, DBUSER, DBPASS);
			  PreparedStatement pstat = conn.prepareStatement(sql);
			  pstat.setInt(1,  worker.getWorkerIdentity());
			  pstat.setString(2, worker.getFirstName());
			  pstat.setString(3, worker.getSurName());
			  pstat.setString(4, worker.getAddress());
			  pstat.setString(5, worker.getEmail());
			  pstat.setString(6, worker.getGender());
			  pstat.setString(7, worker.getPhoneNumber());
			  pstat.setDate(8,Date.valueOf(worker.getDateOfBirth()));
			  pstat.setString(9, worker.getDepartment());
			  pstat.setString(10, worker.getDuty());
			  pstat.setString(11, worker.getUserType());
			  pstat.setString(12, worker.getShift());
			  pstat.setInt(13, worker.getNovice());
			  pstat.setInt(14, worker.getIntermediate());
			  pstat.setInt(15, worker.getExpert());
			  pstat.setString(16, worker.getUserAlias());
			  pstat.setString(17, worker.getCredential());
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
		   	alert.setTitle("Worker");
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

