package com.example.pedro.testvocces.model;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {

    private int id;
    private String gender;
    private Name name;
    private Date dob;
    private String phone;
    private Picture picture;
    private int visits;

    public User(){
        name = new Name();
        picture = new Picture();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }
    public void setDob(Long dob) {
        this.dob = new Date(dob);
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    public int getVisits() {
        return visits;
    }

    public void setVisits(int visits) {
        this.visits = visits;
    }
}
