package com.myadhd.myadhd.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.myadhd.MyApplication;
import com.myadhd.R;
import com.myadhd.myadhd.Retrofit.ApiClient;
import com.myadhd.myadhd.DTOS.Authenticate;
import com.myadhd.myadhd.DTOS.AuthenticateResponse;
import com.myadhd.myadhd.Retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogInActivity extends AppCompatActivity {

    private ProgressBar loading;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);

        TextView email = findViewById(R.id.username);
        TextView password = findViewById(R.id.password);
        MaterialButton loginBtn = findViewById(R.id.loginbtn);
        TextView createAcc = findViewById(R.id.createAccount);
        loading = findViewById(R.id.idLoadingPB);
        email.setText("ari.azamf@gmail.com");
        password.setText("parolamea");
        loginBtn.setOnClickListener(view -> {
            loading.setVisibility(View.VISIBLE);
            ApiClient apiClient = RetrofitClient.getRetrofitInstance().create(ApiClient.class);
            Authenticate dataAuthenticate = new Authenticate(email.getText().toString(), password.getText().toString());
            Call<AuthenticateResponse> call = apiClient.authenticate(dataAuthenticate);
            
            call.enqueue(new Callback<AuthenticateResponse>() {

                @Override
                public void onResponse(@NonNull Call<AuthenticateResponse> call, @NonNull Response<AuthenticateResponse> response) {

                    if(response.isSuccessful()){
                        MyApplication application = new MyApplication();
                        application.setUserToken(response.body().getToken());
                        application.setUserId(response.body().getUserId());
                        Toast.makeText(LogInActivity.this,"Autentificare cu succes!",Toast.LENGTH_SHORT).show();
                        Log.i("RASPUNSUL MEU", String.valueOf(application.getUserId()));
                        Intent intent = new Intent(LogInActivity.this, ControlPanelActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(LogInActivity.this,"Autentificare eșuată!",Toast.LENGTH_SHORT).show();
                    }
                    loading.setVisibility(View.GONE);

                }

                @Override
                public void onFailure(@NonNull Call<AuthenticateResponse> call, @NonNull Throwable t) {
                    Toast.makeText(LogInActivity.this,"Ați introdus un email sau o parolă greșită!" + t,Toast.LENGTH_LONG).show();
                    Log.e("ErrorLogIn" , "Aaa", t);
                    loading.setVisibility(View.GONE);
                }
            });

                });



        createAcc.setOnClickListener(view -> {
            Intent intent = new Intent(LogInActivity.this, SignUpActivity.class);
            startActivity(intent);

        });

    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}