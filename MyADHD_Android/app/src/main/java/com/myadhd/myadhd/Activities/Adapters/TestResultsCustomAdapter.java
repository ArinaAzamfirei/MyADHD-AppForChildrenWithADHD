package com.myadhd.myadhd.Activities.Adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.myadhd.R;
import com.myadhd.myadhd.DTOS.Test;

import java.util.ArrayList;


public class TestResultsCustomAdapter extends ArrayAdapter<Test> {

    ArrayList<Test> testsList;

    public TestResultsCustomAdapter(Activity context, ArrayList<Test>  testsList ){
        super(context, 0, testsList);
        this.testsList = testsList;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.test_list, parent, false);
        }

        Test currentTest = getItem(position);
        TextView diagnostic = listItemView.findViewById(R.id.diagnostic);
        TextView percentage = listItemView.findViewById(R.id.percent);
        TextView timestamp = listItemView.findViewById(R.id.timestamp);
        TextView code = listItemView.findViewById(R.id.code);

        diagnostic.setText("Diagnostic: " + currentTest.getDiagnostic());
        percentage.setText("Procentaj de veridicitate: " + currentTest.getPercentage() + "%");
        timestamp.setText("Data și ora: " + currentTest.getTimestamp());
        code.setText("Cod de acces: " + currentTest.getChildCode());

        return listItemView;
    }
}
