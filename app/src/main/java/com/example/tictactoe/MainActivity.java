package com.example.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public  void startPlay(View view) {
        EditText play1 = findViewById(R.id.firstPlayer);
        EditText play2 = findViewById(R.id.secondPlayer);
        if(TextUtils.isEmpty(play1.getText())) {
            play1.setError("Name is empty");
        }
        else if(TextUtils.isEmpty(play2.getText())) {
            play2.setError("Name is empty");
        }
        else {
            Intent n = new Intent(this,Home.class);
            startActivity(n);
        }


    }
}
