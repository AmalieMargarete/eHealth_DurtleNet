package UserManagement;

/**
 * Singleton class of Admin, so that our admin can easily be recovered at any point in our program
 * @author Viktor Benini, StudentID: 1298976
 */
public class AdminHolder {
    private Admin admin;

    private final static AdminHolder INSTANCE = new AdminHolder();

    /**
     * Using private constructor, so new instances can't be created
     */
    private AdminHolder(){}

    /**
     * returns the instance of the class to use functions and
     * "initialize" class
     * @return
     */
    public static AdminHolder getInstance(){
        return INSTANCE;
    }

    /**
     * set Admin admin
     * @param admin
     */
    public void setAdmin(Admin admin){
        this.admin = admin;
    }

    /**
     * get Admin admin
     * @return
     */
    public Admin getAdmin(){
        return admin;
    }
}
