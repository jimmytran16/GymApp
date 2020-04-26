package com.example.foreverfitness;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foreverfitness.Model.User;
import com.example.foreverfitness.auth.UserAuth;
import com.example.foreverfitness.db.DataBaseHelper;

import androidx.appcompat.app.AppCompatActivity;


public class AccountSettingsPage extends AppCompatActivity {
    TextView GENDER,ADDRESS,HEIGHT,WEIGHT,GOAL_WEIGHT, GOAL_DATE,PHONE;
    private String LOG = "AccountSettingsPage.class";
    Button updateBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accountsettings_activity);
        updateBtn = (Button)findViewById(R.id.updateBtn);
        GENDER = (TextView)findViewById(R.id.accsetting_gender);
        ADDRESS = (TextView)findViewById(R.id.accsetting_address);
        HEIGHT = (TextView)findViewById(R.id.accsetting_height);
        WEIGHT = (TextView)findViewById(R.id.accsetting_weight);
        GOAL_WEIGHT = (TextView)findViewById(R.id.accsetting_goalweight);
        GOAL_DATE = (TextView)findViewById(R.id.accsetting_goaldate);
        PHONE = (TextView)findViewById(R.id.accsetting_phone);

        Log.d(LOG,UserAuth.currentUser.getPhonenumber());
        //Set the user's informations into the text fields
        GENDER.setText(String.valueOf(UserAuth.currentUser.getGender()).toUpperCase());
        ADDRESS.setText(String.valueOf(UserAuth.currentUser.getAddress().toUpperCase()));
        HEIGHT.setText(String.valueOf(UserAuth.currentUser.getHeight()).toUpperCase());
        WEIGHT.setText(String.valueOf(UserAuth.currentUser.getWeight()).toUpperCase());
        GOAL_WEIGHT.setText(String.valueOf(UserAuth.currentUser.getGoalweight()).toUpperCase());
        GOAL_DATE.setText(String.valueOf(UserAuth.currentUser.getGoaldate()).toUpperCase());
        PHONE.setText(String.valueOf(UserAuth.currentUser.getPhonenumber()).toUpperCase());

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //update the fields into a new User Object.
                //update the UserAuth with the new currentUser object
                //call the updateSettings() from DatabaseHelper
                User user = new User(GENDER.getText().toString().charAt(0),UserAuth.currentUser.getFullname(),ADDRESS.getText().toString(),
                        UserAuth.currentUser.getEmail(),PHONE.getText().toString(),UserAuth.currentUser.getUsername(),UserAuth.currentUser.getPassword(),
                        Double.parseDouble(HEIGHT.getText().toString()),Double.parseDouble(WEIGHT.getText().toString()));
                user.setGoalweight(Double.parseDouble(GOAL_WEIGHT.getText().toString()));
                user.setGoaldate(GOAL_DATE.getText().toString());

                UserAuth.currentUser = user;

                DataBaseHelper db = new DataBaseHelper(AccountSettingsPage.this);
                boolean success = db.updateUserSettings();
                if(success){
                    startActivity(new Intent(AccountSettingsPage.this,UserDashboard.class));
                }
                else{
                    Toast.makeText(getApplicationContext(),"ERROR UPDATING!",Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
}

