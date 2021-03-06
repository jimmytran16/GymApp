package com.example.foreverfitness;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foreverfitness.auth.UserAuth;

public class UserDashboard extends AppCompatActivity {
    TextView FULLNAME, GENDER,WEIGHT,HEIGHT;
    Button signOutBtn;
    ImageButton cameraBtn,accountSettingsBtn,weightEditBtn,historyLogBtn;
    ImageView profileImageView;
    private String LOG = "UserDashBoard.class";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //check if user is logged in .. if not redirect to log in page
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_activity);
        profileImageView = (ImageView)findViewById(R.id.profileImageView);
        //Check if there is a profile picture existent in the DB
        if(UserAuth.currentUser.getProfilepic()!=null){
            profileImageView.setImageBitmap(UserAuth.currentUser.getProfilepic());
        }
        FULLNAME = (TextView)findViewById(R.id.dash_name);
        GENDER = (TextView)findViewById(R.id.dash_gender);
        WEIGHT = (TextView)findViewById(R.id.dash_weight);
        HEIGHT = (TextView)findViewById(R.id.dash_height);
        signOutBtn = (Button)findViewById(R.id.signoutBtn);
        weightEditBtn = (ImageButton)findViewById(R.id.weightEditBtn);
        cameraBtn = (ImageButton)findViewById(R.id.cameraBtn);
        accountSettingsBtn = (ImageButton)findViewById(R.id.accountSettingBtn);
        historyLogBtn = (ImageButton)findViewById(R.id.historyLogBtn);
//        User user = (User)getIntent().getSerializableExtra("User"); //get the User object from the intent
        FULLNAME.setText(UserAuth.currentUser.getFullname());
        GENDER.setText(String.valueOf(UserAuth.currentUser.getGender()));
        WEIGHT.setText(UserAuth.currentUser.getWeight());
        HEIGHT.setText(UserAuth.currentUser.getHeight());
        Log.d(LOG,UserAuth.currentUser.getPhonenumber());
        signOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //sign the user out
                UserAuth.logout();
                Intent intent = new Intent(UserDashboard.this, SignInPage.class);
                intent.putExtra("finish", true); // if you are checking for this in your other Activities
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | //Clear all the sessions so that you can't go back to the previous activity after logging out
                        Intent.FLAG_ACTIVITY_CLEAR_TASK |
                        Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
        accountSettingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserDashboard.this,AccountSettingsPage.class)); // Go to the Account's settings page
            }
        });
        weightEditBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserDashboard.this,WeightEntryPage.class));
            }
        });
        historyLogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserDashboard.this,WeightEntryHistory.class));
            }
        });
        cameraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ImageCapture.class));
            }
        });
    }
}
