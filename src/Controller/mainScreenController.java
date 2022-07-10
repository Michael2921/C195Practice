package Controller;

import DBAccess.DBAppointments;
import DBAccess.DBCustomers;
import Model.Appointments;
import Model.Customers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;


public class mainScreenController implements Initializable {

    private final ObservableList<Customers> customers = FXCollections.observableArrayList();
    private final ObservableList<Appointments> appointments = FXCollections.observableArrayList();

    public TableView<Customers> customerTableView;
    public TableColumn<Customers, Integer> colCustomerID;
    public TableColumn<Customers, String> colCustomerName;
    public TableColumn<Customers, String> colPostalCode;
    public TableColumn<Customers, String> colCustomerPhoneNumber;
    public TableColumn<Customers, String> colCustomerAddress;
    public TableColumn<Customers, String> colCustomerCountry;
    public TableColumn<Customers, String> colCustomerDivision;
    public TableView<Appointments> appointmentTableView;
    public TableColumn<Object, Object> colAppointmentID;
    public TableColumn<Object, Object> colTitle;
    public TableColumn<Object, Object> colDescription;
    public TableColumn<Object, Object> colLocation;
    public TableColumn<Object, Object> colContact;
    public TableColumn<Object, Object> colType;
    public TableColumn<Object, Object> colStartDateAndTime;
    public TableColumn<Object, Object> colEndDateAndTime;
    public TableColumn<Object, Object> colAppCustomerID;
    public TableColumn<Object, Object> colUserID;


    /**
     * Displays customer info from database on Tableview
     *
     */

    private void displayCustomers(){
        ObservableList<Customers> cList = DBCustomers.getAllCustomers();
        customers.addAll(cList);

    }


    /**
     * Initializes appointments
     */

    private void initializeCustomer() {
        customerTableView.setItems(customers);
        colCustomerID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        colPostalCode.setCellValueFactory(new PropertyValueFactory<>("customerPostalCode")); // check
        colCustomerPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("customerPhone")); //check
        colCustomerAddress.setCellValueFactory(new PropertyValueFactory<>("customerAddress"));
        colCustomerCountry.setCellValueFactory(new PropertyValueFactory<>("customerCountry"));
        colCustomerDivision.setCellValueFactory(new PropertyValueFactory<>("customerDivision"));
    }


    /**
     * Takes customer to main screen once the back button is clicked
     */

    public void onBack(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/loginPage.fxml"));
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Login window");
        stage.setScene(scene);
        stage.show();


    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        displayCustomers();
        initializeCustomer();
        displayAppointments();
        initializeAppointment();
     //   updateCustomer();

    }


    /**
     * Takes user to a new page to add customers
     * @throws IOException
     */

    public void newCustomer(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/customerPage.fxml"));
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Customer window");
        stage.setScene(scene);
        stage.show();
    }



    /**
     * Takes user to a new page to add appointment
     * @throws IOException
     */

    public void newAppointment(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/appointmentPage.fxml"));
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Appointment window");
        stage.setScene(scene);
        stage.show();
    }



    /**
     * Displays customer info from database on Tableview
     */


    private void displayAppointments(){
        ObservableList<Appointments> aList = DBAppointments.getAllAppointments();
        appointments.addAll(aList);

    }





    /**
     * Initializes appointments
     */
    private void initializeAppointment() {
        appointmentTableView.setItems(appointments);
        colAppointmentID.setCellValueFactory(new PropertyValueFactory<>("appID"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("appTitle"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("appDescription")); // check
        colLocation.setCellValueFactory(new PropertyValueFactory<>("appLocation")); //check
        colContact.setCellValueFactory(new PropertyValueFactory<>("appContact"));
        colType.setCellValueFactory(new PropertyValueFactory<>("appType"));
        colStartDateAndTime.setCellValueFactory(new PropertyValueFactory<>("appStartDateAndTime"));
        colEndDateAndTime.setCellValueFactory(new PropertyValueFactory<>("appEndDateAndTime"));
        colAppCustomerID.setCellValueFactory(new PropertyValueFactory<>("appCustomerID"));
        colUserID.setCellValueFactory(new PropertyValueFactory<>("userID"));
    }


    /**
     * Updates customers
     *  @param actionEvent
     */


    public void customerUpdate(ActionEvent actionEvent) throws IOException, SQLException {

        Customers customer = customerTableView.getSelectionModel().getSelectedItem();

        if(customer != null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Alert");
            alert.setContentText("Please select an appointment");


            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/FXML/customerPage.fxml"));
            Parent parent = loader.load();
            Scene scene = new Scene(parent);
            customerController controller = loader.getController();
            controller.execData(customer);
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            window.setScene(scene);
        }

        if(customer == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Alert");
            alert.setContentText("Please select a customer");
            Optional<ButtonType> result = alert.showAndWait();

        }
    }


    /**
     * updates appointments
     *  @param actionEvent
     */

    public void appointmentUpdate(ActionEvent actionEvent) throws IOException, SQLException {
        Appointments appointment = appointmentTableView.getSelectionModel().getSelectedItem();

        if(appointment != null) {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/FXML/appointmentPage.fxml"));
            Parent parent = loader.load();
            Scene scene = new Scene(parent);
            // get the controller and load our selected appointment into it
            appointmentController controller = loader.getController();
            controller.execDataApp(appointment);
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            window.setScene(scene);
        }

        if(appointment == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Alert");
            alert.setContentText("Please select an appointment");
            Optional<ButtonType> result = alert.showAndWait();
        }

    }

    /**
     * deletes customers
     *  @param actionEvent
     */

    public void deleteCustomer(ActionEvent actionEvent) throws SQLException {
        Customers customer = customerTableView.getSelectionModel().getSelectedItem();

        if(customer == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Alert");
            alert.setContentText("Please select a customer");
            Optional<ButtonType> result = alert.showAndWait();


        }


        else{
            int customerd = customerTableView.getSelectionModel().getSelectedItem().getCustomerID();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Alert");
            alert.setContentText("Customer and corresponding appointments will be deleted");
            Optional<ButtonType> result = alert.showAndWait();
            if(result.get() == ButtonType.OK){
                for(Appointments i : appointments){
                    int cfa = i.getAppCustomerID();
                    if(customerd == cfa){
                        DBCustomers.deleteCustomer(cfa);
                        appointments.remove(i);

                    }
                }
                customers.remove(customer);


            }

        }

    }

    /**
     * deletes appointments
     *  @param actionEvent
     */

    public void deleteAppointment(ActionEvent actionEvent) throws SQLException {
        System.out.println(appointments.get(1).getAppID());
        Appointments appointment = appointmentTableView.getSelectionModel().getSelectedItem();
        if(appointment == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Alert");
            alert.setContentText("Please select an appointment");
            Optional<ButtonType> result = alert.showAndWait();


        }
        else{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Alert");
            alert.setContentText("Appointment will be deleted");
            Optional<ButtonType> result = alert.showAndWait();
                if(result.get() == ButtonType.OK){
                    DBAppointments.deleteAppointment(appointment.getAppID());
                    appointments.remove(appointment);

                }

        }
    }
    /**
     * displays report screen
     *  @param actionEvent
     */


    public void onReportClick(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/reportsPage.fxml"));
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Report window");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * all view
     *  @param actionEvent
     */

    public void allRadio(ActionEvent actionEvent) {
        appointments.clear();
        ObservableList<Appointments> aList = DBAppointments.getAllAppointments();
        appointments.addAll(aList);
        appointmentTableView.setItems(appointments);
        colAppointmentID.setCellValueFactory(new PropertyValueFactory<>("appID"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("appTitle"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("appDescription")); // check
        colLocation.setCellValueFactory(new PropertyValueFactory<>("appLocation")); //check
        colContact.setCellValueFactory(new PropertyValueFactory<>("appContact"));
        colType.setCellValueFactory(new PropertyValueFactory<>("appType"));
        colStartDateAndTime.setCellValueFactory(new PropertyValueFactory<>("appStartDateAndTime"));
        colEndDateAndTime.setCellValueFactory(new PropertyValueFactory<>("appEndDateAndTime"));
        colAppCustomerID.setCellValueFactory(new PropertyValueFactory<>("appCustomerID"));
        colUserID.setCellValueFactory(new PropertyValueFactory<>("userID"));
    }

    /**
     * monthly view
     *  @param actionEvent
     */

    public void monthRadio(ActionEvent actionEvent) {
        appointments.clear();
        ObservableList<Appointments> aList = DBAppointments.getAllAppointments();
        appointments.addAll(aList);
        appointmentTableView.setItems(appointments);
        colAppointmentID.setCellValueFactory(new PropertyValueFactory<>("appID"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("appTitle"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("appDescription")); // check
        colLocation.setCellValueFactory(new PropertyValueFactory<>("appLocation")); //check
        colContact.setCellValueFactory(new PropertyValueFactory<>("appContact"));
        colType.setCellValueFactory(new PropertyValueFactory<>("appType"));
        colStartDateAndTime.setCellValueFactory(new PropertyValueFactory<>("month"));
        colEndDateAndTime.setCellValueFactory(new PropertyValueFactory<>("month"));
        colAppCustomerID.setCellValueFactory(new PropertyValueFactory<>("appCustomerID"));
        colUserID.setCellValueFactory(new PropertyValueFactory<>("userID"));
    }

    /**
     * weekly appointments
     *  @param actionEvent
     */

    public void weekRadio(ActionEvent actionEvent) {
        appointments.clear();
        ObservableList<Appointments> aList = DBAppointments.getAllAppointments();
        appointments.addAll(aList);
        appointmentTableView.setItems(appointments);
        colAppointmentID.setCellValueFactory(new PropertyValueFactory<>("appID"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("appTitle"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("appDescription")); // check
        colLocation.setCellValueFactory(new PropertyValueFactory<>("appLocation")); //check
        colContact.setCellValueFactory(new PropertyValueFactory<>("appContact"));
        colType.setCellValueFactory(new PropertyValueFactory<>("appType"));
        colStartDateAndTime.setCellValueFactory(new PropertyValueFactory<>("day"));
        colEndDateAndTime.setCellValueFactory(new PropertyValueFactory<>("day"));
        colAppCustomerID.setCellValueFactory(new PropertyValueFactory<>("appCustomerID"));
        colUserID.setCellValueFactory(new PropertyValueFactory<>("userID"));
    }
}
