package com.myadhd.myadhd.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.google.android.material.button.MaterialButton;
import com.myadhd.MyApplication;
import com.myadhd.R;
import com.myadhd.myadhd.Activities.Adapters.TestResultsCustomAdapter;
import com.myadhd.myadhd.Retrofit.ApiClient;
import com.myadhd.myadhd.DTOS.Test;
import com.myadhd.myadhd.Retrofit.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DiagnosticActivity extends AppCompatActivity {

    ListView listView;
    TestResultsCustomAdapter testResultsCustomAdapter;
    MyApplication application = new MyApplication();
    String token = "Bearer " + application.getUserToken();
    Integer childId = application.getCurrentChildId();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnostic);
        listView = findViewById(R.id.listView);
        ArrayList<Test> testList = new ArrayList<>();
        ApiClient apiClient = RetrofitClient.getRetrofitInstance().create(ApiClient.class);
        Call<ArrayList<Test>> call = apiClient.getTestsByChildId(token, childId);
        call.enqueue(new Callback<ArrayList<Test>>() {
            @Override
            public void onResponse(Call<ArrayList<Test>> call, Response<ArrayList<Test>> response) {
                assert response.body() != null;
                testList.addAll(response.body());
                Log.i("S-a raspuns!", testList.toString());
                testResultsCustomAdapter = new TestResultsCustomAdapter(DiagnosticActivity.this,testList);
                listView.setAdapter(testResultsCustomAdapter);
                testResultsCustomAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<ArrayList<Test>> call, Throwable t) {

            }
        });
    }
    public void controlPanel(View view) {
        MaterialButton controlPanelBtn;
        controlPanelBtn = findViewById(R.id.controlpanel);
        controlPanelBtn.setOnClickListener(view1 -> {
            Intent intent = new Intent(DiagnosticActivity.this, ControlPanelActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}