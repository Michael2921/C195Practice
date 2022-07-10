package Model;

public class Countries {
    private int id;
    private String name;

    /**
     * Sets ID of Country.
     * @param id of the Country.
     */

    public void setId(int id) {
        this.id = id;
    }

    /**
     * Sets name of Country.
     * @param name of the Country.
     */

    public void setName(String name) {
        this.name = name;
    }

    public Countries(int id, String name){
        this.id = id;
        this.name = name;

    }

    /**
     * Retrieves ID of Country.
     * @return ID of Country.
     */

    public int getId() {return id;}

    /**
     * Retrieves name of Country.
     * @return name of Country.
     */

    public String getName() {return name;}


    @Override
    public String toString(){
        return name;
    }

}
