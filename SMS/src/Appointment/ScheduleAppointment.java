package Appointment;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ScheduleAppointment extends Application {
    private Label lblQuoteIdentity, lblStaffIdentity, lblDate, lblTime, lblService, lblWorkerID, lblClientIdentity;
    private TextField txtTime, txtClientIdentity, txtService, txtWorkerIdentity;
    private DatePicker datePicker;
    private Button btnDisplay, btnSave;
    private ComboBox<Integer> cmbQuoteIdentity, cmbStaffIdentity, cmbWorkerIdentity;

    private final String URL = "jdbc:mysql://localhost:3306/SMS";
    private final String DBUSER = "root";
    private final String DBPASS = "neera@12";
    private final String SELECT_QUOTE_DETAILS_QUERY = "SELECT clientIdentity, serviceCategory FROM Quotes WHERE quoteIdentity = ?";
    private final String SELECT_WORKER_IDENTITIES_QUERY = "SELECT workerIdentity FROM WorkerUsers";
    private final String INSERT_APPOINTMENT_QUERY = "INSERT INTO Appointments (quoteIdentity, appointmentDate, appointmentTime, serviceCategory, clientIdentity, workerIdentity, status, staffIdentity) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        lblQuoteIdentity = new Label("Quote Identity:");
        cmbQuoteIdentity = new ComboBox<>();
        lblStaffIdentity = new Label("Staff Identity:");
        cmbStaffIdentity = new ComboBox<>();
        lblDate = new Label("Date:");
        datePicker = new DatePicker();
        lblTime = new Label("Time:");
        txtTime = new TextField();
        lblService = new Label("Service:");
        txtService = new TextField();
        lblClientIdentity = new Label("Client Identity:");
        txtClientIdentity = new TextField();
        lblWorkerID = new Label("Worker ID:");
        cmbWorkerIdentity = new ComboBox<>();
        btnSave = new Button("Save");
        btnDisplay = new Button("Display");

        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.addRow(0, lblQuoteIdentity, cmbQuoteIdentity, btnDisplay);
        gridPane.addRow(1, lblStaffIdentity, cmbStaffIdentity, lblDate, datePicker);
        gridPane.addRow(2, lblTime, txtTime);
        gridPane.addRow(3, lblService, txtService);
        gridPane.addRow(4, lblClientIdentity, txtClientIdentity);
        gridPane.addRow(5, lblWorkerID, cmbWorkerIdentity);
        gridPane.addRow(6, btnSave);

        Scene scene = new Scene(gridPane, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Schedule Appointment");
        primaryStage.show();

        cmbQuoteIdentity.getItems().addAll(getAcceptedQuoteIdentities());
        cmbStaffIdentity.getItems().addAll(getStaffIdentity());
        cmbWorkerIdentity.getItems().addAll(getWorkerIdentities());

        btnDisplay.setOnAction(event -> displayDetails());
        btnSave.setOnAction(event -> saveAppointment());
    }
// will get staff Identity
    private List<Integer> getStaffIdentity() {
        List<Integer> staffIdentities = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(URL, DBUSER, DBPASS)) {
            String sql = "SELECT staffIdentity FROM StaffUsers";
            try (PreparedStatement statement = conn.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int staffIdentity = resultSet.getInt("staffIdentity");
                    staffIdentities.add(staffIdentity);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return staffIdentities;
    }

    private List<Integer> getAcceptedQuoteIdentities() {
        List<Integer> quoteIdentities = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(URL, DBUSER, DBPASS)) {
            String sql = "SELECT quoteIdentity FROM Quotes WHERE status = 'Accepted'";
            try (PreparedStatement statement = conn.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int quoteIdentity = resultSet.getInt("quoteIdentity");
                    quoteIdentities.add(quoteIdentity);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return quoteIdentities;
    }
// retrieves worker
    private List<Integer> getWorkerIdentities() {
        List<Integer> workerIdentities = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(URL, DBUSER, DBPASS)) {
            String sql = SELECT_WORKER_IDENTITIES_QUERY;
            try (PreparedStatement statement = conn.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int workerIdentity = resultSet.getInt("workerIdentity");
                    workerIdentities.add(workerIdentity);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return workerIdentities;
    }
// method
    private void displayDetails() {
        Integer selectedQuoteIdentity = cmbQuoteIdentity.getValue();
        if (selectedQuoteIdentity != null) {
            try (Connection conn = DriverManager.getConnection(URL, DBUSER, DBPASS)) {
                PreparedStatement statement = conn.prepareStatement(SELECT_QUOTE_DETAILS_QUERY);
                statement.setInt(1, selectedQuoteIdentity);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    int clientIdentity = resultSet.getInt("clientIdentity");
                    String serviceCategory = resultSet.getString("serviceCategory");
                    txtClientIdentity.setText(String.valueOf(clientIdentity));
                    txtService.setText(serviceCategory);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Please select a quote identity.");
        }
    }

    private void saveAppointment() {
        Integer quoteIdentity = cmbQuoteIdentity.getValue();
        Integer staffIdentity = cmbStaffIdentity.getValue();
        Integer workerIdentity = cmbWorkerIdentity.getValue();
        String appointmentDate = datePicker.getValue().toString();
        String appointmentTime = txtTime.getText();
        String serviceCategory = txtService.getText();
        int clientIdentity = Integer.parseInt(txtClientIdentity.getText());
        String status = "Pending";

        try (Connection conn = DriverManager.getConnection(URL, DBUSER, DBPASS)) {
            PreparedStatement statement = conn.prepareStatement(INSERT_APPOINTMENT_QUERY);
            statement.setInt(1, quoteIdentity);
            statement.setString(2, appointmentDate);
            statement.setString(3, appointmentTime);
            statement.setString(4, serviceCategory);
            statement.setInt(5, clientIdentity);
            statement.setInt(6, workerIdentity);
            statement.setString(7, status);
            statement.setInt(8, staffIdentity);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                showDialog("Appointment saved successfully.", primaryStage);
            } else {
                System.out.println("Failed to save appointment.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
// main 
    public static void main(String[] args) {
        launch(args);
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
