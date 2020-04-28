package com.example.foreverfitness;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.foreverfitness.auth.UserAuth;
import com.example.foreverfitness.db.DataBaseHelper;

public class ImageCapture extends AppCompatActivity {
    Bitmap bitmap;
    Button uploadBtn,cancelBtn;
    ImageView capturedView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.capture_activity);
        uploadBtn = (Button)findViewById(R.id.captureBtn);
        capturedView = (ImageView)findViewById(R.id.imageCapture);
        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //call function to upload the image into the database
                DataBaseHelper db = new DataBaseHelper(ImageCapture.this);
                UserAuth.currentUser.setProfilepic(bitmap); //set the profile picture to the UserAuth object
                if(db.updateProfilePicture()){
                    Toast.makeText(getApplicationContext(),"SUCESSFULLY UPLOAD PROFILE PICTURE!", Toast.LENGTH_SHORT);
                }else{Toast.makeText(getApplicationContext(),"FAIL TO UPLOAD PROFILE PICTURE!", Toast.LENGTH_SHORT);}
                startActivity(new Intent(ImageCapture.this,UserDashboard.class));
            }
        });
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,0);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        bitmap = (Bitmap)data.getExtras().get("data");
        capturedView.setImageBitmap(bitmap);
    }
}
