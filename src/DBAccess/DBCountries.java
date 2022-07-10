package DBAccess;

import Database.DBConnection;
import Model.Countries;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class DBCountries {

    /**
     * Retrieves all Countries from the Database.
     * @return An ObservableList of Country type.
     */
    public static ObservableList<Countries> getAllCountries(){
        ObservableList<Countries> countryList = FXCollections.observableArrayList();

        try{
            String sql = "SELECT * FROM COUNTRIES";

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int countryId = rs.getInt("Country_ID");
                String countryName = rs.getString("Country");
                Countries c = new Countries(countryId, countryName);
                countryList.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return countryList;


    }


    /**
     * Gets country name by ID.
     * @return A country.
     */



    public static Countries getCountryByID(int countryId){
        try{
            String sql = "SELECT * FROM COUNTRIES WHERE Country_ID = ?";

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ps.setInt(1, countryId);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
             //   int countryId = rs.getInt("Country_ID");
                String countryName = rs.getString("Country");
                Countries c = new Countries(countryId, countryName);
                return c;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;


    }

    /**
     * Gets country by name.
     * @return A country.
     */



    public static Countries getCountryByName(String countryName){
        try{
            String sql = "SELECT * FROM COUNTRIES WHERE Country = ?";

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ps.setString(1, countryName);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int countryId = rs.getInt("Country_ID");
                Countries c = new Countries(countryId, countryName);
                return c;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;


    }


}
