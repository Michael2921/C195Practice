package Model;

public class Users {
    private int userID;
    private String userName;
    private int userPassword;

    public Users(int userID, String userName, int userPassword) {
        this.userID = userID;
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public Users() {

    }

    /**
     * Retrieves ID of User.
     * @return ID of User.
     */

    public int getUserID() {
        return userID;
    }

    /**
     * Sets ID of User.
     * @param userID of the Contact.
     */

    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * Retrieves name of User.
     * @return name of User.
     */

    public String getUserName() {
        return userName;
    }

    /**
     * Sets name of User.
     * @param userName of the Contact.
     */

    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Retrieves password of User.
     * @return password of User.
     */

    public int getUserPassword() {
        return userPassword;
    }

    /**
     * Sets password of User.
     * @param userPassword of the Contact.
     */

    public void setUserPassword(int userPassword) {
        this.userPassword = userPassword;
    }
}
