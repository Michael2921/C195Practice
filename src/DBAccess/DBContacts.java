package DBAccess;

import Database.DBConnection;
import Model.Contacts;
import Model.Countries;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBContacts {


    /**
     * Gets contact name by ID.
     * @return A contact.
     */


    public static Contacts getContactByID(int contactId){
        try{
            String sql = "SELECT * FROM CONTACTS WHERE Contact_ID = ?";

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ps.setInt(1, contactId);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                String contactName = rs.getString("Contact_Name");
                Contacts c = new Contacts(contactId, contactName);
                return c;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;


    }

    /**
     * Gets contact ID.
     * @return An integer.
     */

    public static int getContactID(String cName){
        int contactID = 0;
        try{

            String sql = "SELECT Contact_ID, Contact_Name FROM contacts WHERE Contact_Name ='"+ cName + "'" ;
            System.out.println(sql);

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();


            while(rs.next()){
                contactID = rs.getInt("Contact_ID");


            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return contactID;

    }
}
