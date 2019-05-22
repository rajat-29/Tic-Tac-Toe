package com.example.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Home extends AppCompatActivity {


    CardView cardView[] = new CardView[9];
    String player1, player2;
    char index[];
    int arr[][] = {{10, 2, 3}, {4, 5, 6}, {7, 8, 9}};
    int turn = 1, count = 0;
    Dialog dialog;
    Button playAgain, exit;
    String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Tic Tac Toe");
        dialog = new Dialog(Home.this,R.style.Dialog);
        dialog.setContentView(R.layout.popup);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        exit = dialog.findViewById(R.id.exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.finishAffinity(Home.this);
            }
        });
        player1 = getIntent().getStringExtra("Player1");
        player2 = getIntent().getStringExtra("Player2");
        cardView[0] = findViewById(R.id.column1);
        cardView[1] = findViewById(R.id.column2);
        cardView[2] = findViewById(R.id.column3);
        cardView[3] = findViewById(R.id.column4);
        cardView[4] = findViewById(R.id.column5);
        cardView[5] = findViewById(R.id.column6);
        cardView[6] = findViewById(R.id.column7);
        cardView[7] = findViewById(R.id.column8);
        cardView[8] = findViewById(R.id.column9);

        for (int i = 0; i < 9; i++) {
            cardView[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    index = getResources().getResourceEntryName(v.getId()).toCharArray();
                    int indexer = index[6];
                    indexer = (indexer % 49);
                    count++;
                    if (indexer < 3) {
                        arr[0][indexer] = turn;
                    } else if (indexer < 6) {
                        arr[1][indexer - 3] = turn;
                    } else {
                        arr[2][indexer - 6] = turn;
                    }
                    if (turn == 0) {
                        turn = 1;
                        v.setBackgroundColor(Color.RED);
                    } else {
                        turn = 0;
                        v.setBackgroundColor(Color.BLACK);
                    }
                    if (((arr[0][0] == arr[0][1]) && (arr[0][1] == arr[0][2])) ||
                            ((arr[1][0] == arr[1][1]) && (arr[1][1] == arr[1][2])) ||
                            ((arr[2][0] == arr[2][1]) && (arr[2][1] == arr[2][2])) ||
                            ((arr[0][0] == arr[1][0]) && (arr[1][0] == arr[2][0])) ||
                            ((arr[0][1] == arr[1][1]) && (arr[1][1] == arr[2][1])) ||
                            ((arr[0][2] == arr[1][2]) && (arr[1][2] == arr[2][2])) ||
                            ((arr[0][0] == arr[1][1]) && (arr[1][1] == arr[2][2])) ||
                            ((arr[0][2] == arr[1][1]) && (arr[1][1] == arr[2][0]))) {
                        if (turn == 0) {
                            text = player1 + " Won";
                            openDialog();
                        } else {
                            text = player2 + " Won";
                            openDialog();
                        }
                    } else if (count == 9) {
                        text = "Game Over";
                        openDialog();
                    }
                    if (dialog.isShowing() == false && count == 9) {
                        dialog.show();
                    }
                }
            });
        }

    }
    public void openDialog () {
        dialog.setTitle(text);
        playAgain = dialog.findViewById(R.id.play_again);
        playAgain.setEnabled(true);
        playAgain.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             dialog.cancel();
                                             recreate();
                                         }
                                     }
        );
        dialog.show();
    }
}
