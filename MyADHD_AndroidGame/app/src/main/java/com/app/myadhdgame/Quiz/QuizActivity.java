package com.app.myadhdgame.Quiz;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import com.app.myadhdgame.EndlessRunnerGame.GameEngine;
import com.app.myadhdgame.MainActivity;
import com.app.myadhdgame.R;


public class QuizActivity extends AppCompatActivity {

    TextView question;
    RadioButton option1, option2, option3, option4;
    ImageButton check;
    String correct;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        question = findViewById(R.id.intrebare);
        option1 = findViewById(R.id.radio_button1);
        option2 = findViewById(R.id.radio_button2);
        option3 = findViewById(R.id.radio_button3);
        option4 = findViewById(R.id.radio_button4);
        check = findViewById(R.id.continueGame);

        if(MainActivity.appState == 2){
            MainActivity.appState = 3;
            question.setText("Tulburarea de deficit de atenție și hiperactivitate poate fi " +
                    "prescurtată drept: ");
            option1.setText("ADHD");
            option2.setText("DDHA");
            option3.setText("HDDA");
            option4.setText("DHDH");
            option1.setOnClickListener(v -> check.setVisibility(View.VISIBLE));
            option2.setOnClickListener(v -> check.setVisibility(View.VISIBLE));
            option3.setOnClickListener(v -> check.setVisibility(View.VISIBLE));
            option4.setOnClickListener(v -> check.setVisibility(View.VISIBLE));

            check.setOnClickListener(v -> {
                if (option1.isChecked()|| option2.isChecked() || option3.isChecked() || option4.isChecked())
                {
                    if(option1.isChecked()){
                        correct = "Răspunsul este corect! Vei primit 5 puncte bonus.";
                        GameEngine.points += 5;
                    }
                    else{
                        correct = "Răspunsul nu este corect!";
                    }

                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(QuizActivity.this);
                    alertDialog.setMessage(correct).setCancelable(false)
                            .setPositiveButton("Continuă", (dialog, which) -> {
                                Intent intent = new Intent(getApplicationContext(), StoryActivity.class);
                                startActivity(intent);
                                finish();
                            });
                    AlertDialog alertDialog1 = alertDialog.create();
                    alertDialog1.show();
                }
            });

        }
        else if(MainActivity.appState == 3){
            MainActivity.appState = 4;
            question.setText("Ce culoare avea pasărea din pădurea prin care a trecut Miau? ");
            option1.setText("Maro");
            option2.setText("Roșie");
            option3.setText("Albastră");
            option4.setText("Galbenă");
            option1.setOnClickListener(v -> check.setVisibility(View.VISIBLE));
            option2.setOnClickListener(v -> check.setVisibility(View.VISIBLE));
            option3.setOnClickListener(v -> check.setVisibility(View.VISIBLE));
            option4.setOnClickListener(v -> check.setVisibility(View.VISIBLE));


            check.setOnClickListener(v -> {
                if (option1.isChecked()|| option2.isChecked() || option3.isChecked() || option4.isChecked())
                {
                    if(option3.isChecked()){
                        correct = "Răspunsul este corect! Vei primit 5 puncte bonus.";
                        GameEngine.points += 5;
                    }
                    else{
                        correct = "Răspunsul nu este corect!";
                    }

                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(QuizActivity.this);
                    alertDialog.setMessage(correct).setCancelable(false)
                            .setPositiveButton("Continuă", (dialog, which) -> {
                                Intent intent = new Intent(getApplicationContext(), StoryActivity.class);
                                startActivity(intent);
                                finish();
                            });
                    AlertDialog alertDialog1 = alertDialog.create();
                    alertDialog1.show();
                }
            });
        }
        else if(MainActivity.appState == 6){
            MainActivity.appState = 7;
            question.setText("Unele persoane cu ADHD pot avea probleme în a fi liniștiți sau atenți? ");
            option1.setText("Nu știu");
            option2.setText("Da");
            option3.setText("Poate");
            option4.setText("Nu");
            option1.setOnClickListener(v -> check.setVisibility(View.VISIBLE));
            option2.setOnClickListener(v -> check.setVisibility(View.VISIBLE));
            option3.setOnClickListener(v -> check.setVisibility(View.VISIBLE));
            option4.setOnClickListener(v -> check.setVisibility(View.VISIBLE));


            check.setOnClickListener(v -> {
                if (option1.isChecked()|| option2.isChecked() || option3.isChecked() || option4.isChecked())
                {
                    if(option2.isChecked()){
                        correct = "Răspunsul este corect! ";
                        GameEngine.points += 5;
                    }
                    else{
                        correct = "Răspunsul nu este corect! ";
                    }

                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(QuizActivity.this);
                    alertDialog.setMessage(correct).setCancelable(false)
                            .setPositiveButton("Continuă", (dialog, which) -> {
                                Intent intent = new Intent(getApplicationContext(), StoryActivity.class);
                                startActivity(intent);
                                finish();
                            });
                    AlertDialog alertDialog1 = alertDialog.create();
                    alertDialog1.show();
                }
            });
        }
        else if(MainActivity.appState == 8){
            MainActivity.appState = 9;
            question.setText("Unde trebuie să stai în clasă? ");
            option1.setText("Lângă geam.");
            option2.setText("Nu știu.");
            option3.setText("Aproape de profesor și departe de geam.");
            option4.setText("În spatele clasei.");
            option1.setOnClickListener(v -> check.setVisibility(View.VISIBLE));
            option2.setOnClickListener(v -> check.setVisibility(View.VISIBLE));
            option3.setOnClickListener(v -> check.setVisibility(View.VISIBLE));
            option4.setOnClickListener(v -> check.setVisibility(View.VISIBLE));


            check.setOnClickListener(v -> {
                if (option1.isChecked()|| option2.isChecked() || option3.isChecked() || option4.isChecked())
                {
                    if(option3.isChecked()){
                        correct = "Răspunsul este corect! Vei primit 5 puncte bonus.";
                        GameEngine.points += 5;
                    }
                    else{
                        correct = "Răspunsul nu este corect!";
                    }

                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(QuizActivity.this);
                    alertDialog.setMessage(correct).setCancelable(false)
                            .setPositiveButton("Continuă", (dialog, which) -> {
                                Intent intent = new Intent(getApplicationContext(), StoryActivity.class);
                                startActivity(intent);
                                finish();
                            });
                    AlertDialog alertDialog1 = alertDialog.create();
                    alertDialog1.show();
                }
            });
        }

    }
}