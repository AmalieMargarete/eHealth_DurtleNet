package UserManagement;


/**
 * class to temporarily hold data used for login
 * @author Viktor Benini, StudentID: 1298976
 */
public class Login {
    private int userId;
    private String email;
    private String salt;
    private String hashedPassword;

    public Login(int userId, String email, String salt, String hashedPassword)
    {
        this.userId = userId;
        this.email = email;
        this.salt = salt;
        this.hashedPassword = hashedPassword;
    }

    public int getUserId()
    {
        return userId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getSalt()
    {
        return salt;
    }

    public void setSalt(String salt)
    {
        this.salt = salt;
    }

    public String getHashedPassword()
    {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword)
    {
        this.hashedPassword = hashedPassword;
    }
}
