package UserManagement;
/**
 * Singleton class of user, so that our user can easily be recovered at any point in our program
 * @author Amalie Wilke; StudentID: 1304925
 */
public class DoctorHolder {
    private Doctor doctor;

    private final static DoctorHolder INSTANCE = new DoctorHolder();
    /**
     * Using private constructor, so new instances can't be created
     */
    private DoctorHolder(){}

    /**
     * returns DoctorHolder Instance
     * @return DoctorHolder Obj
     */
    public static DoctorHolder getInstance(){
        return INSTANCE;
    }

    /**
     * set Doctor doctor
     * @param doctor
     */
    public void setDoctor(Doctor doctor){
        this.doctor = doctor;
    }

    /**
     * get Doctor doctor
     * @return
     */
    public Doctor getDoctor(){
        return doctor;
    }
}
