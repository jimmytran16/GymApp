package com.example.foreverfitness.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.foreverfitness.Model.User;
import com.example.foreverfitness.auth.UserAuth;


public class DataBaseHelper extends SQLiteOpenHelper {
    private String LOG = "DataBaseHelper.class";
    private String USER_TABLE = "Users";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "customer.db",null,1);
    }
    // this ia called the firs time a a db is accessed there should be code in here to eate a new database.
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CreateTableQueries = "CREATE TABLE Users (fullname varchar(40),address varchar(60),email varchar(30),phonenumber varchar(20),username varchar(20),password varchar(30),height int(11),weight int(11),goalweight int(11),goaldate varchar(11),gender varchar(1));";
        db.execSQL(CreateTableQueries);
        db.close();
    }
    //this is called if the db version number changes. It prevents previous users apps from breaking when you change the dataabse design
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public boolean addUser(User user){ //function to inserts the user crudentials
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contents = new ContentValues(); //object to store user values in a key value fashion to insert into the DB
        contents.put("fullname",user.getFullname());
        contents.put("address",user.getAddress());
        contents.put("email",user.getEmail());
        contents.put("phonenumber",user.getPhonenumber());
        contents.put("username",user.getUsername());
        contents.put("password",user.getPassword());
        contents.put("height",user.getHeight());
        contents.put("weight",user.getWeight());
        contents.put("goalweight",user.getGoalweight());
        contents.put("goaldate",user.getGoaldate());
        contents.put("gender",String.valueOf(user.getGender()));
        long success = db.insert("Users",null,contents);
        if(success == -1){
            db.close();
            return false;
        }
        else{
            db.close();
            return true;
        }
    }
    public User getUser(String username,String password){
        User user;
        String query = "SELECT * from Users where username = '"+username+"';"; //query for the user from the database
        SQLiteDatabase db = this.getReadableDatabase(); //only readable, not update or delete operations can be conducted
        Cursor cur = db.rawQuery(query,null); //cursor is a result set
        if(cur.moveToFirst()){ //check if there is a result for the user, if not then return null
            Log.d(LOG,"there is a user!");
            //check if password matches
            if(cur.getString(5).equals(password)){
                user = new User(); //get instance of user object
                //store the user's information into the user object from the DB resultset
                user.setFullname(cur.getString(0));
                user.setAddress(cur.getString(1));
                user.setEmail(cur.getString(2));
                user.setPhonenumber(cur.getString(3));
                user.setUsername(cur.getString(4));
                user.setPassword(cur.getString(5));
                user.setHeight(cur.getInt(6));
                user.setWeight(cur.getInt(7));
                user.setGoalweight(cur.getInt(8));
                user.setGoaldate(cur.getString(9));
                user.setGender(cur.getString(10).charAt(0));
                db.close();
            }else { //if doesnt match, set user object to be a NULL value
                Log.d(LOG,"But password does not match!");
                user = null;
            }

        }
        else { //if there's no existing user
            user = null; //if there is no data in the cursor then return null
        }
        db.close();
        return user; //return the user object
    }

    /*Function to update the user setting*/
    public boolean updateUserSettings(){
        //update setting
        //sql to pass onto the database to update the user
        ContentValues cv = new ContentValues();

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contents = new ContentValues(); //object to store user values in a key value fashion to insert into the DB
        contents.put("fullname",UserAuth.currentUser.getFullname());
        contents.put("address",UserAuth.currentUser.getAddress());
        contents.put("email",UserAuth.currentUser.getEmail());
        contents.put("phonenumber",UserAuth.currentUser.getPhonenumber());
        contents.put("username",UserAuth.currentUser.getUsername());
        contents.put("password",UserAuth.currentUser.getPassword());
        contents.put("height",UserAuth.currentUser.getHeight());
        contents.put("weight",UserAuth.currentUser.getWeight());
        contents.put("goalweight",UserAuth.currentUser.getGoalweight());
        contents.put("goaldate",UserAuth.currentUser.getGoaldate());
        contents.put("gender",String.valueOf(UserAuth.currentUser.getGender()));
        long success = db.update(USER_TABLE,contents,"username=?",new String[]{UserAuth.currentUser.getUsername()});
        if(success == 1){
            db.close();
            Log.d("DataBaseHelper.class","SUCCESS UPDATE");
            return true;
        }
        else{
            db.close();
            Log.d("DataBaseHelper.class","FAIL UPDATE");
            return false;
        }
    }
}
