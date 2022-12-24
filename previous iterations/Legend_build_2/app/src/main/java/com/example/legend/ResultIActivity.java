package com.example.legend;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultIActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_i);

        //receive the collected data from the previous screen
        Bundle extras = getIntent().getExtras();
        //0 = boss 1, 1 = boss 2, 2 = boss 3, 3 = boss 4
        int bossSelection = extras.getInt("boss");
        int modeSelection = extras.getInt("game mode");
        boolean victory = extras.getBoolean("victory");
        int turns = extras.getInt("timer");
        //1 = EMP, 2 = SNT, 3 = ENC, 4 = SAG, 5 = PIR
        int member1 = extras.getInt("mem1");
        int member2 = extras.getInt("mem2");
        int member3 = extras.getInt("mem3");
        int member4 = extras.getInt("mem4");

        //declare all text fields where the results are displayed
        final TextView results = findViewById(R.id.resultText);
        final TextView bossNameF = findViewById(R.id.bossNameF);
        final TextView party1Name = findViewById(R.id.party1Name);
        final TextView party2Name = findViewById(R.id.party2Name);
        final TextView party3Name = findViewById(R.id.party3Name);
        final TextView party4Name = findViewById(R.id.party4Name);
        final TextView turnsTakenField = findViewById(R.id.takenTurnsF);

        //declare all the image buttons
        final ImageButton retryButton = findViewById(R.id.retryButton);
        final ImageButton bossSelectButton = findViewById(R.id.bossButton);
        final ImageButton teamButton = findViewById(R.id.teamButton);
        final ImageButton homeButton = findViewById(R.id.homeButton3);

        //set the results message at the top of the screen
        if(victory == true){
            results.setText(R.string.victory);
        }
        else{
            results.setText(R.string.defeated);
        }

        //set the boss
        switch(bossSelection){
            case 0:
                bossNameF.setText(R.string.boss1Button);
                break;
            case 1:
                bossNameF.setText(R.string.boss2Button);
                break;
            case 2:
                bossNameF.setText(R.string.boss3Button);
                break;
            case 3:
                bossNameF.setText(R.string.boss4Button);
                break;
        }

        //arrays made for simplicity in setting the party members
        int teamSet[] = {member1, member2, member3, member4};
        TextView players[] = {party1Name, party2Name, party3Name, party4Name};
        //cycles through 4 times for 4 members/textDisplays
        for(int i = 0; i <= 3; i++){
            //if statements telling what class would be put in there based on the data from character select
            if(teamSet[i] == 1){
                players[i].setText(R.string.nameTesting5);
            }
            else if(teamSet[i] == 2){
                players[i].setText(R.string.nameTesting3);
            }
            else if(teamSet[i] == 3){
                players[i].setText(R.string.nameTesting4);
            }
            else if(teamSet[i] == 4){
                players[i].setText(R.string.nameTesting2);
            }
            else{
                players[i].setText(R.string.nameTesting1);
            }
        }

        //set the time result
        String time = String.valueOf(turns);
        turnsTakenField.setText(time);

        retryButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //send the player back to the battle with the same settings as before
                Intent battleStart = new Intent(ResultIActivity.this, BattleIActivity.class);
                battleStart.putExtra("mem1", member1);
                battleStart.putExtra("mem2", member2);
                battleStart.putExtra("mem3", member3);
                battleStart.putExtra("mem4", member4);
                battleStart.putExtra("boss", bossSelection);
                battleStart.putExtra("game mode", modeSelection);
                startActivity(battleStart);
                finish();
            }
        });

        bossSelectButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //send the player back to the boss select screen with their same team
                Intent bossStart = new Intent(ResultIActivity.this, BossIActivity.class);
                bossStart.putExtra("member 1", member1);
                bossStart.putExtra("member 2", member2);
                bossStart.putExtra("member 3", member3);
                bossStart.putExtra("member 4", member4);
                bossStart.putExtra("game mode", modeSelection);
                startActivity(bossStart);
                finish();
            }
        });

        teamButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //send the player back to the team select screen
                Intent teamStart = new Intent(ResultIActivity.this, CharacterIActivity.class);
                teamStart.putExtra("game mode", modeSelection);
                startActivity(teamStart);
                finish();
            }
        });

        homeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //send the player back to the mode select screen
                Intent modeStart = new Intent(ResultIActivity.this, ModeActivity.class);
                startActivity(modeStart);
                finish();
            }
        });

    }
}
