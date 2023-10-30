package com.myadhd.myadhd.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.myadhd.MyApplication;
import com.myadhd.R;
import com.myadhd.myadhd.Retrofit.ApiClient;
import com.myadhd.myadhd.DTOS.User;
import com.myadhd.myadhd.Retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateUserProfileActivity extends Activity {

    private EditText firstname, lastname, email, password, username;
    private TextView close;
    MyApplication application = new MyApplication();
    String token = "Bearer " + application.getUserToken();
    Integer userId = application.getUserId();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_update_user);
        close = findViewById(R.id.close);
        firstname = findViewById(R.id.name);
        lastname = findViewById(R.id.lname);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        username = findViewById(R.id.username);
        Button updateUserBtn = findViewById(R.id.updateUserButton);
        close.setOnClickListener(view -> {
            finish();
        });
        updateUserBtn.setOnClickListener(view -> {

            Log.i("EMAIL", email.getText().toString());
            if(!email.getText().toString().trim().equalsIgnoreCase("")){
                validateEmail();
            }
            else if(!password.getText().toString().trim().equalsIgnoreCase("")){
                validatePassword();
            }
            else {
                ApiClient apiClient = RetrofitClient.getRetrofitInstance().create(ApiClient.class);
                User user = new User(firstname.getText().toString(), lastname.getText().toString(),
                        email.getText().toString(), password.getText().toString(), username.getText().toString());

                Call<User> call = apiClient.updateUserById(token, userId, user);
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        Log.i("S-a actualizat user-ul", response.body().toString());;
                        Intent intent = new Intent(UpdateUserProfileActivity.this, UserProfileActivity.class);
                        overridePendingTransition(0, 0);
                        startActivity(intent);
                        overridePendingTransition(0, 0);
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Log.i("Nu actualizat user-ul", t.toString());
                    }
                });
            }

        });
    }

    private boolean validateEmail() {
        String val = email.getText().toString().trim();
        String checkEmail = "[a-zA-Z0-9._-]+@[a-z]+.+[a-z]+";
       if (!val.matches(checkEmail)) {
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
        if (!val.matches(checkPassword)) {
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