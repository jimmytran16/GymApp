package com.example.foreverfitness.auth;

import com.example.foreverfitness.Model.History;
import com.example.foreverfitness.Model.User;

import java.util.ArrayList;

public class UserAuth {
    public static ArrayList<History> LIST_OF_LOGS; //this will keep track of all of the logs that are being made
    public static User currentUser = null; //default current user to be null
    public static boolean isLoggedin = false; //default to set user to not be counted as logged in
    public static void logIn(User user){
        isLoggedin = true;
        currentUser = user;
        LIST_OF_LOGS = new ArrayList<>(); //list of logs that will be set to the UserAuth when the user logs in
    }
    public static void logout(){
        isLoggedin = false; //set isLogedIn to false because they are logged out
        currentUser = null; //empty the currentUser object
        LIST_OF_LOGS.removeAll(LIST_OF_LOGS); //empty out the arraylist
    }
    public static void updateUser(User user){ //function to update the user when they update their settings
        currentUser = user;
    }

}
