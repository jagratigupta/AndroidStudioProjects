package com.example.dell.tictactoenew;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.dell.tictactoenew.R.id.gridLayout;

public class MainActivity extends AppCompatActivity {
    int activePlayer=0;
    boolean gameActive = true;
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int count=0;TextView winnerTextView;Button playAgainButton;
    int[][] winStates = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    public void  dropIn(View view) {
        // 0: redcircle 1: redcross
        count++;

        ImageView counter = (ImageView) view;
        int tag = Integer.parseInt(counter.getTag().toString());
        Log.i("info","Image View Tapped "+ tag);
        if (gameState[tag] == 2 && gameActive) {
            gameState[tag] = activePlayer;

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.redcircle);
                activePlayer = 1;
                counter.setTranslationY(-1500);
                counter.animate().translationYBy(1500).rotation(3600);
            } else {
                counter.setImageResource(R.drawable.redcross);
                activePlayer = 0;
                counter.setTranslationX(-1500);
                counter.animate().translationXBy(1500).rotation(3600);
            }

            for (int[] winState : winStates) {
                if (gameState[winState[0]] == gameState[winState[1]] && gameState[winState[1]] == gameState[winState[2]] && gameState[winState[0]] != 2) {
                    String winner = "";
                    if (activePlayer == 1) {
                        winner = "Player 1 has won!";
                    } else {
                        winner = "Player 2 has won!";
                    }
                    gameActive = false;
                    winnerTextView = findViewById(R.id.winnerTextView);
                    winnerTextView.setText(winner);
                    playAgainButton = findViewById(R.id.playAgainButton);
                    playAgainButton.setVisibility(View.VISIBLE);
                    winnerTextView.setVisibility(View.VISIBLE);
                }
            }
            if(count==9 && gameActive ){
                gameActive = false;

                winnerTextView =  findViewById(R.id.winnerTextView);
                winnerTextView.setText("Game Draw!");
                playAgainButton =findViewById(R.id.playAgainButton);
                playAgainButton.setVisibility(View.VISIBLE);
                winnerTextView.setVisibility(View.VISIBLE);
            }
        }

    }
    public void playAgain(View view)
    {
        winnerTextView = findViewById(R.id.winnerTextView);
        playAgainButton = findViewById(R.id.playAgainButton);
        playAgainButton.setVisibility(View.INVISIBLE);
        winnerTextView.setVisibility(View.INVISIBLE);
        Log.i("info","Play Again pressed");
        android.support.v7.widget.GridLayout gridLayout=(android.support.v7.widget.GridLayout) findViewById(R.id.gridLayout);
        Log.i("info",Integer.toString(gridLayout.getChildCount()));
        for(int i=0;i<gridLayout.getChildCount();i++)
        {
            Log.i("info","Image button pressed");
            ImageView counter = (ImageView)gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }
        activePlayer=0;
        gameActive = true;
        count=0;
        for(int i=0;i<gameState.length;i++)
        {
            gameState[i]=2;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}