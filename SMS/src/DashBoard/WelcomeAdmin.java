package DashBoard;

import Appointment.AppointmentHistory;
import Client.ListAllClient;
import Client.RegisterClient;
import Login.StaffLogin;
import Quote.QuoteHistory;
import Service.Earnings;
import Service.ListAllServices;
import StaffUser.ListAllStaff;
import StaffUser.RegisterStaff;
import StaffUser.StaffUser;
import StaffUser.UpdateStaff;
import Worker.ListAllWorkers;
import Worker.RegisterWorker;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;



public class WelcomeAdmin  extends Application{

	    public static void main(String[] args) {
	        launch(args);
	    }

	    @Override
	    public void start(Stage primaryStage) throws Exception {
	    
	        Label lbl1 = new Label("Logged in as Staff...");
	        Label lbl2 = new Label("Welcome Admin");
	        lbl2.getStyleClass().add("label-lbl2");

	        Label lbl3 = new Label("List of Tasks");
	        lbl3.getStyleClass().add("label-title");

	       
	        // puts the image in UI
	        Image registerStaffImage = new Image("file:C:\\Users\\Asus\\OneDrive\\Pictures\\Saved Pictures\\staff.png");
	        ImageView registerStaffImageView = new ImageView(registerStaffImage);
	        registerStaffImageView.setFitWidth(50); // the width
	        registerStaffImageView.setFitHeight(50);
	       
	        Button btnRegisterStaff = new Button("Register Staff",registerStaffImageView);
	        btnRegisterStaff.setOnAction((event)->{
	        	RegisterStaff staff = new RegisterStaff(); // opens RegisterStaff class
	            Stage registerStage = new Stage();
	            try {
	            	staff.start(registerStage);
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        	    
	    	});
	        
	        Image registerWorkerImage = new Image("file:C:\\Users\\Asus\\OneDrive\\Pictures\\Saved Pictures\\worker.png");
	        ImageView registerWorkerView = new ImageView(registerWorkerImage);
	        registerWorkerView.setFitWidth(50); // the width
	        registerWorkerView.setFitHeight(50);
	     
	        Button btnRegisterWorker = new Button("Register Worker", registerWorkerView);
	        btnRegisterWorker.setOnAction((event)->{
	            RegisterWorker registerWorker = new RegisterWorker();// opens RegisterWorker class
	            Stage registerStage = new Stage();
	            try {
	                registerWorker.start(registerStage);
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        	    
	    	});

	        Image registerCustomerImage = new Image("file:C:\\Users\\Asus\\OneDrive\\Pictures\\Saved Pictures\\customer.png");
	        ImageView registerCustomerView = new ImageView(registerCustomerImage);
	        registerCustomerView.setFitWidth(50); // the width
	        registerCustomerView.setFitHeight(50);

	        Button btnRegisterClient = new Button("Register Client", registerCustomerView);
	        btnRegisterClient.setOnAction((event)->{
	            RegisterClient registerClient = new RegisterClient(); //opens RegisterClient
	            Stage registerStage = new Stage();
	            try {
	                registerClient.start(registerStage);
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        	    
	    	});
	        
	        Image customerDetailImage = new Image("file:C:\\Users\\Asus\\OneDrive\\Pictures\\Saved Pictures\\listCustomer.png");
	        ImageView customerDetailView = new ImageView(customerDetailImage);
	        customerDetailView.setFitWidth(50); // Set the width
	        customerDetailView.setFitHeight(50);

	        Button btnCustomerDetail = new Button("CustomerDetail", customerDetailView);
	        btnCustomerDetail.setOnAction((event)->{
	            ListAllClient client = new ListAllClient();//opens ListAllClient
	            Stage registerStage = new Stage();
	            try {
	                client.start(registerStage);
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        	    
	    	});
	        
	        Image staffDetailImage = new Image("file:C:\\Users\\Asus\\OneDrive\\Pictures\\Saved Pictures\\ListStaff.png");
	        ImageView staffDetailView = new ImageView(staffDetailImage);
	        staffDetailView.setFitWidth(50); // Set the width
	        staffDetailView.setFitHeight(50);
	        
	        Button btnStaffDetail = new Button("Staff Detail", staffDetailView);
	        btnStaffDetail.setOnAction((event)->{
	            ListAllStaff staff = new ListAllStaff();
	            Stage registerStage = new Stage();
	            try {
	            	staff.start(registerStage);
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        	    
	    	});
	        
	        Image workerDetailImage = new Image("file:C:\\Users\\Asus\\OneDrive\\Pictures\\Saved Pictures\\listWorker.png");
	        ImageView workerDetailView = new ImageView(workerDetailImage);
	        workerDetailView.setFitWidth(50); // Set the width
	        workerDetailView.setFitHeight(50);
	        
	        Button btnWorkerDetail = new Button("Worker Detail", workerDetailView);
	        btnWorkerDetail.setOnAction((event)->{
	            ListAllWorkers staff = new ListAllWorkers();
	            Stage registerStage = new Stage();
	            try {
	            	staff.start(registerStage);
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        	    
	    	});
	        
	        Image updateStaffImage = new Image("file:C:\\Users\\Asus\\OneDrive\\Pictures\\Saved Pictures\\updateStaff.png");
	        ImageView updateStaffView = new ImageView(updateStaffImage);
	        updateStaffView.setFitWidth(50); // Set the width
	        updateStaffView.setFitHeight(50);
	        
	        Button btnUpdateStaff = new Button("Update StaffDetail", updateStaffView);
	        btnUpdateStaff.setOnAction((event)->{
	            UpdateStaff staff = new UpdateStaff();
	            Stage registerStage = new Stage();
	            try {
	            	staff.start(registerStage);
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        	    
	    	});
	        Image ServiceDetail = new Image("file:C:\\Users\\Asus\\OneDrive\\Pictures\\Saved Pictures\\updateStaff.png");
	        ImageView ServiceDetailView = new ImageView(ServiceDetail);
	        ServiceDetailView.setFitWidth(50); // Set the width
	        ServiceDetailView.setFitHeight(50);
	        
	        Button btnViewService = new Button("View Service Detail", updateStaffView);
	        btnViewService.setOnAction((event)->{
	            ListAllServices service = new ListAllServices();
	            Stage registerStage = new Stage();
	            try {
	            	service.start(registerStage);
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        	    
	    	});
	      
	        Image appointment = new Image("file:C:\\Users\\Asus\\OneDrive\\Pictures\\Saved Pictures\\appointment.png");
	        ImageView appointmentView = new ImageView(appointment);
	        appointmentView.setFitWidth(50); // Set the width
	        appointmentView.setFitHeight(50);
	        
	        Button btnAppointmentHistory = new Button("AppointmentHistory", appointmentView);
	        btnAppointmentHistory.setOnAction((event)->{
	        	AppointmentHistory history = new AppointmentHistory();
	            Stage registerStage = new Stage();
	            try {
	            	history.start(registerStage);
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        	    
	    	});
	        Image Quote = new Image("file:C:\\Users\\Asus\\OneDrive\\Pictures\\Saved Pictures\\quotes.png");
	        ImageView QuoteView = new ImageView(Quote);
	        QuoteView.setFitWidth(50); 
	        QuoteView.setFitHeight(50);
	        
	        
	        Button btnQuoteHistory = new Button("Quote History", ServiceDetailView);
	        btnQuoteHistory.setOnAction((event)->{
	            QuoteHistory serviceHistory = new QuoteHistory();
	            Stage historyStage = new Stage();
	            try {
	                serviceHistory.start(historyStage);
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        });
	        Button btnTotalEarning = new Button("Total Earning", QuoteView);
	        btnTotalEarning.setOnAction((event)->{
	            Earnings earning = new Earnings();
	            Stage earningStage = new Stage();
	            try {
	                earning.start(earningStage);
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        });
	        Button btnLogout = new Button("Logout");
	        btnLogout.setOnAction(e -> {
	            Stage confirmationStage = new Stage();
	            
	            Label logoutMessage = new Label("You have been logged out.");
	            logoutMessage.setAlignment(Pos.CENTER);
	            
	            Button redirectButton = new Button("OK");
	            redirectButton.setOnAction(event -> {
	                confirmationStage.close();
	                openFirstPage();
	            });
	            VBox box = new VBox(20, logoutMessage, redirectButton);
	            box.setAlignment(Pos.CENTER);
	            box.setPadding(new Insets(20));

	            Scene scene = new Scene(box);
	            confirmationStage.setScene(scene);
	            confirmationStage.show();
	        });
	        
	        HBox leftButtonBox = new HBox(20, btnRegisterStaff, btnRegisterWorker, btnRegisterClient);
	        leftButtonBox.setAlignment(Pos.CENTER);
	        HBox rightButtonBox = new HBox(20, btnCustomerDetail, btnStaffDetail, btnWorkerDetail);
	        HBox box = new HBox(20, btnViewService, btnAppointmentHistory, btnQuoteHistory );
	        HBox box1 = new HBox(20, btnTotalEarning );

	        rightButtonBox.setAlignment(Pos.CENTER);

	        VBox buttonsContainer = new VBox(20, leftButtonBox, rightButtonBox, box, box1);
	        buttonsContainer.setAlignment(Pos.CENTER);
	        buttonsContainer.setPadding(new Insets(20));

	        VBox headerBox = new VBox(10, lbl1);
	        headerBox.setAlignment(Pos.TOP_LEFT);
	        headerBox.setPadding(new Insets(20));
	        
	        VBox centerBox = new VBox(20, lbl2, lbl3, buttonsContainer);
	        centerBox.setAlignment(Pos.TOP_CENTER);
	        
	        VBox footerBox = new VBox(20, btnLogout);
	        footerBox.setAlignment(Pos.BOTTOM_RIGHT);
	        
	        GridPane gridPane = new GridPane();
	        gridPane.setAlignment(Pos.TOP_CENTER);
	        gridPane.add(headerBox, 0, 0);
	        gridPane.add(centerBox, 0, 1);
	        gridPane.add(footerBox, 0, 2);
	        gridPane.setStyle("-fx-background-color: #CCCCCC;");

	        Scene scene = new Scene(gridPane, 800, 500);

	    	String LoginPath = getClass().getResource("/Login/style.css").toExternalForm();
	    	scene.getStylesheets().add(LoginPath);

	    	primaryStage.setTitle("Welcome");
	    	 primaryStage.setScene(scene);
	    	primaryStage.setWidth(700);
	    	primaryStage.setHeight(600);
	    	primaryStage.setResizable(false);
	    	
	    	primaryStage.show();
	        
	        }
	    


	    private void openFirstPage() {
	        // Create a new instance of the StaffLogin class and start it
	        First page = new First();
	        Stage stage = new Stage();
	        try {
	        	page.start(stage);
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	    }
	    }


