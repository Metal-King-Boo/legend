package com.example.legend;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BattleIActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle_i);

        //receive all the collected data from the previous screens
        Bundle extras = getIntent().getExtras();
        //1 = boss 1, 2 = boss 2, 3 = boss 3, 4 = boss 4
        int bossSelection = extras.getInt("boss");
        int modeSelection = extras.getInt("game mode");
        //1 = EMP, 2 = SNT, 3 = ENC, 4 = SAG, 5 = PIR
        int member1 = extras.getInt("mem1");
        int member2 = extras.getInt("mem2");
        int member3 = extras.getInt("mem3");
        int member4 = extras.getInt("mem4");

        int timerCounter[] = {1};

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
        final ImageView bossImage = findViewById(R.id.bossImage);
        final ImageView charIcon = findViewById(R.id.textDisplay);

        final ImageButton quitButton = findViewById(R.id.quitButton);
        final Button timerButton = findViewById(R.id.timerButton);

        final Button confirmButton = findViewById(R.id.confirmButton2);
        final Button cancelButton = findViewById(R.id.cancelButton2);

        //set the boss
        switch(bossSelection){
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

        //arrays made for simplicity in setting the party members
        int teamSet[] = {member1, member2, member3, member4};
        Button players[] = {player1Button, player2Button, player3Button, player4Button};
        //cycles through 4 times for 4 members/buttons
        for(int i = 0; i <= 3; i++){
            //if statements telling what class would be put in there based on the data from character select
            if(teamSet[i] == 1){
                players[i].setText(R.string.suzaku);
            }
            else if(teamSet[i] == 2){
                players[i].setText(R.string.byakko);
            }
            else if(teamSet[i] == 3){
                players[i].setText(R.string.genbu);
            }
            else if(teamSet[i] == 4){
                players[i].setText(R.string.seiryuu);
            }
            else{
                players[i].setText(R.string.haunglong);
            }
        }

        //set the buttons up for launch
        attackButton.setEnabled(false);
        String time = String.valueOf(timerCounter[0]);
        timerButton.setText(time);

        //buttons go here
        //am currently setting the displays and buttons without the fragment
        //also testing without adding any game logic such as the heroes, bosses, or their skills

        player1Button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //disable this button and enable all other player buttons
                player1Button.setEnabled(false);
                player2Button.setEnabled(true);
                player3Button.setEnabled(true);
                player4Button.setEnabled(true);

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

                descBox.setText(R.string.status1);
            }
        });

        player2Button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //disable this button and enable all other player buttons
                player1Button.setEnabled(true);
                player2Button.setEnabled(false);
                player3Button.setEnabled(true);
                player4Button.setEnabled(true);

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

                descBox.setText(R.string.status2);
            }
        });

        player3Button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //disable this button and enable all other player buttons
                player1Button.setEnabled(true);
                player2Button.setEnabled(true);
                player3Button.setEnabled(false);
                player4Button.setEnabled(true);

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

                descBox.setText(R.string.status3);
            }
        });

        player4Button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //disable this button and enable all other player buttons
                player1Button.setEnabled(true);
                player2Button.setEnabled(true);
                player3Button.setEnabled(true);
                player4Button.setEnabled(false);

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

                descBox.setText(R.string.status4);
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

                descBox.setText(R.string.AttackDesc);
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

                descBox.setText(R.string.skill1);
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

                descBox.setText(R.string.skill2);
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

                descBox.setText(R.string.skill3);
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

                descBox.setText(R.string.skill4);
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

                descBox.setText(R.string.skill5);
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

                descBox.setText(R.string.skill6);
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

                descBox.setText(R.string.skill7);
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

                descBox.setText(R.string.overS);
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

                descBox.setText(R.string.DefendDesc);
            }
        });

        confirmButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //will be used to set actions in the queue
                // for now it will be used to increment timer for testing purposes
                timerCounter[0] ++;
                String time = String.valueOf(timerCounter[0]);
                timerButton.setText(time);

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
                resultStart.putExtra("boss", bossSelection);
                resultStart.putExtra("timer", timerCounter[0]);
                resultStart.putExtra("victory", true);
                startActivity(resultStart);
                finish();
            }
        });


    }

// ----------------------- methods ----------------------------------

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
    }

    //needs to be altered to work with the actual hero classes
    //for demo purposes right now
    public void teamFiller(int player1, int player2, int player3, int player4){

        //arrays to easily cycle through the classes and their party positions
        int teamSet[] = {player1, player2, player3, player4};
        Button players[] = {player1Button, player2Button, player3Button, player4Button};
        //cycles through 4 times for 4 members/buttons
        for(int i = 0; i <= 3; i++){
            //if statements telling what class would be put in there based on the data from character select
            if(teamSet[i] == 1){
                players[i].setText(R.string.suzaku);
            }
            else if(teamSet[i] == 2){
                players[i].setText(R.string.byakko);
            }
            else if(teamSet[i] == 2){
                players[i].setText(R.string.genbu);
            }
            else if(teamSet[i] == 2){
                players[i].setText(R.string.seiryuu);
            }
            else{
                players[i].setText(R.string.haunglong);
            }
        }
    }*/

}
