package DBAccess;

import Database.DBConnection;
import Model.Appointments;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DBAppointments {

    /**
     * Retrieves all Appointments from the Database.
     * @return An ObservableList of Appointment type.
     */

    public static ObservableList<Appointments> getAllAppointments() {
        ObservableList<Appointments> aList = FXCollections.observableArrayList();
        try {
            String sql = "SELECT appointments.*, User_Name, Contact_Name, Customer_Name FROM appointments, customers, contacts, users WHERE appointments.Customer_ID=customers.Customer_ID and appointments.Contact_ID=contacts.Contact_ID and appointments.User_ID=users.User_ID";

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int appID = rs.getInt("Appointment_ID");
                String appTitle = rs.getString("Title");
                String appDescription = rs.getString("Description");
                String appLocation = rs.getString("Location");
                String appContact = rs.getString("Contact_Name"); //  check
                String appType = rs.getString("Type");
                LocalDateTime appStartDateAndTime = ((rs.getTimestamp("Start").toLocalDateTime())); //check  //nothing wrong with this line of code, ran perfectly last time
                LocalDateTime appEndDateAndTime = ((rs.getTimestamp("End").toLocalDateTime())); // check
                int appCustomerID = rs.getInt("Customer_ID");
                int userID = rs.getInt("User_ID");


                Appointments app = new Appointments(appID, appTitle, appDescription, appLocation, appContact, appType, appStartDateAndTime, appEndDateAndTime, appCustomerID, userID);
                aList.add(app);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return aList;
    }

    /**
     * Adds appointment info to the GUI that's then displayed in the AppointmentTableView.
     * @return A boolean.
     */

   public static boolean addAppointment(String title, String description, String location, String type, LocalDateTime start, LocalDateTime end, String createdBy, String lastUpdatedBy, int customerID, int userID, int contactID) throws SQLException {
        String sql = "INSERT INTO appointments (Title, Description, Location, Type, Start, End, Create_Date, Created_By, Last_Update, Last_Updated_By, Customer_ID, User_ID, Contact_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        ps.setString(1, title);
        ps.setString(2, description);
        ps.setString(3, location);
        ps.setString(4, type);
        ps.setString(5, String.valueOf(start));
        ps.setString(6, String.valueOf(end));
        ps.setString(7, ZonedDateTime.now(ZoneOffset.UTC).format(formatter)); // .tostring()
        ps.setString(8, createdBy);
        ps.setString(9, ZonedDateTime.now(ZoneOffset.UTC).format(formatter)); // .tostring()
        ps.setString(10, lastUpdatedBy);
        ps.setInt(11, customerID);
        ps.setInt(12, userID);
        ps.setInt(13, contactID);


;

        try {

            ps.executeUpdate();
            ps.close();
            return true;
        }
        catch(SQLException error){
            error.printStackTrace();
            ps.close();
            return false;
        }

    }

    public static ObservableList<Appointments> addApptReport() throws SQLException {
        ObservableList<Appointments> bList = FXCollections.observableArrayList();
        try {
            String sql = "SELECT Type, month(Start) AS month, COUNT(Appointment_ID) AS count FROM appointments GROUP BY Type, month";
            PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String type = rs.getString("Type");
                String month = rs.getString("month");
                int count = rs.getInt("count");

                Appointments app = new Appointments(type, month, count);
                bList.add(app);
            }
        }

        catch(SQLException e){
            e.printStackTrace();

        }
        return bList;

    }



    /**
     * Updates customer info to the GUI that's then displayed in the CustomerTableView.
     * @return A boolean.
     */

    public static boolean updateAppointment(int appID, String title, String description, String location, String type, LocalDateTime start, LocalDateTime end, String createdBy, String lastUpdatedBy, int customerID, int userID, int contactID) throws SQLException {
        String sql = "UPDATE appointments SET Title=?, Description=?, Location=?, Type=?, Start=?, End=?, Create_Date=?, Last_Update=?, Last_Updated_By=?, Customer_ID=?, User_ID=?, Contact_ID=? WHERE Appointment_ID = ?";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        try {
            ps.setString(1, title);
            ps.setString(2, description);
            ps.setString(3, location);
            ps.setString(4, type);
            ps.setString(5, String.valueOf(start));
            ps.setString(6, String.valueOf(end));
            ps.setString(7, ZonedDateTime.now(ZoneOffset.UTC).format(formatter)); // .tostring()
            ps.setString(8, ZonedDateTime.now(ZoneOffset.UTC).format(formatter)); // .tostring()
            ps.setString(9, lastUpdatedBy);
            ps.setInt(10, customerID);
            ps.setInt(11, userID);
            ps.setInt(12, contactID);
            ps.setInt(13, appID);

            ps.executeUpdate();
            ps.close();
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }

    }


    /**
     * Deletes customer info from the Database.
     * @return A boolean.
     */

    public static boolean deleteAppointment(Integer appID) throws SQLException {
        String sql = "DELETE FROM appointments WHERE Appointment_ID = ?";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ps.setInt(1, appID);

        try {
            ps.executeUpdate();
            ps.close();
            return true;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

