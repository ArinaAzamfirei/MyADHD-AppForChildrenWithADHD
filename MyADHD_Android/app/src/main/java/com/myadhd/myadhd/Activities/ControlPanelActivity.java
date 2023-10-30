package com.myadhd.myadhd.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.myadhd.MyApplication;
import com.myadhd.R;
import com.myadhd.myadhd.Activities.Adapters.ChildCustomAdapter;
import com.myadhd.myadhd.Retrofit.ApiClient;
import com.myadhd.myadhd.DTOS.Child;
import com.myadhd.myadhd.Retrofit.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ControlPanelActivity extends AppCompatActivity {

    MaterialButton addChild;
    ListView listView;
    ChildCustomAdapter childCustomAdapter;
    MyApplication application = new MyApplication();
    String token = "Bearer " + application.getUserToken();
    Integer userId = application.getUserId();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_panel);
        addChild  = findViewById(R.id.addChild);
        listView = findViewById(R.id.listView);
        ArrayList<Child> childrenList = new ArrayList<>();
        ApiClient apiClient = RetrofitClient.getRetrofitInstance().create(ApiClient.class);
        Call<ArrayList<Child>> call = apiClient.getChildren(token, userId);
        call.enqueue(new Callback<ArrayList<Child>>() {

            @Override
            public void onResponse(Call<ArrayList<Child>> call, Response<ArrayList<Child>> response) {

                assert response.body() != null;
                childrenList.addAll(response.body());
                Log.i("S-a raspuns:)!", childrenList.toString());
                childCustomAdapter = new ChildCustomAdapter( ControlPanelActivity.this, childrenList);
                listView.setAdapter(childCustomAdapter);
                childCustomAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ArrayList<Child>> call, Throwable t) {

            }
        });
    }

    public void addChild(View v){
        addChild.setOnClickListener(view -> {
                    Intent intent = new Intent(ControlPanelActivity.this, AddChildActivity.class);
                    startActivity(intent);
                    childCustomAdapter.notifyDataSetChanged();
                }
                );
    }
    public void logOut(View v){
        MaterialButton logOutBtn;
        logOutBtn = findViewById(R.id.logout);
        logOutBtn.setOnClickListener(view -> {
            application.setUserId(null);
            application.setUserToken(null);
            Intent intent = new Intent(ControlPanelActivity.this, LogInActivity.class);
            startActivity(intent);
        });


    }

    public void myProfile(View view) {
        MaterialButton myProfileBtn;
        myProfileBtn = findViewById(R.id.myProfile);
        myProfileBtn.setOnClickListener(view1 -> {
            Intent intent = new Intent(ControlPanelActivity.this, UserProfileActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}

