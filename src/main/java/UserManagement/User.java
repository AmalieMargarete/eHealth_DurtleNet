package UserManagement;

import Enum.Gender; //I think we might not need gender?

import java.util.Date;

/**
 * class that holds our Userdata
 * and has basic functionality for user management,
 * like registering, updating a User
 * @authors Viktor Benini, StudentID: 1298976 and Amalie Wilke; StudentID: 1304925
 * Model for doctor class
 */

public class User {

    //I had to rename the variable names here because some were missing or not clear enough (Amalie)
    protected int id;
    protected Gender gender;
    protected String firstname;
    protected String lastName;
    protected String street;
    int IsAdmin;
    protected String housenumber;
    protected String zip;
    protected String town;
    protected String email;
    protected Date birthday;
    protected String Created;
    protected String insurancename;  //need to think here
    protected String insurancetype; //need to think  here about enums or drop down menu just as above
    protected String phoneNumber;
    protected float latitude;
    protected float longitude;

    /**
     * test constructor for less work during DB view testing
     * @param id
     * @param firstname
     */
    public User(int id, String firstname){
        this.id=id;
        this.firstname=firstname;
    }

    /**
     * Basic constructor
     */
    public User(){}

    /**
     * Main Constructor by the given values
     * @param id
     * @param firstname
     * @param lastName
     * @param street
     * @param housenumber
     * @param zip
     * @param town
     * @param email
     * @param birthday
     * @param Created
     * @param insurancename
     * @param IsAdmin
     * @param insurancetype
     * @param phoneNumber
     * @param latitude
     * @param longitude
     */
    public User(int id, String firstname, String lastName, String street, String housenumber, String zip, String town,
                String email, Date birthday, String Created, String insurancename, int IsAdmin, String insurancetype, String phoneNumber, float latitude, float longitude){
        this.id = id;
        //this.gender = gender;
        this.firstname = firstname;
        this.lastName = lastName;
        this.street= street;
        this.housenumber=housenumber;
        this.zip=zip;
        this.town=town;
        this.email = email;
        this.birthday=birthday;
        this.Created=Created;
        this.insurancename=insurancename;
        this.IsAdmin=IsAdmin;
        this.insurancetype=insurancetype;
        this.phoneNumber = phoneNumber;
        this.latitude=latitude;
        this.longitude=longitude;
    }

    /**
     * Constructor without the GEOCoding values
     * @param id
     * @param firstname
     * @param lastName
     * @param street
     * @param housenumber
     * @param zip
     * @param town
     * @param email
     * @param birthday
     * @param Created
     * @param insurancename
     * @param insurancetype
     * @param phoneNumber
     */
    public User(int id, String firstname, String lastName, String street, String housenumber, String zip, String town,
                String email, Date birthday, String Created, String insurancename, String insurancetype, String phoneNumber){
        this.id = id;
        //this.gender = gender;
        this.firstname = firstname;
        this.lastName = lastName;
        this.street= street;
        this.housenumber=housenumber;
        this.zip=zip;
        this.town=town;
        this.email = email;
        this.birthday=birthday;
        this.Created=Created;
        this.insurancename=insurancename;
        this.insurancetype=insurancetype;
        this.phoneNumber = phoneNumber;
        this.latitude=latitude;
        this.longitude=longitude;
    }

    /**
     * short main constructor for testing
     * @param id
     * @param firstName
     * @param lastName
     * @param street
     * @param houseNumber
     * @param zip
     * @param town
     * @param email
     * @param birthday
     * @param insuranceType
     * @param insuranceName
     * @param latitude
     * @param longitude
     */
    public User(int id, String firstName, String lastName, String street, String houseNumber, String zip, String town,
                String email, Date birthday, String insuranceType, String insuranceName, float latitude, float longitude) {
        this.id = id;
        this.firstname = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.street = street;
        this.housenumber = houseNumber;
        this.zip = zip;
        this.town = town;
        this.email = email;
        this.insurancename = insuranceName;
        this.insurancetype = insuranceType;
        this.latitude=latitude;
        this.longitude=longitude;
    }

    /**
     * new Constructor for test reasons / to get rid of Patient
     * @param id
     * @param firstName
     * @param lastName
     * @param birthday
     * @param street
     * @param houseNumber
     * @param zip
     * @param town
     * @param email
     * @param insuranceName
     * @param insuranceType
     * @param latitude
     * @param longitude
     */
    public User(int id, String firstName, String lastName, Date birthday, String street, String houseNumber, String zip, String town,
                String email, String insuranceName, String insuranceType, float latitude, float longitude) {
        this.id = id;
        this.firstname = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.street = street;
        this.housenumber = houseNumber;
        this.zip = zip;
        this.town = town;
        this.email = email;
        this.insurancename = insuranceName;
        this.insurancetype = insuranceType;
        this.latitude=latitude;
        this.longitude=longitude;
    }

    // Set-Method's

    /**
     * set Gender gender
     * @param gender
     */
    public void setGender(Gender gender){
        this.gender = gender;
    }

    /**
     * set int id
     * @param id
     */
    public void setUserId(int id){
        this.id = id;
    }

    /**
     * set String firstname
     * @param firstname
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
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

    // Get-Method's

    /**
     * get int id
     * @return
     */
    public int getUserId(){
        return id;
    }

    /**
     * get Gender gender
     * @return
     */
    public Gender getGender(){
        return gender;
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

    //Amalie getter und setter methods

    /**
     * get String first name
     * @return
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * get String house number
     * @return
     */
    public String getHousenumber() {
        return housenumber;
    }

    /**
     *  set String house number
     * @param housenumber
     */
    public void setHousenumber(String housenumber) {
        this.housenumber = housenumber;
    }

    /**
     * get String street
     * @return
     */
    public String getStreet() {
        return street;
    }

    /**
     * set String street
     * @param street
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * get String Zip
     * @return
     */
    public String getZip() {
        return zip;
    }

    /**
     * set String zip
     * @param zip
     */
    public void setZip(String zip) {
        this.zip = zip;
    }

    /**
     * get String town
     * @return
     */
    public String getTown() {
        return town;
    }

    /**
     * set String town
     * @param town
     */
    public void setTown(String town) {
        this.town = town;
    }

    /**
     * get Date birthday
     * @return
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * set String birthday
     * @param birthday
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * get String created
     * @return
     */
    public String getCreated() {
        return Created;
    }

    /**
     * set String created
     * @param created
     */
    public void setCreated(String created) {
        Created = created;
    }

    /**
     * get String insurance name
     * @return
     */
    public String getInsurancename() {
        return insurancename;
    }

    /**
     * set String insurance name
     * @param insurancename
     */
    public void setInsurancename(String insurancename) {
        this.insurancename = insurancename;
    }

    /**
     * get String insurance type
     * @return
     */
    public String getInsurancetype() {
        return insurancetype;
    }

    /**
     * set String insurance type
     * @param insurancetype
     */
    public void setInsurancetype(String insurancetype) {
        this.insurancetype = insurancetype;
    }

    /**
     * get float latitude
     * @return
     */
    public float getLatitude() {
        return latitude;
    }

    /**
     * set float latitude
     * @param latitude
     */
    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    /**
     * get float longitude
     * @return
     */
    public float getLongitude() {
        return longitude;
    }

    /**
     * get int admin
     * @return
     */
    public int getIsAdmin() {
        return IsAdmin;
    }

    /**
     * set int admin
     * @param isAdmin
     */
    public void setIsAdmin(int isAdmin) {
        IsAdmin = isAdmin;
    }

    /**
     * set float longitude
     * @param longitude
     */
    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }


}

