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

    public HealthInformation(String socialSecurityNumber, String medicalRecordNumber, String healthInsuranceNumber, String height, String weight, String medicalUse, String allergies) {
        this.socialSecurityNumber = socialSecurityNumber;
        this.medicalRecordNumber = medicalRecordNumber;
        this.healthInsuranceNumber = healthInsuranceNumber;
        this.height = height;
        this.weight = weight;
        this.medicalUse = medicalUse;
        this.allergies = allergies;
    }

    public HealthInformation() {

    }


    // GET-METHODS
    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public String getMedicalRecordNumber() {
        return medicalRecordNumber;
    }

    public String getHealthInsuranceNumber() {
        return healthInsuranceNumber;
    }

    public String getHeight() {
        return height;
    }

    public String getWeight() {
        return weight;
    }

    public String getMedicalUse() {
        return medicalUse;
    }

    public String getAllergies() {
        return allergies;
    }

    // SET-METHODS
    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public void setMedicalRecordNumber(String medicalRecordNumber) {
        this.medicalRecordNumber = medicalRecordNumber;
    }

    public void setHealthInsuranceNumber(String healthInsuranceNumber) {
        this.healthInsuranceNumber = healthInsuranceNumber;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public void setMedicalUse(String medicalUse) {
        this.medicalUse = medicalUse;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }



}
