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

public class MatchActivity extends AppCompatActivity {

    EditText ans1, ans2;
    ImageButton imageButton;
    String answer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);
        ans1 = findViewById(R.id.ans1);
        ans2 = findViewById(R.id.ans2);
        imageButton = findViewById(R.id.continueGame);

        imageButton.setOnClickListener(v -> {


            if(!validateAns1() || !validateAns2()){
                Toast.makeText(MatchActivity.this,"Introduceți răspunsurile!",Toast.LENGTH_SHORT).show();
            }
            else{
                if ((ans1.getText().toString().equals("B") || ans1.getText().toString().equals("b"))
                        && (!ans2.getText().toString().equals("D") || !ans2.getText().toString().equals("d")) ){
                    answer = "Ai răspuns corect la primul exercițiu. Vei primi 5 puncte.";
                    GameEngine.points += 5;
                }
                else if((!ans1.getText().toString().equals("B") || !ans1.getText().toString().equals("b"))
                        && (ans2.getText().toString().equals("D") || ans2.getText().toString().equals("d")) ){
                    answer = "Ai răspuns corect la al doilea exercițiu.";
                    GameEngine.points += 5;
                }
                else if((!ans1.getText().toString().equals("B") || !ans1.getText().toString().equals("b"))
                        && (!ans2.getText().toString().equals("D") || !ans2.getText().toString().equals("d")) ){
                    answer = "Din păcate nu ai dat niciun răspuns corect.";
                }
                else if ((ans1.getText().toString().equals("B") || ans1.getText().toString().equals("b"))
                        && (ans2.getText().toString().equals("D") || ans2.getText().toString().equals("d")) )
                {
                    answer = "Felicitări ambele răspunsuri sunt corecte! Vei primi 10 puncte.";
                    GameEngine.points += 10;
                }

                MainActivity.appState = 6;
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MatchActivity.this);
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