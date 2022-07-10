package DBAccess;

import Database.DBConnection;
import Model.Appointments;
import Model.Countries;
import Model.Customers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBCustomers {


    /**
     * Retrieves all Customers from the Database.
     * @return An ObservableList of Customer type.
     */

    public static ObservableList<Customers> getAllCustomers(){
        ObservableList<Customers> cList = FXCollections.observableArrayList();
        try{
            String sql = "SELECT cus.Customer_ID, cus.Customer_Name, cus.Address, cus.Postal_Code, cus.Phone, " +
                    "di.Division, c.Country " +
                    "FROM customers as cus INNER JOIN first_level_divisions as di on cus.Division_ID = di.Division_ID " +
                    "INNER JOIN countries as c ON di.COUNTRY_ID = c.Country_ID ";

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int customerID = rs.getInt("Customer_ID");
                String customerName = rs.getString("Customer_Name");
                String customerAddress = rs.getString("Address");
                String customerPostalCode = rs.getString("Postal_Code");
                String customerPhone = rs.getString("Phone");
                String customerCountry = rs.getString("Country");
                String customerDivision = rs.getString("Division");


                Customers cus = new Customers(customerID, customerName, customerAddress, customerPostalCode, customerPhone, customerDivision, customerCountry);
                cList.add(cus);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return cList;

    }

    /**
     * Retrieves customer ID from the Database.
     * @return An integer.
     */



    public static int getCustomerID(String cName){
        int customerID = 0;
        try{

            String sql = "SELECT Customer_ID, Customer_Name FROM customers WHERE Customer_Name ='"+ cName + "'" ;
            System.out.println(sql);

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();


            while(rs.next()){
                customerID = rs.getInt("Customer_ID");


            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return customerID;

    }



    /**
     * Retrieves DivisionID from the Database.
     * @return An integer.
     */

    public static int getDivisionID(String dName){
        int divisionID = 0;
        try{
            String sql = "SELECT Division_ID, Division FROM first_level_divisions WHERE Division ='"+ dName + "'" ;

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();


            while(rs.next()){
                divisionID = rs.getInt("Division_ID");


            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }


        return divisionID;

    }




    /**
     * Adds customer info to the GUI that's then displayed in the CustomerTableView.
     * @return A boolean.
     */

        public static boolean addCustomer(String name, String address, String postalCode, String phone, int divisionID) throws SQLException {
            String sql = "INSERT INTO customers (Customer_Name, Address, Postal_Code, Phone, Division_ID) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, address);
            ps.setString(3, postalCode);
            ps.setString(4, phone);
            ps.setInt(5, divisionID);

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

    /**
     * Deletes customer from the database.
     * @return A boolean.
     */

        public static boolean deleteCustomer(Integer customerID) throws SQLException {
           String sql = "DELETE FROM customers WHERE Customer_ID = ?";
           PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
            ps.setInt(1, customerID);

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

    /**
     * Updates customer in the database.
     * @return A boolean.
     */


    public static boolean updateCustomer(int cID, String name, String address, String postalCode, String phone, int divisionId) throws SQLException {
        String sql = "UPDATE customers SET Customer_Name=?, Address=?, Postal_Code=?, Phone=?, Division_ID=? WHERE Customer_ID = ?";
        System.out.println(cID);
        try {
            PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, address);
            ps.setString(3, postalCode);
            ps.setString(4, phone);
            ps.setInt(5, divisionId);
            ps.setInt(6, cID);


            ps.executeUpdate();
            ps.close();
            return true;
        } catch (SQLException error) {
            error.printStackTrace();
            return false;

        }

    }


        public static ObservableList<Customers> addCustReport() throws SQLException{
            ObservableList<Customers> cList = FXCollections.observableArrayList();
            try {
                String sqla = "SELECT customers.Customer_Name AS  customer, dayname(appointments.Start) as day FROM customers INNER JOIN appointments ON customers.Customer_ID=appointments.Customer_ID;\n" +
                        "\n";
                PreparedStatement ps = DBConnection.connection.prepareStatement(sqla);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String type = rs.getString("customer");
                    String month = rs.getString("day");
                    Customers cust = new Customers(type, month);
                    cList.add(cust);
                }
            }

            catch(SQLException e){
                e.printStackTrace();

            }
            return cList;

        }



    /**
     * Gets customer by ID from Database.
     * @return A customer.
     */

    public static Customers getCustomerByID(int customerId){
        try{
            String sql = "SELECT * FROM CUSTOMERS WHERE Customer_ID = ?";

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ps.setInt(1, customerId);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                //   int countryId = rs.getInt("Country_ID");
                String customerName = rs.getString("Customer_Name");
                Customers c = new Customers(customerId, customerName);
                return c;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;


    }



}
