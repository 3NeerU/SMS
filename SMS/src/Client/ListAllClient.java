package Client;
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

public class ListAllClient extends Application{

		public static void main(String[] args) {
		    launch(args);
		}

		@Override
		public void start(Stage primaryStage) throws Exception {
			
			TableView<Client> table1 = new TableView<>();
			table1.setPrefWidth(1050);
			table1.setPrefHeight(250);
			
			//Table columns Name
			TableColumn<Client, Integer> colClientIdentity = new TableColumn<>("ClientId");
			colClientIdentity.setCellValueFactory(new PropertyValueFactory<>("clientIdentity"));
			colClientIdentity.setMinWidth(50);
			
			TableColumn<Client, Integer> colFirstName = new TableColumn<>("FirstName");
			colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
			colFirstName.setMinWidth(150);
			
			TableColumn<Client, Integer> colSurName = new TableColumn<>("SurName");
			colSurName.setCellValueFactory(new PropertyValueFactory<>("surName"));
			colSurName.setMinWidth(150);
			
			TableColumn<Client, Integer> colAddress = new TableColumn<>("Address");
			colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
			colAddress.setMinWidth(150);
			
			TableColumn<Client, Integer> colEmail = new TableColumn<>("Email");
			colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
			colEmail.setMinWidth(150);
			
			TableColumn<Client, Integer> colGender = new TableColumn<>("Gender");
			colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
			colGender.setMinWidth(75);
			
			TableColumn<Client, Integer> colPhoneNumber= new TableColumn<>("PhoneNumber");
			colPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
			colPhoneNumber.setMinWidth(50);
			
			TableColumn<Client, Integer> colDateOfBirth = new TableColumn<>("DateOfBirth");
			colDateOfBirth.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
			colDateOfBirth.setMinWidth(75);
			
			TableColumn<Client, Integer> colAlias= new TableColumn<>("Alias");
			colAlias.setCellValueFactory(new PropertyValueFactory<>("alias"));
			colAlias.setMinWidth(50);
			
			
			table1.getColumns().addAll(colClientIdentity, colFirstName, colSurName, colAddress, colEmail,
					colGender, colPhoneNumber, colDateOfBirth, colAlias);
			
			
			Button btnClose= new Button("Close");
			btnClose.setOnAction((event)->{
				primaryStage.close();
			});
			
			ArrayList customer = allRecords();
		
			 table1.getItems().addAll(customer);
		// retrieves all customers	 
		        
		        VBox vbox = new VBox(); // VBox layout
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
			
			  
			  ArrayList<Client> customer = new ArrayList<>();
			  String DRIVER = "com.mysql.cj.jdbc.Driver";
			  String HOST = "localhost";
			  int PORT = 3306;
			  String DATABASE = "SMS";
			  String DBUSER = "root";
			  String DBPASS ="neera@12";
			  String URL ="jdbc:mysql://"+HOST+":"+PORT+"/"+DATABASE;
			  String sql ="SELECT clientIdentity, firstName, surName, address, email, gender, phoneNumber, dateOfBirth, alias FROM Clients";
			  try {
				  Class.forName(DRIVER);  //loading driver
				  Connection conn = DriverManager.getConnection(URL, DBUSER, DBPASS);
				  PreparedStatement pstat = conn.prepareStatement(sql);
				  ResultSet rs = pstat.executeQuery();
				  while(rs.next()){
					  int clientIdentity = rs.getInt("clientIdentity");
						String firstName = rs.getString("firstName");  
						String surName = rs.getString("surName");  
						String address = rs.getString("address");
						String email = rs.getString("email");
						String gender = rs.getString("gender");
						String phoneNumber = rs.getString("phoneNumber");  
						String dateOfBirth = rs.getString("dateOfBirth");
						String alias = rs.getString("alias");
						Client customers = new Client(clientIdentity, firstName, surName, address, email, gender, phoneNumber, dateOfBirth, alias);
						customer.add(customers);
					  }
				  pstat.close();
				  conn.close();
			
				  
			  
		  } catch (ClassNotFoundException e) {
		        e.printStackTrace(); //class loading errors
		    } catch (SQLException e) {
		        e.printStackTrace(); //  database errors
		    }
			  return customer;
		}
		 
		}

