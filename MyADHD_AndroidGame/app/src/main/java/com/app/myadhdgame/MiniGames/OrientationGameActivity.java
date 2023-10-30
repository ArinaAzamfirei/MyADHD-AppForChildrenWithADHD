package com.app.myadhdgame.MiniGames;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.app.myadhdgame.EndlessRunnerGame.GameEngine;
import com.app.myadhdgame.MainActivity;
import com.app.myadhdgame.R;
import com.app.myadhdgame.Quiz.StoryActivity;

public class OrientationGameActivity extends AppCompatActivity {

    EditText ans1, ans2;
    ImageButton imageButton;
    String answer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orientation_game);

        ans1 = findViewById(R.id.ans1);
        ans2 = findViewById(R.id.ans2);
        imageButton = findViewById(R.id.continueGame);

        imageButton.setOnClickListener(v -> {


            if(!validateAns1() || !validateAns2()){
                Toast.makeText(OrientationGameActivity.this,"Introduceți răspunsurile!",Toast.LENGTH_SHORT).show();
            }
            else{
                if (ans1.getText().toString().equals("8") && !ans2.getText().toString().equals("6")  ){
                    answer = "Ai răspuns corect pentru Dreapta. Vei primi 5 puncte.";
                    GameEngine.points += 5;
                }
                else if(!ans1.getText().toString().equals("8") && ans2.getText().toString().equals("6")){
                    answer = "Ai răspuns corect pentru Stânga. Vei primi 5 puncte.";
                    GameEngine.points += 5;
                }
                else if(!ans1.getText().toString().equals("8") && !ans2.getText().toString().equals("6")){
                    answer = "Din păcate nu ai dat niciun răspuns corect.";
                }
                else{
                    answer = "Felicitări ambele răspunsuri sunt corecte. Vei primi 10 puncte.";
                    GameEngine.points += 5;
                }

                MainActivity.appState = 8;
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(OrientationGameActivity.this);
                alertDialog.setMessage(answer).setCancelable(false)
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

    private boolean validateAns1() {
        String val = ans1.getText().toString();
        if (val.isEmpty()) {
            ans1.setError("Câmpul nu poate fi gol");
            return false;
        } else {
            ans1.setError(null);
            return true;
        }
    }

    private boolean validateAns2() {
        String val = ans2.getText().toString();
        if (val.isEmpty()) {
            ans2.setError("Câmpul nu poate fi gol");
            return false;
        } else {
            ans2.setError(null);
            return true;
        }
    }
}