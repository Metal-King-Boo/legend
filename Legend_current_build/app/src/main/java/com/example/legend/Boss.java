package com.example.legend;

import java.util.Random;

//default parent Boss class for all the actual classes to become children
//allows an easy format for future classes or changes to be made
public class Boss {

    //default health parameters
    int hp;
    int currenthp;
    int atk;
    int matk;
    int def;
    int mdef;
    int spd;

    int playerImmortal;
    boolean alive;

    //these will be the slots for the buffs and debuffs
    String buffSlot1;
    String buffSlot2;
    String buffSlot3;
    String buffSlot4;
    String buffSlot5;
    int buffSlot1Counter;
    int buffSlot2Counter;
    int buffSlot3Counter;
    int buffSlot4Counter;
    int buffSlot5Counter;

    String debuffSlot1;
    String debuffSlot2;
    String debuffSlot3;
    String debuffSlot4;
    String debuffSlot5;
    int debuffSlot1Counter;
    int debuffSlot2Counter;
    int debuffSlot3Counter;
    int debuffSlot4Counter;
    int debuffSlot5Counter;

    String ailment;
    int ailmentCounter;

    int prevAction;
    int prevAction2;
    int prevAction4;

    public Boss() {
        //these are the stats used in the game
        hp = 1000;
        currenthp = 1000;
        atk = 500;
        matk = 500;
        def = 500;
        mdef = 500;
        spd = 500;
        alive = true;
        playerImmortal = 1;

        buffSlot1 = "none";
        buffSlot2 = "none";
        buffSlot3 = "none";
        buffSlot4 = "none";
        buffSlot5 = "none";
        buffSlot1Counter = 0;
        buffSlot2Counter = 0;
        buffSlot3Counter = 0;
        buffSlot4Counter = 0;
        buffSlot5Counter = 0;

        debuffSlot1 = "none";
        debuffSlot2 = "none";
        debuffSlot3 = "none";
        debuffSlot4 = "none";
        debuffSlot5 = "none";
        debuffSlot1Counter = 0;
        debuffSlot2Counter = 0;
        debuffSlot3Counter = 0;
        debuffSlot4Counter = 0;
        debuffSlot5Counter = 0;

        ailment = "healthy";
        ailmentCounter = 0;

    }

    public String display() {
        String buffed = "No";
        if(!buffSlot1.equals("none")){
            buffed = "Yes";
        }

        return " HP: " + hp + "\n\n Status: " + ailment + " | " + ailmentCounter +
                " \n Buffed: " + buffed + " \n Debuffs \n " +
                debuffSlot1 + ": " + debuffSlot1Counter + "\n " +
                debuffSlot2 + ": " + debuffSlot2Counter + "\n " +
                debuffSlot3 + ": " + debuffSlot3Counter + "\n " +
                debuffSlot4 + ": " + debuffSlot4Counter + "\n " +
                debuffSlot5 + ": " + debuffSlot5Counter;
    }

    public String pattern1(int turn, Hero[] players, ManaPool mana) {
        String option = "Error: pattern1 is not working";

        //on turn 1, 6, 11, 16, 21... follow this action path
        if((turn - 1) % 5 == 0 || turn == 1){
            //randomly pick between three attacks for the first turn
            Random random = new Random();
            int seed = random.nextInt(3);
            System.out.println("I made it in here! for turn 1");
            System.out.println(seed);
            switch(seed){
                case 0:
                    this.prevAction = 0;
                    option = doSkill1(players);
                    System.out.println(option);
                    break;
                case 1:
                    this.prevAction = 1;
                    option = this.doSkill2(players);
                    System.out.println(option);
                    break;
                case 2:
                    this.prevAction = 2;
                    option = this.doSkill3(players);
                    System.out.println(option);
                    break;
            }
        }

        //on turn 2, 7, 12, 17, 22... follow this action path
        else if((turn - 2) % 5 == 0 || turn == 2){
            //randomly pick between the remaining two attacks from the previous turn's selection
            Random random = new Random();
            int seed = random.nextInt(3);

            while(prevAction == seed){
                seed = random.nextInt(3);
            }

            switch(seed){
                case 0:
                    prevAction2 = 0;
                    option = doSkill1(players);
                    break;
                case 1:
                    prevAction2 = 1;
                    option = doSkill2(players);
                    break;
                case 2:
                    prevAction2 = 2;
                    option = doSkill3(players);
                    break;
            }
        }

        //on turn 3, 8, 13, 18, 23... follow this action path
        else if((turn - 3) % 5 == 0 || turn == 3){
            //pick the attack that was not used by the previous turn's selection
            Random random = new Random();
            int seed = random.nextInt(3);

            while(prevAction == seed || prevAction2 == seed){
                seed = random.nextInt(3);
            }

            switch(seed){
                case 0:
                    option = doSkill1(players);
                    break;
                case 1:
                    option = doSkill2(players);
                    break;
                case 2:
                    option = doSkill3(players);
                    break;
            }
        }

        //on turn 4, 9, 14, 19, 24... follow this action path
        else if((turn - 4) % 5 == 0 || turn == 4){
            //pick one of two attacks, the other attack will be used the next time this path is chosen
            Random random = new Random();
            int seed = random.nextInt(2);

            while(prevAction4 == seed){
                seed = random.nextInt(2);
            }

            switch(seed){
                case 0:
                    prevAction4 = 0;
                    option = doSkill4(players);
                    break;
                case 1:
                    prevAction4 = 1;
                    option = doSkill6(players);
                    break;
            }
        }

        //on turn 5, 10, 15, 20... follow this action path
        else{
            option = doSkill5(players);
        }

        return option;
    }

    public String pattern2(int turn, Hero[] players) {
        String option = "Error: pattern2 is not working";

        //on turn 1, 6, 11, 16, 21... follow this action path
        if((turn - 1) % 5 == 0 || turn == 1){
            //randomly pick between three attacks for the first turn
            Random random = new Random();
            int seed = random.nextInt(3);
            System.out.println("I made it in here! for turn 1");
            System.out.println(seed);
            switch(seed){
                case 0:
                    this.prevAction = 0;
                    option = doSkill1(players);
                    System.out.println(option);
                    break;
                case 1:
                    this.prevAction = 1;
                    option = this.doSkill2(players);
                    System.out.println(option);
                    break;
                case 2:
                    this.prevAction = 2;
                    option = this.doSkill3(players);
                    System.out.println(option);
                    break;
            }
        }

        //on turn 2, 7, 12, 17, 22... follow this action path
        else if((turn - 2) % 5 == 0 || turn == 2){
            //randomly pick between the remaining two attacks from the previous turn's selection
            Random random = new Random();
            int seed = random.nextInt(3);

            while(prevAction == seed){
                seed = random.nextInt(3);
            }

            switch(seed){
                case 0:
                    prevAction2 = 0;
                    option = doSkill1(players);
                    break;
                case 1:
                    prevAction2 = 1;
                    option = doSkill2(players);
                    break;
                case 2:
                    prevAction2 = 2;
                    option = doSkill3(players);
                    break;
            }
        }

        //on turn 3, 8, 13, 18, 23... follow this action path
        else if((turn - 3) % 5 == 0 || turn == 3){
            //pick the attack that was not used by the previous turn's selection
            Random random = new Random();
            int seed = random.nextInt(3);

            while(prevAction2 == seed){
                seed = random.nextInt(3);
            }

            switch(seed){
                case 0:
                    option = doSkill1(players);
                    break;
                case 1:
                    option = doSkill2(players);
                    break;
                case 2:
                    option = doSkill3(players);
                    break;
            }
        }

        //on turn 4, 9, 14, 19, 24... follow this action path
        else if((turn - 4) % 5 == 0 || turn == 4){
            //pick one of two attacks, the other attack will be used the next time this path is chosen
            Random random = new Random();
            int seed = random.nextInt(2);

            while(prevAction4 == seed){
                seed = random.nextInt(2);
            }

            switch(seed){
                case 0:
                    prevAction4 = 0;
                    option = doSkill4(players);
                    break;
                case 1:
                    prevAction4 = 1;
                    option = doSkill6(players);
                    break;
            }
        }

        //on turn 5, 10, 15, 20... follow this action path
        else{
            option = doSkill5(players);
        }

        return option;
    }

    public String pattern3(int turn, Hero[] players) {
        String option = "Error: pattern3 is not working";

        //select a random skill to use
        Random random = new Random();
        int seed = random.nextInt(6);

        //if an action is repeated or skill5 is selected reroll
        while(prevAction == seed || seed == 4){
            seed = random.nextInt(6);
        }

        //if the previous action was skill4 or skill6 then always use skill5
        if(prevAction == 3 || prevAction == 5){
            this.prevAction = 4;
            option = doSkill5(players);

        }
        //if not then follow this path
        else{
            //do the skill that was selected and make it the previously selected skill
            switch(seed){
                case 0:
                    this.prevAction = 0;
                    option = doSkill1(players);
                    break;
                case 1:
                    this.prevAction = 1;
                    option = doSkill2(players);
                    break;
                case 2:
                    this.prevAction = 2;
                    option = doSkill3(players);
                    break;
                case 3:
                    this.prevAction = 3;
                    option = doSkill4(players);
                    break;
                case 5:
                    this.prevAction = 5;
                    option = doSkill6(players);
                    break;
            }
        }

        return option;
    }

    public int realHealth(int currentHealth) {
        if(currentHealth <= 0){
            currentHealth = 0;
            //need to have it check with confirmation to skip its buttons
            alive = false;
        }

        return currentHealth;
    }

    public void applyBuff(String buffName, int buffTimer) {
        //if a buff would be repeated instead reset its timer
        boolean duplicate = false;
        String[] buffSlots = {this.buffSlot1, this.buffSlot2, this.buffSlot3, this.buffSlot4, this.buffSlot5};
        int[] buffTimers = {buffSlot1Counter, buffSlot2Counter, buffSlot3Counter, buffSlot4Counter, buffSlot5Counter};
        for(int i = 0; i < 5; i++){
            if(buffSlots[i].equals(buffName)){
                buffTimers[i] = buffTimer;
                duplicate = true;
            }
        }

        //if a new buff is being added then shift everything to make space for it
        if(!duplicate){

            removeBuff(buffSlots[4]);

            for(int j = 4; j > 0; j--){
                buffSlots[j] = buffSlots[j - 1];
                buffTimers[j] = buffTimers[j - 1];

            }
            buffSlots[0] = buffName;
            buffTimers[0] = buffTimer;
        }

        //make sure to put the new buffs to the actual buff slot
        buffSlot1 = buffSlots[0];
        buffSlot1Counter = buffTimers[0];
        buffSlot2 = buffSlots[1];
        buffSlot2Counter = buffTimers[1];
        buffSlot3 = buffSlots[2];
        buffSlot3Counter = buffTimers[2];
        buffSlot4 = buffSlots[3];
        buffSlot4Counter = buffTimers[3];
        buffSlot5 = buffSlots[4];
        buffSlot5Counter = buffTimers[4];
    }

    public void removeBuff(String buffName) {
        switch(buffName){
            //Ruhtra buffs
            case "focused":
                def = def - 100;
                break;
            case "sharpened":
                atk = atk - 100;
                break;
        }
    }

    public void applyDebuff(String debuffName, int debuffTimer) {
        //if a debuff would be repeated instead reset its timer
        boolean duplicate = false;
        String[] debuffSlots = {debuffSlot1, debuffSlot2, debuffSlot3, debuffSlot4, debuffSlot5};
        int[] debuffTimers = {debuffSlot1Counter, debuffSlot2Counter, debuffSlot3Counter, debuffSlot4Counter, debuffSlot5Counter};
        for(int i = 0; i < 5; i++){
            if(debuffSlots[i] == debuffName){
                debuffTimers[i] = debuffTimer;
                duplicate = true;
            }
        }

        //if a new debuff is being added then shift everything to make space for it
        if(!duplicate){

            removeDebuff(debuffSlots[4]);

            for(int j = 4; j > 0; j--){
                debuffSlots[j] = debuffSlots[j - 1];
                debuffTimers[j] = debuffTimers[j - 1];

            }
            debuffSlots[0] = debuffName;
            debuffTimers[0] = debuffTimer;
        }

        //make sure to put the new debuffs to the actual debuff slot
        debuffSlot1 = debuffSlots[0];
        debuffSlot1Counter = debuffTimers[0];
        debuffSlot2 = debuffSlots[1];
        debuffSlot2Counter = debuffTimers[1];
        debuffSlot3 = debuffSlots[2];
        debuffSlot3Counter = debuffTimers[2];
        debuffSlot4 = debuffSlots[3];
        debuffSlot4Counter = debuffTimers[3];
        debuffSlot5 = debuffSlots[4];
        debuffSlot5Counter = debuffTimers[4];
    }

    public void removeDebuff(String buffName) {
        switch(buffName){
            //Enchantress debuffs
            case "strength":
                atk = atk + 100;
                matk = matk + 100;
                break;
            case "tower":
                def = def + 100;
                mdef = mdef + 100;
                break;
            case "chariot":
                spd = spd + 100;
                break;
            //Pirate debuffs
            case "cheap shot":
                def = def + 100;
                break;
        }
    }

    public void applyAilment(String ailmentName, int ailmentTimer) {
        //if an ailment would be repeated instead reset its timer
        //if a new ailment is being applied replace the current one
        if(!ailment.equals(ailmentName)){
            ailment = ailmentName;
        }

        ailmentCounter = ailmentTimer;
    }

    public void passTurn() {
        String[] debuffSlots = {this.debuffSlot1, this.debuffSlot2, this.debuffSlot3, this.debuffSlot4, this.debuffSlot5};
        int[] debuffTimers = {debuffSlot1Counter, debuffSlot2Counter, debuffSlot3Counter, debuffSlot4Counter, debuffSlot5Counter};
        String[] buffSlots = {this.buffSlot1, this.buffSlot2, this.buffSlot3, this.buffSlot4, this.buffSlot5};
        int[] buffTimers = {buffSlot1Counter, buffSlot2Counter, buffSlot3Counter, buffSlot4Counter, buffSlot5Counter};

        //this loop is used for buff skills being checked
        for(int i = 0; i < 5; i++){
            switch (buffSlots[i]) {
                //BEGINNING OF SHAE D AVAILABLE BUFFS

                //END OF SHAE D AVAILABLE BUFFS
                //BEGINNING OF MISS LEAD AVAILABLE BUFFS

                //END OF MISS LEAD AVAILABLE BUFFS
                //BEGINNING OF RUHTRA AVAILABLE BUFFS
                //if the buffSlot carries Skill 4
                case "focused":
                    if (buffTimers[i] > 1) {
                        buffTimers[i]--;
                    } else {
                        if (i != 0) {
                            for (int j = i; j > 0; j--) {
                                buffSlots[j] = buffSlots[j - 1];
                                buffTimers[j] = buffTimers[j - 1];
                            }
                        }
                        buffSlots[0] = "none";
                        buffTimers[0] = 0;
                        def = def - 100;
                    }
                    break;
                //if the buffSlot carries Skill 6
                case "sharpened":
                    if (buffTimers[i] > 1) {
                        buffTimers[i]--;
                    } else {
                        if (i != 0) {
                            for (int j = i; j > 0; j--) {
                                buffSlots[j] = buffSlots[j - 1];
                                buffTimers[j] = buffTimers[j - 1];
                            }
                        }
                        buffSlots[0] = "none";
                        buffTimers[0] = 0;
                        atk = atk - 100;
                    }
                    break;
                //END OF RUHTRA AVAILABLE BUFFS
                //BEGINNING OF DEN MO AVAILABLE BUFFS

                //END OF DEN MO AVAILABLE BUFFS
            }
        }

        //make sure to put the new buffs to the actual buff slot
        buffSlot1 = buffSlots[0];
        buffSlot1Counter = buffTimers[0];
        buffSlot2 = buffSlots[1];
        buffSlot2Counter = buffTimers[1];
        buffSlot3 = buffSlots[2];
        buffSlot3Counter = buffTimers[2];
        buffSlot4 = buffSlots[3];
        buffSlot4Counter = buffTimers[3];
        buffSlot5 = buffSlots[4];
        buffSlot5Counter = buffTimers[4];

        // -----------------------------------------------------------------------------------------

        //this loop is used for debuff skills being checked
        for(int i = 0; i < 5; i++){
            //BEGINNING OF EMPEROR AVAILABLE DEBUFFS
            //if the debuffSlot carries SKILL NAME
            switch (debuffSlots[i]) {
                case "placeholder1":
                    if (debuffTimers[i] > 1) {
                        debuffTimers[i]--;
                    } else {
                        if (i != 0) {
                            for (int j = i; j > 0; j--) {
                                debuffSlots[j] = debuffSlots[j - 1];
                                debuffTimers[j] = debuffTimers[j - 1];
                            }
                        }
                        debuffSlots[0] = "none";
                        debuffTimers[0] = 0;
                    }
                    break;
                //END OF EMPEROR AVAILABLE DEBUFFS
                //BEGINNING OF SAINT AVAILABLE DEBUFFS
                //if the debuffSlot carries SKILL NAME
                case "placeholder2":
                    if (debuffTimers[i] > 1) {
                        debuffTimers[i]--;
                    } else {
                        if (i != 0) {
                            for (int j = i; j > 0; j--) {
                                debuffSlots[j] = debuffSlots[j - 1];
                                debuffTimers[j] = debuffTimers[j - 1];
                            }
                        }
                        debuffSlots[0] = "none";
                        debuffTimers[0] = 0;
                        System.out.println("placeholder2");
                    }
                    break;
                //END OF SAINT AVAILABLE DEBUFFS
                //BEGINNING OF ENCHANTRESS AVAILABLE DEBUFFS
                //if the debuffSlot carries Strength VIII
                case "strength":
                    if (debuffTimers[i] > 1) {
                        debuffTimers[i]--;
                    } else {
                        if (i != 0) {
                            for (int j = i; j > 0; j--) {
                                debuffSlots[j] = debuffSlots[j - 1];
                                debuffTimers[j] = debuffTimers[j - 1];
                            }
                        }
                        debuffSlots[0] = "none";
                        debuffTimers[0] = 0;
                        atk = atk + 100;
                        matk = matk + 100;
                    }
                    break;
                //if the debuffSlot carries The Tower XVI
                case "tower":
                    if (debuffTimers[i] > 1) {
                        debuffTimers[i]--;
                    } else {
                        if (i != 0) {
                            for (int j = i; j > 0; j--) {
                                debuffSlots[j] = debuffSlots[j - 1];
                                debuffTimers[j] = debuffTimers[j - 1];
                            }
                        }
                        debuffSlots[0] = "none";
                        debuffTimers[0] = 0;
                        def = def + 100;
                        mdef = mdef + 100;
                    }
                    break;
                //if the debuffSlot carries The Chariot VII
                case "chariot":
                    if (debuffTimers[i] > 1) {
                        debuffTimers[i]--;
                    } else {
                        if (i != 0) {
                            for (int j = i; j > 0; j--) {
                                debuffSlots[j] = debuffSlots[j - 1];
                                debuffTimers[j] = debuffTimers[j - 1];
                            }
                        }
                        debuffSlots[0] = "none";
                        debuffTimers[0] = 0;
                        spd = spd + 100;
                    }
                    break;
                //END OF ENCHANTRESS AVAILABLE DEBUFFS
                //BEGINNING OF PIRATE AVAILABLE DEBUFFS
                //if the debuffSlot carries SKILL NAME
                case "cheap shot":
                    if (debuffTimers[i] > 1) {
                        debuffTimers[i]--;
                    } else {
                        if (i != 0) {
                            for (int j = i; j > 0; j--) {
                                debuffSlots[j] = debuffSlots[j - 1];
                                debuffTimers[j] = debuffTimers[j - 1];
                            }
                        }
                        debuffSlots[0] = "none";
                        debuffTimers[0] = 0;
                        def = def + 100;
                    }
                    break;
            }
            //END OF DEN MO AVAILABLE DEBUFFS
        }

        //make sure to put the new debuffs to the actual debuff slot
        debuffSlot1 = debuffSlots[0];
        debuffSlot1Counter = debuffTimers[0];
        debuffSlot2 = debuffSlots[1];
        debuffSlot2Counter = debuffTimers[1];
        debuffSlot3 = debuffSlots[2];
        debuffSlot3Counter = debuffTimers[2];
        debuffSlot4 = debuffSlots[3];
        debuffSlot4Counter = debuffTimers[3];
        debuffSlot5 = debuffSlots[4];
        debuffSlot5Counter = debuffTimers[4];

        //------------------------------------------------------------------------------------------

        //this is the space for ailment checking
        int statusCheck = 0;
        switch(ailment){
            //burn does static unchanging damage per turn
            case "burned":
                statusCheck = currenthp - (hp / 10);
                statusCheck = realHealth(statusCheck);
                currenthp = statusCheck;

                if(!alive){
                    ailment = "healthy";
                    ailmentCounter = 1;
                }

                ailmentCounter--;

                if(ailmentCounter == 0){
                    ailment = "healthy";
                    ailmentCounter = 0;
                }
                break;
            //poison does damage that grows the stronger the longer you have it
            case "poisoned":
                statusCheck = currenthp - ((hp / 10) + (40 - (ailmentCounter * 10)));
                statusCheck = realHealth(statusCheck);
                currenthp = statusCheck;

                if(!alive){
                    ailment = "healthy";
                    ailmentCounter = 1;
                }

                ailmentCounter--;

                if(ailmentCounter == 0){
                    ailment = "healthy";
                    ailmentCounter = 0;
                }
                break;
            //paralysis cancels your next action for as long as you have it
            case "paralyzed":
                if(!alive){
                    ailment = "healthy";
                    ailmentCounter = 1;
                }

                ailmentCounter--;

                if(ailmentCounter == 0){
                    ailment = "healthy";
                    ailmentCounter = 0;
                }
                //its main implementation takes place in defend() and the BattleIActivity
                break;
            case "dread":
                //dummied out ailment that would make skills cost double mp
                ailmentCounter--;
                break;
        }
    }

    public int singleAggro(Hero[] players) {
        Random random = new Random();
        int seed = random.nextInt(5);
        System.out.println("INITIAL SEED: " + seed);

        //normal aggro detection
        //if the seed lands on the position that is where the attack will land
        //if it lands on 4 then it picks the highest hp target
        //in the case of the seed landing on a person with the avoid buff it will reroll
        if(seed == 4){
            int maxHp = players[0].currenthp;
            for(int i = 0; i < 4; i++){
                if(maxHp <= players[i].currenthp){
                    maxHp = players[i].currenthp;
                    seed = i;
                }
            }
        }
        //handle fate and avoid buff as well as death
        else{
            //avoid
            if(players[seed].buffSlot1.equals("void") || players[seed].buffSlot2.equals("void") || players[seed].buffSlot3.equals("void") || players[seed].buffSlot4.equals("void") || players[seed].buffSlot5.equals("void")){
                seed = random.nextInt(5);
            }
            //fate
            else if(players[seed].buffSlot1.equals("fate") || players[seed].buffSlot2.equals("fate") || players[seed].buffSlot3.equals("fate") || players[seed].buffSlot4.equals("fate") || players[seed].buffSlot5.equals("fate")){
                seed = 4;
            }
            //death
            System.out.println(players[seed].alive);
            if(!players[seed].alive){
                int badSeed = seed;
                while(seed == badSeed && !players[seed].alive){
                    seed = random.nextInt(5);
                    System.out.println("DEATH SEED: " + seed);
                }
            }

            //target person with highest hp if a 4 is rolled
            if(seed == 4){
                int maxHp = players[0].currenthp;
                for(int i = 0; i < 4; i++){
                    if(maxHp < players[i].currenthp){
                        maxHp = players[i].currenthp;
                        seed = i;
                    }
                }
            }
        }

        //handling emperor cover
        if(players[0].cover || players[0].buffSlot1.equals("prov") || players[0].buffSlot2.equals("prov") || players[0].buffSlot3.equals("prov") || players[0].buffSlot4.equals("prov") || players[0].buffSlot5.equals("prov")){
            seed = 0;
        }
        else if(players[1].cover || players[1].buffSlot1.equals("prov") || players[1].buffSlot2.equals("prov") || players[1].buffSlot3.equals("prov") || players[1].buffSlot4.equals("prov") || players[1].buffSlot5.equals("prov")){
            seed = 1;
        }
        else if(players[2].cover || players[2].buffSlot1.equals("prov") || players[2].buffSlot2.equals("prov") || players[2].buffSlot3.equals("prov") || players[2].buffSlot4.equals("prov") || players[2].buffSlot5.equals("prov")){
            seed = 2;
        }
        else if(players[3].cover || players[3].buffSlot1.equals("prov") || players[3].buffSlot2.equals("prov") || players[3].buffSlot3.equals("prov") || players[3].buffSlot4.equals("prov") || players[3].buffSlot5.equals("prov")){
            seed = 3;
        }

        System.out.println("SEED END:" + seed);
        return seed;
    }

    public String strike() {return "Attack!";}
    public String doSkill1(Hero[] players) {return "Skill 1 Activate!";}
    public String doSkill2(Hero[] players) {return "Skill 2 Activate!";}
    public String doSkill3(Hero[] players) {return "Skill 3 Activate!";}
    public String doSkill4(Hero[] players) {return "Skill 4 Activate!";}
    public String doSkill5(Hero[] players) {return "Skill 5 Activate!";}
    public String doSkill6(Hero[] players) {return "Skill 6 Activate!";}

    public String defend() {
        String display = "";

        if(!alive){
            display = " lies there unmoving...";
        }
        else if (ailment.equals("paralyzed")){
            display = " is paralyzed and unable to move!";
        }

        //currently does nothing but should mitigate damage
        else{
            display = " defends!";
        }
        return display;
    }
}

class ShaeD extends Boss {

    boolean prayer;

    public ShaeD() {
        //these are the stats used in the game
        hp = 20000;
        currenthp = 20000;
        atk = 500;
        matk = 500;
        def = 500;
        mdef = 500;
        spd = 500;
        prayer = false;
        alive = true;
    }

    public String strike() {return "Shae D tears into the party!";}

    public String doSkill1(Hero[] players) {
        String display = "";
        int seed = singleAggro(players);
        int damage = 0;
        int afterHealth = 0;
        Random random = new Random();
        int newSeed = 0;
        switch(seed){
            case 0:
                //hit p1 and p2
                //only hit p2 if they are alive
                //first calculate the actual damage dealt and convert to real numbers
                damage = ((this.atk / 2) - players[seed].def) * (playerImmortal);
                damage = players[seed].realDamage(damage);
                //then calculate the remaining health of the player and keep it positive
                afterHealth = players[seed].currenthp - damage;
                afterHealth = players[seed].realHealth(afterHealth);
                players[seed].currenthp = afterHealth;

                //calculate the same stuff for the second target if they are alive
                if(players[seed + 1].alive){
                    damage = ((this.atk / 2) - players[seed + 1].def) * (playerImmortal);
                    damage = players[seed + 1].realDamage(damage);
                    afterHealth = players[seed + 1].currenthp - damage;
                    afterHealth = players[seed + 1].realHealth(afterHealth);
                    players[seed + 1].currenthp = afterHealth;

                    display = "Shae D. slams her tail on " + players[seed].name + " and " + players[seed + 1].name + " dealing " + damage + " damage!";
                }
                else{
                    display = "Shae D. slams her tail on " + players[seed].name + " dealing " + damage + " damage!";
                }
                break;
            case 1:
                //hit p2 and either p1 or p3
                //only hit p1/p3 if they are alive
                //first calculate the actual damage dealt and convert to real numbers
                damage = ((this.atk / 2) - players[seed].def) * (playerImmortal);
                damage = players[seed].realDamage(damage);
                //then calculate the remaining health of the player and keep it positive
                afterHealth = players[seed].currenthp - damage;
                afterHealth = players[seed].realHealth(afterHealth);
                players[seed].currenthp = afterHealth;

                //pick between the left or right target
                newSeed = random.nextInt(2);
                if(newSeed == 0){
                    newSeed = 0;
                }
                else{
                    newSeed = 2;
                }

                //calculate the same stuff for the second target if they are alive
                if(players[newSeed].alive){
                    damage = ((this.atk / 2) - players[newSeed].def) * (playerImmortal);
                    damage = players[newSeed].realDamage(damage);
                    afterHealth = players[newSeed].currenthp - damage;
                    afterHealth = players[newSeed].realHealth(afterHealth);
                    players[seed + 1].currenthp = afterHealth;

                    display = "Shae D. slams her tail on " + players[seed].name + " and " + players[newSeed].name + " dealing " + damage + " damage!";
                }
                else{
                    display = "Shae D. slams her tail on " + players[seed].name + " dealing " + damage + " damage!";
                }
                break;
            case 2:
                //hit p3 and either p2 or p4
                //only hit p1/p3 if they are alive
                //first calculate the actual damage dealt and convert to real numbers
                damage = ((this.atk / 2) - players[seed].def) * (playerImmortal);
                damage = players[seed].realDamage(damage);
                //then calculate the remaining health of the player and keep it positive
                afterHealth = players[seed].currenthp - damage;
                afterHealth = players[seed].realHealth(afterHealth);
                players[seed].currenthp = afterHealth;

                //pick between the left or right target
                newSeed = random.nextInt(2);
                if(newSeed == 0){
                    newSeed = 1;
                }
                else{
                    newSeed = 3;
                }

                //calculate the same stuff for the second target if they are alive
                if(players[newSeed].alive){
                    damage = ((this.atk / 2) - players[newSeed].def) * (playerImmortal);
                    damage = players[newSeed].realDamage(damage);
                    afterHealth = players[newSeed].currenthp - damage;
                    afterHealth = players[newSeed].realHealth(afterHealth);
                    players[seed + 1].currenthp = afterHealth;

                    display = "Shae D. slams her tail on " + players[seed].name + " and " + players[newSeed].name + " dealing " + damage + " damage!";
                }
                else{
                    display = "Shae D. slams her tail on " + players[seed].name + " dealing " + damage + " damage!";
                }
                break;
            case 3:
                //hit p4 and p3
                //only hit p3 if they are alive
                //first calculate the actual damage dealt and convert to real numbers
                damage = ((this.atk / 2) - players[seed].def) * (playerImmortal);
                damage = players[seed].realDamage(damage);
                //then calculate the remaining health of the player and keep it positive
                afterHealth = players[seed].currenthp - damage;
                afterHealth = players[seed].realHealth(afterHealth);
                players[seed].currenthp = afterHealth;

                //calculate the same stuff for the second target if they are alive
                if(players[seed - 1].alive){
                    damage = ((this.atk / 2) - players[seed - 1].def) * (playerImmortal);
                    damage = players[seed - 1].realDamage(damage);
                    afterHealth = players[seed - 1].currenthp - damage;
                    afterHealth = players[seed - 1].realHealth(afterHealth);
                    players[seed - 1].currenthp = afterHealth;

                    display = "Shae D. slams her tail on " + players[seed].name + " and " + players[seed - 1].name + " dealing " + damage + " damage!";
                }
                else{
                    display = "Shae D. slams her tail on " + players[seed].name + " dealing " + damage + " damage!";
                }
                break;
        }
        return display;
    }

    public String doSkill2(Hero[] players) {
        String display = "";
        int damage = 0;
        int totalDamage = 0;

        for(int i  = 0; i < 4; i++){
            if(players[i].alive){
                //first calculate the actual damage dealt and convert to real numbers
                damage = ((this.matk) - players[i].mdef) * (playerImmortal);
                damage = players[i].realDamage(damage);
                //then calculate the remaining health of the player and keep it positive
                int afterHealth = players[i].currenthp - damage;
                afterHealth = players[i].realHealth(afterHealth);
                players[i].currenthp = afterHealth;
                totalDamage += damage;
            }
        }

        display = "Shae D. blasts the party with a dark light dealing a shared total of " + totalDamage + " damage!";
        return display;
    }

    public String doSkill3(Hero[] players) {
        String display = "";
        int seed = singleAggro(players);

        //first calculate the actual damage dealt and convert to real numbers
        int damage = (int) (((this.atk * 1.5) - players[seed].def) * (playerImmortal));
        damage = players[seed].realDamage(damage);
        //then calculate the remaining health of the player and keep it positive
        int afterHealth = players[seed].currenthp - damage;
        afterHealth = players[seed].realHealth(afterHealth);
        players[seed].currenthp = afterHealth;

        display = "Shae D. tears into " + players[seed].name + " dealing " + damage + " damage!";
        return display;
    }

    public String doSkill4(Hero[] players) {
        String display = "";
        prayer = true;

        display = "Shae D. starts chanting a wicked prayer!";
        return display;
    }

    public String doSkill5(Hero[] players) {
        String display = "";
        int damage = 0;
        int afterHealth = 0;
        int totalDamage = 0;

        //the action that comes after skill 4
        if(prayer){
            for(int i  = 0; i < 4; i++){
                if(players[i].alive){
                    //first calculate the actual damage dealt and convert to real numbers
                    damage = ((this.matk) - players[i].mdef) * (playerImmortal);
                    damage = players[i].realDamage(damage);
                    //then calculate the remaining health of the player and keep it positive
                    afterHealth = players[i].currenthp - damage;
                    afterHealth = players[i].realHealth(afterHealth);
                    players[i].currenthp = afterHealth;
                    totalDamage += damage;

                    players[i].applyAilment("burned", 4);
                }
            }

            display = "Shae D. scorches the party with cleansing fire dealing " + totalDamage + " damage!";
        }

        //the action that comes after skill6
        else{
            for(int i  = 0; i < 4; i++){
                if(players[i].alive){
                    //first calculate the actual damage dealt and convert to real numbers
                    damage = ((this.atk * 2) - players[i].def) * (playerImmortal);
                    damage = players[i].realDamage(damage);
                    //then calculate the remaining health of the player and keep it positive
                    afterHealth = players[i].currenthp - damage;
                    afterHealth = players[i].realHealth(afterHealth);
                    players[i].currenthp = afterHealth;
                    totalDamage += damage;
                }
            }

            display = "Shae D. throws a statue at the party dealing a shared total of " + totalDamage + " damage!";
        }

        prayer = false;
        return display;
    }

    public String doSkill6(Hero[] players) {
        String display = "";
        int damage = 0;
        int seed = 0;

        for(int i = 0; i < 3; i++){
            seed = singleAggro(players);
            //first calculate the actual damage dealt and convert to real numbers
            damage = (int) (((this.atk * 0.5) - players[seed].def) * (playerImmortal));
            damage = players[seed].realDamage(damage);
            //then calculate the remaining health of the player and keep it positive
            int afterHealth = players[seed].currenthp - damage;
            afterHealth = players[seed].realHealth(afterHealth);
            players[seed].currenthp = afterHealth;
        }

        //inaccurate damage display
        display = "Shae D. thrashes about angrily dealing " + damage + " damage 3 times!";
        return display;
    }

    public String defend() {
        String display = "";

        if(!alive){
            display = "Shae D. lies there unmoving...";
        }
        else if (ailment.equals("paralyzed")){
            display = "Shae D. is paralyzed and unable to move!";
        }

        //currently does nothing but should mitigate damage
        else{
            display = "Shae D. defends!";
        }
        return display;
    }
}

class MissLead extends Boss {

    boolean deranged;

    public MissLead() {
        //these are the stats used in the game
        hp = 30000;
        currenthp = 30000;
        atk = 500;
        matk = 500;
        def = 500;
        mdef = 500;
        spd = 500;
        deranged = false;
        alive = true;
    }

    public String strike() {return "Miss Lead shoots a beam of light!";}

    public String doSkill1(Hero[] players) {
        String display = "";
        int seed = singleAggro(players);

        Random random = new Random();
        int paraCheck = random.nextInt(100);

        if(paraCheck > 49){
            players[seed].applyAilment("paralyzed", 4);
            display = "Miss Lead coddles " + players[seed].name + "paralyzing them!";
        }
        else{
            display = "Miss Lead coddles " + players[seed].name + "!";
        }

        return display;
    }

    public String doSkill2(Hero[] players) {
        String display = "";
        int damage = 0;
        int afterHealth = 0;
        int totalDamage = 0;

        for(int i  = 0; i < 4; i++){
            if(players[i].alive){
                //first calculate the actual damage dealt and convert to real numbers
                damage = ((this.matk) - players[i].mdef) * (playerImmortal);
                damage = players[i].realDamage(damage);
                //then calculate the remaining health of the player and keep it positive
                afterHealth = players[i].currenthp - damage;
                afterHealth = players[i].realHealth(afterHealth);
                players[i].currenthp = afterHealth;
                totalDamage += damage;
            }
        }

        display = "Miss Lead scolds the party harshly dealing a shared total of " + totalDamage + " damage!";
        return display;
    }

    public String doSkill3(Hero[] players) {
        String display = "";

        for(int i = 0; i < 4; i++){
            if(players[i].alive){
                applyDebuff("guil", 4);
                players[i].atk = players[i].atk - 100;
                players[i].matk = players[i].matk - 100;
            }
        }

        display = "Miss Lead guilt trips the party decreasing their moral!";
        return display;
    }

    public String doSkill4(Hero[] players) {
        String display = "";
        deranged = true;

        display = "Miss Lead calls for her son beyond the abyss!";
        return display;
    }

    public String doSkill5(Hero[] players) {
        String display = "";
        int damage = 0;
        int afterHealth = 0;
        int totalDamage = 0;

        if(deranged){
            for(int i  = 0; i < 4; i++){
                if(players[i].alive){
                    //first calculate the actual damage dealt and convert to real numbers
                    //if the target has a buff do even extreme damage
                    //if the target has no buff then do small damage
                    if(players[i].buffSlot1Counter > 0){
                        damage = ((this.matk * 2) - players[i].mdef) * (playerImmortal);
                    }
                    else{
                        damage = ((this.matk / 2) - players[i].mdef) * (playerImmortal);
                    }
                    damage = players[i].realDamage(damage);
                    //then calculate the remaining health of the player and keep it positive
                    afterHealth = players[i].currenthp - damage;
                    afterHealth = players[i].realHealth(afterHealth);
                    players[i].currenthp = afterHealth;
                    totalDamage += damage;
                }
            }

            display = "A demon's hand crashes into the party dealing a shared total of " + totalDamage + " damage!";
        }
        else{

            display = "Miss Lead digs herself out of the surrounding rubble!";
        }

        deranged = false;

        return display;
    }

    public String doSkill6(Hero[] players) {
        String display = "";
        int damage = 0;
        int afterHealth = 0;
        int totalDamage = 0;

        for(int i  = 0; i < 4; i++){
            if(players[i].alive){
                //first calculate the actual damage dealt and convert to real numbers
                damage = ((this.matk + this.atk) - (players[i].mdef + players[i].def)) * (playerImmortal);
                damage = players[i].realDamage(damage);
                //then calculate the remaining health of the player and keep it positive
                afterHealth = players[i].currenthp - damage;
                afterHealth = players[i].realHealth(afterHealth);
                players[i].currenthp = afterHealth;
                totalDamage += damage;
            }
        }

        display = "Miss Lead summons meteors dealing a shared total of " + totalDamage + " damage!";
        return display;
    }

    public String defend() {
        String display = "";

        if(!alive){
            display = "Miss Lead lies there unmoving...";
        }
        else if (ailment.equals("paralyzed")){
            display = "Miss Lead is paralyzed and unable to move!";
        }

        //currently does nothing but should mitigate damage
        else{
            display = "Miss Lead guards herself!";
        }
        return display;
    }
}

class Ruhtra extends Boss {

    public Ruhtra() {
        //these are the stats used in the game
        hp = 40000;
        currenthp = 40000;
        atk = 500;
        matk = 500;
        def = 500;
        mdef = 500;
        spd = 500;
        alive = true;
    }

    public String strike() {return "Ruhtra brings his sword down on the party!";}

    public String doSkill1(Hero[] players) {
        String display = "";
        Random random = new Random();
        int burnCheck = random.nextInt(100);
        int seed = singleAggro(players);
        int damage = 0;
        int totalDamage = 0;
        int afterHealth = 0;

        //first calculate the actual damage dealt and convert to real numbers
        damage = ((this.matk) - (players[seed].mdef)) * (playerImmortal);
        damage = players[seed].realDamage(damage);
        //then calculate the remaining health of the player and keep it positive
        afterHealth = players[seed].currenthp - damage;
        afterHealth = players[seed].realHealth(afterHealth);
        players[seed].currenthp = afterHealth;
        totalDamage += damage;

        if(burnCheck > 59){
            players[seed].applyAilment("burned", 4);
            display = "Ruhtra envelops " + players[seed].name + " in flames burning them for " + damage + " damage!";
        }
        else{
            display = "Ruhtra envelops " + players[seed].name + " in flames dealing " + damage + " damage!";
        }

        return display;
    }

    public String doSkill2(Hero[] players) {
        String display = "";
        int damage = 0;
        int afterHealth = 0;
        int totalDamage = 0;

        for(int i  = 0; i < 4; i++){
            if(players[i].alive){
                //first calculate the actual damage dealt and convert to real numbers
                damage = (int) (((this.atk * 0.75) - (players[i].def)) * (playerImmortal));
                damage = players[i].realDamage(damage);
                //then calculate the remaining health of the player and keep it positive
                afterHealth = players[i].currenthp - damage;
                afterHealth = players[i].realHealth(afterHealth);
                players[i].currenthp = afterHealth;
                totalDamage += damage;

                players[i].def = players[i].def - 100;
                players[i].applyDebuff("crsh", 4);
            }
        }

        display = "Ruhtra make a crushing swing at the party dealing a shared total of " + totalDamage + " damage!";
        return display;
    }

    public String doSkill3(Hero[] players) {
        String display = "";
        int damage = 0;
        int seed = 0;

        for(int i = 0; i < 3; i++){
            seed = singleAggro(players);
            //first calculate the actual damage dealt and convert to real numbers
            damage = (int) (((this.atk * 0.5) - players[seed].def) * (playerImmortal));
            damage = players[seed].realDamage(damage);
            //then calculate the remaining health of the player and keep it positive
            int afterHealth = players[seed].currenthp - damage;
            afterHealth = players[seed].realHealth(afterHealth);
            players[seed].currenthp = afterHealth;
        }

        //inaccurate damage display
        display = "Ruhtra makes several swift strikes dealing " + damage + " damage 3 times!";
        return display;
    }

    public String doSkill4(Hero[] players) {
        String display = "";

        this.def = def + 100;
        this.applyBuff("focused", 3);
        display = "Ruhtra focuses his mind...";
        return display;
    }

    public String doSkill5(Hero[] players) {
        String display = "";
        int seed = singleAggro(players);
        int damage = 0;

        //first calculate the actual damage dealt and convert to real numbers
        damage = (int) (((this.atk * 1.5) - players[seed].def) * (playerImmortal));
        damage = players[seed].realDamage(damage);
        //then calculate the remaining health of the player and keep it positive
        int afterHealth = players[seed].currenthp - damage;
        afterHealth = players[seed].realHealth(afterHealth);
        players[seed].currenthp = afterHealth;
        display = "Ruhtra makes a desperate attack on " + players[seed].name + " dealing " + damage + " damage!";
        return display;
    }

    public String doSkill6(Hero[] players) {
        String display = "";

        this.atk = atk + 100;
        this.applyBuff("sharpened", 3);
        display = "Ruhtra sharpens his blade...";
        return display;
    }

    public String defend() {
        String display = "";

        if(!alive){
            display = "Ruhtra lies there unmoving...";
        }
        else if (ailment.equals("paralyzed")){
            display = "Ruhtra is paralyzed and unable to move!";
        }

        //currently does nothing but should mitigate damage
        else{
            display = "Ruhtra takes up a defensive stance!";
        }
        return display;
    }
}

class DenMo extends Boss {

    boolean curtain;
    boolean rift;

    public DenMo() {
        //these are the stats used in the game
        hp = 50000;
        currenthp = 50000;
        atk = 500;
        matk = 500;
        def = 500;
        mdef = 500;
        spd = 500;
        curtain = false;
        rift = false;
        alive = true;
        prevAction4 = 1;
    }

    public String pattern1(int turn, Hero[] players, ManaPool mana) {
        String option = "Error: pattern1 is not working";

        //on turn 1, 6, 11, 16, 21... follow this action path
        if((turn - 1) % 5 == 0 || turn == 1){
            //randomly pick between three attacks for the first turn
            Random random = new Random();
            int seed = random.nextInt(3);
            System.out.println("I made it in here! for turn 1");
            System.out.println(seed);
            switch(seed){
                case 0:
                    this.prevAction = 0;
                    option = doSkill1(players);
                    System.out.println(option);
                    break;
                case 1:
                    this.prevAction = 1;
                    option = this.doSkill2(players);
                    System.out.println(option);
                    break;
                case 2:
                    this.prevAction = 2;
                    option = this.doSkill3(players);
                    System.out.println(option);
                    break;
            }
        }

        //on turn 2, 7, 12, 17, 22... follow this action path
        else if((turn - 2) % 5 == 0 || turn == 2){
            //randomly pick between the remaining two attacks from the previous turn's selection
            Random random = new Random();
            int seed = random.nextInt(3);

            while(prevAction == seed){
                seed = random.nextInt(3);
            }

            switch(seed){
                case 0:
                    prevAction2 = 0;
                    option = doSkill1(players);
                    break;
                case 1:
                    prevAction2 = 1;
                    option = doSkill2(players);
                    break;
                case 2:
                    prevAction2 = 2;
                    option = doSkill3(players);
                    break;
            }
        }

        //on turn 3, 8, 13, 18, 23... follow this action path
        else if((turn - 3) % 5 == 0 || turn == 3){
            //pick the attack that was not used by the previous turn's selection
            Random random = new Random();
            int seed = random.nextInt(3);

            while(prevAction == seed || prevAction2 == seed){
                seed = random.nextInt(3);
            }

            switch(seed){
                case 0:
                    option = doSkill1(players);
                    break;
                case 1:
                    option = doSkill2(players);
                    break;
                case 2:
                    option = doSkill3(players);
                    break;
            }
        }

        //on turn 4, 9, 14, 19, 24... follow this action path
        else if((turn - 4) % 5 == 0 || turn == 4){
            //pick one of two attacks, the other attack will be used the next time this path is chosen
            Random random = new Random();
            int seed = random.nextInt(2);

            while(prevAction4 == seed){
                seed = random.nextInt(2);
            }

            switch(seed){
                case 0:
                    prevAction4 = 0;
                    option = doSkill4(players);
                    break;
                case 1:
                    prevAction4 = 1;
                    option = doSkill6(players);
                    break;
            }
        }

        //on turn 5, 10, 15, 20... follow this action path
        else{
            option = doSkill5(players, mana);
        }

        return option;
    }

    public String pattern2(int turn, Hero[] players) {
        String option = "Error: pattern2 is not working";

        //on turn 1, 6, 11, 16, 21... follow this action path
        if((turn - 1) % 5 == 0 || turn == 1){
            //randomly pick between three attacks for the first turn
            Random random = new Random();
            int seed = random.nextInt(3);
            System.out.println("I made it in here! for turn 1");
            System.out.println(seed);
            switch(seed){
                case 0:
                    this.prevAction = 0;
                    option = doSkill1Alt(players);
                    System.out.println(option);
                    break;
                case 1:
                    this.prevAction = 1;
                    option = this.doSkill2Alt(players);
                    System.out.println(option);
                    break;
                case 2:
                    this.prevAction = 2;
                    option = this.doSkill3Alt(players);
                    System.out.println(option);
                    break;
            }
        }

        //on turn 2, 7, 12, 17, 22... follow this action path
        else if((turn - 2) % 5 == 0 || turn == 2){
            //randomly pick between the remaining two attacks from the previous turn's selection
            Random random = new Random();
            int seed = random.nextInt(3);

            while(prevAction == seed){
                seed = random.nextInt(3);
            }

            switch(seed){
                case 0:
                    prevAction2 = 0;
                    option = doSkill1Alt(players);
                    break;
                case 1:
                    prevAction2 = 1;
                    option = doSkill2Alt(players);
                    break;
                case 2:
                    prevAction2 = 2;
                    option = doSkill3Alt(players);
                    break;
            }
        }

        //on turn 3, 8, 13, 18, 23... follow this action path
        else if((turn - 3) % 5 == 0 || turn == 3){
            //pick the attack that was not used by the previous turn's selection
            Random random = new Random();
            int seed = random.nextInt(3);

            while(prevAction == seed || prevAction2 == seed){
                seed = random.nextInt(3);
            }

            switch(seed){
                case 0:
                    option = doSkill1Alt(players);
                    break;
                case 1:
                    option = doSkill2Alt(players);
                    break;
                case 2:
                    option = doSkill3Alt(players);
                    break;
            }
        }

        //on turn 4, 9, 14, 19, 24... follow this action path
        else if((turn - 4) % 5 == 0 || turn == 4){
            //pick one of two attacks, the other attack will be used the next time this path is chosen
            Random random = new Random();
            int seed = random.nextInt(2);

            while(prevAction4 == seed){
                seed = random.nextInt(2);
            }

            switch(seed){
                case 0:
                    prevAction4 = 0;
                    option = doSkill4Alt(players);
                    break;
                case 1:
                    prevAction4 = 1;
                    option = doSkill6Alt(players);
                    break;
            }
        }

        //on turn 5, 10, 15, 20... follow this action path
        else{
            option = doSkill5Alt(players);
        }

        return option;
    }

    public String pattern3(int turn, Hero[] players) {
        String option = "Error: pattern3 is not working";

        //select a random skill to use
        Random random = new Random();
        int seed = random.nextInt(6);

        //if an action is repeated or skill5 is selected reroll
        while(prevAction == seed || seed == 4){
            seed = random.nextInt(6);
        }

        //if the previous action was skill4 or skill6 then always use skill5
        if(prevAction == 3 || prevAction == 5){
            this.prevAction = 4;
            option = doSkill5Alt(players);

        }
        //if not then follow this path
        else{
            //do the skill that was selected and make it the previously selected skill
            switch(seed){
                case 0:
                    this.prevAction = 0;
                    option = doSkill1Alt(players);
                    break;
                case 1:
                    this.prevAction = 1;
                    option = doSkill2Alt(players);
                    break;
                case 2:
                    this.prevAction = 2;
                    option = doSkill3Alt(players);
                    break;
                case 3:
                    this.prevAction = 3;
                    option = doSkill4Alt(players);
                    break;
                case 5:
                    this.prevAction = 5;
                    option = doSkill6Alt(players);
                    break;
            }
        }

        return option;
    }

    public String strike() {return "Den Mo swipes at the party!";}

    public String doSkill1(Hero[] players) {
        String display = "";
        Random random = new Random();
        int seed = random.nextInt(2);
        int damage = 0;
        int totalDamage = 0;
        int afterHealth = 0;

        if(players[seed].alive){
            //first calculate the actual damage dealt and convert to real numbers
            damage = (int) (((this.atk) - players[seed].def) * (playerImmortal));
            damage = players[seed].realDamage(damage);
            //then calculate the remaining health of the player and keep it positive
            afterHealth = players[seed].currenthp - damage;
            afterHealth = players[seed].realHealth(afterHealth);
            players[seed].currenthp = afterHealth;
            totalDamage += damage;

        }

        if(players[seed + 2].alive){
            //first calculate the actual damage dealt and convert to real numbers
            damage = (int) (((this.atk) - players[seed + 2].def) * (playerImmortal));
            damage = players[seed + 2].realDamage(damage);
            //then calculate the remaining health of the player and keep it positive
            afterHealth = players[seed + 2].currenthp - damage;
            afterHealth = players[seed + 2].realHealth(afterHealth);
            players[seed + 2].currenthp = afterHealth;
            totalDamage += damage;
        }

        display = "Den Mo makes a desperate attack on " + players[seed].name + " and " + players[seed + 2].name + " dealing " + totalDamage + " damage!";
        return display;
    }

    public String doSkill2(Hero[] players) {
        String display = "";
        int damage = 0;
        int totalDamage = 0;
        int afterHealth = 0;
        int seed = singleAggro(players);

        //first calculate the actual damage dealt and convert to real numbers
        damage = (int) (((this.atk) - players[seed].mdef) * (playerImmortal));
        damage = players[seed].realDamage(damage);
        //then calculate the remaining health of the player and keep it positive
        afterHealth = players[seed].currenthp - damage;
        afterHealth = players[seed].realHealth(afterHealth);
        players[seed].currenthp = afterHealth;
        totalDamage += damage;


        int healing = (int) ((damage * 1.5) + currenthp);
        currenthp = healing;
        if(currenthp > hp){
            currenthp = hp;
        }

        display = "Den Mo attacked " + players[seed].name + " dealing " + damage + " and healing " + healing + " health!";
        return display;
    }

    public String doSkill3(Hero[] players) {
        String display = "";
        int damage = 0;
        int afterHealth = 0;
        int totalDamage = 0;

        for(int i  = 0; i < 4; i++){
            if(players[i].alive){
                //first calculate the actual damage dealt and convert to real numbers
                damage = (int) (((this.matk * 0.75) - (players[i].mdef)) * (playerImmortal));
                damage = players[i].realDamage(damage);
                //then calculate the remaining health of the player and keep it positive
                afterHealth = players[i].currenthp - damage;
                afterHealth = players[i].realHealth(afterHealth);
                players[i].currenthp = afterHealth;
                totalDamage += damage;
            }
        }

        display = "Den Mo exhales a cold mist dealing a shared total of " + totalDamage + " damage!";
        return display;
    }

    public String doSkill4(Hero[] players) {
        String display = "";

        for(int i = 0; i < 4; i++){
            players[i].atk = players[i].atk + 100;
            players[i].matk = players[i].matk + 100;
            players[i].spd = players[i].spd + 100;
            players[i].def = players[i].def - 100;
            players[i].mdef = players[i].mdef - 100;
        }

        this.atk = this.atk + 100;
        this.matk = this.matk + 100;
        this.spd = this.spd + 100;
        this.def = this.def - 100;
        this.mdef = this.mdef - 100;

        curtain = true;

        display = "Den Mo sets the stage for a bloodbath!";
        return display;
    }

    public String doSkill5(Hero[] players, ManaPool mana) {
        String display = "";

        int damage = 0;
        int afterHealth = 0;
        int totalDamage = 0;

        for(int i  = 0; i < 4; i++){
            if(players[i].alive){
                //first calculate the actual damage dealt and convert to real numbers
                damage = (int) (((this.matk) - (players[i].mdef)) * (playerImmortal));
                damage = players[i].realDamage(damage);
                //then calculate the remaining health of the player and keep it positive
                afterHealth = players[i].currenthp - damage;
                afterHealth = players[i].realHealth(afterHealth);
                players[i].currenthp = afterHealth;
                totalDamage += damage;
            }
        }

        mana.currentMana -= 44;

        display = "Den Mo shoots a dark ball dealing a shared total of " + totalDamage + " damage and draining 44 MP!";
        return display;
    }

    public String doSkill6(Hero[] players) {
        String display = "";

        for(int i = 0; i < 4; i++){
            players[i].atk = players[i].atk - 100;
            players[i].matk = players[i].matk - 100;
            players[i].spd = players[i].spd - 100;
            players[i].def = players[i].def + 100;
            players[i].mdef = players[i].mdef + 100;
        }

        this.atk = this.atk + 100;
        this.matk = this.matk + 100;
        this.spd = this.spd + 100;
        this.def = this.def - 100;
        this.mdef = this.mdef - 100;

        curtain = false;
        display = "Den Mo lowers the curtain!";
        return display;
    }

    //skills for Den Mo's phase 2 transformation

    public String doSkill1Alt(Hero[] players){
        String display = "";
        int damage = 0;
        int afterHealth = 0;
        int totalDamage = 0;

        for(int i  = 0; i < 4; i++){
            if(players[i].alive){
                //first calculate the actual damage dealt and convert to real numbers
                damage = (int) (((this.matk * 0.75) - (players[i].mdef)) * (playerImmortal));
                damage = players[i].realDamage(damage);
                //then calculate the remaining health of the player and keep it positive
                afterHealth = players[i].currenthp - damage;
                afterHealth = players[i].realHealth(afterHealth);
                players[i].currenthp = afterHealth;
                totalDamage += damage;

                players[i].applyAilment("poisoned", 3);
            }
        }

        display = "Den Mo calls forth dark mist poisoning the party and dealing a shared total of " + totalDamage + " damage!";
        return display;
    }

    public String doSkill2Alt(Hero[] players){
        String display = "";

        for(int i = 0; i < 4; i++){
            //placeholder until buffs/debuffs are fixed
            players[i].applyBuff("HERE", 0);
            players[i].applyBuff("IN  ", 0);
            players[i].applyBuff("ALWD", 0);
            players[i].applyBuff("BUFFS", 0);
            players[i].applyBuff("NO  ", 0);
        }

        display = "Den Mo summons forth an evil wave!";
        return display;
    }

    public String doSkill3Alt(Hero[] players){
        String display = "";
        int totalDamage = 0;
        int damage = 0;
        int afterHealth = 0;


        if(players[0].alive){
            //first calculate the actual damage dealt and convert to real numbers
            damage = (int) (((this.atk * .75) - players[0].def) * (playerImmortal));
            damage = players[0].realDamage(damage);
            //then calculate the remaining health of the player and keep it positive
            afterHealth = players[0].currenthp - damage;
            afterHealth = players[0].realHealth(afterHealth);
            players[0].currenthp = afterHealth;
            totalDamage += damage;
        }

        if(players[3].alive){
            //first calculate the actual damage dealt and convert to real numbers
            damage = (int) (((this.atk * 0.4) - players[3].def) * (playerImmortal));
            damage = players[3].realDamage(damage);
            //then calculate the remaining health of the player and keep it positive
            afterHealth = players[3].currenthp - damage;
            afterHealth = players[3].realHealth(afterHealth);
            players[3].currenthp = afterHealth;
            totalDamage += damage;
        }

        display = "Den Mo slams his fists down on " + players[0].name + " and " + players[3].name + " dealing a shared total of " + totalDamage + " damage!";
        return display;
    }

    public String doSkill4Alt(Hero[] players){
        String display = "";
        rift = true;
        this.def = def - 100;
        this.mdef = mdef - 100;

        display = "Den Mo opens up a rift in the fabric of the world!";
        return display;
    }

    public String doSkill5Alt(Hero[] players){
        String display = "";
        int damage = 0;
        int afterHealth = 0;
        int seed = 0;

        if(rift){
            for(int i = 0; i < 5; i++){
                seed = singleAggro(players);
                //first calculate the actual damage dealt and convert to real numbers
                damage = (int) (((this.atk * 0.25) - players[seed].def) * (playerImmortal));
                damage = players[seed].realDamage(damage);
                //then calculate the remaining health of the player and keep it positive
                afterHealth = players[seed].currenthp - damage;
                afterHealth = players[seed].realHealth(afterHealth);
                players[seed].currenthp = afterHealth;

            }

            this.def = def + 100;
            this.mdef = mdef + 100;
            display = "Den Mo summons a swarm of demons dealing " + damage + " damage 5 times!";
        }
        else{
            Random random = new Random();
            int deathCheck = 0;
            int totalDamage = 0;
            int deathTotal = 0;

            for(int i = 1; i < 3; i ++){
                deathCheck = random.nextInt(100);
                if(deathCheck > 89){
                    afterHealth = 0;
                    afterHealth = players[i].realHealth(afterHealth);
                    players[i].currenthp = afterHealth;
                    deathTotal ++;
                }
                //do this if the insta-kill did not trigger
                else{
                    if(players[i].alive){
                        //first calculate the actual damage dealt and convert to real numbers
                        damage = (int) (((this.atk * 0.4) - players[i].mdef) * (playerImmortal));
                        damage = players[i].realDamage(damage);
                        //then calculate the remaining health of the player and keep it positive
                        afterHealth = players[i].currenthp - damage;
                        afterHealth = players[i].realHealth(afterHealth);
                        players[i].currenthp = afterHealth;
                        totalDamage += damage;
                    }
                }

            }

            //displays a different message based on whether someone or both were insta-killed
            switch(deathTotal){
                case 0:
                    display = "Den Mo swipes at " + players[1].name + " and " + players[2].name + " dealing a shared total of " + totalDamage + " damage!";
                    break;
                case 1:
                    if(players[1].alive){
                        display = "Den Mo swipes at " + players[1].name + " dealing "+ damage + " damage and killing " + players[2].name + "!";
                    }
                    else{
                        display = "Den Mo swipes at " + players[2].name + " dealing "+ damage + " damage and killing " + players[1].name + "!";
                    }
                    break;
                case 2:
                    display = "Den Mo swipes at " + players[1].name + " and " + players[2].name + "  killing them both!";
                    break;
            }
        }

        rift = false;
        return display;
    }

    public String doSkill6Alt(Hero[] players){
        String display = "";
        this.atk = atk + 100;
        this.matk = matk + 100;

        display = "Den Mo grins menacingly at the party!";
        return display;
    }

    public String defend() {

        String display = "";

        if(!alive){
            display = "Den Mo lies there unmoving...";
        }
        else if (ailment.equals("paralyzed")){
            display = "Den Mo is paralyzed and unable to move!";
        }

        //currently does nothing but should mitigate damage
        else{
            display = "Den Mo cautiously watches the party.";
        }
        return display;
    }
}