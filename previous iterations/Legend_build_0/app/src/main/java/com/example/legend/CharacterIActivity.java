package com.example.legend;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class CharacterIActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_i);

        Bundle extras = getIntent().getExtras();
        int modeSelection = extras.getInt("game mode");

        //declare all buttons to be used on the screen
        //these are the selection buttons found at the top
        final Button empButton = findViewById(R.id.empButton);
        final Button saiButton = findViewById(R.id.saiButton);
        final Button encButton = findViewById(R.id.encButton);
        final Button sagButton = findViewById(R.id.sagButton);
        final Button pirButton = findViewById(R.id.pirButton);

        final Button confirmButton = findViewById(R.id.confirmButton);
        final Button cancelButton = findViewById(R.id.cancelButton);

        //these are the buttons that move the user from screen to screen
        final ImageButton backButton = findViewById(R.id.backButton);
        final ImageButton homeButton = findViewById(R.id.homeButton);
        final ImageButton forwardButton = findViewById(R.id.fowardButton2);

        //arrays for storing the user's party selections
        //1 = EMP, 2 = SNT, 3 = ENC, 4 = SAG, 5 = PIR
        final int[] teamSetup = {0, 0, 0, 0};
        final int[] charSelection = {1};

        //declare the checkboxes used to indicate to the user that a party member has been selected
        CheckBox check1 = findViewById(R.id.checkBox1);
        CheckBox check2 = findViewById(R.id.checkBox2);
        CheckBox check3 = findViewById(R.id.checkBox3);
        CheckBox check4 = findViewById(R.id.checkBox4);

        //declare the objects for the character portrait, icon, and description
        ImageView charImage = findViewById(R.id.charImage);
        ImageView charIcon = findViewById(R.id.charIcon);
        TextView charDesc = findViewById(R.id.charText);

        //disable these to setup the screen on launch
        forwardButton.setEnabled(false);
        cancelButton.setEnabled(false);
        empButton.setEnabled(false);

        empButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //disabling the clicked button and enabling the others
                empButton.setEnabled(false);
                saiButton.setEnabled(true);
                encButton.setEnabled(true);
                sagButton.setEnabled(true);
                pirButton.setEnabled(true);

                //setting the image, icon, and text to match the selected job
                charDesc.setText(R.string.empText);
                //charIcon.setImageDrawable(R.drawable.empIcon);
                //charImage.setImageDrawable(R.drawable.empPortrait);

                charSelection[0] = 1;

            }
        });

        saiButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //disabling the clicked button and enabling the others
                empButton.setEnabled(true);
                saiButton.setEnabled(false);
                encButton.setEnabled(true);
                sagButton.setEnabled(true);
                pirButton.setEnabled(true);

                //setting the image, icon, and text to match the selected job
                charDesc.setText(R.string.saiText);
                //charIcon.setImageDrawable(R.drawable.saiIcon);
                //charImage.setImageDrawable(R.drawable.saiPortrait);

                charSelection[0] = 2;

            }
        });

        encButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //disabling the clicked button and enabling the others
                empButton.setEnabled(true);
                saiButton.setEnabled(true);
                encButton.setEnabled(false);
                sagButton.setEnabled(true);
                pirButton.setEnabled(true);

                //setting the image, icon, and text to match the selected job
                charDesc.setText(R.string.encText);
                //charIcon.setImageDrawable(R.drawable.encIcon);
                //charImage.setImageDrawable(R.drawable.encPortrait);

                charSelection[0] = 3;

            }
        });

        sagButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //disabling the clicked button and enabling the others
                empButton.setEnabled(true);
                saiButton.setEnabled(true);
                encButton.setEnabled(true);
                sagButton.setEnabled(false);
                pirButton.setEnabled(true);

                //setting the image, icon, and text to match the selected job
                charDesc.setText(R.string.sagText);
                //charIcon.setImageDrawable(R.drawable.sagIcon);
                //charImage.setImageDrawable(R.drawable.sagPortrait);

                charSelection[0] = 4;

            }
        });

        pirButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //disabling the clicked button and enabling the others
                empButton.setEnabled(true);
                saiButton.setEnabled(true);
                encButton.setEnabled(true);
                sagButton.setEnabled(true);
                pirButton.setEnabled(false);

                //setting the image, icon, and text to match the selected job
                charDesc.setText(R.string.pirText);
                //charIcon.setImageDrawable(R.drawable.pirIcon);
                //charImage.setImageDrawable(R.drawable.pirPortrait);

                charSelection[0] = 5;

            }
        });

        confirmButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //if statement to decide what the confirm button does at different stages of team building
                if(check1.isChecked() == false){
                    //signify that a party member has been selected and store the specific class
                    check1.setChecked(true);
                    teamSetup[0] = charSelection[0];
                    //re-enable the cancel button since a selection has been made
                    cancelButton.setEnabled(true);
                }
                else if(check2.isChecked() == false){
                    //signify that a party member has been selected and store the specific class
                    check2.setChecked(true);
                    teamSetup[1] = charSelection[0];
                }
                else if(check3.isChecked() == false){
                    //signify that a party member has been selected and store the specific class
                    check3.setChecked(true);
                    teamSetup[2] = charSelection[0];
                }
                else{
                    //signify that a party member has been selected and store the specific class
                    check4.setChecked(true);
                    teamSetup[3] = charSelection[0];
                    //disable the confirm button since all selections have been made
                    //re-enable to forward button as well
                    confirmButton.setEnabled(false);
                    forwardButton.setEnabled(true);
                }
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //if statement to decide what the cancel button does at different stages of team building
                if(check4.isChecked() == true){
                    //signify that a party member has been removed and change the stored value
                    check4.setChecked(false);
                    teamSetup[3] = 0;
                    //re-enable the confirm button since a selection has been removed
                    //disable the forward button as well
                    confirmButton.setEnabled(true);
                    forwardButton.setEnabled(false);
                }
                else if(check3.isChecked() == true){
                    //signify that a party member has been removed and change the stored value
                    check3.setChecked(false);
                    teamSetup[2] = 0;
                }
                else if(check2.isChecked() == true){
                    //signify that a party member has been removed and change the stored value
                    check2.setChecked(false);
                    teamSetup[1] = 0;
                }
                else{
                    //signify that a party member has been removed and change the stored value
                    check1.setChecked(false);
                    teamSetup[0] = 0;
                    //disable the cancel button since all selections have been removed
                    cancelButton.setEnabled(false);
                }
            }
        });

        forwardButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //if on standard mode it will send to the boss selection screen
                if(modeSelection == 1){
                    Intent charStart = new Intent(CharacterIActivity.this, BossIActivity.class);
                    //carry the user's screen selection to the next screen
                    charStart.putExtra("member 1", teamSetup[0]);
                    charStart.putExtra("member 2", teamSetup[1]);
                    charStart.putExtra("member 3", teamSetup[2]);
                    charStart.putExtra("member 4", teamSetup[3]);
                    charStart.putExtra("game mode", modeSelection);
                    startActivity(charStart);
                    finish();
                }
                //if on boss rush mode it will send to the battle screen
                else{
                    Intent charStart = new Intent(CharacterIActivity.this, BattleIActivity.class);
                    //carry the user's team selections to the next screen
                    charStart.putExtra("member 1", teamSetup[0]);
                    charStart.putExtra("member 2", teamSetup[1]);
                    charStart.putExtra("member 3", teamSetup[2]);
                    charStart.putExtra("member 4", teamSetup[3]);
                    charStart.putExtra("boss", modeSelection);

                    // do something about how there will be no boss intent package on the next activity

                    startActivity(charStart);
                    finish();
                }

            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //return the player back to the home
                Intent modeStart = new Intent(CharacterIActivity.this, ModeActivity.class);
                startActivity(modeStart);
                finish();
            }
        });

        homeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //do something cool or remove it [UP FOR DEBATE]
                //option 1 - show character stats on the text display
                //option 2 - show recommendations and/or skill names on the text display

            }
        });

    }
}
