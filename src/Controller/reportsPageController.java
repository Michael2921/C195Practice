package Controller;

import DBAccess.DBAppointments;
import DBAccess.DBCustomers;
import Model.Appointments;
import Model.Customers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.Month;

public class reportsPageController {
    public TableColumn reportAppID;
    public TableColumn reportTitle;
    public TableColumn reportType;
    public TableColumn reportDescription;
    public TableColumn reportStartDateTime;
    public TableColumn reportEndDateTime;
    public TableColumn reportCustomerID;
    public TableView contactReportTableView;

     private final ObservableList<Appointments> appointments = DBAppointments.getAllAppointments();
     private final ObservableList<Customers> customers = DBCustomers.getAllCustomers();

    public TableColumn reportAppType;
    public TableColumn reportMonthType;
    public TableColumn reportTotalType;
    public TableView appointmentReportTableView;
    public TableView customerReportTableView;
    public TableColumn customerReportCol;
    public TableColumn dayReportCol;

    /**
     * displays Anika's information
     *  @param actionEvent
     */

    public void reportAnika(ActionEvent actionEvent) {
        // LAMBDA 2
        FilteredList<Appointments> selectAnika = new FilteredList<>(appointments, i -> i.getAppContact().equals("Anika Costa"));
        contactReportTableView.setItems(selectAnika);
        reportAppID.setCellValueFactory(new PropertyValueFactory<>("appID"));
        reportTitle.setCellValueFactory(new PropertyValueFactory<>("appTitle"));
        reportType.setCellValueFactory(new PropertyValueFactory<>("appType"));
        reportDescription.setCellValueFactory(new PropertyValueFactory<>("appDescription"));;
        reportStartDateTime.setCellValueFactory(new PropertyValueFactory<>("appStartDateAndTime"));
        reportEndDateTime.setCellValueFactory(new PropertyValueFactory<>("appEndDateAndTime"));
        reportCustomerID.setCellValueFactory(new PropertyValueFactory<>("appCustomerID"));


    }

    /**
     * displays Daniel's information
     *  @param actionEvent
     */

    public void reportDaniel(ActionEvent actionEvent) {
        FilteredList<Appointments> selectDaniel = new FilteredList<>(appointments, i -> i.getAppContact().equals("Daniel Garcia"));
        contactReportTableView.setItems(selectDaniel);
        reportAppID.setCellValueFactory(new PropertyValueFactory<>("appID"));
        reportTitle.setCellValueFactory(new PropertyValueFactory<>("appTitle"));
        reportType.setCellValueFactory(new PropertyValueFactory<>("appType"));
        reportDescription.setCellValueFactory(new PropertyValueFactory<>("appDescription"));;
        reportStartDateTime.setCellValueFactory(new PropertyValueFactory<>("appStartDateAndTime"));
        reportEndDateTime.setCellValueFactory(new PropertyValueFactory<>("appEndDateAndTime"));
        reportCustomerID.setCellValueFactory(new PropertyValueFactory<>("appCustomerID"));
    }

    /**
     * displays Li's information
     *  @param actionEvent
     */

    public void reportLi(ActionEvent actionEvent) {
        // LAMBDA 1 IS THIS A LAMBDA? ASK!!
        FilteredList<Appointments> selectLi = new FilteredList<>(appointments, i -> i.getAppContact().equals("Li Lee"));
        contactReportTableView.setItems(selectLi);
        reportAppID.setCellValueFactory(new PropertyValueFactory<>("appID"));
        reportTitle.setCellValueFactory(new PropertyValueFactory<>("appTitle"));
        reportType.setCellValueFactory(new PropertyValueFactory<>("appType"));
        reportDescription.setCellValueFactory(new PropertyValueFactory<>("appDescription"));;
        reportStartDateTime.setCellValueFactory(new PropertyValueFactory<>("appStartDateAndTime"));
        reportEndDateTime.setCellValueFactory(new PropertyValueFactory<>("appEndDateAndTime"));
        reportCustomerID.setCellValueFactory(new PropertyValueFactory<>("appCustomerID"));
    }

    /**
     * Returns from the main screen
     *  @param actionEvent
     */

    public void backfromReport(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/mainScreen.fxml"));
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Main window");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * displays month report information
     *  @param actionEvent
     */

    public void monthReport(ActionEvent actionEvent) {
      //  System.out.println(appointments.get(1).getAppEndDateAndTime().getMonth());
        FilteredList<Appointments> selectMonth = new FilteredList<>(appointments, i -> i.getAppStartDateAndTime().getMonth().equals(Month.JANUARY) ||  i.getAppStartDateAndTime().getMonth().equals(Month.FEBRUARY)
        || i.getAppStartDateAndTime().getMonth().equals(Month.MARCH) || i.getAppStartDateAndTime().getMonth().equals(Month.APRIL) ||
                i.getAppStartDateAndTime().getMonth().equals(Month.MAY)||
                i.getAppStartDateAndTime().getMonth().equals(Month.JUNE)|| i.getAppStartDateAndTime().getMonth().equals(Month.JULY) ||
                i.getAppStartDateAndTime().getMonth().equals(Month.AUGUST) || i.getAppStartDateAndTime().getMonth().equals(Month.SEPTEMBER) ||
                i.getAppStartDateAndTime().getMonth().equals(Month.OCTOBER) || i.getAppStartDateAndTime().getMonth().equals(Month.NOVEMBER) ||
                i.getAppStartDateAndTime().getMonth().equals(Month.DECEMBER));
        appointmentReportTableView.setItems(selectMonth);
        reportMonthType.setCellValueFactory(new PropertyValueFactory<>("month"));



    }

    /**
     * displays type report information
     *  @param actionEvent
     */

    public void typeReport(ActionEvent actionEvent) throws SQLException {
        ObservableList typeReport = DBAppointments.addApptReport();
        appointmentReportTableView.setItems(typeReport);
        reportAppType.setCellValueFactory(new PropertyValueFactory<>("appType"));
        reportMonthType.setCellValueFactory(new PropertyValueFactory<>("reportMonth"));
        reportTotalType.setCellValueFactory(new PropertyValueFactory<>("count"));



    }


    /**
     * displays customer report information
     *  @param actionEvent
     */

    public void customerReport(ActionEvent actionEvent) throws SQLException {
//        FilteredList<Customers> selectCustomer = new FilteredList<>(customers, i -> i.getCustomerName().equals("Daddy Warbucks") ||
//                i.getCustomerName().equals("Lady McAnderson") || i.getCustomerName().equals("Dudley Do-Right"));
        ObservableList custReport = DBCustomers.addCustReport();
        customerReportTableView.setItems(custReport);
        customerReportCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        dayReportCol.setCellValueFactory(new PropertyValueFactory<>("custDay"));

    }
}
