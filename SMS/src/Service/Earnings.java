package Service;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class Earnings extends Application {
    private final String URL = "jdbc:mysql://localhost:3306/SMS";
    private final String DBUSER = "root";
    private final String DBPASS = "neera@12";
    private final String SELECT_QUERY = "SELECT serviceCategory, SUM(TotalPrice) AS totalEarned FROM Quotes WHERE status IN ('Accepted', 'Declined', 'Pending') GROUP BY serviceCategory";

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));

        Label titleLabel = new Label("Service Earnings");

        VBox vbox = new VBox(10);
        vbox.getChildren().add(titleLabel);

        Map<String, Double> serviceEarnings = calculateEarnings();

        DecimalFormat df = new DecimalFormat("#0.00");
        for (Map.Entry<String, Double> entry : serviceEarnings.entrySet()) {
            Label label = new Label(entry.getKey() + ": $" + df.format(entry.getValue()));
            vbox.getChildren().add(label);
        }

        root.setCenter(vbox);

        Scene scene = new Scene(root, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Service Earnings");
        primaryStage.show();
    }

    private Map<String, Double> calculateEarnings() {
        Map<String, Double> serviceEarnings = new HashMap<>();
        try {
            Connection conn = DriverManager.getConnection(URL, DBUSER, DBPASS);
            PreparedStatement ptmt = conn.prepareStatement(SELECT_QUERY);
            ResultSet rs = ptmt.executeQuery();
            while (rs.next()) {
                String serviceCategory = rs.getString("serviceCategory");
                double totalEarned = rs.getDouble("totalEarned");
                serviceEarnings.put(serviceCategory, totalEarned);
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return serviceEarnings;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
