package com.example.foreverfitness;

import android.content.Intent;
import android.os.Bundle;

import com.example.foreverfitness.Model.User;
import com.example.foreverfitness.db.DataBaseHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

public class RegisterPage extends AppCompatActivity {
    private TextView fullname,address,email,phone,username,password,height,weight;
    private Button signupBtn;
    private RadioGroup radioGenderGroup;
    private RadioButton radioGenderChosen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        fullname = (TextView)findViewById(R.id.fullname);
        address = (TextView)findViewById(R.id.address);
        email = (TextView)findViewById(R.id.email);
        phone = (TextView)findViewById(R.id.phone);
        username = (TextView)findViewById(R.id.signup_username);
        password = (TextView)findViewById(R.id.signup_password);
        height = (TextView)findViewById(R.id.height);
        weight = (TextView)findViewById(R.id.weight);
        radioGenderGroup= (RadioGroup)findViewById(R.id.radioSex);
        signupBtn = (Button)findViewById(R.id.signup);
        //set a click listener for the sign up button
        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Get an instance of the user Model and pass in the information from the inputs
                //check for the male or female radio button that is selected
                int selected_id = radioGenderGroup.getCheckedRadioButtonId();
                radioGenderChosen = (RadioButton)findViewById(selected_id);
                char gender = getGender(radioGenderChosen.getText().toString());
                User user = new User(gender,fullname.getText().toString(),address.getText().toString(),email.getText().toString(),phone.getText().toString(),
                        username.getText().toString(),password.getText().toString(),Double.parseDouble(height.getText().toString()),Double.parseDouble(weight.getText().toString()));
                DataBaseHelper db = new DataBaseHelper(RegisterPage.this); //get the db instance, passing in the activity context
                if(db.addUser(user)){
                    Log.d("msg","SUCCESS INSERT!");
                    startActivity(new Intent(getApplicationContext(),SignInPage.class));
                }else{
                    Log.d("msg","FAIL TO INSERT!");
                }
            }
        });
    }
    private char getGender(String g){ //function to determine gender based on radio button, and returns in a char that represents the gender
        if(g.equals("Male")){
            return 'M';
        }else{
            return 'F';
        }
    }
}
