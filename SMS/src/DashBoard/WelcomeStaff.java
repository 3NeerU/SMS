package DashBoard;



import Appointment.ScheduleAppointment;
import Client.ListAllClient;
import Client.RegisterClient;
import Client.UpdateClient;
import Quote.ViewQuote;
import Service.ListAllServices;
import Service.RegisterService;
import Service.UpdateService;
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

public class WelcomeStaff extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Label lbl1 = new Label("Logged in as Staff...");
        Label lbl2 = new Label("Welcome ");
        lbl2.getStyleClass().add("label-lbl2");

        Label lbl3 = new Label("List of Tasks");
        lbl3.getStyleClass().add("label-title");
        
        Image registerWorkerImage = new Image("file:C:\\Users\\Asus\\OneDrive\\Pictures\\Saved Pictures\\worker.png");
        ImageView registerWorkerView = new ImageView(registerWorkerImage);
        registerWorkerView.setFitWidth(50); // Set the width
        registerWorkerView.setFitHeight(50);
        
        Button btnRegisterWorker = new Button("Register Worker",registerWorkerView);
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
        registerCustomerView.setFitWidth(50); // Set the width
        registerCustomerView.setFitHeight(50);
        
        Button btnRegisterClient = new Button("Register Client", registerCustomerView);
        btnRegisterClient.setOnAction((event)->{
            RegisterClient registerClient = new RegisterClient();// opens RegisterClient class
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
            ListAllClient client = new ListAllClient();// opens ListAllClient class
            Stage registerStage = new Stage();
            try {
                client.start(registerStage);
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
            ListAllWorkers staff = new ListAllWorkers();// opens ListAllWorkers class
            Stage registerStage = new Stage();
            try {
            	staff.start(registerStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        	    
    	});
        Image updateCustomerImage = new Image("file:C:\\Users\\Asus\\OneDrive\\Pictures\\Saved Pictures\\updateCustomer.png");
        ImageView updateCustomerView = new ImageView(updateCustomerImage);
        updateCustomerView.setFitWidth(50); // Set the width
        updateCustomerView.setFitHeight(50);
        
        Button btnUpdateCustomer = new Button("Update Customer Detail", updateCustomerView);
        btnUpdateCustomer.setOnAction((event)->{
            UpdateClient staff = new UpdateClient();// opens UpdateClient class
            Stage registerStage = new Stage();
            try {
            	staff.start(registerStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
            
         
        	    
    	});
        Image Quote = new Image("file:C:\\Users\\Asus\\OneDrive\\Pictures\\Saved Pictures\\quotes.png");
        ImageView QuoteView = new ImageView(Quote);
        QuoteView.setFitWidth(50); // Set the width
        QuoteView.setFitHeight(50);
        
        Button btnViewQuote = new Button("View Quote", QuoteView);
        btnViewQuote.setOnAction((event)->{
        	ViewQuote quote = new ViewQuote();
            Stage registerStage = new Stage();
            try {
            	quote.start(registerStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
            });
        
        Image Service = new Image("file:C:\\Users\\Asus\\OneDrive\\Pictures\\Saved Pictures\\service.png");
        ImageView ServiceView = new ImageView(Service);
        ServiceView.setFitWidth(50); // width
        ServiceView.setFitHeight(50);
        
        Button btnRegisterService = new Button("Register Service", ServiceView);
        btnRegisterService.setOnAction((event)->{
        	RegisterService service = new RegisterService();
            Stage registerStage = new Stage();
            try {
            	service.start(registerStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
            });
        Image ServiceDetail = new Image("file:C:\\Users\\Asus\\OneDrive\\Pictures\\Saved Pictures\\updateStaff.png");
        ImageView ServiceDetailView = new ImageView(ServiceDetail); //sets the image
        ServiceDetailView.setFitWidth(50); // Set the width
        ServiceDetailView.setFitHeight(50);
        
        Button btnViewService = new Button("View Service Detail", ServiceDetailView);
        btnViewService.setOnAction((event)->{
            ListAllServices service = new ListAllServices();
            Stage registerStage = new Stage();
            try {
            	service.start(registerStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        	    
    	});
        Image update = new Image("file:C:\\Users\\Asus\\OneDrive\\Pictures\\Saved Pictures\\service.png");
        ImageView updateView = new ImageView(update);
        updateView.setFitWidth(50); // width
        updateView.setFitHeight(50);
        
        Button btnUpdateService = new Button("Update Service Detail", updateView);
        btnUpdateService.setOnAction((event)->{
            UpdateService service = new UpdateService();
            Stage registerStage = new Stage();
            try {
            	service.start(registerStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        	    
    	});
        
        Image Appointment = new Image("file:C:\\Users\\Asus\\OneDrive\\Pictures\\Saved Pictures\\updateService.png");
        ImageView ScheduleAppointmentView = new ImageView(Appointment);
        ScheduleAppointmentView.setFitWidth(50); // Set the width
        ScheduleAppointmentView.setFitHeight(50);
        
        Button btnScheduleAppointment = new Button("Schedule Appointment", ScheduleAppointmentView);
        btnScheduleAppointment.setOnAction((event)->{
            ScheduleAppointment schedule = new ScheduleAppointment();
            Stage registerStage = new Stage();
            try {
            	schedule.start(registerStage);
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
        
        HBox leftButtonBox = new HBox(20, btnRegisterWorker, btnRegisterClient, btnViewQuote);
        leftButtonBox.setAlignment(Pos.CENTER);
        HBox rightButtonBox = new HBox(20, btnCustomerDetail, btnWorkerDetail, btnUpdateCustomer);
        rightButtonBox.setAlignment(Pos.CENTER);
        HBox box = new HBox(20, btnRegisterService, btnViewService, btnUpdateService, btnScheduleAppointment);
        rightButtonBox.setAlignment(Pos.CENTER);

        VBox buttonsContainer = new VBox(20, leftButtonBox, rightButtonBox, box);
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

        
        
        GridPane pane = new GridPane();

    	String LoginPath = getClass().getResource("/Login/style.css").toExternalForm();
    	scene.getStylesheets().add(LoginPath);
         
    	primaryStage.setTitle("Welcome");
    	primaryStage.setWidth(700);
    	primaryStage.setHeight(600);
    	primaryStage.setResizable(false);
    	primaryStage.setScene(scene);
    	primaryStage.show();
    }


    private void openFirstPage() {
        //instance of the StaffLogin
        First page = new First();
        Stage stage = new Stage();
        try {
        	page.start(stage);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
