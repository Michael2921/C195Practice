package Model;

public class Divisions {
    private int divisionID;
    private String divisionName;
    private int countryId;

    public Divisions(int divisionID, String divisionName, int countryId){
        this.divisionID = divisionID;
        this.divisionName = divisionName;
        this.countryId = countryId;

    }

    public Divisions(String divisionName) {
        this.divisionName = divisionName;
    }

    public Divisions(int divisionId, String divisionName) {
        this.divisionID = divisionId;
        this.divisionName = divisionName;
    }

    /**
     * Retrieves ID of Division.
     * @return ID of Division.
     */

    public int getDivisionID() {
        return this.divisionID;
    }

    /**
     * Sets ID of Division.
     * @param divisionID of the Division.
     */

    public void setDivisionID(int divisionID) {
        this.divisionID = divisionID;
    }

    /**
     * Retrieves name of Division.
     * @return name of Division.
     */


    public String getDivisionName() {
        return divisionName;
    }

    /**
     * Sets name of Division.
     * @param divisionName of the Contact.
     */

    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    @Override
    public String toString(){
        return divisionName;
    }


}
