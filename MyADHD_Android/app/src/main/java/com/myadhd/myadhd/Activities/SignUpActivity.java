package com.myadhd.myadhd.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.myadhd.myadhd.Retrofit.ApiClient;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.google.android.material.button.MaterialButton;
import com.myadhd.R;
import com.myadhd.myadhd.DTOS.Register;
import com.myadhd.myadhd.Retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SignUpActivity extends AppCompatActivity {

    private EditText firstname, lastname, email, nickname, password;
    private ProgressBar loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // initializing our views
        firstname = findViewById(R.id.name);
        lastname = findViewById(R.id.lname);
        email = findViewById(R.id.email);
        nickname = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loading = findViewById(R.id.idLoadingPB);
        MaterialButton singUpBtn = findViewById(R.id.signupbtn);

        singUpBtn.setOnClickListener(view -> {
            loading.setVisibility(View.VISIBLE);
            // validating if the text field is empty or not.

            if(!validateEmail() || !validateFirstName() || !validateLastName() || !validateNickname() || !validatePassword()){
                Toast.makeText(SignUpActivity.this,"Introduceți datele corect!",Toast.LENGTH_SHORT).show();
            }
            else
            {
                ApiClient apiClient = RetrofitClient.getRetrofitInstance().create(ApiClient.class);
                Register dataRegister  = new Register(firstname.getText().toString(), lastname.getText().toString()
                        ,email.getText().toString(), nickname.getText().toString(), password.getText().toString());
                Call<Register> call = apiClient.register(dataRegister);

                call.enqueue(new Callback<Register>() {

                    @Override
                    public void onResponse(@NonNull Call<Register> call, @NonNull Response<Register> response) {
                        loading.setVisibility(View.GONE);
                        Toast.makeText(SignUpActivity.this,"Înregistrare cu succes!",Toast.LENGTH_SHORT).show();
                        Log.i("Response", response.toString());
                        Intent intent = new Intent(SignUpActivity.this, LogInActivity.class);
                        startActivity(intent);

                    }

                    @Override
                    public void onFailure(@NonNull Call<Register> call, @NonNull Throwable t) {
                        loading.setVisibility(View.GONE);
                        Toast.makeText(SignUpActivity.this,"A apărut o eroare!" + t,Toast.LENGTH_SHORT).show();
                        Log.e("ErrorSignIn" , t.toString());
                    }
                });
            }

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

    private boolean validateNickname() {
        String val = nickname.getText().toString().trim();
        String checkSpaces = "Aw{1,20}z";
        if (val.isEmpty()) {
            nickname.setError("Câmpul nu poate fi gol");
            return false;
        } else if (val.length() > 20) {
            nickname.setError("Nickname-ul este prea lung!");
            return false;
        } else {
            nickname.setError(null);
            return true;
        }
    }

    private boolean validateEmail() {
        String val = email.getText().toString().trim();
        String checkEmail = "[a-zA-Z0-9._-]+@[a-z]+.+[a-z]+";
        if (val.isEmpty()) {
            email.setError("Câmpul nu poate fi gol");
            return false;
        } else if (!val.matches(checkEmail)) {
            email.setError("Email invalid!");
            return false;
        } else {
            email.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        String val = password.getText().toString().trim();
        String checkPassword = "^" +
                "(?=.*[0-9])" +         //at least 1 digit
                "(?=.*[a-z])" +         //at least 1 lower case letter
                "(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=S+$)" +           //no white spaces
                ".{4,}" +               //at least 4 characters
                "$";
        if (val.isEmpty()) {
            password.setError("Câmpul nu poate fi gol");
            return false;
        } else if (!val.matches(checkPassword)) {
            password.setError("Parola trebuie să conțină măcar 4 caractere, o cifră, o literă mică, o literă mare, un caracter special" +
                    "și să nu conțină spații!");
            return false;
        } else {
            password.setError(null);
            return true;
        }
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}




