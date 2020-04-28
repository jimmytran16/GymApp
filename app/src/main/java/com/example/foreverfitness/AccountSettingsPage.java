package com.example.foreverfitness;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foreverfitness.Model.User;
import com.example.foreverfitness.auth.UserAuth;
import com.example.foreverfitness.db.DataBaseHelper;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;


public class AccountSettingsPage extends AppCompatActivity {
    TextView GENDER,ADDRESS,HEIGHT,WEIGHT,GOAL_WEIGHT, GOAL_DATE,PHONE;
    private String LOG = "AccountSettingsPage.class";
    Button updateBtn;
    //Calender attributes
    private DatePickerDialog picker;
    private EditText eText;
    private Button btnGet;
    private TextView tvw;
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
        GOAL_DATE=(EditText) findViewById(R.id.accsetting_goaldate);
        PHONE = (TextView)findViewById(R.id.accsetting_phone);

        Log.d(LOG,UserAuth.currentUser.getPhonenumber());
        //Set the user's informations into the text fields
        GENDER.setText(String.valueOf(UserAuth.currentUser.getGender()).toUpperCase());
        ADDRESS.setText(UserAuth.currentUser.getAddress().toUpperCase());
        HEIGHT.setText(UserAuth.currentUser.getHeight().toUpperCase());
        WEIGHT.setText(UserAuth.currentUser.getWeight().toUpperCase());
        GOAL_WEIGHT.setText(UserAuth.currentUser.getGoalweight().toUpperCase());
        GOAL_DATE.setText(UserAuth.currentUser.getGoaldate().toUpperCase());
        PHONE.setText(UserAuth.currentUser.getPhonenumber().toUpperCase());


        GOAL_DATE.setInputType(InputType.TYPE_NULL);
        GOAL_DATE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(AccountSettingsPage.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                GOAL_DATE.setText((monthOfYear + 1) + "/" + dayOfMonth + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //update the fields into a new User Object.
                //update the UserAuth with the new currentUser object
                //call the updateSettings() from DatabaseHelper
                User user = new User(GENDER.getText().toString().charAt(0),UserAuth.currentUser.getFullname(),ADDRESS.getText().toString(),
                        UserAuth.currentUser.getEmail(),PHONE.getText().toString(),UserAuth.currentUser.getUsername(),UserAuth.currentUser.getPassword(),
                        HEIGHT.getText().toString(),WEIGHT.getText().toString());
                user.setGoalweight(GOAL_WEIGHT.getText().toString());
                user.setGoaldate(GOAL_DATE.getText().toString());
                user.setProfilepic(UserAuth.currentUser.getProfilepic());
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

