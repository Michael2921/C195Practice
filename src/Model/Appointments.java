package Model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Appointments {
    private int appID;
    private String appTitle;
    private String appDescription;
    private String appLocation;
    private String appContact;
    private String appType;
    private LocalDateTime appStartDateAndTime;
    private LocalDateTime appEndDateAndTime;
    private int appCustomerID;
    private int userID;
    private String reportMonth;
    private String day;
    private Customers customer;
    private int count;

    public Appointments(int appID, String appTitle, String appDescription, String appLocation, String appContact, String appType, LocalDateTime appStartDateAndTime, LocalDateTime appEndDateAndTime, int appCustomerID, int userID) {
        this.appID = appID;
        this.appTitle = appTitle;
        this.appDescription = appDescription;
        this.appLocation = appLocation;
        this.appContact = appContact;
        this.appType = appType;
        this.appStartDateAndTime = appStartDateAndTime;
        this.appEndDateAndTime = appEndDateAndTime;
        this.appCustomerID = appCustomerID;
        this.userID = userID;
    }

    public Appointments() {

    }

    public Appointments(String type, String month, int count) {
        this.appType = type;
        this.reportMonth = month;
        this.count = count;


    }



    public int getCount() {
        return count;
    }

    public Appointments(Customers customer) {
        this.customer = customer;

    }

    public Appointments(String day) {
        this.day = day;


    }




    public String getMonth(){
       return appEndDateAndTime.getMonth().toString() + " " +  appEndDateAndTime.getDayOfMonth() + " " + appEndDateAndTime.getYear();
    }


    public String getReportMonth() {
        return reportMonth;
    }

    public String getDay(){
        return appEndDateAndTime.getDayOfWeek().toString() + " " +  appEndDateAndTime.getDayOfMonth() + " " + appEndDateAndTime.getYear();
    }

    public String getCustomer(){
        return customer.getCustomerName().toString();
    }






    /**
     * Retrieves ID of appointment.
     * @return ID of appointment.
     */
    public int getAppID() {
        return appID;
    }

    /**
     * Sets appID of customer.
     * @param appID of the customer.
     */

    public void setAppID(int appID) {
        this.appID = appID;
    }

    /**
     * Retrieves title of appointment.
     * @return title of appointment.
     */

    public String getAppTitle() {
        return appTitle;
    }

    /**
     * Sets title of appointment.
     * @param appTitle of the customer.
     */

    public void setAppTitle(String appTitle) {
        this.appTitle = appTitle;
    }

    /**
     * Retrieves description of appointment.
     * @return description of appointment.
     */

    public String getAppDescription() {
        return appDescription;
    }

    /**
     * Sets description of appointment.
     * @param appDescription of the customer.
     */

    public void setAppDescription(String appDescription) {
        this.appDescription = appDescription;
    }

    /**
     * Retrieves location of appointment.
     * @return location of appointment.
     */

    public String getAppLocation() {return appLocation;}

    /**
     * Sets location of appointment.
     * @param appLocation of the customer.
     */

    public void setAppLocation(String appLocation) {
        this.appLocation = appLocation;
    }

    /**
     * Retrieves contact of appointment.
     * @return contact of appointment.
     */

    public String getAppContact() {
        return appContact;
    }

    /**
     * Sets contact of appointment.
     * @param appContact of the customer.
     */

    public void setAppContact(String appContact) {
        this.appContact = appContact;
    }

    /**
     * Retrieves type of appointment.
     * @return type of appointment.
     */

    public String getAppType() {
        return appType;
    }

    /**
     * Sets type of appointment.
     * @param appType of the appointment.
     */

    public void setAppType(String appType) {
        this.appType = appType;
    }

    /**
     * Retrieves start date and time of appointment.
     * @return start date and time of appointment.
     */

    public LocalDateTime getAppStartDateAndTime() {
        return appStartDateAndTime;
    }

    /**
     * Sets start date and time of appointment.
     * @param appStartDateAndTime the appointment.
     */

    public void setAppStartDateAndTime(LocalDateTime appStartDateAndTime) {
        this.appStartDateAndTime = appStartDateAndTime;

    }

    /**
     * Retrieves end date and time of appointment.
     * @return end date and time of appointment.
     */

    public LocalDateTime getAppEndDateAndTime() {
        return appEndDateAndTime;
    }

    /**
     * Sets end date and time of appointment.
     * @param appEndDateAndTime of the customer.
     */

    public void setAppEndDateAndTime(LocalDateTime appEndDateAndTime) {
        this.appEndDateAndTime = appEndDateAndTime;
    }

    /**
     * Retrieves appointment customerID of appointment.
     * @return appointment customer ID of appointment.
     */

    public int getAppCustomerID() {
        return appCustomerID;
    }

    /**
     * Sets customerID of appointment.
     * @param appCustomerID the customer.
     */

    public void setAppCustomerID(int appCustomerID) {
        this.appCustomerID = appCustomerID;
    }

    /**
     * Retrieves userID of appointment.
     * @return userID of appointment.
     */

    public int getUserID() {
        return userID;
    }

    /**
     * Sets UserID of appointment.
     * @param userID  the customer.
     */

    public void setUserID(int userID) {
        this.userID = userID;
    }




}
