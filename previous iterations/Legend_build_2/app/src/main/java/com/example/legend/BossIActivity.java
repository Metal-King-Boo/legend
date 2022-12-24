package com.example.legend;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class BossIActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boss_i);

        //collect the party information and game mode from the previous screen
        Bundle extras = getIntent().getExtras();
        int member1 = extras.getInt("member 1");
        int member2 = extras.getInt("member 2");
        int member3 = extras.getInt("member 3");
        int member4 = extras.getInt("member 4");
        int modeSelection = extras.getInt("game mode");


        //declare the buttons for selecting the boss
        final Button boss1Button = findViewById(R.id.boss1Button);
        final Button boss2Button = findViewById(R.id.boss2Button);
        final Button boss3Button = findViewById(R.id.boss3Button);
        final Button boss4Button = findViewById(R.id.boss4Button);

        //declare the buttons for moving screens
        final ImageButton backButton2 = findViewById(R.id.backButton2);
        final ImageButton homeButton2 = findViewById(R.id.homeButton2);
        final ImageButton forwardButton3 = findViewById(R.id.forwardButton3);

        //declare the text fields that change with the selected buttons
        final TextView selectedBoss = findViewById(R.id.bossTitle);
        final TextView bossText = findViewById(R.id.bossDesc);

        //setup the screen for how it looks on launch
        boss1Button.setEnabled(false);
        //0 = boss 1, 1 = boss 2, 2 = boss 3, 3 = boss 4
        final int[] bossSelection = {0};

        boss1Button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //change the text to match the selected boss
                selectedBoss.setText(R.string.boss1Button);
                bossText.setText(R.string.boss1Text);

                //disable the selected button and confirm the user's selection
                bossSelection[0] = 0;
                boss1Button.setEnabled(false);
                boss2Button.setEnabled(true);
                boss3Button.setEnabled(true);
                boss4Button.setEnabled(true);
            }
        });

        boss2Button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //change the text to match the selected boss
                selectedBoss.setText(R.string.boss2Button);
                bossText.setText(R.string.boss2Text);

                //disable the selected button and confirm the user's selection
                bossSelection[0] = 1;
                boss1Button.setEnabled(true);
                boss2Button.setEnabled(false);
                boss3Button.setEnabled(true);
                boss4Button.setEnabled(true);
            }
        });

        boss3Button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //change the text to match the selected boss
                selectedBoss.setText(R.string.boss3Button);
                bossText.setText(R.string.boss3Text);

                //disable the selected button and confirm the user's selection
                bossSelection[0] = 2;
                boss1Button.setEnabled(true);
                boss2Button.setEnabled(true);
                boss3Button.setEnabled(false);
                boss4Button.setEnabled(true);
            }
        });

        boss4Button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //change the text to match the selected boss
                selectedBoss.setText(R.string.boss4Button);
                bossText.setText(R.string.boss4Text);

                //disable the selected button and confirm the user's selection
                bossSelection[0] = 3;
                boss1Button.setEnabled(true);
                boss2Button.setEnabled(true);
                boss3Button.setEnabled(true);
                boss4Button.setEnabled(false);
            }
        });

        backButton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //go back to character select screen
                Intent charStart = new Intent(BossIActivity.this, CharacterIActivity.class);
                charStart.putExtra("game mode", modeSelection);
                startActivity(charStart);
                finish();
            }
        });

        homeButton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //returns the user back to the mode select screen
                Intent modeStart = new Intent(BossIActivity.this, ModeActivity.class);
                startActivity(modeStart);
                finish();
            }
        });

        forwardButton3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //goes to the battle screen with the team and boss selected
                Intent bossStart = new Intent(BossIActivity.this, BattleIActivity.class);
                bossStart.putExtra("mem1", member1);
                bossStart.putExtra("mem2", member2);
                bossStart.putExtra("mem3", member3);
                bossStart.putExtra("mem4", member4);
                bossStart.putExtra("boss", bossSelection[0]);
                bossStart.putExtra("game mode", modeSelection);
                startActivity(bossStart);
                finish();
            }
        });
    }
}
