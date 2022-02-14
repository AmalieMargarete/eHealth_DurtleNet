package UserManagement;

/**
 * Singleton class of user, so that our user can easily be recovered at any point in our program
 * @author Viktor Benini, StudentID: 1298976
 */
public class UserHolder {
    private User user;

    private final static UserHolder INSTANCE = new UserHolder();
    /**
     * Using private constructor, so new instances can't be created
     */
    private UserHolder(){}

    /**
     * returns UserHolder Instance
     * @return USerHolder Obj
     */
    public static UserHolder getInstance(){
        return INSTANCE;
    }

    /**
     * set User user
     * @param user
     */
    public void setUser(User user){
        this.user = user;
    }

    /**
     * get User user
     * @return
     */
    public User getUser(){
        return user;
    }

}
