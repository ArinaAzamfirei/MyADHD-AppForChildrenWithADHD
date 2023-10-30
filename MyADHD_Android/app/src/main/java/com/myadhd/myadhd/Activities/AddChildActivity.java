package com.myadhd.myadhd.Activities;

import androidx.annotation.NonNull;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.myadhd.MyApplication;
import com.myadhd.R;
import com.myadhd.myadhd.Retrofit.ApiClient;
import com.myadhd.myadhd.DTOS.Child;
import com.myadhd.myadhd.Retrofit.RetrofitClient;



import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddChildActivity extends Activity {

    private EditText firstname, lastname, age;
    String gender;
    private TextView close;
    RadioButton girl, boy;
    int max = 999900, min = 100000;
    MyApplication application = new MyApplication();
    String token = "Bearer " + application.getUserToken();
    Integer userId = application.getUserId();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_add_child);
        close = findViewById(R.id.close);
        firstname = findViewById(R.id.name);
        lastname = findViewById(R.id.lname);
        age = findViewById(R.id.age);
        girl = findViewById(R.id.girl);
        boy = findViewById(R.id.boy);
        Button addChildBtn = findViewById(R.id.addChildBtn);
        int code = (int) Math.floor(Math.random() * (max - min + 1) + min);
        close.setOnClickListener(view -> {
            finish();
        });

        addChildBtn.setOnClickListener(view -> {
            if(girl.isChecked()){
                gender = "Fată";
            }
            else if (boy.isChecked()){
                gender = "Băiat" ;
            }

            if(!validateFirstName() || !validateLastName() || !validateAge() ){
                Toast.makeText(AddChildActivity.this,"Introduceți datele corect!!",Toast.LENGTH_SHORT).show();

            }
            else
            {
            ApiClient apiClient = RetrofitClient.getRetrofitInstance().create(ApiClient.class);
            Child childData = new Child(firstname.getText().toString(), lastname.getText().toString(),lastname.getText().toString(),
                    Integer.parseInt(age.getText().toString()), gender, String.valueOf(code));

            Call<Child> call = apiClient.addChild(childData, token, userId);
            call.enqueue(new Callback<Child>() {

                @Override
                public void onResponse(@NonNull Call<Child> call, @NonNull Response<Child> response) {
                    Log.i("RASPUNS", response.toString());
                    Toast.makeText(AddChildActivity.this,"Adăugare cu succes!",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddChildActivity.this, ControlPanelActivity.class);
                    overridePendingTransition(0, 0);
                    startActivity(intent);
                    overridePendingTransition(0, 0);
                }

                @Override
                public void onFailure(@NonNull Call<Child> call, @NonNull Throwable t) {
                    Toast.makeText(AddChildActivity.this,"Nu s-a putut adăuga!",Toast.LENGTH_SHORT).show();
                }
        });}

    });
    }

    private boolean validateFirstName() {
        String val = firstname.getText().toString().trim();
        if (val.isEmpty()) {
            firstname.setError("Câmpul nu poate fi gol");
            return false;
        } else {
            firstname.setError(null);
            return true;
        }
    }

    private boolean validateLastName() {
        String val = lastname.getText().toString().trim();
        if (val.isEmpty()) {
            lastname.setError("Câmpul nu poate fi gol");
            return false;
        } else {
            lastname.setError(null);
            return true;
        }
    }

    private boolean validateAge() {
        int childAge = Integer.parseInt(String.valueOf(age.getText()));
        if (childAge < 6 || childAge > 17) {
            Toast.makeText(this, "Copilul dumnavoastră trebuie sa aibă vârsta" +
                    "cuprinsă între 6 și 17 ani!", Toast.LENGTH_SHORT).show();
            return false;
        } else
            return true;
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}