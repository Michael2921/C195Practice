package Controller;

import DBAccess.DBAppointments;
import DBAccess.DBContacts;
import DBAccess.DBCountries;
import DBAccess.DBCustomers;
import Database.DBConnection;
import Model.Contacts;
import Model.Countries;
import Model.Customers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import Model.Appointments;

import java.io.IOException;
import java.net.URL;
import java.security.Timestamp;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;

public class appointmentController implements Initializable {
    public ComboBox<String> contactComboBox;
    public ComboBox<String>customerComboBox;
    public final ObservableList<String> contacts = FXCollections.observableArrayList();
    public final ObservableList<String> customers = FXCollections.observableArrayList();
    public TextField appTitle;
    public TextField appType;
    public TextField appLocation;
    public TextArea appDescription;
    public DatePicker startDatePicker;
    public DatePicker endDatePicker;
    public ComboBox startTimePicker;
    public ComboBox endTimePicker;
    public TextField appointmentIDTextBox;

    private ObservableList<String> hours = FXCollections.observableArrayList(
            "08:00","09:00","10:00","11:00","12:00", "13:00","14:00","15:00","16:00","17:00","18:00","19:00","20:00","21:00","22:00");




    /**
     * Goes back to main screen when user clicks the back button
     * @param actionEvent
     */

    public void appointmentCancel(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/FXML/mainScreen.fxml"));
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Main window");
        stage.setScene(scene);
        stage.show();
    }


    /**
     * Adds contacts to contact combobox
     * @return Observable list of Strings
     */

    public ObservableList<String> addContactCombobox(){
        try{
            String sql = "SELECT Contact_Name FROM contacts;";

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                String country = rs.getString("Contact_Name");
                System.out.println(country);

                contacts.add(country);

            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }


        System.out.println(contacts);
        return contacts;

    }


    /**
     * Adds customers to customer combobox
     * @return Observable list of Strings
     */

    public ObservableList<String> addCustomerCombobox(){
        try{
            String sql = "SELECT Customer_Name FROM customers;";

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                String country = rs.getString("Customer_Name");
                System.out.println(country);

                customers.add(country);

            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }


        return customers;

    }



    @Override
    /**
     * Sets values to the customer
     *  @param resourceBundle, url
     */
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addCustomerCombobox();
        customerComboBox.setItems(customers);
        addContactCombobox();
        contactComboBox.setItems(contacts);
        startTimePicker.setItems(hours);
        endTimePicker.setItems(hours);
    }



    /**
     * Saves information from appointment page and displays in Tableview in main screen
     * @param actionEvent
     */
   public void onSubmitApp(ActionEvent actionEvent) throws IOException {
       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
       LocalDate localDateStartandEndDate = startDatePicker.getValue();
       LocalTime localStartTime = LocalTime.parse(startTimePicker.getValue().toString());
       LocalTime localEndTime = LocalTime.parse(endTimePicker.getValue().toString());
       LocalDateTime startDateTime1 = LocalDateTime.of(localDateStartandEndDate, localStartTime);
       LocalDateTime endDateTime1 = LocalDateTime.of(localDateStartandEndDate, localEndTime);



        String title = appTitle.getText();
        String type = appType.getText();
        String location = appLocation.getText();
        String description = appDescription.getText();
        int contactID = DBContacts.getContactID(contactComboBox.getValue());
        int customerID = DBCustomers.getCustomerID(customerComboBox.getValue());
        LocalDateTime startDateTime = null;
        LocalDateTime endDateTime = null;


        try{
            startDateTime = LocalDateTime.of(startDatePicker.getValue(), (LocalTime.parse(startTimePicker.getValue().toString())));
        }

        catch(Exception e){
            e.printStackTrace();
        }

        try{
            endDateTime = LocalDateTime.of(startDatePicker.getValue(), (LocalTime.parse(endTimePicker.getValue().toString())));
        }

        catch(Exception e){
            e.printStackTrace();
        }




       try {
            if(startDateTime1.isAfter(endDateTime1)){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Alert");
                alert.setContentText("Start time after end time");
                Optional<ButtonType> result = alert.showAndWait();
                return;
            }

           if(startDateTime1.isEqual(endDateTime1)){
               Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
               alert.setTitle("Alert");
               alert.setContentText("Start and End time are similar");
               Optional<ButtonType> result = alert.showAndWait();
               return;
           }


                int appID = Integer.parseInt(appointmentIDTextBox.getText());
                //HERE
                for(Appointments appointment : DBAppointments.getAllAppointments()){
                    LocalDateTime checkStart = appointment.getAppStartDateAndTime();
                    LocalDateTime checkEnd = appointment.getAppEndDateAndTime();
                    if((appointment.getAppCustomerID() == customerID) && (appointment.getAppID() != appID) && (startDateTime1.isBefore(checkStart)) && endDateTime1.isAfter(checkEnd)){
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Alert");
                        alert.setContentText("Overlap with already existing appointment");
                        Optional<ButtonType> result = alert.showAndWait();
                        return;
                    }

                    if((appointment.getAppCustomerID() == customerID) && (appointment.getAppID() != appID) && (startDateTime1.isAfter(checkStart)) && startDateTime1.isBefore(checkEnd)){
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Alert");
                        alert.setContentText("Start time overlap");
                        Optional<ButtonType> result = alert.showAndWait();
                        return;
                    }

                    if((appointment.getAppCustomerID() == customerID) && (appointment.getAppID() != appID) && (endDateTime1.isAfter(checkStart)) && endDateTime1.isBefore(checkEnd)){
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Alert");
                        alert.setContentText("End time overlap");
                        Optional<ButtonType> result = alert.showAndWait();
                        return;
                    }


                }

           if(!appointmentIDTextBox.getText().isBlank() || !appointmentIDTextBox.getText().equals("")) {
                DBAppointments.updateAppointment(appID, title, description, location, type, startDateTime, endDateTime, "script", "testscript", customerID, 1, contactID);
            }

            else{
                DBAppointments.addAppointment(title, description, location, type, startDateTime, endDateTime, "script", "testscript", customerID, 1, contactID);
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }



        Parent root = FXMLLoader.load(getClass().getResource("/FXML/mainScreen.fxml"));
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Main window");
        stage.setScene(scene);
        stage.show();

    }



    /**
     * Sets values to the appointment
     * @throws SQLException
     *  @param appointment
     */
    public void execDataApp(Appointments appointment) throws SQLException {


        Customers customer = DBCustomers.getCustomerByID(appointment.getAppCustomerID());
        customerComboBox.setValue(customer.toString());

        contactComboBox.setValue(appointment.getAppContact());

        appointmentIDTextBox.setText(String.valueOf( appointment.getAppID()));

        appTitle.setText(appointment.getAppTitle());
        appLocation.setText(appointment.getAppLocation());
        appType.setText(appointment.getAppType());
        appDescription.setText(appointment.getAppDescription());
        startTimePicker.setValue(appointment.getAppStartDateAndTime().toLocalTime());
        endTimePicker.setValue(appointment.getAppEndDateAndTime().toLocalTime());
        startDatePicker.setValue(appointment.getAppStartDateAndTime().toLocalDate());



    }



}
