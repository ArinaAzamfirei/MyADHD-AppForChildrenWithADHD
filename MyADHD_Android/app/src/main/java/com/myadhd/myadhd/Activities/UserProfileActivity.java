package com.myadhd.myadhd.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.myadhd.MyApplication;
import com.myadhd.R;
import com.myadhd.myadhd.Retrofit.ApiClient;
import com.myadhd.myadhd.DTOS.User;
import com.myadhd.myadhd.Retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserProfileActivity extends AppCompatActivity {


    MyApplication application = new MyApplication();
    String token = "Bearer " + application.getUserToken();
    TextView nume_prenume, username, email;
    MaterialButton updateBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        nume_prenume = findViewById(R.id.firstnameLastname);
        email = findViewById(R.id.email);
//        username = findViewById(R.id.username);

        ApiClient apiClient = RetrofitClient.getRetrofitInstance().create(ApiClient.class);
        Call<User> call = apiClient.getUserById(token, application.getUserId());
        call.enqueue(new Callback<User>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.i("S-a raspuns:)!", response.body().toString());
                nume_prenume.setText("Nume și prenume: "  + response.body().getFirstname() + " " + response.body().getLastname());
//                username.setText("Nume utilizator: " + response.body().getUsername());
                email.setText("Email: "  + response.body().getEmail());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.i("FAILURE", "UserProfileActivity", t);
            }
        });
    }

    public void updateUser(View v){
        updateBtn = findViewById(R.id.updateUserButton);
        updateBtn.setOnClickListener(view -> {
            Intent intent = new Intent(UserProfileActivity.this, UpdateUserProfileActivity.class);
            startActivity(intent);
        });
        Log.i("ALL GOOD", "DA");

    }

    public void logOut(View v){
        MaterialButton logOutBtn;
        logOutBtn = findViewById(R.id.logout);
        logOutBtn.setOnClickListener(view -> {
            application.setUserId(null);
            application.setUserToken(null);
            Intent intent = new Intent(UserProfileActivity.this, LogInActivity.class);
            startActivity(intent);
        });
    }

    public void controlPanel(View view) {
        MaterialButton controlPanelBtn = findViewById(R.id.controlpanel);
        controlPanelBtn.setOnClickListener(view1 -> {
            Intent intent = new Intent(UserProfileActivity.this, ControlPanelActivity.class);
            startActivity(intent);
        });

    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

}