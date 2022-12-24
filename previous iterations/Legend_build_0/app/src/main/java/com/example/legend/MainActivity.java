package com.example.legend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //declare the only button used
        final Button gameStartButton = findViewById(R.id.gameStartButton);

        //set the button to move to the next screen [Mode Select Screen]
        gameStartButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent startGame = new Intent(MainActivity.this, ModeActivity.class);
                startActivity(startGame);
                finish();
            }
        });
    }
}