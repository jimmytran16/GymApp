package com.example.foreverfitness;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foreverfitness.Model.History;
import com.example.foreverfitness.Model.User;
import com.example.foreverfitness.db.DataBaseHelper;
import com.example.foreverfitness.auth.UserAuth;

import java.util.ArrayList;


public class SignInPage extends AppCompatActivity {
    private Button SIGNUP_BTN, LOGIN_BTN, RESET_BTN;
    private TextView USERNAME, PASSWORD;
    private String LOG = "SignInPage.class";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);
        SIGNUP_BTN= (Button)findViewById(R.id.signupBtn);
        LOGIN_BTN= (Button)findViewById(R.id.login);
        RESET_BTN = (Button)findViewById(R.id.resetpassword);
        //SET ALL BUTTONS FOR ON CLICK LISTENER
        SIGNUP_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignInPage.this,RegisterPage.class));
            }
        });
        LOGIN_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataBaseHelper db = new DataBaseHelper(SignInPage.this);
                USERNAME = (TextView)findViewById(R.id.username);
                PASSWORD = (TextView)findViewById(R.id.password);
                if(USERNAME.getText().toString().trim().length() == 0||PASSWORD.getText().toString().trim().length()==0){ //If the username field or password field is empty... prompt the user to fill out the fields
                    Toast.makeText(getApplicationContext(),"Please fill in the fields!",Toast.LENGTH_SHORT).show();
                }
                else{
                    User u  = db.getUser(USERNAME.getText().toString(),PASSWORD.getText().toString());
                    if(u!=null){
                        Intent intent = new Intent(getApplicationContext(),UserDashboard.class);
                        UserAuth.logIn(u); // log the user into by passing the User object into the UserAuth object
                        ArrayList<History> logs_list = db.loadLogs();
                        if(logs_list!=null){ //if you have logs from the database, then populate to the UserAuth's ArrayList
                            UserAuth.LIST_OF_LOGS = logs_list;
                        }
                        startActivity(intent);
                    }
                    else{ // If there is no user with that username then prompt user
                        Toast.makeText(getApplicationContext(),"Wrong crudentials!",Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
}
