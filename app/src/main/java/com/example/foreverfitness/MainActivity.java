package com.example.foreverfitness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private Button MAIN_SIGNUP, MAIN_SIGNIN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MAIN_SIGNUP= (Button)findViewById(R.id.main_signup);
        MAIN_SIGNIN= (Button)findViewById(R.id.main_signin);
        //SET ALL BUTTONS FOR ON CLICK LISTENER
        MAIN_SIGNUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,RegisterPage.class));
            }
        });
        MAIN_SIGNIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SignInPage.class));
            }
        });

    }
}
