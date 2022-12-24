package com.example.legend;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class ModeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode);

        //declare and initialize each button used
        final Button standardButton = findViewById(R.id.standardButton);
        final Button bossRushButton = findViewById(R.id.bossRushButton);
        final Button reversalButton = findViewById(R.id.reversalButton);
        final ImageButton forwardButton = findViewById(R.id.forwardButton);

        //declare the TextViews to be modified with different actions
        TextView modeTitle = findViewById(R.id.selectedMode);
        TextView modeDesc = findViewById(R.id.modeDescription);

        //array for storing the user's mode selection
        //1 = standard, 2 = boss rush, 3 = reversal
        final int[] modeSelection = {1};

        //enable and disable the buttons based on the launch state
        standardButton.setEnabled(false);
        bossRushButton.setEnabled(true);
        reversalButton.setEnabled(true);

        //set the buttons to display the current mode at the top
        standardButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                modeTitle.setText(R.string.standardButtonText);
                modeDesc.setText(R.string.standardTextDesc);
                modeSelection[0] = 1;

                //disable the current button and enable the rest
                standardButton.setEnabled(false);
                bossRushButton.setEnabled(true);
                reversalButton.setEnabled(true);
            }
        });

        bossRushButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                modeTitle.setText(R.string.bossRushButtonText);
                modeDesc.setText(R.string.bossRushTextDesc);
                modeSelection[0] = 2;

                //disable the current button and enable the rest
                standardButton.setEnabled(true);
                bossRushButton.setEnabled(false);
                reversalButton.setEnabled(true);
            }
        });

        reversalButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                modeTitle.setText(R.string.reversalButtonText);
                modeDesc.setText(R.string.reversalTextDesc);
                modeSelection[0] = 3;

                //disable the current button and enable the rest
                standardButton.setEnabled(true);
                bossRushButton.setEnabled(true);
                reversalButton.setEnabled(false);
            }
        });

        forwardButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //determines the next screen based on the game mode selected
                if(modeSelection[0] == 1){
                    Intent modeStart = new Intent(ModeActivity.this, CharacterIActivity.class);
                    modeStart.putExtra("game mode", modeSelection[0]);
                    startActivity(modeStart);
                    finish();
                }
                else if(modeSelection[0] == 2){
                    Intent modeStart = new Intent(ModeActivity.this, CharacterIActivity.class);
                    modeStart.putExtra("game mode", modeSelection[0]);
                    startActivity(modeStart);
                    finish();
                }
                else{
                    //Intent modeStart = new Intent(ModeActivity.this, BossIIActivity.class);
                    //startActivity(modeStart);
                    finish();
                }

            }
        });
    }
}