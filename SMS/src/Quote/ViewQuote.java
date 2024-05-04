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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewQuote extends Application {
	GridPane gridPane;
    private ObservableList<Quote> quote = FXCollections.observableArrayList();
    private ListView<Quote> listView = new ListView<>();
   
    private final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private final String HOST = "localhost";
    private final int PORT = 3306;
    private final String DATABASE = "SMS";
    private final String DBUSER = "root";
    private final String DBPASS = "neera@12";
    private final String URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE;
    private final String SELECT_QUERY = "SELECT * FROM Quotes WHERE Status = ?";
    private final String UPDATE_QUERY = "UPDATE Quotes SET Status = ? WHERE quoteIdentity=?";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));
        
        Label titleLabel = new Label("Quote Requests");

        Button closeButton = new Button("Close");
        closeButton.setOnAction((event) ->{ 
        	primaryStage.close();});

        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(titleLabel, listView, closeButton);

        root.setCenter(vbox);

        Scene scene = new Scene(root, 500, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("View Quotes");
        primaryStage.show();
        displayQuotes();
    }

    public void displayQuotes() {
        quote.clear();
        try {
            Connection conn = DriverManager.getConnection(URL, DBUSER, DBPASS);
            PreparedStatement ptmt = conn.prepareStatement(SELECT_QUERY);
            ptmt.setString(1, "Pending");
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

                quote.add(new Quote(quoteIdentity,serviceIdentity, clientIdentity, serviceCategory, preferredDate,
                                            serviceCost, status, workerMastery, totalPrice));
            }
        
            listView.setItems(quote);
            listView.setCellFactory(param -> new CustomListCell());
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void updateQuoteStatus(Quote quote, String status) {
        try {
            Connection conn = DriverManager.getConnection(URL, DBUSER, DBPASS);
            PreparedStatement ptmt = conn.prepareStatement(UPDATE_QUERY);
            ptmt.setString(1, status);
            ptmt.setInt(2, quote.getQuoteIdentity());
            ptmt.executeUpdate();
            conn.close();

            quote.setStatus(status);

            listView.refresh();
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
                Label quoteLabel = new Label(item.getServiceCategory() + " Cost  " + item.getServiceCost() + " -TotalPrice " +
                        item.getTotalPrice() + " by " + item.getWorkerMastery());

                Button acceptButton = new Button("Accept");
                Button declineButton = new Button("Decline");

                acceptButton.setOnAction(e -> {
                    updateQuoteStatus(item, "Accepted");
                    quote.remove(item); 
                });
                declineButton.setOnAction(e -> {
                    updateQuoteStatus(item, "Denied");
                    quote.remove(item); 
                });

                HBox buttonBox = new HBox(10);
                buttonBox.getChildren().addAll(acceptButton, declineButton);

                VBox vbox = new VBox(5);
                vbox.getChildren().addAll(quoteLabel,buttonBox);

                setGraphic(vbox);
            }
        }
    }

}


   