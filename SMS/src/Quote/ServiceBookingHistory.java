package Quote;

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

import Login.globalvariable;

public class ServiceBookingHistory extends Application {
    int clientIdentity = globalvariable.id;

    public static void main(String[] args) {
        launch(args);
    }

    private ObservableList<Quote> quoteHistory = FXCollections.observableArrayList();
    private ListView<Quote> listView = new ListView<>();

    private final String URL = "jdbc:mysql://localhost:3306/SMS";
    private final String DBUSER = "root";
    private final String DBPASS = "neera@12";
    private final String SELECT_QUERY = "SELECT * FROM Quotes WHERE clientIdentity = ? AND status IN ('Accepted', 'Declined', 'Pending')";

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));

        Label titleLabel = new Label("Service Booking History");

        Button closeButton = new Button("Close");
        closeButton.setOnAction((event) -> primaryStage.close());

        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(titleLabel, listView, closeButton);

        root.setCenter(vbox);

        Scene scene = new Scene(root, 500, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Service Booking History");
        primaryStage.show();

        displayQuoteHistory();
    }

    public void setClientIdentity(int clientIdentity) {
        this.clientIdentity = clientIdentity;
    }

    public void displayQuoteHistory() {
        quoteHistory.clear();
        try {
            Connection conn = DriverManager.getConnection(URL, DBUSER, DBPASS);
            PreparedStatement ptmt = conn.prepareStatement(SELECT_QUERY);
            ptmt.setInt(1, clientIdentity); // Pass the client's identity to the query
            ResultSet rs = ptmt.executeQuery();
            while (rs.next()) {
                int quoteIdentity = rs.getInt("quoteIdentity");
                int serviceIdentity = rs.getInt("serviceIdentity");
                int clientIdentity = rs.getInt("clientIdentity");
                String serviceCategory = rs.getString("serviceCategory");
                String preferredDate = rs.getString("preferredDate");
                double serviceCost = rs.getDouble("serviceCost");
                String status = rs.getString("status");
                String workerMastery = rs.getString("workerMastery");
                double totalPrice = rs.getDouble("TotalPrice");

                Quote quote = new Quote(quoteIdentity, serviceIdentity, clientIdentity, serviceCategory, preferredDate,
                        serviceCost, status, workerMastery, totalPrice);
                quote.setStatus(status);
                quoteHistory.add(quote);
            }

            listView.setItems(quoteHistory);
            listView.setCellFactory(param -> new CustomListCell());
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    class CustomListCell extends ListCell<Quote> {
        @Override
        protected void updateItem(Quote item, boolean empty) {
            super.updateItem(item, empty);
            if (empty || item == null) {
                setText(null);
                setGraphic(null);
            } else {
                String statusText;
                String status = item.getStatus();
                if (status.equals("Accepted")) {
                    statusText = "Accepted";
                } else if (status.equals("Declined")) {
                    statusText = "Declined";
                } else if (status.equals("Pending")) {
                    statusText = "Pending";
                } else {
                    statusText = "Unknown";
                }

                setText(" Client ID: " + item.getClientIdentity() + " " + "TotalPrice: " + item.getTotalPrice() + " Service: " + item.getServiceCategory() + "\nStatus: " + statusText);
                setGraphic(null);
            }
        }
    }
}
