/**
 * Test User Class for database informatio management, login, registration and admin work
 * DB: MYSQL, local host
 * After registration immediate return to login page for login
 * @author Amalie Wilke; StudentID: 1304925
 */
package com.gui.ehealt_v2;

import java.util.Date;

/**
 * Class to hold TestUSer Information: same as user
 * @author Amalie Wilke; StudentID: 1304925
 */
public class TestUser {

    int id_user;
    //Gender gender; tricky with enums, discuss solution here
    String first;
    String last;
    String stre;
    String numb;
    String zip_;
    String town_;
    String email_;
    Date birth;
    String insu;
    String type;
    int isAdmin;
    //Insurance type; tricky with enums discuss solution here

    /**
     * Constructor to create a TestUser by the given values
     * @param id_user
     * @param first
     * @param last
     * @param stre
     * @param numb
     * @param zip_
     * @param town_
     * @param email_
     * @param birth
     * @param insu
     * @param type
     * @param isAdmin
     */
    public TestUser(int id_user, String first, String last, String stre, String numb, String zip_, String town_, String email_, Date birth, String insu, String type, int isAdmin) {
    this.id_user=id_user;
    this.first=first;
    this.last=last;
    this.stre=stre;
    this.numb=numb;
    this.zip_=zip_;
    this.town_=town_;
    this.email_=email_;
    this.birth=birth;
    this.insu=insu;
    this.isAdmin=isAdmin;
    this.type=type;
    }

    /**
     * returns String type
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     * sets Sting type
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * gets int admin
     * @return
     */
    public int getIsAdmin() {
        return isAdmin;
    }

    /**
     * sets int admin
     * @param isAdmin
     */
    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }

    /**
     * get int userId
     * @return
     */
    public int getId_user() {
        return id_user;
    }

    /**
     * sets int userId
     * @param id_user
     */
    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    /**
     * gets String firstname
     * @return
     */
    public String getFirst() {
        return first;
    }

    /**
     * sets String firstname
     * @param first
     */
    public void setFirst(String first) {
        this.first = first;
    }

    /**
     * gets String lastname
     * @return
     */
    public String getLast() {
        return last;
    }

    /**
     * sets String lastName
     * @param last
     */
    public void setLast(String last) {
        this.last = last;
    }

    /**
     * gets String street
     * @return
     */
    public String getStre() {
        return stre;
    }

    /**
     * sets String street
     * @param stre
     */
    public void setStre(String stre) {
        this.stre = stre;
    }

    /**
     * gets String house number
     * @return
     */
    public String getNumb() {
        return numb;
    }

    /**
     * sets String house number
     * @param numb
     */
    public void setNumb(String numb) {
        this.numb = numb;
    }

    /**
     * gets String ZIP
     * @return
     */
    public String getZip_() {
        return zip_;
    }

    /**
     * sets String ZIP
     * @param zip_
     */
    public void setZip_(String zip_) {
        this.zip_ = zip_;
    }

    /**
     * gets String town
     * @return
     */
    public String getTown_() {
        return town_;
    }

    /**
     * Sets String town
     * @param town_
     */
    public void setTown_(String town_) {
        this.town_ = town_;
    }

    /*public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    } */

    /**
     * gets String insurance
     * @return
     */
    public String getInsu() {
        return insu;
    }

    /**
     * sets string insurance
     * @param insu
     */
    public void setInsu(String insu) {
        this.insu = insu;
    }

    /**
     * gets Sting email
     * @return
     */
    public String getEmail_() {
        return email_;
    }

    /**
     * sets string email
     * @param email_
     */
    public void setEmail_(String email_) {
        this.email_ = email_;
    }

    /**
     * gets Date birthday
     * @return
     */
    public Date getBirth() {
        return birth;
    }

    /**
     * sets Date birthday
     * @param birth
     */
    public void setBirth(Date birth) {
        this.birth = birth;
    }
}
