package DBAccess;

import Database.DBConnection;
import Model.Countries;
import Model.Divisions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class DBDivisions {


    /**
     * Retrieves all Divisions from the Database.
     * @return An ObservableList of Division type.
     */

    public static ObservableList<Divisions> getAllDivisions(){
        ObservableList<Divisions> divisionList = FXCollections.observableArrayList();

        try{
            String sql = "SELECT * FROM first_level_divisions";

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int divisionId = rs.getInt("Division_ID");
                String divisionName = rs.getString("Division");
                int countryId = rs.getInt("country_ID");
                Divisions c = new Divisions(divisionId,divisionName, countryId);
                divisionList.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return divisionList;


    }


    /**
     * Gets division name by ID.
     * @return A division.
     */

    public static Divisions getDivisionByID(int divisionId){
        try{
            String sql = "SELECT * FROM first_level_divisions WHERE Division_ID = ?";

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ps.setInt(1, divisionId);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
            //    int divisionId = rs.getInt("Division_ID");
                String divisionName = rs.getString("Division");
                int countryId = rs.getInt("country_ID");
                Divisions c = new Divisions(divisionId,divisionName, countryId);
                return c;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;


    }


    /**
     * Gets division ID by name.
     * @return A division.
     */

    public static Divisions getDivisionIDByName(String divisionName){
        try{
            String sql = "SELECT * FROM first_level_divisions WHERE Division = ?";

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ps.setString(1, divisionName);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                //    int divisionId = rs.getInt("Division_ID");
               int divisionId = rs.getInt("Division_ID");
                int countryId = rs.getInt("country_ID");
                Divisions c = new Divisions(divisionId,divisionName);
                return c;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;


    }


    /**
     * Gets division by country ID.
     * @return An Observable list of Division type.
     */

    public static ObservableList<Divisions> getDivisionByCountryID(int countryId){
        ObservableList<Divisions> divisionList = FXCollections.observableArrayList();

        try{
            String sql = "SELECT * FROM first_level_divisions WHERE Country_ID = " + countryId;

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int divisionId = rs.getInt("Division_ID");
                String divisionName = rs.getString("Division");
               // int countryId = rs.getInt("country_ID");
                Divisions c = new Divisions(divisionId, divisionName);
                divisionList.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return divisionList;
    }

}
