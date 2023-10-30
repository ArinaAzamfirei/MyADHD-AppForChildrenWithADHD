package com.myadhd.myadhd.Activities;

import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.app.Dialog;

import android.content.Intent;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.myadhd.MyApplication;
import com.myadhd.R;
import com.myadhd.myadhd.DTOS.Question;
import com.myadhd.myadhd.DTOS.Test;
import com.myadhd.myadhd.Retrofit.ApiClient;
import com.myadhd.myadhd.Retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.Arrays;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener{



    MyApplication application = new MyApplication();
    String token = "Bearer " + application.getUserToken();
    TextView questionCount, questionText;
    RadioButton option1, option2, option3, option4;
    ArrayList<Question> questionsList;
    ArrayList<Integer> answersList = new ArrayList<>();
    ApiClient api = RetrofitClient.getRetrofitInstance().create(ApiClient.class);
    Button confirmButton;
    MaterialButton controlPanel;
    Dialog myDialog;
    private int currentQuestionIndex;
    private int pageNo = 0;
    Integer childId = application.getCurrentChildId();
    String  childCode = application.getCurrentChildCode();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        myDialog = new Dialog(this);

        questionCount = findViewById(R.id.text_view_question_count);
        questionText = findViewById(R.id.text_view_question);
        option1 = findViewById(R.id.radio_button1);
        option2 = findViewById(R.id.radio_button2);
        option3 = findViewById(R.id.radio_button3);
        option4 = findViewById(R.id.radio_button4);
        confirmButton = findViewById(R.id.button_confirm_next);
        confirmButton.setOnClickListener(this);
        myDialog.setContentView(R.layout.activity_loading);

        option1.setText("0");
        option2.setText("1");
        option3.setText("2");
        option4.setText("3");


        showQuestions();
    }

    @SuppressLint({"SetTextI18n", "NonConstantResourceId"})
    @Override
    public void onClick(View v)
    {
        // checking which button is clicked by user
        if (v.getId() == R.id.button_confirm_next && (option1.isChecked()
                || option2.isChecked() || option3.isChecked() || option4.isChecked())) {// go to next question

            if(answersList.size() + 1 == 108){
                if(option1.isChecked()){
                    answersList.add(0);
                }
                else if(option2.isChecked()){
                    answersList.add(1);
                }
                else if(option3.isChecked()){
                    answersList.add(2);
                }
                else if (option4.isChecked()){
                    answersList.add(3);
                }
                Log.i("Answers", String.valueOf(answersList.size()));
                ApiClient api = RetrofitClient.getRetrofitInstance().create(ApiClient.class);
                Call<Test> call= api.sendTestResponses(token, answersList, childId, childCode);
                myDialog.show();
                myDialog.setCancelable(false);
                myDialog.setCanceledOnTouchOutside(false);
                call.enqueue(new Callback<Test>() {
                    @Override
                    public void onResponse(Call<Test> call, Response<Test> response) {
                        Log.i("RASPUNS", response.toString());
                        myDialog.dismiss();
                        Intent intent = new Intent(QuizActivity.this, DiagnosticActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<Test> call, Throwable t) {
                        Log.i("FAILURE", t.toString());
                    }
                });
            }

            else{

                if(option1.isChecked()){
                    answersList.add(0);
                }
                else if(option2.isChecked()){
                    answersList.add(1);
                }
                else if(option3.isChecked()){
                    answersList.add(2);
                }
                else if (option4.isChecked()){
                    answersList.add(3);
                }
                Log.i("Answers", String.valueOf(answersList.size()));

                if (currentQuestionIndex < 10) {
                    currentQuestionIndex = currentQuestionIndex + 1;

                    // last question reached
                    if (currentQuestionIndex == 10) {
                        ((RadioGroup) findViewById(R.id.radio_group)).clearCheck();
                        showQuestions();
                        Log.i("Answers", answersList.toString());
                    }

                    else {
                        updateQuestion();
                        ((RadioGroup) findViewById(R.id.radio_group)).clearCheck();
                    }
                }

            }
        }
        else{
            Toast.makeText(QuizActivity.this, "Alegeți o variantă! ", Toast.LENGTH_LONG);
        }
    }

    @SuppressLint("SetTextI18n")
    private void updateQuestion()
    {
        Log.d("Current", "onClick: " + currentQuestionIndex);

        questionCount.setText("Întrebarea  " + questionsList.get(currentQuestionIndex).getNumber().toString() +"/108");
        questionText.setText(questionsList.get(currentQuestionIndex).getText());

    }

    public void showQuestions(){
        currentQuestionIndex = 0;
        questionsList = new ArrayList<>();
        Call<ArrayList<Question>> call = api.getQuestions(token, String.valueOf(pageNo/10), String.valueOf(10));
        myDialog.show();
        myDialog.setCancelable(false);
        myDialog.setCanceledOnTouchOutside(false);
        call.enqueue(new Callback<ArrayList<Question>>() {
            @Override
            public void onResponse(Call<ArrayList<Question>> call, Response<ArrayList<Question>> response) {
                myDialog.dismiss();
                assert response.body() != null;
                questionsList.addAll(response.body());
                pageNo += 10;
                Log.i("QUESTIONS ", questionsList.toString());
                Log.i("SIZE", String.valueOf(questionsList.size()));
                Log.i("Page", String.valueOf(pageNo));
                Log.i("Answers", String.valueOf(answersList.size()));
                updateQuestion();
            }
            @Override
            public void onFailure(Call<ArrayList<Question>> call, Throwable t) {
                Log.i("FAILURE ", t.toString());
            }
        });
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    public void controlPanel(View view) {
        MaterialButton controlPanelBtn = findViewById(R.id.controlpanel);
        controlPanelBtn.setOnClickListener(view1 -> {
            Intent intent = new Intent(QuizActivity.this, ControlPanelActivity.class);
            startActivity(intent);
        });
    }
}