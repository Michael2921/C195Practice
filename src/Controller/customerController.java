package Controller;

import DBAccess.DBCountries;
import DBAccess.DBCustomers;
import DBAccess.DBDivisions;
import Database.DBConnection;
import Model.Countries;
import Model.Customers;
import Model.Divisions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.function.Consumer;

public class customerController implements Initializable {
    public TextField fullNameField;
    public TextField addressField;
    public ComboBox<Countries> countryComboBox;
    public ComboBox<Divisions> divisionsComboBox;
    public TextField postalCodeField;
    public TextField phoneField;
    public TableView customerTableView;
    public TextField customerIDTextBox;
    private Customers targetCustomer = null;




    private ObservableList<String> divisions = FXCollections.observableArrayList();


    /**
     * Adds division data to divisioncombobox
     * @return ObservableList
     *
     */


    public ObservableList<String> addDivisionCombobox(){
        try{
            String sql = "SELECT DISTINCT Division from first_level_divisions;";

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                String division = rs.getString("Division");
                System.out.println(division);

                divisions.add(division);


            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }



        System.out.println(divisions);
        return divisions;

    }






    /**
     * Goes back to main screen when user clicks the back button
     * @param actionEvent
     */

    public void customerCancel(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/FXML/mainScreen.fxml"));
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Customer window");
        stage.setScene(scene);
        stage.show();
    }


    /**
     * Adds passed customer info to table view upon click of submit button
     * @throws SQLException
     * @throws IOException
     *  @param actionEvent
     */

    public void onSubmit(ActionEvent actionEvent) throws IOException, SQLException {
        String name = fullNameField.getText();
        String address = addressField.getText();
        String postal = postalCodeField.getText();
        String phone = phoneField.getText();
        Countries country = countryComboBox.getValue();
        Divisions division = divisionsComboBox.getValue();


        try {
                if(!customerIDTextBox.getText().isBlank() || !customerIDTextBox.getText().equals("")){
                    int customerID = Integer.parseInt((customerIDTextBox.getText()));
                   // System.out.println("hello");
                    DBCustomers.updateCustomer(customerID, name, address, postal, phone, division.getDivisionID());


                }

                else {

                    DBCustomers.addCustomer(name, address, postal, phone, division.getDivisionID());


                }
        }
        catch(Exception e){
            e.printStackTrace();
        }




            Parent root = FXMLLoader.load(getClass().getResource("/FXML/mainScreen.fxml"));
            Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setTitle("Customer window");
            stage.setScene(scene);
            stage.show();

        }






    @Override

    /**
     * Sets values to the customer
     *  @param resourceBundle, url
     */
    public void initialize(URL url, ResourceBundle resourceBundle) {
      countryComboBox.setItems(DBCountries.getAllCountries());


    }


    /**
     * Sets values to the customer
     * @throws SQLException
     *  @param customer
     */


    public void execData(Customers customer) throws SQLException {

        Countries country = DBCountries.getCountryByName(customer.getCustomerCountry());
        countryComboBox.setValue(country);

        divisionsComboBox.setValue(DBDivisions.getDivisionIDByName(customer.getCustomerDivision()));




        customerIDTextBox.setText(String.valueOf( customer.getCustomerID()));

        fullNameField.setText(customer.getCustomerName());
        addressField.setText(customer.getCustomerAddress());
        postalCodeField.setText(customer.getCustomerPostalCode());
        phoneField.setText(customer.getCustomerPhone());



    }

    /**
     * Displays country from combobox when clicked
     *  @param actionEvent
     */

    public void onCountryClick(ActionEvent actionEvent) {
        divisionsComboBox.setItems(DBDivisions.getDivisionByCountryID(countryComboBox.getValue().getId()));

    }
}
