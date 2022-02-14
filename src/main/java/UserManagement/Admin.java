package UserManagement;

/**
 * Class to store our Admin data
 * @author Viktor Benini, StudentID: 1298976
 */
public class Admin {
    private int id;
    private String name;
    private String lastName;
    private String email;

    /**
     * Basic Constructor
     */
    public Admin(){}

    /**
     * Constructor with given values
     * @param id
     * @param name
     * @param lastName
     * @param email
     */
    public Admin(int id, String name, String lastName, String email){
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
    }

    // Set-Method's

    /**
     * set int id
     * @param id
     */
    public void setId(int id)
    {
        this.id = id;
    }

    /**
     * set String firstname
     * @param firstname
     */
    public void setName(String firstname)
    {
        this.name = firstname;
    }

    /**
     * set String lastname
     * @param lastname
     */
    public void setLastname(String lastname)
    {
        this.lastName = lastname;
    }

    /**
     * set String email
     * @param email
     */
    public void setEmail(String email)
    {
        this.email = email;
    }

    // Get-Method's

    /**
     * get int id
     * @return
     */
    public int getId()
    {
        return id;
    }

    /**
     * get String firstname
     * @return
     */
    public String getName()
    {
        return name;
    }

    /**
     * get String lastname
     * @return
     */
    public String getLastname()
    {
        return lastName;
    }

    /**
     * get String email
     * @return
     */
    public String getEmail()
    {
        return email;
    }

}
