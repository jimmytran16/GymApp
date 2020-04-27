package com.example.foreverfitness;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.foreverfitness.Model.History;

import java.util.ArrayList;
import java.util.List;

public class HistoryLogAdapter  extends ArrayAdapter<History> {
    private String LOG = "HistoryLogAdapter";
    private Context pageContext;
    int pageResource;

    public HistoryLogAdapter(@NonNull Context context, int resource, @NonNull ArrayList<History> objects) {
        super(context, resource, objects);
        pageContext = context;
        pageResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        String entryDate = getItem(position).getEntryDate();
        String entryWeight = getItem(position).getWeighEntered();
        Log.d(LOG,"ENTRY: "+entryDate+" WEIGHT: "+entryWeight);
        LayoutInflater inflater = LayoutInflater.from(pageContext);
        convertView = inflater.inflate(pageResource,parent,false);

        TextView dateField = (TextView) convertView.findViewById(R.id.dateLogView);
        TextView weightField = (TextView) convertView.findViewById(R.id.weightLogView);
        weightField.setText(entryWeight);
        dateField.setText(entryDate);

        return convertView;
    }
}
