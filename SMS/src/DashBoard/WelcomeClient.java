package DashBoard;

import Appointment.ViewAppointmentRequest;
import Client.RegisterClient;
import Client.UpdateClient;
import Quote.RequestQuote;
import Quote.ServiceBookingHistory;
import Service.ListAllServices;
import StaffUser.StaffUser;
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
public class WelcomeClient extends Application {
	
	    public static void main(String[] args) {
	        launch(args);
	    }

	    @Override
	    public void start(Stage primaryStage) throws Exception {
	    
	    	 Label lbl1 = new Label("Logged in as Customer...");
	        Label lbl2 = new Label("Welcome ");
	        lbl2.getStyleClass().add("label-lbl2");

	        Label lbl3 = new Label("List of Tasks");
	        lbl3.getStyleClass().add("label-title");
	        
	        Image updateCustomer = new Image("file:C:\\Users\\Asus\\OneDrive\\Pictures\\Saved Pictures\\updateCustomer.png");
	        ImageView updateCustomerView = new ImageView(updateCustomer);
	        updateCustomerView.setFitWidth(50); // Set the width
	        updateCustomerView.setFitHeight(50);
	        
	        Button btnUpdateCustomer = new Button("Update Info", updateCustomerView);
	        btnUpdateCustomer.setOnAction((event)->{
	            UpdateClient staff = new UpdateClient(); // opens UpdateClient Class
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
	            ListAllWorkers staff = new ListAllWorkers();// opens ListAllWorkers Class
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
	        
	        Button btnViewService = new Button("View Service Detail", ServiceDetailView);
	        btnViewService.setOnAction((event)->{
	            ListAllServices service = new ListAllServices();//opens ListAllServices Class
	            Stage registerStage = new Stage();
	            try {
	            	service.start(registerStage);
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        	    
	    	});
	        Image Quote = new Image("file:C:\\Users\\Asus\\OneDrive\\Pictures\\Saved Pictures\\quotes.png");
	        ImageView QuoteView = new ImageView(Quote);
	        QuoteView.setFitWidth(30); // Set the width
	        QuoteView.setFitHeight(30);
	        
	        Button btnRequestQuote = new Button("Request Quote", QuoteView);
	        btnRequestQuote.setOnAction((event)->{
	            RequestQuote quote = new RequestQuote();//opens RequestQuote Class
	            Stage quoteStage = new Stage();
	            try {
	            	quote.start(quoteStage);
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        	    
	    	});
	        Image appointment = new Image("file:C:\\Users\\Asus\\OneDrive\\Pictures\\Saved Pictures\\appointment.png");
	        ImageView appointmentView = new ImageView(appointment);
	        appointmentView.setFitWidth(50); // Set the width
	        appointmentView.setFitHeight(50);
	        
	        Button btnAppointmentRequest = new Button("View Appointment Request", appointmentView);
	        btnAppointmentRequest.setOnAction((event)->{
	            ViewAppointmentRequest request = new ViewAppointmentRequest();
	            Stage quoteStage = new Stage();
	            try {
	            	request.start(quoteStage);
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        	    
	    	});
	        Button btnServiceBooking = new Button("Service Booking History");
	        btnServiceBooking.setOnAction((event)->{
	            ServiceBookingHistory staff = new ServiceBookingHistory(); // opens ServiceBookingHistory class
	            Stage registerStage = new Stage();
	            try {
	            	staff.start(registerStage);
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
	        HBox leftButtonBox = new HBox(20, btnWorkerDetail, btnUpdateCustomer,btnAppointmentRequest );
	        leftButtonBox.setAlignment(Pos.CENTER);
	        HBox rightButtonBox = new HBox(20, btnViewService,btnRequestQuote, btnServiceBooking);
	  

	        rightButtonBox.setAlignment(Pos.CENTER);

	        VBox buttonsContainer = new VBox(20, leftButtonBox, rightButtonBox);
	        buttonsContainer.setAlignment(Pos.CENTER);
	        buttonsContainer.setPadding(new Insets(20));

	        VBox headerBox = new VBox(10, lbl1);
	        headerBox.setAlignment(Pos.TOP_LEFT);
	        headerBox.setPadding(new Insets(20));
	        
	        VBox centerBox = new VBox(20, lbl2,lbl3, buttonsContainer);
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
	    	primaryStage.setScene(scene);

	    	String LoginPath = getClass().getResource("/Login/style.css").toExternalForm();
	    	scene.getStylesheets().add(LoginPath);

	    	primaryStage.setTitle("Welcome");
	    	primaryStage.setWidth(700);
	    	primaryStage.setHeight(600);
	    	primaryStage.setResizable(false);
	    	
	    	primaryStage.show();
	    }
	    private void openFirstPage() {
	        // instance of the StaffLogin class
	        First page = new First();
	        Stage stage = new Stage();
	        try {
	        	page.start(stage);
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	    }
	}


