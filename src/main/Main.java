package main;

import Database.DBConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.sql.SQLException;
import java.util.Locale;
import java.util.Objects;

//Each class has its own javadoc. You can create a javadoc from IDEA or from CMD
/**
 * JavaDoc files are located in src/JavaDoc
 *
 *
 *
 *
 */
public class Main extends Application {

    /**
     * The start method creates the FXML stage and loads the initial scene.
     * RUNTIME ERROR Did not add Inventory.addProduct(ProductName) resulting in product not being shown on the productTableview, included the pieced of code, and the products showed up
     * FUTURE ENHANCEMENT It would extend the functionality of the table, if we could search by other means apart from ID and Name. i.e price
     * @param stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/FXML/loginPage.fxml")));
        stage.setTitle("Scheduling Application");
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) throws SQLException {
     // Locale.setDefault(new Locale("fr"));
      DBConnection.openConnection();




       launch(args);


      //  DBConnection.terminateConnection();
    }
}