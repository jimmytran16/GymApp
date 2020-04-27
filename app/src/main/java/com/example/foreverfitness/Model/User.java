package com.example.foreverfitness.Model;
import java.io.Serializable;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.Format;


public class User implements Serializable {
    private String fullname;
    private String address;
    private String email;
    private String phonenumber;
    private String username;
    private String password;
    private String height;
    private String weight;
    private char gender;
    private String goaldate;
    private String goalweight;

    //constructor
    public User(char gender, String fullname, String address, String email, String phonenumber, String username, String password, String height, String weight) {
        Format f = new SimpleDateFormat("MM/dd/yy");
        String strDate = f.format(new Date());
        this.fullname = fullname;
        this.gender = gender;
        this.address = address;
        this.email = email;
        this.phonenumber = phonenumber;
        this.username= username;
        this.password = password;
        this.height = height;
        this.weight = weight;
        this.goaldate = strDate; //set default current date until update
        this.goalweight = weight; //set default weight
    }
    public User(){}

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getGoaldate() {
        return goaldate;
    }

    public void setGoaldate(String goaldate) {
        this.goaldate = goaldate;
    }

    public String getGoalweight() {
        return goalweight;
    }

    public void setGoalweight(String goalweight) {
        this.goalweight = goalweight;
    }
}
