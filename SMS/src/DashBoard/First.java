 package DashBoard;

import java.util.ArrayList;
import java.util.List;

import Client.RegisterClient;
import Login.ClientLogin;
import Login.StaffLogin;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class First extends Application{

    public static void main(String[] args) {
        launch(args);}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		  Label lblImg = new Label(); // adds label in UI
		  Label lbl1 = new Label("Welcome to NN Service Provider");
		  lbl1.getStyleClass().add("label-lbl1");
		  
		  Label lbl2 = new Label("Service at your door");
		  lbl2.getStyleClass().add("label-lbl2");
		  
		  Label lbl3 = new Label("Select User type to login!");
		  Label lbl4 = new Label("New? Register here ->");
		  
	        Image image = new Image("file:///C:/Users/Asus/Downloads/dove.png");

	        ImageView View = new ImageView(image);
	       
	        View.setFitWidth(150); // Set the width
	        View.setFitHeight(150);
	       
	     	ComboBox cmbUserType = new ComboBox<>();  // gives a drop down arrow list
	    	List lstUserType = new ArrayList<>(); 
	    	lstUserType.add("Staff");
	    	lstUserType.add("Client");
	    	
	    	cmbUserType.getItems().addAll(lstUserType);
	       
	    	  Button btnLogin = new Button("Login");
	    	  btnLogin.setDisable(true);
	    	  
	          cmbUserType.setOnAction(event -> 
	          btnLogin.setDisable(cmbUserType.getValue() == null));

	    	  btnLogin.setOnAction(event -> {
	    		  String selectedUser = cmbUserType.getValue().toString();

	    		  
	    		    if (selectedUser != null && selectedUser.equals("Staff")) {
	    		        StaffLogin staff = new StaffLogin();
	    		        Stage welcomeStage = new Stage();
	    		        try {
	    		            staff.start(welcomeStage);
	    		        } catch (Exception e) {
	    		            System.out.println("Error");
	    		        }
	    		    } else if (selectedUser != null && selectedUser.equals("Client")) {
	    		        ClientLogin client = new ClientLogin();
	    		        Stage welcomeStage = new Stage();
	    		        try {
	    		            client.start(welcomeStage);
	    		        } catch (Exception e) {
	    		            System.out.println("Error");
	    		        }
	    		    }
	          });
	    	  Button btnRegister = new Button("Register");
	    	  btnRegister.setOnAction((event)->{
	    		  RegisterClient register = new RegisterClient();
	              Stage registerStage = new Stage();
	              try {
	                  register.start(registerStage);
	              } catch (Exception e) {
	                  e.printStackTrace();
	              }
		    	}); //when click opens the RegisterClient page
        
	    	 VBox vbox = new VBox(10); 
		        vbox.setAlignment(Pos.TOP_CENTER);
		        vbox.getChildren().addAll( View , lbl1, lbl2, lbl3, cmbUserType); 
		        vbox.getChildren().addAll(btnLogin);
		        vbox.setStyle("-fx-background-color: #CCCCCC;");

		        Scene scene = new Scene(vbox);
		        vbox.getChildren().addAll(lbl4, btnRegister);
		        
         String LoginPath = getClass().getResource("/Login/style.css").toExternalForm();
	    	scene.getStylesheets().add(LoginPath);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Dashboard");
        primaryStage.setWidth(700);
    	primaryStage.setHeight(600);
    	primaryStage.setResizable(false);
    	
    	primaryStage.show();
      
	}
	}


