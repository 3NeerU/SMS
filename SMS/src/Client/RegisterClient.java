package Client;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.geometry.Insets;
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

public class RegisterClient extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Label lblTitle = new Label("Registration Form");
        lblTitle.getStyleClass().add("label-title");

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

        ComboBox<String> cmbUserType = new ComboBox<>();
        List<String> lstUser = new ArrayList<>();
        lstUser.add("Staff");
        lstUser.add("Client");
        lstUser.add("Worker");
        cmbUserType.getItems().addAll(lstUser);

        TextField txtAlias = new TextField();
        PasswordField txtCredential = new PasswordField();

        Button btnSave = new Button("Save");
        btnSave.getStyleClass().add("button");

        Button btnClose = new Button("Close");
        btnClose.getStyleClass().add("button");

        btnClose.setOnAction((event) -> {
            primaryStage.close();
        });
        btnSave.setOnAction((event) -> {
            // Reads from UI
            String firstName = txtFirstName.getText();
            String surName = txtSurName.getText();
            String address = txtAddress.getText();
            String email = txtEmail.getText();
            if (!email.toLowerCase().endsWith("@gmail.com")) {
		        showDialog("Write email like: example@gmail.com", Alert.AlertType.ERROR);
		        return;
		    }
            String gender = rbMale.isSelected() ? "Male" : "Female";
            String phoneNumber = txtPhoneNumber.getText();
            String dateOfBirth = datePicker.getValue() != null ? datePicker.getValue().toString() : "";
            String userType = cmbUserType.getValue();
            String alias = txtAlias.getText();
            String credential = txtCredential.getText();
            
            if (phoneNumber.length() != 10) {
                showDialog("Write 10 digit Phone Number",Alert.AlertType.INFORMATION);
                return;
            } // shows a dialogue box
            Client client = new Client(firstName, surName, address, email,
                    gender, phoneNumber, dateOfBirth, userType, alias, credential);
            boolean res = saveRecord(client);
            if (res) {
                showDialog(" Registered ", primaryStage);
                System.out.println("Record Inserted!");
            } else {
                System.out.println("Error to Insert Record");
            }
        });

        GridPane pane = new GridPane();
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setPadding(new Insets(20));

        pane.add(lblFirstName, 0, 0);
        pane.add(txtFirstName, 1, 0);

        pane.add(lblSurName, 0, 1);
        pane.add(txtSurName, 1, 1);

        pane.add(lblAddress, 0, 2);
        pane.add(txtAddress, 1, 2);

        pane.add(lblEmail, 0, 3);
        pane.add(txtEmail, 1, 3);

        pane.add(lblGender, 0, 4);
        pane.add(rbMale, 1, 4);
        pane.add(rbFemale, 1, 5);

        pane.add(lblPhoneNumber, 0, 6);
        pane.add(txtPhoneNumber, 1, 6);

        pane.add(lblDateOfBirth, 0, 7);
        pane.add(datePicker, 1, 7);

        pane.add(lblUserType, 0, 8);
        pane.add(cmbUserType, 1, 8);

        pane.add(lblAlias, 0, 9);
        pane.add(txtAlias, 1, 9);

        pane.add(lblCredential, 0, 10);
        pane.add(txtCredential, 1, 10);

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

        primaryStage.setTitle("Insert Client's Info");
        primaryStage.setWidth(700);
        primaryStage.setHeight(600);
        primaryStage.setResizable(false);

        primaryStage.show();
    }

    public boolean saveRecord(Client client) {
        boolean result = false;
        String DRIVER = "com.mysql.cj.jdbc.Driver";
        String HOST = "localhost";
        int PORT = 3306;
        String DATABASE = "SMS";
        String DBUSER = "root";
        String DBPASS = "neera@12";
        String URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE;
        String sql = "INSERT INTO Clients (firstName, surName, address, email, gender, phoneNumber, dateOfBirth, userType, Alias, credential) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            Class.forName(DRIVER);  //loading driver
            Connection conn = DriverManager.getConnection(URL, DBUSER, DBPASS);
            PreparedStatement pstat = conn.prepareStatement(sql);
            pstat.setString(1, client.getFirstName());
            pstat.setString(2, client.getSurName());
            pstat.setString(3, client.getAddress());
            pstat.setString(4, client.getEmail());
            pstat.setString(5, client.getGender());
            pstat.setString(6, client.getPhoneNumber());
            pstat.setDate(7, Date.valueOf(client.getDateOfBirth()));
            pstat.setString(8, client.getUserType());
            pstat.setString(9, client.getAlias());
            pstat.setString(10, client.getCredential());
            pstat.executeUpdate();
            pstat.close();
            conn.close();
            result = true;

        } catch (Exception ex) {
            System.out.println("Error");
        }
        return result;
    }

    public void showDialog(String message, Stage primaryStage) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Customer");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.setOnCloseRequest(event -> {
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
    }  // method to display dialogue box
}
