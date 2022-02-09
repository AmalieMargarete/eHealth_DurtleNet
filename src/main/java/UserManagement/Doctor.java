package UserManagement;

import Enum.DocType;
import Enum.Gender;


/**
 * Class to hold the data of our doctor
 * @author Viktor Benini, 12989876
 * @author Amalie Wilke
 */
public class Doctor extends User {

    String docType;
    String openingHours;
    String practiceAddress;
    int id;
    String name;
    String lastName;
    String street;
    String housenumber;
    String zip;
    String town;
    String email;
    String phonenumber;
    float latitude;
    float longitude;

    // Constructor
    public Doctor(){}

    public Doctor(int id, /*Gender gender,*/ String name, String lastName, String street, String housenumber,
                  String zip, String town, String email, String phonenumber, String docType,
                  String openingHours, float latitude, float longitude){
        this.id = id;
        //this.gender = gender;
        this.name = name;
        this.lastName = lastName;
        this.street= street;
        this.housenumber=housenumber;
        this.zip=zip;
        this.town=town;
        this.email = email;
        this.phoneNumber = phonenumber;
        this.docType = docType;
        this.openingHours = openingHours;
        this.latitude=latitude;
        this.longitude=longitude;
    }
    public Doctor(String name, String lastName, String docType, String street, String housenumber,
                  String zip, String town){

        this.name = name;
        this.lastName = lastName;
        this.street= street;
        this.housenumber=housenumber;
        this.zip=zip;
        this.town=town;
        this.docType = docType;

    }
    public Doctor(int id, /*Gender gender,*/ String name, String lastName, String street, String housenumber,
                  String zip, String town, String email, String phonenumber, String docType,
                  String openingHours){
        this.id = id;
        //this.gender = gender;
        this.name = name;
        this.lastName = lastName;
        this.street= street;
        this.housenumber=housenumber;
        this.zip=zip;
        this.town=town;
        this.email = email;
        this.phoneNumber = phonenumber;
        this.docType = docType;
        this.openingHours = openingHours;

    }
    // Get-Methods
    public int getId(){
        return this.id;
    }
    public Gender getGender() {
        return gender;
    }
    public String getName(){
        return name;
    }
    public String getLastName(){
        return lastName;
    }
    public String getEmail(){
        return email;
    }
    public String getPhoneNumber(){
        return phoneNumber;
    }
    public String getDocType(){
        return docType;
    }
    public String getOpeningHours(){
        return openingHours;
    }
    public String getPracticeAddress(){
        return practiceAddress;
    }
    public String getStreet(){
        return street;
    }
    public String getZIP(){
        return zip;
    }
    public String getTown(){
        return town;
    }
    public String getHouseNumber(){
        return housenumber;
    }
    public float getLatitude(){return latitude;}
    public float getLongitude(){return longitude;}
    // Set-Method's
    public void setUserId(int id){
        this.id = id;
    }
    public void setGender(Gender gender){
        this.gender = gender;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    public void setEMail(String email){
        this.email = email;
    }
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }
    public void setDocType(String docType){
        this.docType = docType;
    }
    public void setOpeningHours(String openingHours){
        this.openingHours = openingHours;
    }
    public void setPracticeAddress(String practiceAddress){
        this.practiceAddress = practiceAddress;
    }
    public void setLatitude(float latitude){this.latitude=latitude;}
    public void setLongitude(float longitude){this.longitude=longitude;}

}
