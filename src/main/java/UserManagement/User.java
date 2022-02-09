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
    //test constructor for less work during DB view testing
    public User(int id, String firstname){
        this.id=id;
        this.firstname=firstname;
    }
    //  main Constructor
    public User(){}
    public User(int id, String firstname, String lastName, String street, String housenumber, String zip, String town,
                String email, Date birthday, String Created, String insurancename, String insurancetype, String phoneNumber, float latitude, float longitude){
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


    public User(int id, String firstName, String lastName, String street, String houseNumber, String zip, String town,
                String email, Date birthday, String insuranceType, String insuranceName, float latitude, float longitude) {
        this.id = id;
        this.firstname = firstname;
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
    // new Constructor for test reasons / to get rid of Patient
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

    public User(int id, String firstName, String lastName, String street, String houseNumber, String zip, String town, String email, java.sql.Date birthDate, String insuranceType, String insuranceName, float latitude, float longitude) {
    }

    // Set-Method's
    public void setGender(Gender gender){
        this.gender = gender;
    }
    public void setUserId(int id){
        this.id = id;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
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

    // Get-Method's
    public int getUserId(){
        return id;
    }
    public Gender getGender(){
        return gender;
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

    //Amalie getter und setter methods
    public String getFirstname() {
        return firstname;
    }
    public String getHousenumber() {
        return housenumber;
    }

    public void setHousenumber(String housenumber) {
        this.housenumber = housenumber;
    }
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getCreated() {
        return Created;
    }

    public void setCreated(String created) {
        Created = created;
    }

    public String getInsurancename() {
        return insurancename;
    }

    public void setInsurancename(String insurancename) {
        this.insurancename = insurancename;
    }

    public String getInsurancetype() {
        return insurancetype;
    }

    public void setInsurancetype(String insurancetype) {
        this.insurancetype = insurancetype;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }
    // Functions
    /**
     * register a user into our system
     * @param password
     * @return
     */
    public boolean register(String password){

        return true;
    }

    /**
     * function that updates the current attributes of a class to the database
     */
    public void updateUser(){

    }

    /**
     * change only the password of the given user
     * @param password
     */
    public void changePassword(String password){

    }

    /**
     * deletes the user
     */
    public void deleteUser(){

    }

}

