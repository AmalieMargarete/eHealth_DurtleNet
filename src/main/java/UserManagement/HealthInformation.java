package UserManagement;

/**
 * Class to hold the health info after they get queried from the DB for easier use
 * @author Viktor Benini; StudentID: 1298976
 */
public class HealthInformation {

    private String socialSecurityNumber;
    private String medicalRecordNumber;
    private String healthInsuranceNumber;
    private String height;
    private String weight;
    private String medicalUse;
    private String allergies;

    /**
     * Constructor to set given values
     * @param socialSecurityNumber
     * @param medicalRecordNumber
     * @param healthInsuranceNumber
     * @param height
     * @param weight
     * @param medicalUse
     * @param allergies
     */
    public HealthInformation(String socialSecurityNumber, String medicalRecordNumber, String healthInsuranceNumber, String height, String weight, String medicalUse, String allergies) {
        this.socialSecurityNumber = socialSecurityNumber;
        this.medicalRecordNumber = medicalRecordNumber;
        this.healthInsuranceNumber = healthInsuranceNumber;
        this.height = height;
        this.weight = weight;
        this.medicalUse = medicalUse;
        this.allergies = allergies;
    }

    /**
     * Basic constructor
     */
    public HealthInformation() {

    }


    // GET-METHODS

    /**
     * get String Social Security Number
     * @return
     */
    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    /**
     * get String Medical Record Number
     * @return
     */
    public String getMedicalRecordNumber() {
        return medicalRecordNumber;
    }

    /**
     * get String Health Insurance Number
     * @return
     */
    public String getHealthInsuranceNumber() {
        return healthInsuranceNumber;
    }

    /**
     * get String height
     * @return
     */
    public String getHeight() {
        return height;
    }

    /**
     * get String weight
     * @return
     */
    public String getWeight() {
        return weight;
    }

    /**
     * get String Medical Use
     * @return
     */
    public String getMedicalUse() {
        return medicalUse;
    }

    /**
     * get String Allergies
     * @return
     */
    public String getAllergies() {
        return allergies;
    }



    // SET-METHODS

    /**
     * set String Social Security Number
     * @param socialSecurityNumber
     */
    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    /**
     * set string Medical Record Number
     * @param medicalRecordNumber
     */
    public void setMedicalRecordNumber(String medicalRecordNumber) {
        this.medicalRecordNumber = medicalRecordNumber;
    }

    /**
     * set String Health Insurance Number
     * @param healthInsuranceNumber
     */
    public void setHealthInsuranceNumber(String healthInsuranceNumber) {
        this.healthInsuranceNumber = healthInsuranceNumber;
    }

    /**
     * set String height
     * @param height
     */
    public void setHeight(String height) {
        this.height = height;
    }

    /**
     * set String weight
     * @param weight
     */
    public void setWeight(String weight) {
        this.weight = weight;
    }

    /**
     * set String Medical Use
     * @param medicalUse
     */
    public void setMedicalUse(String medicalUse) {
        this.medicalUse = medicalUse;
    }

    /**
     * set String Allergies
     * @param allergies
     */
    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }



}
