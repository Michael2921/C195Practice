package Model;

public class Contacts {
    private int contactID;
    private String contactName;
    private String contactEmail;

    public Contacts(int contactID, String contactName){
        this.contactID = contactID;
        this.contactName = contactName;

    }

    public Contacts(int contactID, String contactName, String contactEmail){
        this.contactID = contactID;
        this.contactName = contactName;
        this.contactEmail = contactEmail;
    }



    /**
     * Retrieves phone of Contact.
     * @return phone of Contact.
     */

    public int getContactID() {
        return contactID;
    }

    /**
     * Sets contactID of Contact.
     * @param contactID of the Contact.
     */

    public void setContactID(int contactID) {
        this.contactID = contactID;
    }

    /**
     * Retrieves phone of Contact.
     * @return phone of Contact.
     */

    public String getContactName() {
        return contactName;
    }

    /**
     * Sets contactName of Contact.
     * @param contactName of the Contact.
     */

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     * Retrieves phone of Contact.
     * @return phone of Contact.
     */

    public String getContactEmail() {
        return contactEmail;
    }

    /**
     * Sets contactEmail of Contact.
     * @param contactEmail of the Contact.
     */

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    @Override
    public String toString() {
        return contactName;
    }
}
