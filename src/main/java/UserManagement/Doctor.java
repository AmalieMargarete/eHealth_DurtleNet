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

    /**
     * Basic constructor
     */
    public Doctor(){}

    /**
     * Constructor by the given values.
     * Set all.
     * @param id
     * @param name
     * @param lastName
     * @param street
     * @param housenumber
     * @param zip
     * @param town
     * @param email
     * @param phonenumber
     * @param docType
     * @param openingHours
     * @param latitude
     * @param longitude
     */
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

    /**
     * short for test reasons
     * Constructor by the given values
     * @param name
     * @param lastName
     * @param docType
     * @param street
     * @param housenumber
     * @param zip
     * @param town
     */
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

    /**
     * Constructor by the given values.
     * Used don't have geocoding included
     * @param id
     * @param name
     * @param lastName
     * @param street
     * @param housenumber
     * @param zip
     * @param town
     * @param email
     * @param phonenumber
     * @param docType
     * @param openingHours
     */
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

    /**
     * get int id
     * @return
     */
    public int getId(){
        return this.id;
    }

    /**
     * get Gender gender
     * @return
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * get String firstname
     * @return
     */
    public String getName(){
        return name;
    }

    /**
     * same as get name
     * @return
     */
    public String getFirstname(){
        return name;
    }

    /**
     * get String lastname
     * @return
     */
    public String getLastName(){
        return lastName;
    }

    /**
     * get String email
     * @return
     */
    public String getEmail(){
        return email;
    }

    /**
     * get String phone number
     * @return
     */
    public String getPhoneNumber(){
        return phoneNumber;
    }

    /**
     * get String docType
     * @return
     */
    public String getDocType(){
        return docType;
    }

    /**
     * get String opening hours
     * @return
     */
    public String getOpeningHours(){
        return openingHours;
    }

    /**
     * get string practice address
     * @return
     */
    public String getPracticeAddress(){
        return practiceAddress;
    }

    /**
     * get string street
     * @return
     */
    public String getStreet(){
        return street;
    }

    /**
     * get String ZIP
     * @return
     */
    public String getZIP(){
        return zip;
    }

    /**
     * get string town
     * @return
     */
    public String getTown(){
        return town;
    }

    /**
     * get String house number
     * @return
     */
    public String getHouseNumber(){
        return housenumber;
    }

    /**
     * get float latitude
     * @return
     */
    public float getLatitude(){return latitude;}

    /**
     * get float longitude
     * @return
     */
    public float getLongitude(){return longitude;}


    // Set-Method's

    /**
     * set int id
     * @param id
     */
    public void setUserId(int id){
        this.id = id;
    }

    /**
     * set Gender gender
     * @param gender
     */
    public void setGender(Gender gender){
        this.gender = gender;
    }

    /**
     * set String firstname
     * @param name
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * set String lastname
     * @param lastName
     */
    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    /**
     * set String email
     * @param email
     */
    public void setEMail(String email){
        this.email = email;
    }

    /**
     * set String phone number
     * @param phoneNumber
     */
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    /**
     * set String docType
     * @param docType
     */
    public void setDocType(String docType){
        this.docType = docType;
    }

    /**
     * set String opening hours
     * @param openingHours
     */
    public void setOpeningHours(String openingHours){
        this.openingHours = openingHours;
    }

    /**
     * set String practice address
     * @param practiceAddress
     */
    public void setPracticeAddress(String practiceAddress){
        this.practiceAddress = practiceAddress;
    }

    /**
     * set float latitude
     * @param latitude
     */
    public void setLatitude(float latitude){this.latitude=latitude;}

    /**
     * set float longitude
     * @param longitude
     */
    public void setLongitude(float longitude){this.longitude=longitude;}

}
