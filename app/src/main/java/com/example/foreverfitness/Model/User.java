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
    private double height;
    private double weight;
    private char gender;
    private String goaldate;
    private double goalweight;

    //constructor
    public User(char gender, String fullname, String address, String email, String phonenumber, String username, String password, double height, double weight) {
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
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public void setGoaldate(String goaldate) {
        this.goaldate = goaldate;
    }

    public void setGoalweight(double goalweight) {
        this.goalweight = goalweight;
    }

    public String getGoaldate() {
        return goaldate;
    }

    public double getGoalweight() {
        return goalweight;
    }

    public String getFullname() {
        return fullname;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public char getGender(){
        return gender;
    }
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }
}
