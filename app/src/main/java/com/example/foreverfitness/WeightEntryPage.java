package com.example.foreverfitness;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foreverfitness.Model.History;
import com.example.foreverfitness.auth.UserAuth;
import com.example.foreverfitness.db.DataBaseHelper;

import java.util.Calendar;

public class WeightEntryPage extends AppCompatActivity {
    private  DatePickerDialog picker;
    private EditText eText;
    private Button saveBtn,cancelBtn;
    private TextView entryDateField,weightField;
    private RadioGroup radioMeasurement;
    private RadioButton radioChosenWeight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weight_entry_activity);
        radioMeasurement = (RadioGroup) findViewById(R.id.measureMent);
        saveBtn = (Button)findViewById(R.id.saveBtn);
        cancelBtn = (Button)findViewById(R.id.cancelBtn);
        weightField = (EditText) findViewById(R.id.weightEntry);
        entryDateField=(EditText) findViewById(R.id.entryDateField);
        entryDateField.setInputType(InputType.TYPE_NULL);
        entryDateField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(WeightEntryPage.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                entryDateField.setText((monthOfYear + 1) + "/" + dayOfMonth + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Find the chosen measurement for weight
                int selected_weight = radioMeasurement.getCheckedRadioButtonId();
                radioChosenWeight = (RadioButton)findViewById(selected_weight);
                String chosen_measurement = radioChosenWeight.getText().toString();
                //set a historyLog record passing the input fields into the parameters ---- users, date , weight
                History historyLog = new History(UserAuth.currentUser.getUsername(),entryDateField.getText().toString(),weightField.getText().toString()+" "+chosen_measurement);
                //call the db obj to update the logs
                DataBaseHelper db = new DataBaseHelper(getApplicationContext());
                if(db.addLog(historyLog)) { //pass in the history object, if it successsfully updates it will return TRUE
                    Toast.makeText(WeightEntryPage.this,"UPLOAD LOG SUCCESS!", Toast.LENGTH_SHORT);
                    startActivity(new Intent(WeightEntryPage.this,UserDashboard.class));
                }
                else{
                    Toast.makeText(WeightEntryPage.this,"ERROR UPDATING LOG!", Toast.LENGTH_SHORT);
                }
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WeightEntryPage.this,UserDashboard.class)); // if user presses cancels, redirect to the dashboard
            }
        });
    }
}
