package Quote;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Client.Client;
import Login.globalvariable;
import Service.Service;
import Worker.WorkerUser;
import javafx.application.Application;
import javafx.concurrent.Worker;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class RequestQuote extends Application {
    
    public static void main(String[] args) {
        launch(args);
    }
    int clientIdentity = globalvariable.id;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Label lblTitle = new Label("Fill the form to request Quote");
        lblTitle.getStyleClass().add("label-title");
        
        Label lblServiceCategory = new Label("Service Category");
        Label lblServiceCost = new Label("Service Cost");
        Label lblPreferredDate =  new Label("Preferred Date");
        Label lblStatus = new Label("Status");
        Label lblWorkerMastery = new Label("Worker Mastery");
        Label lblServiceIdentity = new Label("Service Identity");

        Label lblTotalPrice = new Label("Total Price");
        
        
        TextField txtServiceCost = new TextField();
       

        
        ComboBox cmbServiceCategory = new ComboBox();  // gives a drop down arrow list
        List lstServiceCategory = new ArrayList(); 
        lstServiceCategory.add("Plumbing");
        lstServiceCategory.add("Electrical");
        lstServiceCategory.add("Cleaning");
        lstServiceCategory.add("Gardening");
        cmbServiceCategory.getItems().addAll(lstServiceCategory);
        
        
         DatePicker datePicker = new DatePicker();
      
        ComboBox<String> cmbStatus = new ComboBox<>();
        List<String> lstStatus = List.of("Pending", "Accepted", "Denied");
        cmbStatus.getItems().addAll(lstStatus);
        cmbStatus.setValue("Pending");
        cmbStatus.setDisable(true);
        
        ComboBox cmbWorkerMastery = new ComboBox();  // gives a drop down arrow list
        List lstWorkerMastery = new ArrayList(); 
        lstWorkerMastery.add("Novice");
        lstWorkerMastery.add("Intermediate");
        lstWorkerMastery.add("Expert");
        cmbWorkerMastery.getItems().addAll(lstWorkerMastery);

        TextField txtServiceIdentity = new TextField();
        TextField txtTotalPrice = new TextField();
        txtTotalPrice.setEditable(false);
       
       

        Button btnQuote = new Button("Request");
        Button btnClose = new Button("Close");
        btnClose.setOnAction((event)->{
            primaryStage.close();
        });


        cmbServiceCategory.setOnAction(e -> {  // listener
            String selectedCategory = cmbServiceCategory.getValue().toString();
            double serviceCost = getServiceCost(selectedCategory); // service cost
            txtServiceCost.setText(String.valueOf(serviceCost)); 
            txtServiceCost.setEditable(false);

        });
        cmbWorkerMastery.setOnAction(e -> { // listener
            String selectedWorkerMastery = cmbWorkerMastery.getValue().toString();
            double serviceCost = Double.parseDouble(txtServiceCost.getText());
            double totalPrice = calculateTotalPrice(serviceCost, selectedWorkerMastery);
            txtTotalPrice.setText(String.valueOf(totalPrice));
        });
        btnQuote.setOnAction((event)->{
            int ClientIdentity = clientIdentity;
            String serviceCategory = cmbServiceCategory.getValue().toString(); 
            String preferredDate = datePicker.getValue() != null ? datePicker.getValue().toString() : "";       
            double serviceCost = Double.parseDouble(txtServiceCost.getText());
            String status = cmbStatus.getValue().toString();
            String workerMastery = cmbWorkerMastery.getValue().toString();
            double totalPrice = Double.parseDouble(txtTotalPrice.getText());
    		int serviceIdentity = Integer.parseInt(txtServiceIdentity.getText());  //string->INT

                Quote quote = new Quote(ClientIdentity, serviceIdentity, serviceCategory, preferredDate, serviceCost, status,
                        workerMastery, totalPrice);
                // Save record
                boolean res = saveRecord(quote);
                if (res) {
                    showDialog("Quote on pending list", primaryStage);
                    System.out.println("Quote Requested!");
                } else {
                    System.out.println("Error");
                }
          
        });
     
        GridPane pane = new GridPane();
        pane.setHgap(10);
        pane.setVgap(10);
        pane.add(lblServiceCategory, 0, 0);
        pane.add(cmbServiceCategory, 1, 0);
        pane.add(lblPreferredDate, 0, 1);
        pane.add(datePicker, 1, 1);
        pane.add(lblStatus, 0, 2);
        pane.add(cmbStatus, 1, 2);
        pane.add(lblWorkerMastery, 0, 3);
        pane.add(cmbWorkerMastery, 1, 3);
        pane.add(lblServiceCost, 0, 4);
        pane.add(txtServiceCost, 1, 4);
        pane.add(lblServiceIdentity, 0, 5);
        pane.add(txtServiceIdentity, 1, 5);
        pane.add(lblTotalPrice, 0, 6);
        pane.add(txtTotalPrice, 1, 6);

        BorderPane bPane = new BorderPane();
        bPane.setTop(lblTitle);
        BorderPane.setAlignment(lblTitle, Pos.CENTER); 
        bPane.setCenter(pane); 

        HBox button = new HBox(10); 
        button.setAlignment(Pos.CENTER); 
        button.getChildren().addAll(btnQuote, btnClose); 
        bPane.setStyle("-fx-background-color: #CCCCCC;");
        bPane.setBottom(button); 

        Scene scene = new Scene(bPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Request Quote");
        primaryStage.setWidth(600);
        primaryStage.setHeight(500);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public boolean saveRecord(Quote quote) {
        boolean result = false;
        String DRIVER = "com.mysql.cj.jdbc.Driver";
        String HOST = "localhost";
        int PORT = 3306;
        String DATABASE = "SMS";
        String DBUSER = "root";
        String DBPASS ="neera@12";
        String URL ="jdbc:mysql://"+HOST+":"+PORT+"/"+DATABASE;
        String sql = "INSERT INTO Quotes (clientIdentity, serviceIdentity, serviceCategory, serviceCost, preferredDate, status, workerMastery, totalPrice) " +
                   "VALUES (?, ?, ?, ?, ?,?, ?,?)";
        try {
            Class.forName(DRIVER);  //loading driver
            Connection conn = DriverManager.getConnection(URL, DBUSER, DBPASS);
            PreparedStatement pstat = conn.prepareStatement(sql);
                  pstat.setInt(1, quote.getClientIdentity());
                  pstat.setInt(2, quote.getServiceIdentity());
                  pstat.setString(3, quote.getServiceCategory());
                  pstat.setDouble(4, quote.getServiceCost());
                  pstat.setString(5, quote.getPreferredDate());
                  pstat.setString(6, quote.getStatus());
                  pstat.setString(7, quote.getWorkerMastery());
                  pstat.setDouble(8, quote.getTotalPrice());
              
                pstat.executeUpdate();
                pstat.close();
                conn.close();
                result = true;
                
            }
        
            catch(Exception ex){
   
                System.out.println("Error : "+ex.getMessage());
                
            }
            return result;
    }

    private double getServiceCost(String serviceCategory) {
         boolean result = false;
          String DRIVER = "com.mysql.cj.jdbc.Driver";
          String HOST = "localhost";
          int PORT = 3306;
          String DATABASE = "SMS";
          String DBUSER = "root";
          String DBPASS ="neera@12";
          String URL ="jdbc:mysql://"+HOST+":"+PORT+"/"+DATABASE;
          String sql = "SELECT serviceCost FROM services WHERE serviceCategory = ?";
          double serviceCost = 0.0;
          try {
                Class.forName(DRIVER);  // Load driver
                try (Connection conn = DriverManager.getConnection(URL, DBUSER, DBPASS);
                     PreparedStatement pstat = conn.prepareStatement(sql)) {
                    pstat.setString(1, serviceCategory);
                    try (ResultSet rs = pstat.executeQuery()) {
                        if (rs.next()) {
                            serviceCost = rs.getDouble("serviceCost");
                        }
                    }}}
          catch (Exception e) {
            e.printStackTrace();
        }
        return serviceCost;
    
    }
       private double calculateTotalPrice(double serviceCost, String workerMastery) {
           double totalPrice = 0.0;

           if (workerMastery.equals("Novice")) {
               totalPrice = serviceCost * 1.1; // 10 percent
           } else if (workerMastery.equals("Intermediate")) {
               totalPrice = serviceCost * 1.2; // 20 percent
           } else if (workerMastery.equals("Expert")) {
               totalPrice = serviceCost * 1.3; // 30 percent
           }
           return totalPrice;
       }
     
       public void showDialog(String message, Stage primaryStage) {
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("RequestSent");
           alert.setHeaderText(null);
           alert.setContentText(message);
           alert.setOnCloseRequest(event->{
               primaryStage.close();
           
           });
           alert.showAndWait();
       }
}

