package com.example.legend;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Layout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

public class BattleIActivity extends AppCompatActivity {
    public Hero[] team = new Hero[4];
    public Boss[] enemy = {new ShaeD(), new MissLead(), new Ruhtra(), new DenMo()};
    public int mpPoolTotal = 0;
    public boolean gameOver = false;
    public boolean victory = true;
    public int battleLogTimer = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle_i);

        //receive all the collected data from the previous screens
        Bundle extras = getIntent().getExtras();
        //0 = boss 1, 1 = boss 2, 2 = boss 3, 3 = boss 4
        final int[] bossSelection = {extras.getInt("boss")};
        int modeSelection = extras.getInt("game mode");
        //1 = EMP, 2 = SNT, 3 = ENC, 4 = SAG, 5 = PIR
        int member1 = extras.getInt("mem1");
        int member2 = extras.getInt("mem2");
        int member3 = extras.getInt("mem3");
        int member4 = extras.getInt("mem4");

        createHero(member1, 0);
        createHero(member2, 1);
        createHero(member3, 2);
        createHero(member4, 3);
        mpPoolTotal = createMP(team);

        ManaPool sharedPool = new ManaPool(mpPoolTotal, mpPoolTotal);

        //counter for turns taken
        int timerCounter[] = {1};
        //setup for getting the moves to activate
        final int turnSlot[] = {0};
        final int actionSelect[] = {0};
        //for storing all the moves
        final int actionQueue[] = {0, 0, 0, 0};

        //declare the player buttons on the battle screen
        final Button player1Button = findViewById(R.id.player1Display);
        final Button player2Button = findViewById(R.id.player2Display);
        final Button player3Button = findViewById(R.id.player3Display);
        final Button player4Button = findViewById(R.id.player4Display);

        //declare the action buttons on the battle screen
        final Button attackButton = findViewById(R.id.attackButton);
        final Button skill1Button = findViewById(R.id.skill1Button);
        final Button skill2Button = findViewById(R.id.skill2Button);
        final Button skill3Button = findViewById(R.id.skill3Button);
        final Button skill4Button = findViewById(R.id.skill4Button);
        final Button skill5Button = findViewById(R.id.skill5Button);
        final Button skill6Button = findViewById(R.id.skill6Button);
        final Button skill7Button = findViewById(R.id.skill7Button);
        final Button overSoulButton = findViewById(R.id.overSoulButton);
        final Button defendButton = findViewById(R.id.defendButton);

        //declare the non buttons seen on the battle screen
        final TextView descBox = findViewById(R.id.skillBox);
        final TextView bossName = findViewById(R.id.bossName);
        final TextView mpBarText = findViewById(R.id.mpBarText);
        final ImageView bossImage = findViewById(R.id.bossImage);
        final ImageView charIcon = findViewById(R.id.textDisplay);

        final ImageButton quitButton = findViewById(R.id.quitButton);
        final Button timerButton = findViewById(R.id.timerButton);

        final Button confirmButton = findViewById(R.id.confirmButton2);
        final Button cancelButton = findViewById(R.id.cancelButton2);
        final Button bossImageButton = findViewById(R.id.bossImageButton);

        final View buttonList = findViewById(R.id.combatList);

        //set the boss
        switch(bossSelection[0]){
            case 0:
                bossName.setText(R.string.boss1Name);
                //bossImage.setImageDrawable(R.drawable.####);
                break;
            case 1:
                bossName.setText(R.string.boss2Name);
                //bossImage.setImageDrawable(R.drawable.####);
                break;
            case 2:
                bossName.setText(R.string.boss3Name);
                //bossImage.setImageDrawable(R.drawable.####);
                break;
            case 3:
                bossName.setText(R.string.boss4Name);
                //bossImage.setImageDrawable(R.drawable.####);
                break;
        }

        //sets the status buttons to match the names of the team members and their hp
        Button players[] = {player1Button, player2Button, player3Button, player4Button};
        for(int i = 0; i <= 3; i++){
            players[i].setText(team[i].getStatus());
        }

        //set the buttons up for launch
        attackButton.setEnabled(false);
        cancelButton.setEnabled(false);
        String time = String.valueOf(timerCounter[0]);
        timerButton.setText(time);
        mpBarText.setText(sharedPool.currentMana + "/" + sharedPool.totalMana);
        //this uses a method to set the buttons to match the current hero's turn
        int[] skillsStrings = buttonSet(team[0]);
        Button skills[] = {skill1Button, skill2Button, skill3Button, skill4Button, skill5Button, skill6Button, skill7Button, overSoulButton};
        for(int j = 0; j <= 7; j++){
            String setup = "skill" + j;
            skills[j].setText(skillsStrings[j]);
        }

// ----------------------- buttons ----------------------------------

        player1Button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //disable this button and enable all other player buttons
                player1Button.setEnabled(false);
                player2Button.setEnabled(true);
                player3Button.setEnabled(true);
                player4Button.setEnabled(true);
                bossImageButton.setEnabled(true);

                //enable all action button
                attackButton.setEnabled(true);
                skill1Button.setEnabled(true);
                skill2Button.setEnabled(true);
                skill3Button.setEnabled(true);
                skill4Button.setEnabled(true);
                skill5Button.setEnabled(true);
                skill6Button.setEnabled(true);
                skill7Button.setEnabled(true);
                overSoulButton.setEnabled(true);
                defendButton.setEnabled(true);

                descBox.setText(team[0].getDisplay());
            }
        });

        player2Button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //disable this button and enable all other player buttons
                player1Button.setEnabled(true);
                player2Button.setEnabled(false);
                player3Button.setEnabled(true);
                player4Button.setEnabled(true);
                bossImageButton.setEnabled(true);

                //enable all action button
                attackButton.setEnabled(true);
                skill1Button.setEnabled(true);
                skill2Button.setEnabled(true);
                skill3Button.setEnabled(true);
                skill4Button.setEnabled(true);
                skill5Button.setEnabled(true);
                skill6Button.setEnabled(true);
                skill7Button.setEnabled(true);
                overSoulButton.setEnabled(true);
                defendButton.setEnabled(true);

                descBox.setText(team[1].getDisplay());
            }
        });

        player3Button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //disable this button and enable all other player buttons
                player1Button.setEnabled(true);
                player2Button.setEnabled(true);
                player3Button.setEnabled(false);
                player4Button.setEnabled(true);
                bossImageButton.setEnabled(true);

                //enable all action button
                attackButton.setEnabled(true);
                skill1Button.setEnabled(true);
                skill2Button.setEnabled(true);
                skill3Button.setEnabled(true);
                skill4Button.setEnabled(true);
                skill5Button.setEnabled(true);
                skill6Button.setEnabled(true);
                skill7Button.setEnabled(true);
                overSoulButton.setEnabled(true);
                defendButton.setEnabled(true);

                descBox.setText(team[2].getDisplay());
            }
        });

        player4Button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //disable this button and enable all other player buttons
                player1Button.setEnabled(true);
                player2Button.setEnabled(true);
                player3Button.setEnabled(true);
                player4Button.setEnabled(false);
                bossImageButton.setEnabled(true);

                //enable all action button
                attackButton.setEnabled(true);
                skill1Button.setEnabled(true);
                skill2Button.setEnabled(true);
                skill3Button.setEnabled(true);
                skill4Button.setEnabled(true);
                skill5Button.setEnabled(true);
                skill6Button.setEnabled(true);
                skill7Button.setEnabled(true);
                overSoulButton.setEnabled(true);
                defendButton.setEnabled(true);

                descBox.setText(team[3].getDisplay());
            }
        });

        attackButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //disable this button and enable all other action buttons
                attackButton.setEnabled(false);
                skill1Button.setEnabled(true);
                skill2Button.setEnabled(true);
                skill3Button.setEnabled(true);
                skill4Button.setEnabled(true);
                skill5Button.setEnabled(true);
                skill6Button.setEnabled(true);
                skill7Button.setEnabled(true);
                overSoulButton.setEnabled(true);
                defendButton.setEnabled(true);

                //enable all the player buttons
                player1Button.setEnabled(true);
                player2Button.setEnabled(true);
                player3Button.setEnabled(true);
                player4Button.setEnabled(true);
                bossImageButton.setEnabled(true);

                descBox.setText(R.string.AttackDesc);
                actionSelect[0] = 0;

                //change all the button colors
                attackButton.setBackgroundColor(getResources().getColor(R.color.leg_red));
                attackButton.setTextColor(getResources().getColor(R.color.leg_sand));
                skill1Button.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                skill1Button.setTextColor(getResources().getColor(R.color.leg_gold));
                skill2Button.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                skill2Button.setTextColor(getResources().getColor(R.color.leg_gold));
                skill3Button.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                skill3Button.setTextColor(getResources().getColor(R.color.leg_gold));
                skill4Button.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                skill4Button.setTextColor(getResources().getColor(R.color.leg_gold));
                skill5Button.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                skill5Button.setTextColor(getResources().getColor(R.color.leg_gold));
                skill6Button.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                skill6Button.setTextColor(getResources().getColor(R.color.leg_gold));
                skill7Button.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                skill7Button.setTextColor(getResources().getColor(R.color.leg_gold));
                overSoulButton.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                overSoulButton.setTextColor(getResources().getColor(R.color.leg_gold));
                defendButton.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                defendButton.setTextColor(getResources().getColor(R.color.leg_gold));
            }
        });

        skill1Button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //disable this button and enable all other action buttons
                attackButton.setEnabled(true);
                skill1Button.setEnabled(false);
                skill2Button.setEnabled(true);
                skill3Button.setEnabled(true);
                skill4Button.setEnabled(true);
                skill5Button.setEnabled(true);
                skill6Button.setEnabled(true);
                skill7Button.setEnabled(true);
                overSoulButton.setEnabled(true);
                defendButton.setEnabled(true);

                //enable all the player buttons
                player1Button.setEnabled(true);
                player2Button.setEnabled(true);
                player3Button.setEnabled(true);
                player4Button.setEnabled(true);
                bossImageButton.setEnabled(true);

                descBox.setText(team[turnSlot[0]].skill1desc);
                actionSelect[0] = 1;

                //change all the button colors
                attackButton.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                attackButton.setTextColor(getResources().getColor(R.color.leg_gold));
                skill1Button.setBackgroundColor(getResources().getColor(R.color.leg_red));
                skill1Button.setTextColor(getResources().getColor(R.color.leg_sand));
                skill2Button.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                skill2Button.setTextColor(getResources().getColor(R.color.leg_gold));
                skill3Button.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                skill3Button.setTextColor(getResources().getColor(R.color.leg_gold));
                skill4Button.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                skill4Button.setTextColor(getResources().getColor(R.color.leg_gold));
                skill5Button.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                skill5Button.setTextColor(getResources().getColor(R.color.leg_gold));
                skill6Button.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                skill6Button.setTextColor(getResources().getColor(R.color.leg_gold));
                skill7Button.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                skill7Button.setTextColor(getResources().getColor(R.color.leg_gold));
                overSoulButton.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                overSoulButton.setTextColor(getResources().getColor(R.color.leg_gold));
                defendButton.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                defendButton.setTextColor(getResources().getColor(R.color.leg_gold));
            }
        });

        skill2Button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //disable this button and enable all other action buttons
                attackButton.setEnabled(true);
                skill1Button.setEnabled(true);
                skill2Button.setEnabled(false);
                skill3Button.setEnabled(true);
                skill4Button.setEnabled(true);
                skill5Button.setEnabled(true);
                skill6Button.setEnabled(true);
                skill7Button.setEnabled(true);
                overSoulButton.setEnabled(true);
                defendButton.setEnabled(true);

                //enable all the player buttons
                player1Button.setEnabled(true);
                player2Button.setEnabled(true);
                player3Button.setEnabled(true);
                player4Button.setEnabled(true);
                bossImageButton.setEnabled(true);

                descBox.setText(team[turnSlot[0]].skill2desc);
                actionSelect[0] = 2;

                //change all the button colors
                attackButton.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                attackButton.setTextColor(getResources().getColor(R.color.leg_gold));
                skill1Button.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                skill1Button.setTextColor(getResources().getColor(R.color.leg_gold));
                skill2Button.setBackgroundColor(getResources().getColor(R.color.leg_red));
                skill2Button.setTextColor(getResources().getColor(R.color.leg_sand));
                skill3Button.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                skill3Button.setTextColor(getResources().getColor(R.color.leg_gold));
                skill4Button.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                skill4Button.setTextColor(getResources().getColor(R.color.leg_gold));
                skill5Button.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                skill5Button.setTextColor(getResources().getColor(R.color.leg_gold));
                skill6Button.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                skill6Button.setTextColor(getResources().getColor(R.color.leg_gold));
                skill7Button.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                skill7Button.setTextColor(getResources().getColor(R.color.leg_gold));
                overSoulButton.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                overSoulButton.setTextColor(getResources().getColor(R.color.leg_gold));
                defendButton.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                defendButton.setTextColor(getResources().getColor(R.color.leg_gold));
            }
        });

        skill3Button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //disable this button and enable all other action buttons
                attackButton.setEnabled(true);
                skill1Button.setEnabled(true);
                skill2Button.setEnabled(true);
                skill3Button.setEnabled(false);
                skill4Button.setEnabled(true);
                skill5Button.setEnabled(true);
                skill6Button.setEnabled(true);
                skill7Button.setEnabled(true);
                overSoulButton.setEnabled(true);
                defendButton.setEnabled(true);

                //enable all the player buttons
                player1Button.setEnabled(true);
                player2Button.setEnabled(true);
                player3Button.setEnabled(true);
                player4Button.setEnabled(true);
                bossImageButton.setEnabled(true);

                descBox.setText(team[turnSlot[0]].skill3desc);
                actionSelect[0] = 3;

                //change all the button colors
                attackButton.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                attackButton.setTextColor(getResources().getColor(R.color.leg_gold));
                skill1Button.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                skill1Button.setTextColor(getResources().getColor(R.color.leg_gold));
                skill2Button.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                skill2Button.setTextColor(getResources().getColor(R.color.leg_gold));
                skill3Button.setBackgroundColor(getResources().getColor(R.color.leg_red));
                skill3Button.setTextColor(getResources().getColor(R.color.leg_sand));
                skill4Button.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                skill4Button.setTextColor(getResources().getColor(R.color.leg_gold));
                skill5Button.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                skill5Button.setTextColor(getResources().getColor(R.color.leg_gold));
                skill6Button.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                skill6Button.setTextColor(getResources().getColor(R.color.leg_gold));
                skill7Button.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                skill7Button.setTextColor(getResources().getColor(R.color.leg_gold));
                overSoulButton.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                overSoulButton.setTextColor(getResources().getColor(R.color.leg_gold));
                defendButton.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                defendButton.setTextColor(getResources().getColor(R.color.leg_gold));
            }
        });

        skill4Button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //disable this button and enable all other action buttons
                attackButton.setEnabled(true);
                skill1Button.setEnabled(true);
                skill2Button.setEnabled(true);
                skill3Button.setEnabled(true);
                skill4Button.setEnabled(false);
                skill5Button.setEnabled(true);
                skill6Button.setEnabled(true);
                skill7Button.setEnabled(true);
                overSoulButton.setEnabled(true);
                defendButton.setEnabled(true);

                //enable all the player buttons
                player1Button.setEnabled(true);
                player2Button.setEnabled(true);
                player3Button.setEnabled(true);
                player4Button.setEnabled(true);
                bossImageButton.setEnabled(true);

                descBox.setText(team[turnSlot[0]].skill4desc);
                actionSelect[0] = 4;

                //change all the button colors
                attackButton.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                attackButton.setTextColor(getResources().getColor(R.color.leg_gold));
                skill1Button.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                skill1Button.setTextColor(getResources().getColor(R.color.leg_gold));
                skill2Button.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                skill2Button.setTextColor(getResources().getColor(R.color.leg_gold));
                skill3Button.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                skill3Button.setTextColor(getResources().getColor(R.color.leg_gold));
                skill4Button.setBackgroundColor(getResources().getColor(R.color.leg_red));
                skill4Button.setTextColor(getResources().getColor(R.color.leg_sand));
                skill5Button.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                skill5Button.setTextColor(getResources().getColor(R.color.leg_gold));
                skill6Button.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                skill6Button.setTextColor(getResources().getColor(R.color.leg_gold));
                skill7Button.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                skill7Button.setTextColor(getResources().getColor(R.color.leg_gold));
                overSoulButton.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                overSoulButton.setTextColor(getResources().getColor(R.color.leg_gold));
                defendButton.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                defendButton.setTextColor(getResources().getColor(R.color.leg_gold));
            }
        });

        skill5Button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //disable this button and enable all other action buttons
                attackButton.setEnabled(true);
                skill1Button.setEnabled(true);
                skill2Button.setEnabled(true);
                skill3Button.setEnabled(true);
                skill4Button.setEnabled(true);
                skill5Button.setEnabled(false);
                skill6Button.setEnabled(true);
                skill7Button.setEnabled(true);
                overSoulButton.setEnabled(true);
                defendButton.setEnabled(true);

                //enable all the player buttons
                player1Button.setEnabled(true);
                player2Button.setEnabled(true);
                player3Button.setEnabled(true);
                player4Button.setEnabled(true);
                bossImageButton.setEnabled(true);

                descBox.setText(team[turnSlot[0]].skill5desc);
                actionSelect[0] = 5;

                //change all the button colors
                attackButton.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                attackButton.setTextColor(getResources().getColor(R.color.leg_gold));
                skill1Button.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                skill1Button.setTextColor(getResources().getColor(R.color.leg_gold));
                skill2Button.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                skill2Button.setTextColor(getResources().getColor(R.color.leg_gold));
                skill3Button.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                skill3Button.setTextColor(getResources().getColor(R.color.leg_gold));
                skill4Button.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                skill4Button.setTextColor(getResources().getColor(R.color.leg_gold));
                skill5Button.setBackgroundColor(getResources().getColor(R.color.leg_red));
                skill5Button.setTextColor(getResources().getColor(R.color.leg_sand));
                skill6Button.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                skill6Button.setTextColor(getResources().getColor(R.color.leg_gold));
                skill7Button.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                skill7Button.setTextColor(getResources().getColor(R.color.leg_gold));
                overSoulButton.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                overSoulButton.setTextColor(getResources().getColor(R.color.leg_gold));
                defendButton.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                defendButton.setTextColor(getResources().getColor(R.color.leg_gold));
            }
        });

        skill6Button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //disable this button and enable all other action buttons
                attackButton.setEnabled(true);
                skill1Button.setEnabled(true);
                skill2Button.setEnabled(true);
                skill3Button.setEnabled(true);
                skill4Button.setEnabled(true);
                skill5Button.setEnabled(true);
                skill6Button.setEnabled(false);
                skill7Button.setEnabled(true);
                overSoulButton.setEnabled(true);
                defendButton.setEnabled(true);

                //enable all the player buttons
                player1Button.setEnabled(true);
                player2Button.setEnabled(true);
                player3Button.setEnabled(true);
                player4Button.setEnabled(true);
                bossImageButton.setEnabled(true);

                descBox.setText(team[turnSlot[0]].skill6desc);
                actionSelect[0] = 6;

                //change all the button colors
                attackButton.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                attackButton.setTextColor(getResources().getColor(R.color.leg_gold));
                skill1Button.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                skill1Button.setTextColor(getResources().getColor(R.color.leg_gold));
                skill2Button.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                skill2Button.setTextColor(getResources().getColor(R.color.leg_gold));
                skill3Button.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                skill3Button.setTextColor(getResources().getColor(R.color.leg_gold));
                skill4Button.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                skill4Button.setTextColor(getResources().getColor(R.color.leg_gold));
                skill5Button.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                skill5Button.setTextColor(getResources().getColor(R.color.leg_gold));
                skill6Button.setBackgroundColor(getResources().getColor(R.color.leg_red));
                skill6Button.setTextColor(getResources().getColor(R.color.leg_sand));
                skill7Button.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                skill7Button.setTextColor(getResources().getColor(R.color.leg_gold));
                overSoulButton.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                overSoulButton.setTextColor(getResources().getColor(R.color.leg_gold));
                defendButton.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                defendButton.setTextColor(getResources().getColor(R.color.leg_gold));
            }
        });

        skill7Button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //disable this button and enable all other action buttons
                attackButton.setEnabled(true);
                skill1Button.setEnabled(true);
                skill2Button.setEnabled(true);
                skill3Button.setEnabled(true);
                skill4Button.setEnabled(true);
                skill5Button.setEnabled(true);
                skill6Button.setEnabled(true);
                skill7Button.setEnabled(false);
                overSoulButton.setEnabled(true);
                defendButton.setEnabled(true);

                //enable all the player buttons
                player1Button.setEnabled(true);
                player2Button.setEnabled(true);
                player3Button.setEnabled(true);
                player4Button.setEnabled(true);
                bossImageButton.setEnabled(true);

                descBox.setText(team[turnSlot[0]].skill7desc);
                actionSelect[0] = 7;

                //change all the button colors
                attackButton.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                attackButton.setTextColor(getResources().getColor(R.color.leg_gold));
                skill1Button.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                skill1Button.setTextColor(getResources().getColor(R.color.leg_gold));
                skill2Button.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                skill2Button.setTextColor(getResources().getColor(R.color.leg_gold));
                skill3Button.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                skill3Button.setTextColor(getResources().getColor(R.color.leg_gold));
                skill4Button.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                skill4Button.setTextColor(getResources().getColor(R.color.leg_gold));
                skill5Button.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                skill5Button.setTextColor(getResources().getColor(R.color.leg_gold));
                skill6Button.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                skill6Button.setTextColor(getResources().getColor(R.color.leg_gold));
                skill7Button.setBackgroundColor(getResources().getColor(R.color.leg_red));
                skill7Button.setTextColor(getResources().getColor(R.color.leg_sand));
                overSoulButton.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                overSoulButton.setTextColor(getResources().getColor(R.color.leg_gold));
                defendButton.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                defendButton.setTextColor(getResources().getColor(R.color.leg_gold));
            }
        });

        overSoulButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //disable this button and enable all other action buttons
                attackButton.setEnabled(true);
                skill1Button.setEnabled(true);
                skill2Button.setEnabled(true);
                skill3Button.setEnabled(true);
                skill4Button.setEnabled(true);
                skill5Button.setEnabled(true);
                skill6Button.setEnabled(true);
                skill7Button.setEnabled(true);
                overSoulButton.setEnabled(false);
                defendButton.setEnabled(true);

                //enable all the player buttons
                player1Button.setEnabled(true);
                player2Button.setEnabled(true);
                player3Button.setEnabled(true);
                player4Button.setEnabled(true);
                bossImageButton.setEnabled(true);

                descBox.setText(team[turnSlot[0]].oversouldesc);
                actionSelect[0] = 8;

                //change all the button colors
                attackButton.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                attackButton.setTextColor(getResources().getColor(R.color.leg_gold));
                skill1Button.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                skill1Button.setTextColor(getResources().getColor(R.color.leg_gold));
                skill2Button.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                skill2Button.setTextColor(getResources().getColor(R.color.leg_gold));
                skill3Button.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                skill3Button.setTextColor(getResources().getColor(R.color.leg_gold));
                skill4Button.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                skill4Button.setTextColor(getResources().getColor(R.color.leg_gold));
                skill5Button.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                skill5Button.setTextColor(getResources().getColor(R.color.leg_gold));
                skill6Button.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                skill6Button.setTextColor(getResources().getColor(R.color.leg_gold));
                skill7Button.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                skill7Button.setTextColor(getResources().getColor(R.color.leg_gold));
                overSoulButton.setBackgroundColor(getResources().getColor(R.color.leg_red));
                overSoulButton.setTextColor(getResources().getColor(R.color.leg_sand));
                defendButton.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                defendButton.setTextColor(getResources().getColor(R.color.leg_gold));
            }
        });

        defendButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //disable this button and enable all other action buttons
                attackButton.setEnabled(true);
                skill1Button.setEnabled(true);
                skill2Button.setEnabled(true);
                skill3Button.setEnabled(true);
                skill4Button.setEnabled(true);
                skill5Button.setEnabled(true);
                skill6Button.setEnabled(true);
                skill7Button.setEnabled(true);
                overSoulButton.setEnabled(true);
                defendButton.setEnabled(false);

                //enable all the player buttons
                player1Button.setEnabled(true);
                player2Button.setEnabled(true);
                player3Button.setEnabled(true);
                player4Button.setEnabled(true);
                bossImageButton.setEnabled(true);

                descBox.setText(R.string.DefendDesc);
                actionSelect[0] = 9;

                //change all the button colors
                attackButton.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                attackButton.setTextColor(getResources().getColor(R.color.leg_gold));
                skill1Button.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                skill1Button.setTextColor(getResources().getColor(R.color.leg_gold));
                skill2Button.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                skill2Button.setTextColor(getResources().getColor(R.color.leg_gold));
                skill3Button.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                skill3Button.setTextColor(getResources().getColor(R.color.leg_gold));
                skill4Button.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                skill4Button.setTextColor(getResources().getColor(R.color.leg_gold));
                skill5Button.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                skill5Button.setTextColor(getResources().getColor(R.color.leg_gold));
                skill6Button.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                skill6Button.setTextColor(getResources().getColor(R.color.leg_gold));
                skill7Button.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                skill7Button.setTextColor(getResources().getColor(R.color.leg_gold));
                overSoulButton.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                overSoulButton.setTextColor(getResources().getColor(R.color.leg_gold));
                defendButton.setBackgroundColor(getResources().getColor(R.color.leg_red));
                defendButton.setTextColor(getResources().getColor(R.color.leg_sand));
            }
        });

        confirmButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //set the current action into the first queue
                actionQueue[turnSlot[0]] = actionSelect[0];

                //when the first character action is selected use this one
                if(turnSlot[0] == 0){
                    turnSlot[0] = 1;
                    int[] skillsStrings = buttonSet(team[turnSlot[0]]);
                    //set the buttons of the next character
                    for(int j = 0; j <= 7; j++){
                        String setup = "skill" + j;
                        skills[j].setText(skillsStrings[j]);

                    }
                    cancelButton.setEnabled(true);
                    attackButton.performClick();

                    //change all the button colors
                    player1Button.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                    player1Button.setTextColor(getResources().getColor(R.color.leg_gold));
                    player2Button.setBackgroundColor(getResources().getColor(R.color.leg_red));
                    player2Button.setTextColor(getResources().getColor(R.color.leg_sand));
                    player3Button.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                    player3Button.setTextColor(getResources().getColor(R.color.leg_gold));
                    player4Button.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                    player4Button.setTextColor(getResources().getColor(R.color.leg_gold));

                    cancelButton.setBackgroundColor(getResources().getColor(R.color.leg_blue));
                    cancelButton.setTextColor(getResources().getColor(R.color.leg_gold));
                }

                //when the second character action is selected use this one
                else if(turnSlot[0] == 1){
                    turnSlot[0] = 2;
                    int[] skillsStrings = buttonSet(team[turnSlot[0]]);
                    //set the buttons of the next character
                    for(int j = 0; j <= 7; j++){
                        String setup = "skill" + j;
                        skills[j].setText(skillsStrings[j]);
                    }
                    attackButton.performClick();

                    //change all the button colors
                    player1Button.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                    player1Button.setTextColor(getResources().getColor(R.color.leg_gold));
                    player2Button.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                    player2Button.setTextColor(getResources().getColor(R.color.leg_gold));
                    player3Button.setBackgroundColor(getResources().getColor(R.color.leg_red));
                    player3Button.setTextColor(getResources().getColor(R.color.leg_sand));
                    player4Button.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                    player4Button.setTextColor(getResources().getColor(R.color.leg_gold));
                }

                //when the third character action is selected use this one
                else if(turnSlot[0] == 2){
                    turnSlot[0] = 3;
                    int[] skillsStrings = buttonSet(team[turnSlot[0]]);
                    //set the buttons of the next character
                    for(int j = 0; j <= 7; j++){
                        String setup = "skill" + j;
                        skills[j].setText(skillsStrings[j]);
                    }
                    attackButton.performClick();
                    confirmButton.setText(R.string.confirmButtonAlt);

                    //change all the button colors
                    player1Button.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                    player1Button.setTextColor(getResources().getColor(R.color.leg_gold));
                    player2Button.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                    player2Button.setTextColor(getResources().getColor(R.color.leg_gold));
                    player3Button.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                    player3Button.setTextColor(getResources().getColor(R.color.leg_gold));
                    player4Button.setBackgroundColor(getResources().getColor(R.color.leg_red));
                    player4Button.setTextColor(getResources().getColor(R.color.leg_sand));
                }

                //when the last character action is selected use this one
                else{
                    confirmButton.setEnabled(false);
                    cancelButton.setEnabled(false);

                    //disable this button and enable all other action buttons
                    attackButton.setEnabled(false);
                    skill1Button.setEnabled(false);
                    skill2Button.setEnabled(false);
                    skill3Button.setEnabled(false);
                    skill4Button.setEnabled(false);
                    skill5Button.setEnabled(false);
                    skill6Button.setEnabled(false);
                    skill7Button.setEnabled(false);
                    overSoulButton.setEnabled(false);
                    defendButton.setEnabled(false);

                    //disable all the player buttons
                    player1Button.setEnabled(false);
                    player2Button.setEnabled(false);
                    player3Button.setEnabled(false);
                    player4Button.setEnabled(false);

                    //empty out the text box
                    descBox.setText("");


                    //change all the button colors
                    player1Button.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                    player1Button.setTextColor(getResources().getColor(R.color.leg_gold));
                    player2Button.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                    player2Button.setTextColor(getResources().getColor(R.color.leg_gold));
                    player3Button.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                    player3Button.setTextColor(getResources().getColor(R.color.leg_gold));
                    player4Button.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                    player4Button.setTextColor(getResources().getColor(R.color.leg_gold));

                    //CHANGE THE COLOR OF ALL THE BUTTONS TO DIM OUT OR REMOVE THEM ALL
                    attackButton.setBackgroundColor(getResources().getColor(R.color.leg_metal));
                    attackButton.setTextColor(getResources().getColor(R.color.leg_metal));
                    skill1Button.setBackgroundColor(getResources().getColor(R.color.leg_metal));
                    skill1Button.setTextColor(getResources().getColor(R.color.leg_metal));
                    skill2Button.setBackgroundColor(getResources().getColor(R.color.leg_metal));
                    skill2Button.setTextColor(getResources().getColor(R.color.leg_metal));
                    skill3Button.setBackgroundColor(getResources().getColor(R.color.leg_metal));
                    skill3Button.setTextColor(getResources().getColor(R.color.leg_metal));
                    skill4Button.setBackgroundColor(getResources().getColor(R.color.leg_metal));
                    skill4Button.setTextColor(getResources().getColor(R.color.leg_metal));
                    skill5Button.setBackgroundColor(getResources().getColor(R.color.leg_metal));
                    skill5Button.setTextColor(getResources().getColor(R.color.leg_metal));
                    skill6Button.setBackgroundColor(getResources().getColor(R.color.leg_metal));
                    skill6Button.setTextColor(getResources().getColor(R.color.leg_metal));
                    skill7Button.setBackgroundColor(getResources().getColor(R.color.leg_metal));
                    skill7Button.setTextColor(getResources().getColor(R.color.leg_metal));
                    overSoulButton.setBackgroundColor(getResources().getColor(R.color.leg_metal));
                    overSoulButton.setTextColor(getResources().getColor(R.color.leg_metal));
                    defendButton.setBackgroundColor(getResources().getColor(R.color.leg_metal));
                    defendButton.setTextColor(getResources().getColor(R.color.leg_metal));

                    descBox.setBackgroundColor(getResources().getColor(R.color.leg_metal));

                    cancelButton.setBackgroundColor(getResources().getColor(R.color.leg_metal));
                    cancelButton.setTextColor(getResources().getColor(R.color.leg_metal));
                    confirmButton.setBackgroundColor(getResources().getColor(R.color.leg_metal));
                    confirmButton.setTextColor(getResources().getColor(R.color.leg_metal));
                    //THEN PUT THEM BACK TO THEIR ORIGINAL COLORS AFTERWARDS

                    //gathering the actual calculations for the turn
                    Hero[] speedQueue = speedQueue(team, actionQueue);

                    //displaying the actions in a timely manner with a toast
                    Handler atb = new Handler();
                    //display the text of activating the first action
                    atb.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            String activatedSkill = soloAction(speedQueue[0], team, enemy, bossSelection[0], actionQueue[0], sharedPool);
                            Toast battleLog2 = Toast.makeText(getApplicationContext(), activatedSkill, Toast.LENGTH_SHORT);
                            battleLog2.setGravity(Gravity.TOP, 0, 575);
                            battleLog2.show();

                            //set the health and mp displays
                            for(int i = 0; i <= 3; i++){
                                players[i].setText(team[i].getStatus());
                            }
                        }
                    }, battleLogTimer);

                    battleLogTimer += 3000;

                    //display the text of activating the second action
                    atb.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            String activatedSkill = soloAction(speedQueue[1], team, enemy, bossSelection[0], actionQueue[1], sharedPool);
                            Toast battleLog2 = Toast.makeText(getApplicationContext(), activatedSkill, Toast.LENGTH_SHORT);
                            battleLog2.setGravity(Gravity.TOP, 0, 575);
                            battleLog2.show();

                            //set the health and mp displays
                            for(int i = 0; i <= 3; i++){
                                players[i].setText(team[i].getStatus());
                            }
                        }
                    }, battleLogTimer);

                    battleLogTimer += 3000;

                    //display the text of activating the third action
                    atb.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            String activatedSkill = soloAction(speedQueue[2], team, enemy, bossSelection[0], actionQueue[2], sharedPool);
                            Toast battleLog2 = Toast.makeText(getApplicationContext(), activatedSkill, Toast.LENGTH_SHORT);
                            battleLog2.setGravity(Gravity.TOP, 0, 575);
                            battleLog2.show();

                            //set the health and mp displays
                            for(int i = 0; i <= 3; i++){
                                players[i].setText(team[i].getStatus());
                            }
                        }
                    }, battleLogTimer);

                    battleLogTimer += 3000;

                    //display the text of activating the last skill
                    atb.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            String activatedSkill = soloAction(speedQueue[3], team, enemy, bossSelection[0], actionQueue[3], sharedPool);
                            Toast battleLog2 = Toast.makeText(getApplicationContext(), activatedSkill, Toast.LENGTH_SHORT);
                            battleLog2.setGravity(Gravity.TOP, 0, 575);
                            battleLog2.show();

                            //set the health and mp displays
                            for(int i = 0; i <= 3; i++){
                                players[i].setText(team[i].getStatus());
                            }
                        }
                    }, battleLogTimer);

                    battleLogTimer += 3000;

                    //display the text of activating the boss's actions
                    atb.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            String testing = enemyPattern(bossSelection[0], timerCounter[0], sharedPool);
                            Toast battleLog2 = Toast.makeText(getApplicationContext(), testing, Toast.LENGTH_SHORT);
                            battleLog2.setGravity(Gravity.TOP, 0, 575);
                            battleLog2.show();
                            bossSelection[0] = deadCheck(bossSelection[0], modeSelection);

                            //set the health and mp displays
                            for(int i = 0; i <= 3; i++){
                                players[i].setText(team[i].getStatus());
                            }
                        }
                    }, battleLogTimer);

                    battleLogTimer += 3500;

                    //enable all the buttons again and show the change in HP
                    atb.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            //set the boss
                            switch(bossSelection[0]){
                                case 0:
                                    bossName.setText(R.string.boss1Name);
                                    //bossImage.setImageDrawable(R.drawable.####);
                                    break;
                                case 1:
                                    bossName.setText(R.string.boss2Name);
                                    //bossImage.setImageDrawable(R.drawable.####);
                                    break;
                                case 2:
                                    bossName.setText(R.string.boss3Name);
                                    //bossImage.setImageDrawable(R.drawable.####);
                                    break;
                                case 3:
                                    bossName.setText(R.string.boss4Name);
                                    //bossImage.setImageDrawable(R.drawable.####);
                                    break;
                            }

                            if(gameOver){
                                String gameEnder = "";
                                if(victory){
                                    gameEnder = "The enemy has been defeated!";
                                }
                                else{
                                    gameEnder = "The party has been defeated...";
                                }
                                Toast battleLog2 = Toast.makeText(getApplicationContext(), gameEnder, Toast.LENGTH_SHORT);
                                battleLog2.setGravity(Gravity.TOP, 0, 575);
                                battleLog2.show();
                            }
                            else{
                                String bossLine = "";
                                if(enemy[bossSelection[0]].currenthp > 50){
                                    bossLine = "The enemy is still raring to go.";
                                }
                                else if(enemy[bossSelection[0]].currenthp > 25){
                                    bossLine = "The enemy is severely weakened.";
                                }
                                else{
                                    bossLine = "The enemy is on their last legs!";
                                }
                                Toast battleLog2 = Toast.makeText(getApplicationContext(), bossLine, Toast.LENGTH_SHORT);
                                battleLog2.setGravity(Gravity.TOP, 0, 575);
                                battleLog2.show();
                            }
                        }

                    }, battleLogTimer);

                    battleLogTimer += 3000;

                    atb.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            if(gameOver){
                                endGame(member1, member2, member3, member4, modeSelection, bossSelection[0], timerCounter[0]);;
                            }
                            else{
                                attackButton.performClick();
                                confirmButton.setEnabled(true);
                                confirmButton.setText(R.string.confirmButton2);

                                //set the timer to the next turn
                                timerCounter[0] ++;
                                String time = String.valueOf(timerCounter[0]);
                                timerButton.setText(time);

                                //set the health and mp displays
                                for(int i = 0; i <= 3; i++){
                                    team[i].passTurn();
                                    players[i].setText(team[i].getStatus());
                                }
                                enemy[bossSelection[0]].passTurn();
                                mpBarText.setText(sharedPool.currentMana + "/" + sharedPool.totalMana);

                                turnSlot[0] = 0;
                                int[] skillsStrings = buttonSet(team[turnSlot[0]]);
                                //set the buttons of the next character
                                for(int j = 0; j <= 7; j++){
                                    String setup = "skill" + j;
                                    skills[j].setText(skillsStrings[j]);
                                }

                                //change all the button colors
                                player1Button.setBackgroundColor(getResources().getColor(R.color.leg_red));
                                player1Button.setTextColor(getResources().getColor(R.color.leg_sand));
                                player2Button.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                                player2Button.setTextColor(getResources().getColor(R.color.leg_gold));
                                player3Button.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                                player3Button.setTextColor(getResources().getColor(R.color.leg_gold));
                                player4Button.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                                player4Button.setTextColor(getResources().getColor(R.color.leg_gold));

                                descBox.setBackgroundColor(getResources().getColor(R.color.leg_ocean));

                                cancelButton.setBackgroundColor(getResources().getColor(R.color.leg_metal));
                                cancelButton.setTextColor(getResources().getColor(R.color.leg_gold));
                                confirmButton.setBackgroundColor(getResources().getColor(R.color.leg_blue));
                                confirmButton.setTextColor(getResources().getColor(R.color.leg_gold));
                            }
                        }

                    }, battleLogTimer);

                    battleLogTimer = 1000;
                }

            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //when the last character action is being selected use this one
                if(turnSlot[0] == 3){
                    turnSlot[0] = 2;
                    int[] skillsStrings = buttonSet(team[turnSlot[0]]);
                    //set the buttons of the previous character
                    for(int j = 0; j <= 7; j++){
                        String setup = "skill" + j;
                        skills[j].setText(skillsStrings[j]);
                    }
                    confirmButton.setEnabled(true);
                    confirmButton.setText(R.string.confirmButton2);
                    attackButton.performClick();

                    //change all the button colors
                    player1Button.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                    player1Button.setTextColor(getResources().getColor(R.color.leg_gold));
                    player2Button.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                    player2Button.setTextColor(getResources().getColor(R.color.leg_gold));
                    player3Button.setBackgroundColor(getResources().getColor(R.color.leg_red));
                    player3Button.setTextColor(getResources().getColor(R.color.leg_sand));
                    player4Button.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                    player4Button.setTextColor(getResources().getColor(R.color.leg_gold));
                }

                //when the third character action is being selected use this one
                else if(turnSlot[0] == 2){
                    turnSlot[0] = 1;
                    int[] skillsStrings = buttonSet(team[turnSlot[0]]);
                    //set the buttons of the next character
                    for(int j = 0; j <= 7; j++){
                        String setup = "skill" + j;
                        skills[j].setText(skillsStrings[j]);
                    }
                    attackButton.performClick();

                    //change all the button colors
                    player1Button.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                    player1Button.setTextColor(getResources().getColor(R.color.leg_gold));
                    player2Button.setBackgroundColor(getResources().getColor(R.color.leg_red));
                    player2Button.setTextColor(getResources().getColor(R.color.leg_sand));
                    player3Button.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                    player3Button.setTextColor(getResources().getColor(R.color.leg_gold));
                    player4Button.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                    player4Button.setTextColor(getResources().getColor(R.color.leg_gold));
                }

                //when the second character action is being selected use this one
                else if(turnSlot[0] == 1){
                    turnSlot[0] = 0;
                    int[] skillsStrings = buttonSet(team[turnSlot[0]]);
                    //set the buttons of the next character
                    for(int j = 0; j <= 7; j++){
                        String setup = "skill" + j;
                        skills[j].setText(skillsStrings[j]);
                    }
                    cancelButton.setEnabled(false);
                    attackButton.performClick();

                    //change all the button colors
                    player1Button.setBackgroundColor(getResources().getColor(R.color.leg_red));
                    player1Button.setTextColor(getResources().getColor(R.color.leg_sand));
                    player2Button.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                    player2Button.setTextColor(getResources().getColor(R.color.leg_gold));
                    player3Button.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                    player3Button.setTextColor(getResources().getColor(R.color.leg_gold));
                    player4Button.setBackgroundColor(getResources().getColor(R.color.leg_teal));
                    player4Button.setTextColor(getResources().getColor(R.color.leg_gold));

                    cancelButton.setBackgroundColor(getResources().getColor(R.color.leg_gray));
                    cancelButton.setTextColor(getResources().getColor(R.color.leg_gold));
                }

                else{
                    int[] skillsStrings = buttonSet(team[turnSlot[0]]);
                    //set the buttons of the next character
                    for(int j = 0; j <= 7; j++){
                        String setup = "skill" + j;
                        skills[j].setText(skillsStrings[j]);

                    }
                    cancelButton.setEnabled(false);


                }

            }
        });

        quitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //send the user back to the mode select screen
                Intent modeStart = new Intent(BattleIActivity.this, ModeActivity.class);
                startActivity(modeStart);
                finish();
            }
        });

        timerButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //potentially make it a pause button?
                //for now use it as a test to get to the results screen
                Intent resultStart = new Intent(BattleIActivity.this, ResultIActivity.class);
                resultStart.putExtra("mem1", member1);
                resultStart.putExtra("mem2", member2);
                resultStart.putExtra("mem3", member3);
                resultStart.putExtra("mem4", member4);
                resultStart.putExtra("game mode", modeSelection);
                resultStart.putExtra("boss", bossSelection[0]);
                resultStart.putExtra("timer", timerCounter[0]);
                resultStart.putExtra("victory", true);
                startActivity(resultStart);
                finish();
            }
        });

        bossImageButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //disable this button and enable all other player buttons
                player1Button.setEnabled(true);
                player2Button.setEnabled(true);
                player3Button.setEnabled(true);
                player4Button.setEnabled(true);
                bossImageButton.setEnabled(false);

                //enable all action button
                attackButton.setEnabled(true);
                skill1Button.setEnabled(true);
                skill2Button.setEnabled(true);
                skill3Button.setEnabled(true);
                skill4Button.setEnabled(true);
                skill5Button.setEnabled(true);
                skill6Button.setEnabled(true);
                skill7Button.setEnabled(true);
                overSoulButton.setEnabled(true);
                defendButton.setEnabled(true);


                String name = (String) bossName.getText();
                //display the boss text
                descBox.setText(" Name: " + name + "\n" + enemy[bossSelection[0]].display());
            }
        });

    }

// ----------------------- methods ----------------------------------

    public void endGame(int member1, int member2, int member3, int member4, int modeSelection, int bossSelection, int timerCounter) {
        Intent resultStart = new Intent(BattleIActivity.this, ResultIActivity.class);
        resultStart.putExtra("mem1", member1);
        resultStart.putExtra("mem2", member2);
        resultStart.putExtra("mem3", member3);
        resultStart.putExtra("mem4", member4);
        resultStart.putExtra("game mode", modeSelection);
        resultStart.putExtra("boss", bossSelection);
        resultStart.putExtra("timer", timerCounter);
        resultStart.putExtra("victory", victory);
        startActivity(resultStart);
        finish();
    }

    //this function creates the heroes by calling them and placing them in their respective positions
    public void createHero(int heroClass, int position) {
        switch(heroClass){
            case 1:
                team[position] = new Emperor(position);
                break;
            case 2:
                team[position] = new Saint(position);
                break;
            case 3:
                team[position] = new Enchantress(position);
                break;
            case 4:
                team[position] = new Sage(position);
                break;
            case 5:
                team[position] = new Pirate(position);
                break;
        }
    }

    //this function creates the actual value of the sharable MP Bar
    public int createMP(Hero[] players) {
        //create a variable to store the whole value and iterate all the parties mp gauges to add them
        int mp = 0;
        for(int i = 0; i < 4; i++){
            mp += players[i].mp;
        }

        return mp;
    }

    //this function sets all the menu buttons depending on the character action being selected
    public int[] buttonSet(Hero player) {
        int[] skills = {0, 0, 0, 0, 0, 0, 0, 0};
        skills[0] = player.skill1;
        skills[1] = player.skill2;
        skills[2] = player.skill3;
        skills[3] = player.skill4;
        skills[4] = player.skill5;
        skills[5] = player.skill6;
        skills[6] = player.skill7;
        skills[7] = player.oversoul;

        return skills;
    }

    //this function places all the actions in order from fastest to slowest
    public Hero[] speedQueue(Hero[] players, int[] actionQueue) {
        Hero[] tempPlayers = {null, null, null, null};
        for(int i = 0; i < 4; i++){
            tempPlayers[i] = players[i];
        }
        Hero temp = players[0];
        int temp2 = actionQueue[0];

        for(int x = 0; x < 4; x++){
            for(int y = x + 1; y < 4; y++){
                if(tempPlayers[x].spd < tempPlayers[y].spd){
                    //change the order of the temp team to do fastest first
                    temp = tempPlayers[x];
                    tempPlayers[x] = tempPlayers[y];
                    tempPlayers[y] = temp;

                    //change the order of the actions to match the temp team
                    temp2 = actionQueue[x];
                    actionQueue[x] = actionQueue[y];
                    actionQueue[y] = temp2;
                }
            }

        }

        return tempPlayers;
    }

    //this function converts a single action into its respective damage function
    public String soloAction(Hero player, Hero[] players, Boss[] foes, int bossSelected, int queue, ManaPool mp){
        String action = "";

        if(player.ailment.equals("paralyzed") || !player.alive){
            queue = 9;
        }

        switch(queue){
            case 0:
                action = player.strike(foes, bossSelected);
                break;
            case 1:
                action = player.doSkill1(players, foes, bossSelected, mp);
                break;
            case 2:
                action = player.doSkill2(players, foes, bossSelected, mp);
                break;
            case 3:
                action = player.doSkill3(players, foes, bossSelected, mp);
                break;
            case 4:
                action = player.doSkill4(players, foes, bossSelected, mp);
                break;
            case 5:
                action = player.doSkill5(players, foes, bossSelected, mp);
                break;
            case 6:
                action = player.doSkill6(players, foes, bossSelected, mp);
                break;
            case 7:
                action = player.doSkill7(players, foes, bossSelected, mp);
                break;
            case 8:
                action = player.doOversoul(players, foes, bossSelected, mp);
                break;
            case 9:
                action = player.defend();
                break;
        }
        System.out.println("ACTION END MP DISPLAY: " + mp.currentMana + "/" + mp.totalMana );
        return action;
    }

    //this function will pick the boss's attack pattern based on it's current health
    public String enemyPattern(int bossSelected, int timerCounter, ManaPool mp) {
        String bossAction = "";

        if(enemy[bossSelected].ailment.equals("paralyzed") || !enemy[bossSelected].alive){
            bossAction = enemy[bossSelected].defend();
            return bossAction;
        }

        //do pattern 1 if the current hp is greater than 50% of the max
        if(enemy[bossSelected].currenthp > (enemy[bossSelected].hp / 2)){
            bossAction = enemy[bossSelected].pattern1(timerCounter, team, mp);
        }

        //do pattern 2 if the current hp is greater than 25% of max
        else if(enemy[bossSelected].currenthp > (enemy[bossSelected].hp / 4)){
            bossAction = enemy[bossSelected].pattern2(timerCounter, team);
        }
        else{
            bossAction = enemy[bossSelected].pattern3(timerCounter, team);
        }

        return bossAction;
    }

    public int deadCheck(int bossSelected, int gameMode) {
        if(!team[0].alive && !team[1].alive && !team[2].alive && !team[3].alive){
            //end the game right there
            //might be after a delay
            victory = false;
            gameOver = true;
        }

        //decide what to do based on the game mode
        //if standard the game ends
        //if boss rush then there are more steps to check
        if(!enemy[bossSelected].alive){
            if(gameMode == 1){
                //end the game right there
                victory = true;
                gameOver = true;
            }
            else if(gameMode == 2){
                if(bossSelected < 3){
                    //switch the boss
                    //boss switcher method
                    //change the picture as well
                    bossSelected += 1;
                }

                else{
                    //end the game right there
                    victory = true;
                    gameOver = true;
                }
            }
        }

        return bossSelected;
    }

    //places the correct boss based on the data given
    //necessary once for standard and 4 times for boss rush
    /*public void bossDecider(int boss){
        switch(boss){
            case 1:
                bossName.setText(R.string.boss1Name);
                //bossImage.setImageDrawable(R.drawable.####);
                break;
            case 2:
                bossName.setText(R.string.boss2Name);
                //bossImage.setImageDrawable(R.drawable.####);
                break;
            case 3:
                bossName.setText(R.string.boss3Name);
                //bossImage.setImageDrawable(R.drawable.####);
                break;
            case 4:
                bossName.setText(R.string.boss4Name);
                //bossImage.setImageDrawable(R.drawable.####);
                break;
        }
    }*/

}
