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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AppointmentHistory extends Application {
	// makes list to store History and allows updates
    private ObservableList<Appointment> appointmentHistory = FXCollections.observableArrayList();
    private ListView<Appointment> listView = new ListView<>();

    private final String URL = "jdbc:mysql://localhost:3306/SMS";
    private final String DBUSER = "root";
    private final String DBPASS = "neera@12";
    private final String SELECT_QUERY = "SELECT * FROM Appointments WHERE status IN ('Accepted', 'Declined', 'Rescheduled')";

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));

        Label titleLabel = new Label("Appointment History");

        Button closeButton = new Button("Close");
        closeButton.setOnAction((event) -> primaryStage.close());

        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(titleLabel, listView, closeButton);

        root.setCenter(vbox);

        Scene scene = new Scene(root, 500, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Appointment History");
        primaryStage.show();

        displayAppointmentHistory();
    }
// method to display Appointment history
    public void displayAppointmentHistory() {
        appointmentHistory.clear();
        try {
            Connection conn = DriverManager.getConnection(URL, DBUSER, DBPASS);
            PreparedStatement ptmt = conn.prepareStatement(SELECT_QUERY);
            ResultSet rs = ptmt.executeQuery();
            while (rs.next()) {
                int appointmentIdentity = rs.getInt("appointmentIdentity");
                String appointmentDate = rs.getString("appointmentDate");
                String appointmentTime = rs.getString("appointmentTime");
                String serviceCategory = rs.getString("serviceCategory");
                int clientIdentity = rs.getInt("clientIdentity");
                int workerIdentity = rs.getInt("workerIdentity");
                String status = rs.getString("status");

                Appointment appointment = new Appointment(appointmentIdentity, appointmentDate, appointmentTime, serviceCategory, clientIdentity, workerIdentity);
                appointment.setStatus(status); // Set status directly
                appointmentHistory.add(appointment);
            }

            listView.setItems(appointmentHistory);
            listView.setCellFactory(param -> new CustomListCell());
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    class CustomListCell extends ListCell<Appointment> {
        @Override
        protected void updateItem(Appointment item, boolean empty) {
            super.updateItem(item, empty);
            if (empty || item == null) {
                setText(null);
                setGraphic(null);
            } else {
                String statusText;
                String status = item.getStatus();
                if (status.equals("Accepted")) {
                    statusText = "Accepted by Customer";
                } else if (status.equals("Declined")) {
                    statusText = "Declined by Customer";
                } else if (status.equals("Rescheduled")) {
                    statusText = "Rescheduled by Customer";
                } else {
                    statusText = "Unknown";
                }

                setText("Date: " + item.getAppointmentDate() + " Time: " + item.getAppointmentTime() + " Service: " + item.getServiceCategory() + "\nStatus: " + statusText);
                setGraphic(null); // Clear any previous graphics
            }
        }
    }
//main method
    public static void main(String[] args) {
        launch(args);
    }
}
