package UserManagement;


/**
 * Never used due to other solution for this problem.
 * class to temporarily hold data used for login
 * @author Viktor Benini, StudentID: 1298976
 */
public class Login {
    private int userId;
    private String email;
    private String salt;
    private String hashedPassword;

    /**
     * Constructor setting the given values
     * @param userId
     * @param email
     * @param salt
     * @param hashedPassword
     */
    public Login(int userId, String email, String salt, String hashedPassword)
    {
        this.userId = userId;
        this.email = email;
        this.salt = salt;
        this.hashedPassword = hashedPassword;
    }

    /**
     * get int id
     * @return
     */
    public int getUserId()
    {
        return userId;
    }

    /**
     * set int id
     * @param userId
     */
    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    /**
     * get String email
     * @return
     */
    public String getEmail()
    {
        return email;
    }

    /**
     * set String email
     * @param email
     */
    public void setEmail(String email)
    {
        this.email = email;
    }

    /**
     * get String salt
     * @return
     */
    public String getSalt()
    {
        return salt;
    }

    /**
     * set string salt
     * @param salt
     */
    public void setSalt(String salt)
    {
        this.salt = salt;
    }

    /**
     * get String Hashed Password
     * @return
     */
    public String getHashedPassword()
    {
        return hashedPassword;
    }

    /**
     * set String Hashed Password
     * @param hashedPassword
     */
    public void setHashedPassword(String hashedPassword)
    {
        this.hashedPassword = hashedPassword;
    }
}
