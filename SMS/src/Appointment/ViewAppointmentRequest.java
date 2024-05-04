package Appointment;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class ViewAppointmentRequest extends Application {
	// makes list to store appointments and allows updates
    private ObservableList<Appointment> appointments = FXCollections.observableArrayList();
    private ListView<Appointment> listView = new ListView<>();

    private final String URL = "jdbc:mysql://localhost:3306/SMS";
    private final String DBUSER = "root";
    private final String DBPASS = "neera@12";
    private final String SELECT_QUERY = "SELECT * FROM Appointments WHERE status = ?";
    private final String UPDATE_STATUS_QUERY = "UPDATE Appointments SET status = ? WHERE appointmentIdentity = ?";
    private final String UPDATE_APPOINTMENT_QUERY = "UPDATE Appointments SET appointmentDate = ?, appointmentTime = ?, status = ? WHERE appointmentIdentity = ?";

    public static void main(String[] args) {
        launch(args); //main
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));

        Label titleLabel = new Label("Appointment Requests");

        Button closeButton = new Button("Close");
        closeButton.setOnAction((event) -> primaryStage.close());

        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(titleLabel, listView, closeButton);

        root.setCenter(vbox);

        Scene scene = new Scene(root, 500, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("View Appointments");
        primaryStage.show();

        displayAppointments();
    }

    public void displayAppointments() {  // method to display Appointments
        appointments.clear();
        try {
            Connection conn = DriverManager.getConnection(URL, DBUSER, DBPASS);
            PreparedStatement ptmt = conn.prepareStatement(SELECT_QUERY);
            ptmt.setString(1, "Pending");
            ResultSet rs = ptmt.executeQuery();
            while (rs.next()) {
                int appointmentIdentity = rs.getInt("appointmentIdentity");
                String appointmentDate = rs.getString("appointmentDate");
                String appointmentTime = rs.getString("appointmentTime");
                String serviceCategory = rs.getString("serviceCategory");
                int clientIdentity = rs.getInt("clientIdentity");
                int workerIdentity = rs.getInt("workerIdentity");
                String status = rs.getString("status");

                Appointment appointment = new Appointment(appointmentIdentity, appointmentDate, appointmentTime, serviceCategory, clientIdentity, workerIdentity, status);
                appointments.add(appointment);
            }

            listView.setItems(appointments);
            listView.setCellFactory(param -> new CustomListCell());
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateAppointmentStatus(Appointment appointment, String status) {
        try {
            Connection conn = DriverManager.getConnection(URL, DBUSER, DBPASS);
            PreparedStatement ptmt = conn.prepareStatement(UPDATE_STATUS_QUERY);
            ptmt.setString(1, status);
            ptmt.setInt(2, appointment.getAppointmentIdentity());
            ptmt.executeUpdate();
            conn.close();

            appointment.setStatus(status);

            listView.refresh();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateAppointment(Appointment appointment, String newDate, String newTime) {
        try {
            Connection conn = DriverManager.getConnection(URL, DBUSER, DBPASS);
            PreparedStatement ptmt = conn.prepareStatement(UPDATE_APPOINTMENT_QUERY);
            ptmt.setString(1, newDate);
            ptmt.setString(2, newTime);
            ptmt.setString(3, "Pending");
            ptmt.setInt(4, appointment.getAppointmentIdentity());
            ptmt.executeUpdate();
            conn.close();

            appointment.setAppointmentDate(newDate);
            appointment.setAppointmentTime(newTime);


            listView.refresh();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    class CustomListCell extends ListCell<Appointment> {// retrieves appointment from database
        @Override
        protected void updateItem(Appointment item, boolean empty) {
            super.updateItem(item, empty);
            if (empty || item == null) {
                setText(null);
                setGraphic(null);
            } else {
                Label appointmentLabel = new Label("Date: " + item.getAppointmentDate() + " Time: " + item.getAppointmentTime() + " Service: " + item.getServiceCategory());

                Button acceptButton = new Button("Accept");
                Button declineButton = new Button("Decline");
                Button rescheduleButton = new Button("Reschedule");

                acceptButton.setOnAction(e -> {
                    updateAppointmentStatus(item, "Accepted");
                    appointments.remove(item);
                });
                declineButton.setOnAction(e -> {
                    updateAppointmentStatus(item, "Declined");
                    appointments.remove(item);
                });
                rescheduleButton.setOnAction(e -> {
                    openRescheduleDialog(item);
                });

                HBox buttonBox = new HBox(10);
                buttonBox.getChildren().addAll(acceptButton, declineButton, rescheduleButton);

                VBox vbox = new VBox(5);
                vbox.getChildren().addAll(appointmentLabel, buttonBox);

                setGraphic(vbox);
            }
        }
    }

    private void openRescheduleDialog(Appointment appointment) {
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Reschedule Appointment");

        DatePicker datePicker = new DatePicker();
        TextField timeTextField = new TextField();

        Button confirmButton = new Button("Confirm");
        confirmButton.setOnAction(event -> {
            String newDate = datePicker.getValue().toString();
            String newTime = timeTextField.getText();
            updateAppointment(appointment, newDate, newTime);
            dialogStage.close();
        });

        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(
                new Label("New Date:"),
                datePicker,
                new Label("New Time:"),
                timeTextField,
                confirmButton
        );
        vbox.setPadding(new Insets(10));

        Scene scene = new Scene(vbox);
        dialogStage.setScene(scene);
        dialogStage.show();
    }
}
