package Login;
import java.sql.Connection;
import Login.globalvariable;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import DashBoard.WelcomeClient;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
	public class ClientLogin extends Application{

	    public static void main(String[] args) {
	    	 launch(args);
	    }

	    @Override
	    public void start(Stage primaryStage) throws Exception {
	        Label lblUserAlias = new Label("ALIAS : ");
	        Label lblCredential = new Label("CREDENTIAL : "); 

	        TextField txtUserAlias = new TextField();
	        PasswordField txtCredential = new PasswordField();

	        Image customerImage = new Image("file:C:\\Users\\Asus\\OneDrive\\Pictures\\Saved Pictures\\customer.png");
	        ImageView customerView = new ImageView(customerImage);
	        customerView.setFitWidth(100); // Set the width
	        customerView.setFitHeight(100);
	        
	        Button btnLogin = new Button("Login");
	        Button btnClose = new Button("Close");

	        GridPane pane = new GridPane();
	        pane.setAlignment(Pos.CENTER); 
	        pane.setHgap(10);
	        pane.setVgap(10);

	        pane.add(customerView, 1, 0,3,1);
	        pane.add(lblUserAlias, 0, 1);
	        pane.add(txtUserAlias, 1, 1);

	        pane.add(lblCredential, 0, 2);
	        pane.add(txtCredential, 1, 2);

	        pane.add(btnLogin, 0, 4);
	        pane.add(btnClose, 1, 4);
	        
	        btnLogin.setOnAction(event -> {
	            String username = txtUserAlias.getText(); // Assuming Alias is the username
	            String password = txtCredential.getText(); // Assuming Credential is the password
	            int check=loginUser(username, password);
	            
	           if (check>0) {
	        	   globalvariable.id=check;
               	System.out.println(globalvariable.id);
	                	WelcomeClient client= new WelcomeClient();
	                    Stage welcomeStage = new Stage();
	                    try {
	                    	WelcomeClient welcomeClient = new WelcomeClient();
							welcomeClient.start(welcomeStage);
						} catch (Exception e) {
							System.out.println("Error");
						}
	             
	            }   
	        else {
	        	globalvariable.id=1;
	                System.out.println("error");
	            } 
	        });

	        btnClose.setOnAction(event -> {
	            primaryStage.close(); // Close the window
	        });

	        Scene scene = new Scene(pane);
	        primaryStage.setScene(scene);
	        primaryStage.setTitle("Client Login");
	        primaryStage.setWidth(650);
	        primaryStage.setHeight(450);
	        primaryStage.setResizable(false);
	        primaryStage.show();
	    }

	    public int loginUser(String user, String credential) {
	        int result = 0;
	        String DRIVER = "com.mysql.cj.jdbc.Driver";
	        String HOST = "localhost";
	        int PORT = 3306;
	        String DATABASE = "SMS";
	        String DBUSER = "root";
	        String DBPASS = "neera@12";
	        String URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE;
	        String sql = "SELECT * FROM clients where alias =? and credential=?";
	        try {
	            Class.forName(DRIVER); // loading driver
	            Connection conn = DriverManager.getConnection(URL, DBUSER, DBPASS); // connection with database server
	            PreparedStatement pstat = conn.prepareStatement(sql);
	            pstat.setString(1, user);
	            pstat.setString(2, credential);
	            ResultSet rs = pstat.executeQuery(); // Insert Record
	            if (rs.next()) { 
	            	int clientIdentity=rs.getInt("clientIdentity");
	            	result = clientIdentity;
	            }
	            else {
	                showErrorDialog("Incorrect username or password.");
	            }
	            pstat.close();
	            conn.close();
	        } catch (Exception ex) {
	            System.out.println("Error : " + ex.getMessage());
	        }
	        return result;
	    }


	    private void showErrorDialog(String errorMessage) {
	        Alert alert = new Alert(Alert.AlertType.ERROR);
	        alert.setTitle("Login Error");
	        alert.setHeaderText(null);
	        alert.setContentText(errorMessage);
	        alert.showAndWait();
	    }
	}


