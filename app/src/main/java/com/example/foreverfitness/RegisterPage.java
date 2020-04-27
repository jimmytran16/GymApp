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
    private RadioGroup radioGenderGroup,radioHeight,radioWeight;
    private RadioButton radioGenderChosen,radioHeightChosen,radioWeightChosen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        fullname = (TextView)findViewById(R.id.register_fullname);
        address = (TextView)findViewById(R.id.register_address);
        email = (TextView)findViewById(R.id.register_email);
        phone = (TextView)findViewById(R.id.register_phone);
        username = (TextView)findViewById(R.id.register_username);
        password = (TextView)findViewById(R.id.register_password);
        height = (TextView)findViewById(R.id.register_height);
        weight = (TextView)findViewById(R.id.register_weight);
        radioGenderGroup= (RadioGroup)findViewById(R.id.radioSex);
        radioHeight= (RadioGroup)findViewById(R.id.register_heightRadio);
        radioWeight= (RadioGroup)findViewById(R.id.register_weightRadio);
        signupBtn = (Button)findViewById(R.id.signup);
        //set a click listener for the sign up button
        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Get an instance of the user Model and pass in the information from the inputs
                //check for the male or female radio button that is selected
                int selected_id = radioGenderGroup.getCheckedRadioButtonId();
                int selected_height = radioHeight.getCheckedRadioButtonId();
                int selected_weight = radioWeight.getCheckedRadioButtonId();

                radioGenderChosen = (RadioButton)findViewById(selected_id);
                radioHeightChosen = (RadioButton)findViewById(selected_height);
                radioWeightChosen = (RadioButton)findViewById(selected_weight);
                char gender = getGender(radioGenderChosen.getText().toString());
                //chosen measurements for height and weight
                String height_measurement = radioHeightChosen.getText().toString();
                String weight_measurement = radioWeightChosen.getText().toString();

                User user = new User(gender,fullname.getText().toString(),address.getText().toString(),email.getText().toString(),phone.getText().toString(),
                        username.getText().toString(),password.getText().toString(),height.getText().toString()+" "+height_measurement,weight.getText().toString()+" "+weight_measurement);
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
