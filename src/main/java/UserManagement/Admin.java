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

    public Admin(){}

    public Admin(int id, String name, String lastName, String email){
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
    }

    // Set-Method's
    public void setId(int id)
    {
        this.id = id;
    }
    public void setName(String firstname)
    {
        this.name = firstname;
    }
    public void setLastname(String lastname)
    {
        this.lastName = lastname;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }

    // Get-Method's
    public int getId()
    {
        return id;
    }
    public String getName()
    {
        return name;
    }
    public String getLastname()
    {
        return lastName;
    }
    public String getEmail()
    {
        return email;
    }

    // Functions

    /**
     * show a list of users to the admin
     */
    public void viewUser(){

    }

    /**
     * change the users data
     */
    public void updateUser(){

    }

    /**
     * delete a user by the admin
     */
    public void deleteUser(){

    }

}
