package com.app.myadhdgame.MiniGames;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.myadhdgame.R;
import com.app.myadhdgame.Quiz.StoryActivity;

import java.util.Arrays;
import java.util.Collections;

public class MemGame1 extends AppCompatActivity {

    TextView p1;
    ImageView guess11, guess12, guess13,guess14, guess21, guess22, guess23, guess24;

    //Array for the images you have to guess
    Integer[] cardsArray = {101,102,103,104,201,202,203,204};

    //The images
    int img11, img12, img13, img14, img21, img22, img23, img24;

    int fCard, sCard;
    int firstClicked, secondClicked;
    int cardNr = 1;

    int turn = 1;
    int childPts = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_game1);

        p1 = findViewById(R.id.memoryGamePoints);

        guess11 = findViewById(R.id.guess11);
        guess12 = findViewById(R.id.guess12);
        guess13 = findViewById(R.id.guess13);
        guess14 = findViewById(R.id.guess14);

        guess21 = findViewById(R.id.guess21);
        guess22 = findViewById(R.id.guess22);
        guess23 = findViewById(R.id.guess23);
        guess24 = findViewById(R.id.guess24);


        guess11.setTag("0");
        guess12.setTag("1");
        guess13.setTag("2");
        guess14.setTag("3");

        guess21.setTag("4");
        guess22.setTag("5");
        guess23.setTag("6");
        guess24.setTag("7");

        //Loading the images

        loadCards();

        //shuffling the images

        Collections.shuffle(Arrays.asList(cardsArray));

        guess11.setOnClickListener(v -> {
            int theCard = Integer.parseInt((String) v.getTag());
            doStuff(guess11, theCard);
        });


        guess12.setOnClickListener(v -> {
            int theCard = Integer.parseInt((String) v.getTag());
            doStuff(guess12, theCard);
        });


        guess13.setOnClickListener(v -> {
            int theCard = Integer.parseInt((String) v.getTag());
            doStuff(guess13, theCard);
        });


        guess14.setOnClickListener(v -> {
            int theCard = Integer.parseInt((String) v.getTag());
            doStuff(guess14, theCard);
        });

        guess21.setOnClickListener(v -> {
            int theCard = Integer.parseInt((String) v.getTag());
            doStuff(guess21, theCard);
        });

        guess22.setOnClickListener(v -> {
            int theCard = Integer.parseInt((String) v.getTag());
            doStuff(guess22, theCard);
        });

        guess23.setOnClickListener(v -> {
            int theCard = Integer.parseInt((String) v.getTag());
            doStuff(guess23, theCard);
        });

        guess24.setOnClickListener(v -> {
            int theCard = Integer.parseInt((String) v.getTag());
            doStuff(guess24, theCard);
        });
    }

    private void doStuff(ImageView guess, int card){
        if(cardsArray[card] == 101){
            guess.setImageResource(img11);
        }
        else if(cardsArray[card] == 102){
            guess.setImageResource(img12);
        }
        else if(cardsArray[card] == 103){
            guess.setImageResource(img13);
        }
        else if(cardsArray[card] == 104){
            guess.setImageResource(img14);
        }
        else if(cardsArray[card] == 201){
            guess.setImageResource(img21);
        }
        else if(cardsArray[card] == 202){
            guess.setImageResource(img22);
        }
        else if(cardsArray[card] == 203){
            guess.setImageResource(img23);
        }
        else if(cardsArray[card] == 204){
            guess.setImageResource(img24);
        }

        // Checking which image was selected + saving to temporary value
        if(cardNr == 1){
            fCard = cardsArray[card];
            if(fCard > 200){
                fCard = fCard - 100;
            }
            cardNr = 2;
            firstClicked = card;

            guess.setEnabled(false);
        }else if(cardNr == 2){
            sCard = cardsArray[card];
            if(sCard > 200){
                sCard = sCard - 100;
            }
            cardNr = 1;
            secondClicked = card;

            guess11.setEnabled(false);
            guess12.setEnabled(false);
            guess13.setEnabled(false);
            guess14.setEnabled(false);
            guess21.setEnabled(false);
            guess22.setEnabled(false);
            guess23.setEnabled(false);
            guess24.setEnabled(false);

            Handler handler = new Handler();
            handler.postDelayed(this::checkEqual, 1000);

        }

    }

    @SuppressLint("SetTextI18n")
    private void checkEqual(){
        if(fCard == sCard){
            if(firstClicked == 0){
                guess11.setVisibility(View.INVISIBLE);
            }
            else if(firstClicked == 1){
                guess12.setVisibility(View.INVISIBLE);
            }
            else if(firstClicked == 2){
                guess13.setVisibility(View.INVISIBLE);
            }
            else if(firstClicked == 3){
                guess14.setVisibility(View.INVISIBLE);
            }
            else if(firstClicked == 4){
                guess21.setVisibility(View.INVISIBLE);
            }
            else if(firstClicked == 5){
                guess22.setVisibility(View.INVISIBLE);
            }
            else if(firstClicked == 6){
                guess23.setVisibility(View.INVISIBLE);
            }
            else if(firstClicked == 7){
                guess24.setVisibility(View.INVISIBLE);
            }


            if(secondClicked == 0){
                guess11.setVisibility(View.INVISIBLE);
            }
            else if(secondClicked  == 1){
                guess12.setVisibility(View.INVISIBLE);
            }
            else if(secondClicked  == 2){
                guess13.setVisibility(View.INVISIBLE);
            }
            else if(secondClicked  == 3){
                guess14.setVisibility(View.INVISIBLE);
            }
            else if(secondClicked  == 4){
                guess21.setVisibility(View.INVISIBLE);
            }
            else if(secondClicked  == 5){
                guess22.setVisibility(View.INVISIBLE);
            }
            else if(secondClicked  == 6){
                guess23.setVisibility(View.INVISIBLE);
            }
            else if(secondClicked  == 7){
                guess24.setVisibility(View.INVISIBLE);
            }

            if(turn == 1){
                childPts ++;
                p1.setText("Scor:" + childPts);
            }
        }
        else{
            childPts --;
            p1.setText("Scor:" + childPts);
            guess11.setImageResource(R.drawable.bg);
            guess12.setImageResource(R.drawable.bg);
            guess13.setImageResource(R.drawable.bg);
            guess14.setImageResource(R.drawable.bg);

            guess21.setImageResource(R.drawable.bg);
            guess22.setImageResource(R.drawable.bg);
            guess23.setImageResource(R.drawable.bg);
            guess24.setImageResource(R.drawable.bg);


        }
        guess11.setEnabled(true);
        guess12.setEnabled(true);
        guess13.setEnabled(true);
        guess14.setEnabled(true);
        guess21.setEnabled(true);
        guess22.setEnabled(true);
        guess23.setEnabled(true);
        guess24.setEnabled(true);

        checkEndGame();
    }
    private void checkEndGame(){
        if(guess11.getVisibility() == View.INVISIBLE && guess12.getVisibility() == View.INVISIBLE&&
                guess13.getVisibility() == View.INVISIBLE && guess14.getVisibility() == View.INVISIBLE
                && guess21.getVisibility() == View.INVISIBLE && guess22.getVisibility() == View.INVISIBLE &&
                guess23.getVisibility() == View.INVISIBLE && guess23.getVisibility() == View.INVISIBLE){

            AlertDialog.Builder alertDialog = new AlertDialog.Builder(MemGame1.this);
            alertDialog.setMessage("JOCUL S-A TERMINAT!\n Punctele tale: " + childPts ).setCancelable(false)
                    .setPositiveButton("Continuă", (dialog, which) -> {
                        Intent intent = new Intent(getApplicationContext(), StoryActivity.class);
                        startActivity(intent);
                        finish();
                    });
            AlertDialog alertDialog1 = alertDialog.create();
            alertDialog1.show();
        }
    }

    private void loadCards(){
        img11 = R.drawable.frog1;
        img12 = R.drawable.cat1;
        img13 = R.drawable.hippo1;
        img14 = R.drawable.sheep1;

        img21 = R.drawable.frog2;
        img22 = R.drawable.cat2;
        img23 = R.drawable.hippo2;
        img24 = R.drawable.sheep2;

    }
}