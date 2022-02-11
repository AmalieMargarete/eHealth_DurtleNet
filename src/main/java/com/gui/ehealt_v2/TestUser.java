/**
 * Test User Class for database informatio management, login, registration and admin work
 * DB: MYSQL, local host
 * After registration immediate return to login page for login
 * @author Amalie Wilke; StudentID: 1304925
 */
package com.gui.ehealt_v2;

import java.util.Date;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getStre() {
        return stre;
    }

    public void setStre(String stre) {
        this.stre = stre;
    }

    public String getNumb() {
        return numb;
    }

    public void setNumb(String numb) {
        this.numb = numb;
    }

    public String getZip_() {
        return zip_;
    }

    public void setZip_(String zip_) {
        this.zip_ = zip_;
    }

    public String getTown_() {
        return town_;
    }

    public void setTown_(String town_) {
        this.town_ = town_;
    }

    /*public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    } */

    public String getInsu() {
        return insu;
    }

    public void setInsu(String insu) {
        this.insu = insu;
    }

    public String getEmail_() {
        return email_;
    }

    public void setEmail_(String email_) {
        this.email_ = email_;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }
}
