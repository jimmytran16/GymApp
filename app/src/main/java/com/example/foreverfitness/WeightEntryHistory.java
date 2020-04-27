package com.example.foreverfitness;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foreverfitness.Model.History;
import com.example.foreverfitness.auth.UserAuth;

import java.util.ArrayList;

public class WeightEntryHistory extends AppCompatActivity {
    ArrayList<History> historyArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_log_activity);
        ListView historyLogView = (ListView)findViewById(R.id.historyView);
        //SET ALL BUTTONS FOR ON CLICK LISTENER
        //Check if the user's entry logs is existing
        if(UserAuth.LIST_OF_LOGS == null){
            historyArrayList = new ArrayList<>();
            History his = new History("EMPTY","NO LOGS YET!","PLEASE ENTER LOG");
            historyArrayList.add(his);
        }
        else{
            historyArrayList =UserAuth.LIST_OF_LOGS;
        }
        HistoryLogAdapter adapter = new HistoryLogAdapter(WeightEntryHistory.this,R.layout.log_layout,historyArrayList);
        historyLogView.setAdapter(adapter);
    }
}
