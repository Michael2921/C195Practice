package Model;

import java.sql.Timestamp;
// need to have divisionID
public class Customers {
    private int customerID;
    private String customerName;
    private String customerAddress;
    private String customerPostalCode;
    private String customerPhone;
    private String customerCountry;
    private String customerDivision;
    private Appointments day;
    private String custDay;





    public Customers(Appointments appointments) {
        this.day = day;
    }

    public String getDay() {
        return day.getAppEndDateAndTime().getDayOfWeek().toString();
    }

    public Customers(String name, String day){
        this.customerName = name;
        this.custDay = day;
    }

    public String getCustDay() {
        return custDay;
    }

    public void setCustDay(String custDay) {
        this.custDay = custDay;
    }

    public Customers() {

    }

    public Customers(int customerId, String customerName) {
        this.customerID = customerId;
        this.customerName = customerName;
    }



    /**
     * Retrieves ID of Customer.
     * @return ID of Customer.
     */

    public int getCustomerID() {
        return customerID;
    }

    /**
     * Sets ID of customer.
     * @param customerID of the customer.
     */

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    /**
     * Retrieves name of Customer.
     * @return name of Customer.
     */

    public String getCustomerName() {
        return customerName;
    }

    /**
     * Sets name of customer.
     * @param customerName of the customer.
     */

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * Retrieves address of Customer.
     * @return address of Customer.
     */

    public String getCustomerAddress() {
        return customerAddress;
    }

    /**
     * Sets address of customer.
     * @param customerAddress of the customer.
     */

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    /**
     * Retrieves postal code of Customer.
     * @return postal code of Customer.
     */

    public String getCustomerPostalCode() {
        return customerPostalCode;
    }

    /**
     * Sets postal code of customer.
     * @param customerPostalCode of the customer.
     */

    public void setCustomerPostalCode(String customerPostalCode) {
        this.customerPostalCode = customerPostalCode;
    }

    /**
     * Retrieves phone of Customer.
     * @return phone of Customer.
     */

    public String getCustomerPhone() {
        return customerPhone;
    }

    /**
     * Sets phone of customer.
     * @param customerPhone of the customer.
     */

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    /**
     * Retrieves country of Customer.
     * @return country of Customer.
     */

    public String getCustomerCountry() {
        return customerCountry;
    }

    /**
     * Sets country of customer.
     * @param customerCountry of the customer.
     */

    public void setCustomerCountry(String customerCountry) {
        this.customerCountry = customerCountry;
    }

    /**
     * Retrieves division of Customer.
     * @return division of Customer.
     */

    public String getCustomerDivision() {
        return customerDivision;
    }

    /**
     * Sets division of customer.
     * @param customerDivision of the customer.
     */

    public void setCustomerDivision(String customerDivision) {
        this.customerDivision = customerDivision;
    }


    public Customers(int customerID, String customerName, String customerAddress, String customerPostalCode, String customerPhone, String customerDivision, String customerCountry) {

        this.customerID = customerID;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerPostalCode = customerPostalCode;
        this.customerPhone = customerPhone;
        this.customerCountry = customerCountry;
        this.customerDivision = customerDivision;
    }

    @Override
    public String toString() {
        return customerName;
    }
}
