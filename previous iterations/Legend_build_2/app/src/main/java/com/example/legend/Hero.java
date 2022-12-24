package com.example.legend;

import java.util.Random;

//default parent Hero class for all the actual classes to become children
//allows an easy format for future classes or changes to be made
public class Hero {

    //this will hold one of the four possible names
    String name;
    int hp;
    int currenthp;
    int mp;
    int atk;
    int matk;
    int def;
    int mdef;
    int spd;
    int aggro;
    boolean alive;
    boolean oSAvailable;
    boolean cover;
    boolean defendCheck;

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

    //these will hold the string reference to the skill name
    int skill1;
    int skill2;
    int skill3;
    int skill4;
    int skill5;
    int skill6;
    int skill7;

    //these will hold the string reference to the skill description
    int skill1desc;
    int skill2desc;
    int skill3desc;
    int skill4desc;
    int skill5desc;
    int skill6desc;
    int skill7desc;

    //these will hold the string reference to the oversoul and hero description
    int oversoul;
    int oversouldesc;


    public Hero(int slot) {
        //these are the stats used in the game
        //mp is on each person so it can be totalled for the shared mp bar
        name = setName(slot);
        hp = 10;
        currenthp = 10;
        mp = 100;
        atk = 100;
        matk = 100;
        def = 100;
        mdef = 100;
        spd = 100;
        aggro = 100;
        alive = true;
        oSAvailable = true;
        defendCheck = false;

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

    public Hero() {

    }

    public String setName(int slot) {
        //switch case to select the name and return it
        switch(slot){
            case 0:
                name = "H";
                break;
            case 1:
                name = "E";
                break;
            case 2:
                name = "R";
                break;
            case 3:
                name = "O";
                break;
        }
        return name;
    }

    public String getStatus() {
        String status = name + "\nHP: " + this.currenthp + "/" + this.hp;
        return status;
    }

    public String getDisplay() {
        //String display = " Name: " + name + "\n Class: Hero" + "\n\n ATK: " + this.atk + "\n DEF: " + this.def + "\n MATK: " + this.matk + "\n MDEF: " + this.mdef + "\n SPD: " + this.spd;
        String display = " Name: " + name + "\n Class: Hero" + "\n\n " + this.buffSlot1 + ": " + this.buffSlot1Counter + " \t" + this.debuffSlot1 + ": " + this.debuffSlot1Counter +
                "\n " + this.buffSlot2 + ": " + this.buffSlot2Counter + " \t" + this.debuffSlot2 + ": " + this.debuffSlot2Counter +
                "\n " + this.buffSlot3 + ": " + this.buffSlot3Counter + " \t" + this.debuffSlot3 + ": " + this.debuffSlot3Counter +
                "\n " + this.buffSlot4 + ": " + this.buffSlot4Counter + " \t" + this.debuffSlot4 + ": " + this.debuffSlot4Counter +
                "\n " + this.buffSlot5 + ": " + this.buffSlot5Counter + " \t" + this.debuffSlot5 + ": " + this.debuffSlot5Counter +
                "\n " + this.ailment + ": " + this.ailmentCounter;
        return display;
    }

    public int realDamage(int damage) {
        if(damage < 0){
            damage = 0;
        }

        return damage;
    }

    public int realHealth(int currentHealth) {
        if(currentHealth <= 0){
            currentHealth = 0;
            //need to have it check with confirmation to skip its buttons
            alive = false;
            applyAilment("healthy", 0);
            //removes all debuffs and buffs when dead
            String[] death = {"H", "T", "E", "A", "D"};
            for(int i = 0; i < 5; i++){
                applyBuff(death[i], 0);
                applyDebuff(death[i], 0);
            }

            buffSlot1 = "none";
            buffSlot2 = "none";
            buffSlot3 = "none";
            buffSlot4 = "none";
            buffSlot5 = "none";

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
        //Emperor buffs
        //removes the Lead the Charge buff
        switch (buffName) {
            case "taunt":
                System.out.println("taunt was removed");
                break;

            //removes the Hold the Line buff
            case "hold":
                this.def -= 100;
                System.out.println(this.name + " This is not normal");
                break;

            //removes the Reinforcements are Coming buff
            case "reinforce":
                this.mdef -= 100;
                break;

            //removes the Stand United buff
            case "united":
                this.atk += 100;
                this.def -= 200;
                break;

            //Saint buffs
            //removes the Divine Intervention buff
            case "avoid":
                System.out.println("avoid was removed");
                break;

            //removes the Fate Rewritten buff
            case "fate":
                System.out.println("fate was removed");
                break;

            //removes the Blessing buff
            case "blessed":
                System.out.println("blessing was removed");
                break;

            //removes the Consecration buff
            case "consecrated":
                System.out.println("consecration was removed");
                break;

            //Enchantress buffs
            //removes the Strength VIII buff
            case "strength":
                this.atk -= 100;
                this.matk -= 100;
                break;

            //removes The Tower XVI buff
            case "tower":
                this.def -= 100;
                this.mdef -= 100;
                break;

            //removes The Chariot VII buff
            case "chariot":
                this.spd -= 100;
                break;

            //Pirate buffs
            //removes the Strength VIII buff
            case "seadog":
                this.atk -= 200;
                this.def += 100;
                break;
        }
    }

    public void applyDebuff(String debuffName, int debuffTimer) {
        //if a debuff would be repeated instead reset its timer
        boolean duplicate = false;
        String[] debuffSlots = {debuffSlot1, debuffSlot2, debuffSlot3, debuffSlot4, debuffSlot5};
        int[] debuffTimers = {debuffSlot1Counter, debuffSlot2Counter, debuffSlot3Counter, debuffSlot4Counter, debuffSlot5Counter};
        for(int i = 0; i < 5; i++){
            if(debuffSlots[i].equals(debuffName)){
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

    public void removeDebuff(String debuffName) {
        //Miss Lead debuffs
        //removes the guilt debuff
        if(debuffName.equals("guilt")){
            this.atk += 100;
            this.matk += 100;
        }

        //Ruhtra debuffs
        //removes the crushed debuff
        if(debuffName.equals("crushed")){
            this.def += 100;
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

        if(defendCheck){
            this.def = (int) (def / 1.5);
            this.mdef = (int) (mdef / 1.5);
            defendCheck = false;
        }

        //this loop is used for buff skills being checked
        for(int i = 0; i < 5; i++){
        //BEGINNING OF EMPEROR AVAILABLE BUFFS
            //if the buffSlot carries Lead the Charge
            switch (buffSlots[i]) {
                case "taunt":
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
                    }
                    break;
                //if the buffSlot carries Hold the Line
                case "hold":
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
                //if the buffSlot carries Reinforcements are Coming
                case "reinforce":
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
                        mdef = mdef - 100;
                    }
                    break;
                //if the buffSlot carries Stand United
                case "united":
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
                        def = def - 200;
                        mdef = mdef - 200;
                        atk = atk + 200;
                        matk = matk + 200;
                    }
                    break;
                //END OF EMPEROR AVAILABLE BUFFS
                //BEGINNING OF SAINT AVAILABLE BUFFS
                //if the buffSlot carries Divine Intervention
                case "avoid":
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
                        System.out.println("Avoid has run out");
                    }
                    break;
                //if the buffSlot carries Fate Rewritten
                case "fate":
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
                        System.out.println("Fate has run out");
                    }
                    break;
                //if the buffSlot carries Blessing
                case "blessed":
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
                    }
                    currenthp = currenthp + (hp /10);
                    if(currenthp > hp){
                        currenthp = hp;
                    }
                    break;
                //if the buffSlot carries Consecration
                case "consecrated":
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
                    }
                    currenthp = currenthp + (hp / 8);
                    if(currenthp > hp){
                        currenthp = hp;
                    }
                    break;
                //END OF SAINT AVAILABLE BUFFS
                //BEGINNING OF ENCHANTRESS AVAILABLE BUFFS
                //if the buffSlot carries Strength VIII
                case "strength":
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
                        matk = matk - 100;
                    }
                    break;
                //if the buffSlot carries The Tower XVI
                case "tower":
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
                        mdef = mdef - 100;
                    }
                    break;
                //if the buffSlot carries The Chariot VII
                case "chariot":
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
                        spd = spd - 100;
                    }
                    break;
                //END OF ENCHANTRESS AVAILABLE BUFFS
                //BEGINNING OF PIRATE AVAILABLE BUFFS
                //if the buffSlot carries Sea Dog's Soul
                case "seadog":
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
                        def = def + 100;
                        atk = atk - 200;
                    }
                    break;
                //if the buffSlot carries SKILL NAME
                case "placeholder":
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
                        System.out.println("placeholder");
                    }
                    break;
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
            switch (debuffSlots[i]) {
                //BEGINNING OF SHAE D AVAILABLE DEBUFFS

                //END OF SHAE D AVAILABLE DEBUFFS
                //BEGINNING OF MISS LEAD AVAILABLE DEBUFFS
                //if the debuffSlot carries Skill 2
                case "guilt":
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
                //END OF MISS LEAD AVAILABLE DEBUFFS
                //BEGINNING OF RUHTRA AVAILABLE DEBUFFS
                //if the debuffSlot carries Skill 2
                case "crushed":
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
                //END OF RUHTRA AVAILABLE DEBUFFS
                //BEGINNING OF DEN MO AVAILABLE DEBUFFS

                //END OF DEN MO AVAILABLE DEBUFFS
            }
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

    //methods to do the attacks and damage calculations
    //for now they produce strings to see if they work properly
    //these strings will be displayed on toasts
    public String strike(Boss[] foes, int bossSelected) {
        String display = "";

        //may add critical hits and misses to the chances

        //first calculate the actual damage dealt and convert to real numbers
        int damage = (this.atk * 2) - foes[bossSelected].def;
        damage = realDamage(damage);
        //then calculate the remaining health of the opponent and keep it positive
        int afterHealth = foes[bossSelected].currenthp - damage;
        afterHealth = foes[bossSelected].realHealth(afterHealth);
        foes[bossSelected].currenthp = afterHealth;

        if(cover){
            def = def - 200;
            mdef = mdef - 200;
            cover = false;
        }

        //return this to display on the toast with the actual damage so the user can see
        display = name + " attacks for " + damage + " damage!";

        return display;
    }

    public String doSkill1(Hero[] players, Boss[] foes, int bossSelected, ManaPool mana) {return "Skill 1 Activate!";}
    public String doSkill2(Hero[] players, Boss[] foes, int bossSelected, ManaPool mana) {return "Skill 2 Activate!";}
    public String doSkill3(Hero[] players, Boss[] foes, int bossSelected, ManaPool mana) {return "Skill 3 Activate!";}
    public String doSkill4(Hero[] players, Boss[] foes, int bossSelected, ManaPool mana) {return "Skill 4 Activate!";}
    public String doSkill5(Hero[] players, Boss[] foes, int bossSelected, ManaPool mana) {return "Skill 5 Activate!";}
    public String doSkill6(Hero[] players, Boss[] foes, int bossSelected, ManaPool mana) {return "Skill 6 Activate!";}
    public String doSkill7(Hero[] players, Boss[] foes, int bossSelected, ManaPool mana) {return "Skill 7 Activate!";}
    public String doOversoul(Hero[] players, Boss[] foes, int bossSelected, ManaPool mana) {return "Oversoul Activate!";}

    public String defend() {
        String display = "";

        if(!alive){
            display = name + " lies there unmoving...";
        }
        else if (ailment.equals("paralyzed")){
            display = name + " is paralyzed and unable to move!";
        }

        //currently does nothing but should mitigate damage
        else{
            display = name + " defends!";
            this.def = (int) (def * 1.5);
            this.mdef = (int) (def * 1.5);

            defendCheck = true;
        }
        return display;
    }

}

class Emperor extends Hero {

    public Emperor(){
        super();
    }

    public Emperor(int slot) {
        name = setName(slot);
        hp = 500;
        currenthp = 500;
        mp = 100;
        atk = 300;
        matk = 100;
        def = 500;
        mdef = 400;
        spd = 100;
        aggro = 100;
        cover = false;
        oSAvailable = true;
        alive = true;
        defendCheck = false;

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

        skill1 = R.string.empSkill1;
        skill2 = R.string.empSkill2;
        skill3 = R.string.empSkill3;
        skill4 = R.string.empSkill4;
        skill5 = R.string.empSkill5;
        skill6 = R.string.empSkill6;
        skill7 = R.string.empSkill7;
        oversoul = R.string.empOversoul;
        skill1desc = R.string.empSkill1Desc;
        skill2desc = R.string.empSkill2Desc;
        skill3desc = R.string.empSkill3Desc;
        skill4desc = R.string.empSkill4Desc;
        skill5desc = R.string.empSkill5Desc;
        skill6desc = R.string.empSkill6Desc;
        skill7desc = R.string.empSkill7Desc;
        oversouldesc = R.string.empOversoulDesc;
    }

    public String setName(int slot) {
        //switch case to select the name and return it
        switch(slot){
            case 0:
                name = "Galahad";
                break;
            case 1:
                name = "Percival";
                break;
            case 2:
                name = "Aglovale";
                break;
            case 3:
                name = "Tristan";
                break;
        }
        return name;
    }

    public String getDisplay() {
        //String display = " Name: " + name + "\n Class: Emperor" + "\n\n ATK: " + this.atk + "\n DEF: " + this.def + "\n MATK: " + this.matk + "\n MDEF: " + this.mdef + "\n SPD: " + this.spd;
        String display = " Name: " + name + "\n Class: Emperor" + "\n\n " + "Buffs \t | \t Debuffs \n " + this.buffSlot1 + ": " + this.buffSlot1Counter + " \t | \t " + this.debuffSlot1 + ": " + this.debuffSlot1Counter +
                "\n " + this.buffSlot2 + ": " + this.buffSlot2Counter + " \t | \t " + this.debuffSlot2 + ": " + this.debuffSlot2Counter +
                "\n " + this.buffSlot3 + ": " + this.buffSlot3Counter + " \t | \t " + this.debuffSlot3 + ": " + this.debuffSlot3Counter +
                "\n " + this.buffSlot4 + ": " + this.buffSlot4Counter + " \t | \t " + this.debuffSlot4 + ": " + this.debuffSlot4Counter +
                "\n " + this.buffSlot5 + ": " + this.buffSlot5Counter + " \t | \t " + this.debuffSlot5 + ": " + this.debuffSlot5Counter +
                "\n " + "Status \n " + this.ailment + ": " + this.ailmentCounter;
        return display;
    }

    public String doSkill1(Hero[] players, Boss[] foes, int bossSelected, ManaPool mana) {
        String display = "";

        if(mana.currentMana < 7){
            display = name + " ran out of energy!";
            return display;
        }

        display = name + " takes the hit for the party!";
        cover = true;
        def = def + 200;
        mdef = mdef + 200;
        mana.currentMana -= 7;

        return display;
    }

    public String doSkill2(Hero[] players, Boss[] foes, int bossSelected, ManaPool mana) {
        String display = "";
        cover = false;
        def = def - 200;
        mdef = mdef - 200;

        if(mana.currentMana < 12){
            display = name + " ran out of energy!";
            return display;
        }

        //first calculate the actual damage dealt and convert to real numbers
        int damage = (this.atk * 3) - foes[bossSelected].def;
        damage = realDamage(damage);
        //then calculate the remaining health of the opponent and keep it positive
        int afterHealth = foes[bossSelected].currenthp - damage;
        afterHealth = foes[bossSelected].realHealth(afterHealth);
        foes[bossSelected].currenthp = afterHealth;

        display = name + " makes a provoking strike dealing " + damage + " damage!";
        mana.currentMana -= 12;
        applyBuff("taunt", 6);

        return display;
    }

    public String doSkill3(Hero[] players, Boss[] foes, int bossSelected, ManaPool mana) {
        String display = name + " commands the party to take a defensive formation!";
        cover = false;
        def = def - 200;
        mdef = mdef - 200;

        if(mana.currentMana < 15){
            display = name + " ran out of energy!";
            return display;
        }

        //calculations for increasing def for all allies
        for(int i = 0; i < 4; i++){
            players[i].def = players[i].def + 100;
            players[i].applyBuff("hold", 4);
        }

        mana.currentMana -= 15;
        return display;
    }

    public String doSkill4(Hero[] players, Boss[] foes, int bossSelected, ManaPool mana) {
        String display = "";
        cover = false;
        def = def - 200;
        mdef = mdef - 200;

        if(mana.currentMana < 15){
            display = name + " ran out of energy!";
            return display;
        }

        //calculations for increasing def for all allies
        for(int i = 0; i < 4; i++){
            players[i].def = players[i].def + 100;
            players[i].applyBuff("reinforce", 4);
        }

        display = name + " ensures the party of incoming reinforcements!";
        mana.currentMana -= 15;
        return display;
    }

    public String doSkill5(Hero[] players, Boss[] foes, int bossSelected, ManaPool mana) {
        String display = "";
        cover = false;
        def = def - 200;
        mdef = mdef - 200;

        if(mana.currentMana < 30){
            display = name + " ran out of energy!";
            return display;
        }

        if(ailment.equals("healthy")){
            display = name + " failed to summon his courage!";
        }
        else{
            //25% chance to actually apply the ailment to the boss
            //it will always clear the Emperor though
            Random random = new Random();
            int seed = random.nextInt(100);
            if(seed > 74){
                foes[bossSelected].applyAilment(ailment, 4);
                ailment = "healthy";
                ailmentCounter = 0;

                display = name + " lets out a battle cry that shakes the sky!";
            }
            else{
                ailment = "healthy";
                ailmentCounter = 0;

                display = name + " lets out a battle cry that frees his body!";
            }

        }

        mana.currentMana -= 30;
        return display;
    }

    public String doSkill6(Hero[] players, Boss[] foes, int bossSelected, ManaPool mana) {
        String display = "";
        cover = false;
        def = def - 200;
        mdef = mdef - 200;

        if(mana.currentMana < 7){
            display = name + " ran out of energy!";
            return display;
        }

        //first calculate the actual damage dealt and convert to real numbers
        int damage = (this.atk * 4) - foes[bossSelected].def;
        damage = realDamage(damage);
        //then calculate the remaining health of the opponent and keep it positive
        int afterHealth = foes[bossSelected].currenthp - damage;
        afterHealth = foes[bossSelected].realHealth(afterHealth);
        foes[bossSelected].currenthp = afterHealth;

        //lastly calculate the recoil damage
        int recoil = (damage / 10) + 25;
        afterHealth = this.currenthp - recoil;
        afterHealth = foes[bossSelected].realHealth(afterHealth);
        this.currenthp = afterHealth;

        display = name + " swings recklessly dealing " + damage + " damage but taking " +  recoil + " damage!";
        mana.currentMana -= 7;
        return display;
    }

    public String doSkill7(Hero[] players, Boss[] foes, int bossSelected, ManaPool mana) {
        String display = name + " unites the party under one banner!";
        cover = false;
        def = def - 200;
        mdef = mdef - 200;

        if(mana.currentMana < 19){
            display = name + " ran out of energy!";
            return display;
        }

        //calculations for increasing def for all allies while dropping their atk
        for(int i = 0; i < 4; i++){
            players[i].def = players[i].def + 200;
            players[i].mdef = players[i].mdef + 200;
            players[i].atk = players[i].atk - 200;
            players[i].matk = players[i].matk - 200;
            players[i].applyBuff("united", 4);
        }

        mana.currentMana -=19;
        return display;
    }

    public String doOversoul(Hero[] players, Boss[] foes, int bossSelected, ManaPool mana) {
        String display = "";
        if(oSAvailable){
            display = name + " rallies the party with words of honor!";
            foes[bossSelected].playerImmortal = 0;
            cover = false;
            def = def - 200;
            mdef = mdef - 200;
            oSAvailable = false;
        }

        else{
            display = name + " could not be heard!";
            cover = false;
            def = def - 200;
            mdef = mdef - 200;
        }

        return display;
    }
}

class Saint extends Hero {

    boolean sacred;
    int sacredCounter;

    public Saint(){
        super();
    }

    public Saint(int slot) {
        name = setName(slot);
        hp = 400;
        currenthp = 400;
        mp = 300;
        atk = 400;
        matk = 300;
        def = 300;
        mdef = 500;
        spd = 300;
        aggro = 10;
        sacred = false;
        oSAvailable = true;
        sacredCounter = 0;
        cover = false;
        alive = true;
        defendCheck = false;

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

        skill1 = R.string.saiSkill1;
        skill2 = R.string.saiSkill2;
        skill3 = R.string.saiSkill3;
        skill4 = R.string.saiSkill4;
        skill5 = R.string.saiSkill5;
        skill6 = R.string.saiSkill6;
        skill7 = R.string.saiSkill7;
        oversoul = R.string.saiOversoul;
        skill1desc = R.string.saiSkill1Desc;
        skill2desc = R.string.saiSkill2Desc;
        skill3desc = R.string.saiSkill3Desc;
        skill4desc = R.string.saiSkill4Desc;
        skill5desc = R.string.saiSkill5Desc;
        skill6desc = R.string.saiSkill6Desc;
        skill7desc = R.string.saiSkill7Desc;
        oversouldesc = R.string.saiOversoulDesc;
    }

    public String setName(int slot) {
        //switch case to select the name and return it
        switch(slot){
            case 0:
                name = "Holly";
                break;
            case 1:
                name = "Jeanne";
                break;
            case 2:
                name = "Mary";
                break;
            case 3:
                name = "Noelle";
                break;
        }
        return name;
    }

    public String getDisplay() {
        //String display = " Name: " + name + "\n Class: Saint" + "\n\n ATK: " + this.atk + "\n DEF: " + this.def + "\n MATK: " + this.matk + "\n MDEF: " + this.mdef + "\n SPD: " + this.spd + "\nSacred: " + this.sacredCounter + " Actions Left";
        String display = " Name: " + name + "\n Class: Saint" + "\n\n " + "Buffs \t | \t Debuffs \n " + this.buffSlot1 + ": " + this.buffSlot1Counter + " \t | \t " + this.debuffSlot1 + ": " + this.debuffSlot1Counter +
                "\n " + this.buffSlot2 + ": " + this.buffSlot2Counter + " \t | \t " + this.debuffSlot2 + ": " + this.debuffSlot2Counter +
                "\n " + this.buffSlot3 + ": " + this.buffSlot3Counter + " \t | \t " + this.debuffSlot3 + ": " + this.debuffSlot3Counter +
                "\n " + this.buffSlot4 + ": " + this.buffSlot4Counter + " \t | \t " + this.debuffSlot4 + ": " + this.debuffSlot4Counter +
                "\n " + this.buffSlot5 + ": " + this.buffSlot5Counter + " \t | \t " + this.debuffSlot5 + ": " + this.debuffSlot5Counter +
                "\n " + "Status \n " + this.ailment + ": " + this.ailmentCounter +
                "\n " + "Sacred: " + this.sacredCounter + " Actions Left";
        return display;
    }

    public void sacredMoves() {
        if(sacred){
            //make the move text switch to match sacred moves
            skill1 = R.string.saiSkill1Alt;
            skill2 = R.string.saiSkill2Alt;
            skill3 = R.string.saiSkill3Alt;
            skill4 = R.string.saiSkill4Alt;
            skill5 = R.string.saiSkill5Alt;
            skill6 = R.string.saiSkill6Alt;
            skill7 = R.string.saiSkill7Alt;
            oversoul = R.string.saiOversoulAlt;
            skill1desc = R.string.saiSkill1DescAlt;
            skill2desc = R.string.saiSkill2DescAlt;
            skill3desc = R.string.saiSkill3DescAlt;
            skill4desc = R.string.saiSkill4DescAlt;
            skill5desc = R.string.saiSkill5DescAlt;
            skill6desc = R.string.saiSkill6DescAlt;
            skill7desc = R.string.saiSkill7DescAlt;
            oversouldesc = R.string.saiOversoulDescAlt;
        }
        else{
            //make the move text switch to match normal moves
            skill1 = R.string.saiSkill1;
            skill2 = R.string.saiSkill2;
            skill3 = R.string.saiSkill3;
            skill4 = R.string.saiSkill4;
            skill5 = R.string.saiSkill5;
            skill6 = R.string.saiSkill6;
            skill7 = R.string.saiSkill7;
            oversoul = R.string.saiOversoul;
            skill1desc = R.string.saiSkill1Desc;
            skill2desc = R.string.saiSkill2Desc;
            skill3desc = R.string.saiSkill3Desc;
            skill4desc = R.string.saiSkill4Desc;
            skill5desc = R.string.saiSkill5Desc;
            skill6desc = R.string.saiSkill6Desc;
            skill7desc = R.string.saiSkill7Desc;
            oversouldesc = R.string.saiOversoulDesc;
        }
    }

    public String doSkill1(Hero[] players, Boss[] foes, int bossSelected, ManaPool mana) {
        String display = "";

        if(mana.currentMana < 7){
            display = name + " ran out of energy!";
            return display;
        }

        //pick the effect depending on whether or not it is enhanced
        if(sacred){

            //first calculate the actual damage dealt and convert to real numbers
            int damage = ((this.atk / 2) + (this.matk * 3)) - foes[bossSelected].def;
            damage = realDamage(damage);
            //then calculate the remaining health of the opponent and keep it positive
            int afterHealth = foes[bossSelected].currenthp - damage;
            afterHealth = foes[bossSelected].realHealth(afterHealth);
            foes[bossSelected].currenthp = afterHealth;

            //lastly input the drained health into the currenthp
            this.currenthp = this.currenthp + damage;
            if(this.currenthp > this.hp){
                this.currenthp = hp;
            }

            display = name + " enacts divine fury for " + damage + " damage!";
        }

        else{

            //first calculate the actual damage dealt and convert to real numbers
            int damage = ((this.atk / 2) + (this.matk * 2)) - foes[bossSelected].def;
            damage = realDamage(damage);
            //then calculate the remaining health of the opponent and keep it positive
            int afterHealth = foes[bossSelected].currenthp - damage;
            afterHealth = foes[bossSelected].realHealth(afterHealth);
            foes[bossSelected].currenthp = afterHealth;

            //lastly input the drained health into the currenthp
            this.currenthp = this.currenthp + damage;
            if(this.currenthp > this.hp){
                this.currenthp = hp;
            }

            display = name + " smites the enemy for " + damage + " damage!";
        }

        //decrements sacredCounter to make sure it only lasts for 4 actions
        if(sacredCounter > 0){
            sacredCounter --;
            if(sacredCounter == 0){
                sacred = false;
                sacredMoves();
            }
        }

        mana.currentMana -= 7;
        return display;
    }

    public String doSkill2(Hero[] players, Boss[] foes, int bossSelected, ManaPool mana) {
        String display = "";
        //pick the effect depending on whether or not it is enhanced
        if(sacred){
            //calculate how much healing is being done
            int healFactor = currenthp - (currenthp / 20);
            this.currenthp = healFactor;
            int healing = (healFactor * 2) + 50 - (mdef / 2);

            // this is a measure to prevent the skill from healing negative health
            if(healing < 75) {
                healing = 75;
            }

            //give each member the required healing
            for(int i = 0; i < 4; i++){
                if(players[i].alive){
                    players[i].currenthp = players[i].currenthp + healing;
                    if(players[i].currenthp > players[i].hp){
                        players[i].currenthp = players[i].hp;
                    }
                }
            }
            //exclude the saint for using the skill
            this.currenthp = currenthp - healing;
            if(this.currenthp > this.hp){
                this.currenthp = hp;
            }

            display = name + " shares her vitality to restore " + healing + " health!";
        }

        else{
            int healFactor = currenthp - (currenthp / 30);
            this.currenthp = healFactor;
            int healing = (healFactor * 2) + 50 - (mdef);

            // this is a measure to prevent the skill from healing negative health
            if(healing < 75) {
                healing = 75;
            }

            for(int i = 0; i < 4; i++){
                if(players[i].alive){
                    players[i].currenthp += healing;
                    if(players[i].currenthp > players[i].hp){
                        players[i].currenthp = players[i].hp;
                    }
                }
            }
            //exclude the saint for using the skill
            this.currenthp = healFactor;
            if(this.currenthp > this.hp){
                this.currenthp = hp;
            }

            display = name + " sacrifices her vitality to share " + healing + " health!";
        }

        //decrements sacredCounter to make sure it only lasts for 4 actions
        if(sacredCounter > 0){
            sacredCounter --;
            if(sacredCounter == 0){
                sacred = false;
                sacredMoves();
            }
        }

        return display;
    }

    public String doSkill3(Hero[] players, Boss[] foes, int bossSelected, ManaPool mana) {
        String display = "";

        if(mana.currentMana < 28){
            display = name + " ran out of energy!";
            return display;
        }

        //pick the effect depending on whether or not it is enhanced
        if(sacred){
            for(int i = 0; i < 4; i++){
                players[i].alive = true;
                if(players[i].currenthp == 0){
                    players[i].currenthp = players[i].hp;
                }
            }
            display = name + " sprays the water of life!";
        }

        else{
            for(int i = 0; i < 4; i++){
                players[i].alive = true;
                if(players[i].currenthp == 0){
                    players[i].currenthp = 50;
                }
            }
            display = name + " splashes the water of life!";
        }

        //decrements sacredCounter to make sure it only lasts for 4 actions
        if(sacredCounter > 0){
            sacredCounter --;
            if(sacredCounter == 0){
                sacred = false;
                sacredMoves();
            }
        }

        mana.currentMana -= 28;
        return display;
    }

    //probably do this in response to the boss's aggro targeting
    public String doSkill4(Hero[] players, Boss[] foes, int bossSelected, ManaPool mana) {
        String display = "";

        if(mana.currentMana < 14){
            display = name + " ran out of energy!";
            return display;
        }

        //pick the effect depending on whether or not it is enhanced
        if(sacred){
            //will give every one the fate status
            for(int i = 0; i < 4; i++){
                players[i].applyBuff("fate", 4);
            }
            display = name + " rewrites fate!";
        }

        else{
            //will give everyone the avoid status
            for(int i = 0; i < 4; i++){
                players[i].applyBuff("avoid", 4);
            }
            display = name + " calls upon a protective spirit!";
        }

        //decrements sacredCounter to make sure it only lasts for 4 actions
        if(sacredCounter > 0){
            sacredCounter --;
            if(sacredCounter == 0){
                sacred = false;
                sacredMoves();
            }
        }

        mana.currentMana -= 14;
        return display;
    }

    public String doSkill5(Hero[] players, Boss[] foes, int bossSelected, ManaPool mana) {
        String display = "";

        if(mana.currentMana < 7){
            display = name + " ran out of energy!";
            return display;
        }

        //pick the effect depending on whether or not it is enhanced
        if(sacred){
            for(int i = 0; i < 4; i++){
                players[i].applyBuff("consecrated", 4);
            }
            display = name + " consecrates the party!";
        }

        else{
            for(int i = 0; i < 4; i++){
                players[i].applyBuff("blessed", 4);
            }
            display = name + " blessed the party!";
        }

        //decrements sacredCounter to make sure it only lasts for 4 actions
        if(sacredCounter > 0){
            sacredCounter --;
            if(sacredCounter == 0){
                sacred = false;
                sacredMoves();
            }
        }

        mana.currentMana -= 7;
        return display;
    }

    public String doSkill6(Hero[] players, Boss[] foes, int bossSelected, ManaPool mana) {
        String display = "";

        if(mana.currentMana < 14){
            display = name + " ran out of energy!";
            return display;
        }

        //pick the effect depending on whether or not it is enhanced
        if(sacred){
            for(int i = 0; i < 4; i++){
                players[i].applyDebuff("none", 0);
                players[i].applyDebuff("none", 0);
            }
            display = name + " rings the Sacred Chimes for all to hear!";
        }

        else{
            for(int i = 0; i < 4; i++){
                players[i].applyDebuff("none", 0);
            }
            display = name + " sounds the Blessed Bells for all to hear!";
        }

        //decrements sacredCounter to make sure it only lasts for 4 actions
        if(sacredCounter > 0){
            sacredCounter --;
            if(sacredCounter == 0){
                sacred = false;
                sacredMoves();
            }
        }

        mana.currentMana -= 14;
        return display;
    }

    public String doSkill7(Hero[] players, Boss[] foes, int bossSelected, ManaPool mana) {
        String display = "";
        //pick the effect depending on whether or not it is enhanced
        if(sacred){
            //sacrifice 40% of current health
            int offering = (int) (currenthp * .6);
            int restore = (int) (offering * 1.5);

            currenthp = offering;

            if(this.currenthp > this.hp){
                this.currenthp = hp;
            }

            // add the mana into the shared pool
            mana.currentMana += restore;
            if(mana.currentMana > mana.totalMana){
                mana.currentMana = mana.totalMana;
            }

            display = name + " prays for a miracle granting " + restore + " MP!";
        }

        else{
            //sacrifice 60% of current health
            int offering = (int) (currenthp * .4);
            int restore = (int) (offering * 1.5);

            currenthp = offering;

            if(this.currenthp > this.hp){
                this.currenthp = hp;
            }

            // add the mana into the shared pool
            mana.currentMana += restore;
            if(mana.currentMana > mana.totalMana){
                mana.currentMana = mana.totalMana;
            }

            display = name + " prays for blessing granting " + restore + " MP!";
        }

        //decrements sacredCounter to make sure it only lasts for 4 actions
        if(sacredCounter > 0){
            sacredCounter --;
            if(sacredCounter == 0){
                sacred = false;
                sacredMoves();
            }
        }

        return display;
    }

    public String doOversoul(Hero[] players, Boss[] foes, int bossSelected, ManaPool mana) {
        String display = "";

        if(oSAvailable){
            display = name + " offers her body as vessel to the heavens!";
            sacred = true;
            oSAvailable = false;
            sacredMoves();
            sacredCounter = 3;
        }

        else{
            display = name + " could not connect to the spirits!";
        }


        return display;
    }
}

class Enchantress extends Hero {

    String cardPosition;
    boolean divine;
    int divineCounter;

    public Enchantress(){
        super();
    }

    public Enchantress(int slot) {
        name = setName(slot);
        hp = 100;
        currenthp = 100;
        mp = 400;
        atk = 200;
        matk = 400;
        def = 100;
        mdef = 300;
        spd = 500;
        aggro = 20;
        cardPosition = setCardPosition();
        divine = false;
        oSAvailable = true;
        divineCounter = 0;
        cover = false;
        alive = true;
        defendCheck = false;

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

        skill1 = R.string.encSkill1;
        skill2 = R.string.encSkill2;
        skill3 = R.string.encSkill3;
        skill4 = R.string.encSkill4;
        skill5 = R.string.encSkill5;
        skill6 = R.string.encSkill6;
        skill7 = R.string.encSkill7;
        oversoul = R.string.encOversoul;
        skill1desc = R.string.encSkill1Desc;
        skill2desc = R.string.encSkill2Desc;
        skill3desc = R.string.encSkill3Desc;
        skill4desc = R.string.encSkill4Desc;
        skill5desc = R.string.encSkill5Desc;
        skill6desc = R.string.encSkill6Desc;
        skill7desc = R.string.encSkill7Desc;
        oversouldesc = R.string.encOversoulDesc;
    }

    public String setName(int slot) {
        //switch case to select the name and return it
        switch(slot){
            case 0:
                name = "Sephira";
                break;
            case 1:
                name = "Hestia";
                break;
            case 2:
                name = "Medea";
                break;
            case 3:
                name = "Ishtar";
                break;
        }
        return name;
    }

    public String setCardPosition() {
        // randomly decide whether the next card is upright or reversed
        Random random = new Random();
        String face = "";
        if(divine == true){
            face = "Divine";
        }
        else{
            int seed = random.nextInt(2);
            switch(seed){
                case 0:
                    face = "Upright";
                    break;
                case 1:
                    face = "Reversed";
            }
        }

        return face;
    }

    public String getDisplay() {
        //String display = " Name: " + name + "\n Class: Enchantress" + "\n\n ATK: " + this.atk + "\n DEF: " + this.def + "\n MATK: " + this.matk + "\n MDEF: " + this.mdef + "\n SPD: " + this.spd + "\nDivine: " + this.divineCounter + " Actions Left";
        String display = " Name: " + name + "\n Class: Enchantress" + "\n\n " + "Buffs \t | \t Debuffs \n " + this.buffSlot1 + ": " + this.buffSlot1Counter + " \t | \t " + this.debuffSlot1 + ": " + this.debuffSlot1Counter +
                "\n " + this.buffSlot2 + ": " + this.buffSlot2Counter + " \t | \t " + this.debuffSlot2 + ": " + this.debuffSlot2Counter +
                "\n " + this.buffSlot3 + ": " + this.buffSlot3Counter + " \t | \t " + this.debuffSlot3 + ": " + this.debuffSlot3Counter +
                "\n " + this.buffSlot4 + ": " + this.buffSlot4Counter + " \t | \t " + this.debuffSlot4 + ": " + this.debuffSlot4Counter +
                "\n " + this.buffSlot5 + ": " + this.buffSlot5Counter + " \t | \t " + this.debuffSlot5 + ": " + this.debuffSlot5Counter +
                "\n " + "Status \n " + this.ailment + ": " + this.ailmentCounter +
                "\n " + "Divine: " + this.divineCounter + " Actions Left";
        return display;
    }

    public String doSkill1(Hero[] players, Boss[] foes, int bossSelected, ManaPool mana) {
        String display = "";

        if(mana.currentMana < 20){
            display = name + " ran out of energy!";
            return display;
        }

        if(cardPosition.equals("Upright")){
            for(int i = 0; i < 4; i++){
                players[i].atk = players[i].atk + 100;
                players[i].matk = players[i].matk + 100;
                players[i].applyBuff("strength", 5);
            }
        }

        else if(cardPosition.equals("Reversed")){
            foes[bossSelected].atk = foes[bossSelected].atk - 100;
            foes[bossSelected].matk = foes[bossSelected].matk - 100;
        }

        else{
            //do both of the Upright and Reversed actions
            for(int i = 0; i < 4; i++){
                players[i].atk = players[i].atk + 100;
                players[i].matk = players[i].matk + 100;
                players[i].applyBuff("strength", 5);
            }

            foes[bossSelected].atk = foes[bossSelected].atk - 100;
            foes[bossSelected].matk = foes[bossSelected].matk - 100;
        }

        display = name + " reveals Strength VIII in the " + cardPosition + " position!";

        //decrements divineCounter to make sure it only lasts for 4 actions
        if(divineCounter > 0){
            divineCounter --;
            if(divineCounter == 0){
                divine = false;
            }
        }

        cardPosition = setCardPosition();
        mana.currentMana -= 20;
        return display;
    }

    public String doSkill2(Hero[] players, Boss[] foes, int bossSelected, ManaPool mana) {
        String display = "";

        if(mana.currentMana < 20){
            display = name + " ran out of energy!";
            return display;
        }

        if(cardPosition.equals("Upright")){
            for(int i = 0; i < 4; i++){
                players[i].def = players[i].def + 100;
                players[i].mdef = players[i].mdef + 100;
                players[i].applyBuff("tower", 5);
            }
        }

        else if(cardPosition.equals("Reversed")){
            foes[bossSelected].def = foes[bossSelected].def - 100;
            foes[bossSelected].mdef = foes[bossSelected].def - 100;
        }

        else{
            //do both of the Upright and Reversed actions
            for(int i = 0; i < 4; i++){
                players[i].def = players[i].def + 100;
                players[i].def = players[i].mdef + 100;
                players[i].applyBuff("tower", 5);
            }

            foes[bossSelected].def = foes[bossSelected].def - 100;
            foes[bossSelected].def = foes[bossSelected].mdef - 100;
        }

        display = name + " reveals The Tower XVI in the " + cardPosition + " position!";

        //decrements divineCounter to make sure it only lasts for 4 actions
        if(divineCounter > 0){
            divineCounter --;
            if(divineCounter == 0){
                divine = false;
            }
        }

        cardPosition = setCardPosition();
        mana.currentMana -= 20;
        return display;
    }

    public String doSkill3(Hero[] players, Boss[] foes, int bossSelected, ManaPool mana) {
        String display = "";

        if(mana.currentMana < 20){
            display = name + " ran out of energy!";
            return display;
        }

        if(cardPosition.equals("Upright")){
            for(int i = 0; i < 4; i++){
                players[i].spd = players[i].spd + 100;
                players[i].applyBuff("chariot", 5);
            }
        }

        else if(cardPosition.equals("Reversed")){
            foes[bossSelected].spd = foes[bossSelected].spd - 100;
        }

        else{
            //do both of the Upright and Reversed actions
            for(int i = 0; i < 4; i++){
                players[i].spd = players[i].spd + 100;
                players[i].applyBuff("chariot", 5);
            }

            foes[bossSelected].spd = foes[bossSelected].spd - 100;
        }

        display = name + " reveals The Chariot VII in the " + cardPosition + " position!";

        //decrements divineCounter to make sure it only lasts for 4 actions
        if(divineCounter > 0){
            divineCounter --;
            if(divineCounter == 0){
                divine = false;
            }
        }

        cardPosition = setCardPosition();
        mana.currentMana -= 20;
        return display;
    }

    public String doSkill4(Hero[] players, Boss[] foes, int bossSelected, ManaPool mana) {
        String display = "";

        if(mana.currentMana < 50){
            display = name + " ran out of energy!";
            return display;
        }

        if(cardPosition.equals("Upright")){
            Random random = new Random();
            int seed = random.nextInt(100);
            //10% chance of paralysis
            if(seed > 89){
                //para
                foes[bossSelected].applyAilment("paralyzed", 4);
            }
            //20% chance of dread
            else if(seed > 69){
                //dread
                foes[bossSelected].applyAilment("dread", 4);
            }
            //20% chance of dread
            else if(seed > 34){
                //poison
                foes[bossSelected].applyAilment("poisoned", 4);
            }
            else{
                //burn
                foes[bossSelected].applyAilment("burned", 4);
            }
        }

        else if(cardPosition.equals("Reversed")){
            Random random = new Random();
            int seed = random.nextInt(100);
            //10% chance of paralysis
            if(seed > 89){
                //para
                for(int i = 0; i < 4; i++){
                    players[i].applyAilment("paralyzed", 4);
                }
            }
            //20% chance of dread
            else if(seed > 69){
                //dread
                for(int i = 0; i < 4; i++){
                    players[i].applyAilment("dread", 4);
                }
            }
            //20% chance of dread
            else if(seed > 34){
                //poison
                for(int i = 0; i < 4; i++){
                    players[i].applyAilment("poisoned", 4);
                }
            }
            else{
                //burn
                for(int i = 0; i < 4; i++){
                    players[i].applyAilment("burned", 4);
                }
            }


        }

        display = name + " reveals Death XII in the " + cardPosition + " position!";

        //decrements divineCounter to make sure it only lasts for 4 actions
        if(divineCounter > 0){
            divineCounter --;
            if(divineCounter == 0){
                divine = false;
            }
        }

        cardPosition = setCardPosition();
        mana.currentMana -= 50;
        return display;
    }

    public String doSkill5(Hero[] players, Boss[] foes, int bossSelected, ManaPool mana) {
        String display = "";

        if(mana.currentMana < 5){
            display = name + " ran out of energy!";
            return display;
        }

        display = name + " activated The Fool O as CARD NAME in the " + cardPosition + " position!";

        Random random = new Random();
        String filler = "";
        int seed = random.nextInt(4);
        switch(seed){
            case 0:
                filler = doSkill1(players, foes, bossSelected, mana);
                display = name + " revealed The Fool O as Strength VIII in the " + cardPosition + " position!";
                break;
            case 1:
                filler = doSkill2(players, foes, bossSelected, mana);
                display = name + " revealed The Fool O as The Tower XVI in the " + cardPosition + " position!";
                break;
            case 2:
                filler = doSkill3(players, foes, bossSelected, mana);
                display = name + " revealed The Fool O as The Chariot VII in the " + cardPosition + " position!";
                break;
            case 3:
                filler = doSkill4(players, foes, bossSelected, mana);
                display = name + " revealed The Fool O as Death XIII in the " + cardPosition + " position!";
                break;
        }

        //decrements divineCounter to make sure it only lasts for 4 actions
        if(divineCounter > 0){
            divineCounter --;
            if(divineCounter == 0){
                divine = false;
            }
        }

        cardPosition = setCardPosition();
        mana.currentMana -= 5;
        return display;
    }

    public String doSkill6(Hero[] players, Boss[] foes, int bossSelected, ManaPool mana) {
        // apply burned to enemy with high chance
        String display = "";

        if(mana.currentMana < 13){
            display = name + " ran out of energy!";
            return display;
        }

        Random random = new Random();
        int seed = random.nextInt(100);
        if(seed < 70){
            foes[bossSelected].applyAilment("burned", 4);
            display = name + "\'s ritual has burned the enemy!";
        }
        else{
            display = name + " failed to complete the ritual!";
        }

        mana.currentMana -= 13;
        return display;
    }

    public String doSkill7(Hero[] players, Boss[] foes, int bossSelected, ManaPool mana) {
        //String display = name + " performs a sacred dance ritual!";
        String display = "";

        if(mana.currentMana < 10){
            display = name + " ran out of energy!";
            return display;
        }

        if(cardPosition.equals("Upright")){
            //calculate how much healing is being done
            int healFactor = currenthp - (currenthp / 20);
            int healing = (healFactor * 2) + 30 - (mdef / 2);

            // this is a measure to prevent the skill from healing negative health
            if(healing < 25) {
                healing = 25;
            }

            //give each member the required healing
            for(int i = 0; i < 4; i++){
                if(players[i].alive){
                    players[i].currenthp = currenthp + healing;
                    if(players[i].currenthp > players[i].hp){
                        players[i].currenthp = hp;
                    }
                }
            }

            display = name + " performs a sacred dance restoring " + healing + " HP!";
            cardPosition = "Upright";
        }

        else if(cardPosition.equals("Reversed")){
            //first calculate the actual damage dealt and convert to real numbers
            int damage = (this.matk * 2) - foes[bossSelected].def;
            damage = realDamage(damage);
            //then calculate the remaining health of the opponent and keep it positive
            int afterHealth = foes[bossSelected].currenthp - damage;
            afterHealth = foes[bossSelected].realHealth(afterHealth);
            foes[bossSelected].currenthp = afterHealth;

            display = name + " performs a sacred dance dealing " + damage + " damage!";
            cardPosition = "Reversed";
        }

        else{
            //do both of the Upright and Reversed actions
            int healFactor = currenthp - (currenthp / 20);
            int healing = (healFactor * 2) + 30 - (mdef / 2);

            // this is a measure to prevent the skill from healing negative health
            if(healing < 25) {
                healing = 25;
            }

            //give each member the required healing
            for(int i = 0; i < 4; i++){
                if(players[i].alive){
                    players[i].currenthp = currenthp + healing;
                    if(players[i].currenthp > players[i].hp){
                        players[i].currenthp = hp;
                    }
                }
            }

            //first calculate the actual damage dealt and convert to real numbers
            int damage = (this.matk * 2) - foes[bossSelected].def;
            damage = realDamage(damage);
            //then calculate the remaining health of the opponent and keep it positive
            int afterHealth = foes[bossSelected].currenthp - damage;
            afterHealth = foes[bossSelected].realHealth(afterHealth);
            foes[bossSelected].currenthp = afterHealth;

            display = name + " dances dealing" + damage + " damage and restoring " +  healing + " HP!";
        }

        mana.currentMana -= 10;
        return display;
    }

    public String doOversoul(Hero[] players, Boss[] foes, int bossSelected, ManaPool mana) {
        String display = name + " lets the stars guide her actions!";

        if(oSAvailable){
            display = name + " lets the stars guide her actions!";
            divine = true;
            oSAvailable = false;
            divineCounter = 4;
            cardPosition = setCardPosition();
        }

        else{
            display = name + " could not read the stars!";
        }

        return display;
    }
}

class Sage extends Hero {

    int tier1;
    int tier2;
    int tier3;

    public Sage(){
        super();
    }

    public Sage(int slot) {
        name = setName(slot);
        hp = 200;
        currenthp = 200;
        mp = 500;
        atk = 100;
        matk = 500;
        def = 200;
        mdef = 200;
        spd = 200;
        aggro = 40;
        tier1 = 0;
        tier2 = 0;
        tier3 = 0;
        cover = false;
        alive = true;
        defendCheck = false;

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

        skill1 = R.string.sagSkill1;
        skill2 = R.string.sagSkill2;
        skill3 = R.string.sagSkill3;
        skill4 = R.string.sagSkill4;
        skill5 = R.string.sagSkill5;
        skill6 = R.string.sagSkill6;
        skill7 = R.string.sagSkill7;
        oversoul = R.string.sagOversoul;
        skill1desc = R.string.sagSkill1Desc;
        skill2desc = R.string.sagSkill2Desc;
        skill3desc = R.string.sagSkill3Desc;
        skill4desc = R.string.sagSkill4Desc;
        skill5desc = R.string.sagSkill5Desc;
        skill6desc = R.string.sagSkill6Desc;
        skill7desc = R.string.sagSkill7Desc;
        oversouldesc = R.string.sagOversoulDesc;
    }

    public String setName(int slot) {
        //switch case to select the name and return it
        switch(slot){
            case 0:
                name = "Gelid";
                break;
            case 1:
                name = "Ignis";
                break;
            case 2:
                name = "Gaia";
                break;
            case 3:
                name = "Zephyr";
                break;
        }
        return name;
    }

    public String getDisplay() {
        //String display = " Name: " + name + "\n Class: Sage" + "\n\n ATK: " + this.atk + "\n DEF: " + this.def + "\n MATK: " + this.matk + "\n MDEF: " + this.mdef + "\n SPD: " + this.spd + "\n T1: " + this.tier1 + " T2: " + this.tier2 + " T3: " + this.tier3;
        String display = " Name: " + name + "\n Class: Sage" + "\n\n " + "Buffs \t | \t Debuffs \n " + this.buffSlot1 + ": " + this.buffSlot1Counter + " \t | \t " + this.debuffSlot1 + ": " + this.debuffSlot1Counter +
                "\n " + this.buffSlot2 + ": " + this.buffSlot2Counter + " \t | \t " + this.debuffSlot2 + ": " + this.debuffSlot2Counter +
                "\n " + this.buffSlot3 + ": " + this.buffSlot3Counter + " \t | \t " + this.debuffSlot3 + ": " + this.debuffSlot3Counter +
                "\n " + this.buffSlot4 + ": " + this.buffSlot4Counter + " \t | \t " + this.debuffSlot4 + ": " + this.debuffSlot4Counter +
                "\n " + this.buffSlot5 + ": " + this.buffSlot5Counter + " \t | \t " + this.debuffSlot5 + ": " + this.debuffSlot5Counter +
                "\n " + "Status \n " + this.ailment + ": " + this.ailmentCounter +
                "\n " + "T1: " + this.tier1 + " T2: " + this.tier2 + " T3: " + this.tier3;
        return display;
    }

    public String doSkill1(Hero[] players, Boss[] foes, int bossSelected, ManaPool mana) {
        String display = "";

        if(mana.currentMana < 15){
            display = name + " ran out of energy!";
            return display;
        }

        //first calculate the actual damage dealt and convert to real numbers
        int damage = (this.matk * 2) - foes[bossSelected].def;
        damage = realDamage(damage);
        //then calculate the remaining health of the opponent and keep it positive
        int afterHealth = foes[bossSelected].currenthp - damage;
        afterHealth = foes[bossSelected].realHealth(afterHealth);
        foes[bossSelected].currenthp = afterHealth;

        tier1 ++;
        display = name + " casts Eruption dealing " + damage + " damage!";
        mana.currentMana -= 15;
        return display;
    }

    public String doSkill2(Hero[] players, Boss[] foes, int bossSelected, ManaPool mana) {
        String display = "";

        if(mana.currentMana < 15){
            display = name + " ran out of energy!";
            return display;
        }

        //first calculate the actual damage dealt and convert to real numbers
        int damage = (this.matk * 2) - foes[bossSelected].def;
        damage = realDamage(damage);
        //then calculate the remaining health of the opponent and keep it positive
        int afterHealth = foes[bossSelected].currenthp - damage;
        afterHealth = foes[bossSelected].realHealth(afterHealth);
        foes[bossSelected].currenthp = afterHealth;

        display = name + " casts Blizzard dealing " + damage + " damage!";
        tier1 ++;
        mana.currentMana -= 15;
        return display;
    }

    public String doSkill3(Hero[] players, Boss[] foes, int bossSelected, ManaPool mana) {
        String display = "";

        if(mana.currentMana < 15){
            display = name + " ran out of energy!";
            return display;
        }

        //first calculate the actual damage dealt and convert to real numbers
        int damage = (this.matk * 2) - foes[bossSelected].def;
        damage = realDamage(damage);
        //then calculate the remaining health of the opponent and keep it positive
        int afterHealth = foes[bossSelected].currenthp - damage;
        afterHealth = foes[bossSelected].realHealth(afterHealth);
        foes[bossSelected].currenthp = afterHealth;

        display = name + " casts Tornado dealing " + damage + " damage!";
        tier1 ++;
        mana.currentMana -= 15;
        return display;
    }

    public String doSkill4(Hero[] players, Boss[] foes, int bossSelected, ManaPool mana) {
        String display = "";

        if(mana.currentMana < 15){
            display = name + " ran out of energy!";
            return display;
        }

        //first calculate the actual damage dealt and convert to real numbers
        int damage = (this.matk * 2) - foes[bossSelected].def;
        damage = realDamage(damage);
        //then calculate the remaining health of the opponent and keep it positive
        int afterHealth = foes[bossSelected].currenthp - damage;
        afterHealth = foes[bossSelected].realHealth(afterHealth);
        foes[bossSelected].currenthp = afterHealth;

        display = name + " casts Earthquake dealing " + damage + " damage!";
        tier1 ++;
        mana.currentMana -= 15;
        return display;
    }

    public String doSkill5(Hero[] players, Boss[] foes, int bossSelected, ManaPool mana) {
        String display = "";

        if(mana.currentMana < 45){
            display = name + " ran out of energy!";
            return display;
        }

        if(tier1 < 2){
            display = name + " fails to internalize the magic!";
        }

        else{

            //first calculate the actual damage dealt and convert to real numbers
            int damage = (this.matk * 3) - foes[bossSelected].def;
            damage = realDamage(damage);
            //then calculate the remaining health of the opponent and keep it positive
            int afterHealth = foes[bossSelected].currenthp - damage;
            afterHealth = foes[bossSelected].realHealth(afterHealth);
            foes[bossSelected].currenthp = afterHealth;

            display = name + " conjures Light Tempest dealing " + damage + " damage!";
            tier1 -= 2;
            tier1 ++;
            tier2 ++;
            mana.currentMana -= 45;
        }

        return display;
    }

    public String doSkill6(Hero[] players, Boss[] foes, int bossSelected, ManaPool mana) {
        String display = "";

        if(mana.currentMana < 45){
            display = name + " ran out of energy!";
            return display;
        }

        if(tier1 < 2){
            display = name + " fails to internalize the magic!";
        }

        else{

            //first calculate the actual damage dealt and convert to real numbers
            int damage = (this.matk * 3) - foes[bossSelected].def;
            damage = realDamage(damage);
            //then calculate the remaining health of the opponent and keep it positive
            int afterHealth = foes[bossSelected].currenthp - damage;
            afterHealth = foes[bossSelected].realHealth(afterHealth);
            foes[bossSelected].currenthp = afterHealth;

            display = name + " conjures Dark Tsunami dealing " + damage + " damage!";
            tier1 -= 2;
            tier1 ++;
            tier2 ++;
            mana.currentMana -= 45;
        }

        return display;
    }

    public String doSkill7(Hero[] players, Boss[] foes, int bossSelected, ManaPool mana) {
        String display = "";

        if(mana.currentMana < 75){
            display = name + " ran out of energy!";
            return display;
        }

        if(tier2 < 2){
            display = name + " fails to internalize the magic!";
        }
        else{

            //first calculate the actual damage dealt and convert to real numbers
            int damage = (this.matk * 4) - foes[bossSelected].def;
            damage = realDamage(damage);
            //then calculate the remaining health of the opponent and keep it positive
            int afterHealth = foes[bossSelected].currenthp - damage;
            afterHealth = foes[bossSelected].realHealth(afterHealth);
            foes[bossSelected].currenthp = afterHealth;

            display = name + " creates an Aether Catastrophe dealing " + damage + " damage!";
            tier2 -= 2;
            tier3 ++;
            mana.currentMana -= 75;
        }

        return display;
    }

    public String doOversoul(Hero[] players, Boss[] foes, int bossSelected, ManaPool mana) {
        String display = "";

        if(mana.currentMana < 100){
            display = name + " ran out of energy!";
            return display;
        }

        if(tier3 < 1){
            display = name + " fails to internalize the magic!";
        }
        else{

            //first calculate the actual damage dealt and convert to real numbers
            int damage = (this.matk * 5) - foes[bossSelected].def;
            damage = realDamage(damage);
            //then calculate the remaining health of the opponent and keep it positive
            int afterHealth = foes[bossSelected].currenthp - damage;
            afterHealth = foes[bossSelected].realHealth(afterHealth);
            foes[bossSelected].currenthp = afterHealth;

            display = name + " starts the Apocalypse dealing " + damage + " damage!";
            tier3 --;
            mana.currentMana -= 100;
        }

        return display;
    }
}

class Pirate extends Hero {

    public Pirate(){
        super();
    }

    public Pirate(int slot) {
        name = setName(slot);
        hp = 300;
        currenthp = 300;
        mp = 200;
        atk = 500;
        matk = 200;
        def = 400;
        mdef = 100;
        spd = 400;
        aggro = 60;
        oSAvailable = true;
        cover = false;
        alive = true;
        defendCheck = false;

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

        skill1 = R.string.pirSkill1;
        skill2 = R.string.pirSkill2;
        skill3 = R.string.pirSkill3;
        skill4 = R.string.pirSkill4;
        skill5 = R.string.pirSkill5;
        skill6 = R.string.pirSkill6;
        skill7 = R.string.pirSkill7;
        oversoul = R.string.pirOversoul;
        skill1desc = R.string.pirSkill1Desc;
        skill2desc = R.string.pirSkill2Desc;
        skill3desc = R.string.pirSkill3Desc;
        skill4desc = R.string.pirSkill4Desc;
        skill5desc = R.string.pirSkill5Desc;
        skill6desc = R.string.pirSkill6Desc;
        skill7desc = R.string.pirSkill7Desc;
        oversouldesc = R.string.pirOversoulDesc;
    }

    public String setName(int slot) {
        //switch case to select the name and return it
        switch(slot){
            case 0:
                name = "Regina";
                break;
            case 1:
                name = "Titania";
                break;
            case 2:
                name = "Minerva";
                break;
            case 3:
                name = "Katarina";
                break;
        }
        return name;
    }

    public String getDisplay() {
        //String display = " Name: " + name + "\n Class: Pirate" + "\n\n ATK: " + this.atk + "\n DEF: " + this.def + "\n MATK: " + this.matk + "\n MDEF: " + this.mdef + "\n SPD: " + this.spd;
        String display = " Name: " + name + "\n Class: Saint" + "\n\n " + "Buffs \t | \t Debuffs \n " + this.buffSlot1 + ": " + this.buffSlot1Counter + " \t | \t " + this.debuffSlot1 + ": " + this.debuffSlot1Counter +
                "\n " + this.buffSlot2 + ": " + this.buffSlot2Counter + " \t | \t " + this.debuffSlot2 + ": " + this.debuffSlot2Counter +
                "\n " + this.buffSlot3 + ": " + this.buffSlot3Counter + " \t | \t " + this.debuffSlot3 + ": " + this.debuffSlot3Counter +
                "\n " + this.buffSlot4 + ": " + this.buffSlot4Counter + " \t | \t " + this.debuffSlot4 + ": " + this.debuffSlot4Counter +
                "\n " + this.buffSlot5 + ": " + this.buffSlot5Counter + " \t | \t " + this.debuffSlot5 + ": " + this.debuffSlot5Counter +
                "\n " + "Status \n " + this.ailment + ": " + this.ailmentCounter;
        return display;
    }

    public String doSkill1(Hero[] players, Boss[] foes, int bossSelected, ManaPool mana) {
        String display = "";

        if(mana.currentMana < 4){
            display = name + " ran out of energy!";
            return display;
        }

        //first calculate the actual damage dealt and convert to real numbers
        int damage = (this.atk + (this.atk / 4)) - foes[bossSelected].def;
        damage = realDamage(damage);
        //then calculate the remaining health of the opponent and keep it positive
        int afterHealth = foes[bossSelected].currenthp - damage;
        afterHealth = foes[bossSelected].realHealth(afterHealth);
        foes[bossSelected].currenthp = afterHealth;

        //then apply the status to the boss
        foes[bossSelected].applyAilment("poisoned", 6);

        display = name + " fires a coated bullet dealing " + damage + " damage!";

        mana.currentMana -= 4;
        return display;
    }

    public String doSkill2(Hero[] players, Boss[] foes, int bossSelected, ManaPool mana) {
        String display = "";

        if(mana.currentMana < 5){
            display = name + " ran out of energy!";
            return display;
        }

        //first calculate the actual damage dealt and convert to real numbers
        int damage = (this.atk + (this.atk / 4)) - foes[bossSelected].def;
        damage = realDamage(damage);
        //then calculate the remaining health of the opponent and keep it positive
        int afterHealth = foes[bossSelected].currenthp - damage;
        afterHealth = foes[bossSelected].realHealth(afterHealth);
        foes[bossSelected].currenthp = afterHealth;

        //then apply the status to the boss
        foes[bossSelected].def = foes[bossSelected].def - 100;
        foes[bossSelected].applyDebuff("cheap shot", 4);

        display = name + " shoots at a blindspot dealing " + damage + " damage!";
        mana.currentMana -= 5;
        return display;
    }

    public String doSkill3(Hero[] players, Boss[] foes, int bossSelected, ManaPool mana) {
        String display = "";

        if(mana.currentMana < 13){
            display = name + " ran out of energy!";
            return display;
        }

        //both ailment and debuff
        if(!foes[bossSelected].debuffSlot1.equals("none") && !foes[bossSelected].ailment.equals("healthy")){
            //first calculate the actual damage dealt and convert to real numbers
            int damage = (this.atk * 4) - foes[bossSelected].def;
            damage = realDamage(damage);
            //then calculate the remaining health of the opponent and keep it positive
            int afterHealth = foes[bossSelected].currenthp - damage;
            afterHealth = foes[bossSelected].realHealth(afterHealth);
            foes[bossSelected].currenthp = afterHealth;

            display = name + " makes a devastating strike dealing " + damage + " damage!";
        }

        //ailment or debuff
        else if(!foes[bossSelected].debuffSlot1.equals("none") || !foes[bossSelected].ailment.equals("healthy")){
            //first calculate the actual damage dealt and convert to real numbers
            int damage = (this.atk * 2) - foes[bossSelected].def;
            damage = realDamage(damage);
            //then calculate the remaining health of the opponent and keep it positive
            int afterHealth = foes[bossSelected].currenthp - damage;
            afterHealth = foes[bossSelected].realHealth(afterHealth);
            foes[bossSelected].currenthp = afterHealth;

            display = name + " makes a powerful strike dealing " + damage + " damage!";
        }

        //neither
        else{
            //first calculate the actual damage dealt and convert to real numbers
            int damage = this.atk - foes[bossSelected].def;
            damage = realDamage(damage);
            //then calculate the remaining health of the opponent and keep it positive
            int afterHealth = foes[bossSelected].currenthp - damage;
            afterHealth = foes[bossSelected].realHealth(afterHealth);
            foes[bossSelected].currenthp = afterHealth;

            display = name + " makes an average strike dealing " + damage + " damage!";
        }

        mana.currentMana -= 13;
        return display;
    }

    public String doSkill4(Hero[] players, Boss[] foes, int bossSelected, ManaPool mana) {
        String display = "";

        if(mana.currentMana < 20){
            display = name + " ran out of energy!";
            return display;
        }

        Random random = new Random();
        int seed = random.nextInt(2);
        switch(seed){
            case 0:

                //clears the buffs on the boss by returning its stats to normal
                foes[bossSelected].applyBuff("C", 0);
                foes[bossSelected].applyBuff("L", 0);
                foes[bossSelected].applyBuff("E", 0);
                foes[bossSelected].applyBuff("A", 0);
                foes[bossSelected].applyBuff("R", 0);

                foes[bossSelected].buffSlot1 = "none";
                foes[bossSelected].buffSlot2 = "none";
                foes[bossSelected].buffSlot3 = "none";
                foes[bossSelected].buffSlot4 = "none";
                foes[bossSelected].buffSlot5 = "none";

                display = name + " summons a gentle tailwind!";
                break;
            case 1:

                display = name + " failed to control the winds!";
                break;
        }

        mana.currentMana -= 20;
        return display;
    }

    public String doSkill5(Hero[] players, Boss[] foes, int bossSelected, ManaPool mana) {
        String display = name + " starts to get motivated!";

        if(mana.currentMana < 12){
            display = name + " ran out of energy!";
            return display;
        }

        applyBuff("seadog", 6);
        this.def = def - 100;
        this.atk = atk + 200;

        mana.currentMana -= 12;
        return display;
    }

    public String doSkill6(Hero[] players, Boss[] foes, int bossSelected, ManaPool mana) {
        String display = "";

        if(mana.currentMana < 7){
            display = name + " ran out of energy!";
            return display;
        }

        //first calculate the actual damage dealt and convert to real numbers
        int damage = (this.atk + (this.atk / 4)) - foes[bossSelected].def;
        damage = realDamage(damage);
        //then calculate the remaining health of the opponent and keep it positive
        int afterHealth = foes[bossSelected].currenthp - damage;
        afterHealth = foes[bossSelected].realHealth(afterHealth);
        foes[bossSelected].currenthp = afterHealth;

        //then apply the status to the boss
        Random random = new Random();
        int seed = random.nextInt(100);
        if(seed > 69){
            foes[bossSelected].applyAilment("paralyzed", 3);
            display = name + " fires a paralyzing bullet dealing " + damage + " damage!";
        }
        else{
            display = name + " fires a bouncing bullet dealing " + damage + " damage!";
        }

        mana.currentMana -= 7;
        return display;
    }

    public String doSkill7(Hero[] players, Boss[] foes, int bossSelected, ManaPool mana) {
        String display = "";

        if(mana.currentMana < 19){
            display = name + " ran out of energy!";
            return display;
        }

        //if element resistances are added then this is water/ice
        //also it would be composite damage (hit weakness if either is a weakness)

        //first calculate the actual damage dealt and convert to real numbers
        int damage = (this.atk * 3) - foes[bossSelected].def;
        damage = realDamage(damage);
        //then calculate the remaining health of the opponent and keep it positive
        int afterHealth = foes[bossSelected].currenthp - damage;
        afterHealth = foes[bossSelected].realHealth(afterHealth);
        foes[bossSelected].currenthp = afterHealth;

        display = name + " takes a swing strong enough to part the sea dealing " + damage + " damage!";

        mana.currentMana -= 19;
        return display;
    }

    public String doOversoul(Hero[] players, Boss[] foes, int bossSelected, ManaPool mana) {
        String display = "";

        if(oSAvailable){
            oSAvailable = false;
            int totalDamage = 0;

            //the initial strike
            int damage = (this.atk * 2) - foes[bossSelected].def;
            damage = realDamage(damage);
            //then calculate the remaining health of the opponent and keep it positive
            int afterHealth = foes[bossSelected].currenthp - damage;
            afterHealth = foes[bossSelected].realHealth(afterHealth);
            foes[bossSelected].currenthp = afterHealth;
            totalDamage += damage;

            //check all buffs that arent none|0 and add a to a counter for each
            //at the same time it needs to remove those buffs
            int buffCounter = 0;
            String[] buffs = {buffSlot1, buffSlot2, buffSlot3, buffSlot4, buffSlot5};
            int[] buffsC = {buffSlot1Counter, buffSlot2Counter, buffSlot3Counter, buffSlot4Counter, buffSlot5Counter};
            for(int i = 0; i < 5; i++){
                if(!buffs[i].equals("none")){
                    buffCounter++;
                    buffs[i] = "none";
                    buffsC[i] = 0;
                }
            }

            for(int i = 0; i < buffCounter; i++){
                if(!foes[bossSelected].debuffSlot1.equals("none") && !foes[bossSelected].ailment.equals("healthy")){
                    //first calculate the actual damage dealt and convert to real numbers
                    damage = (this.atk * 4) - foes[bossSelected].def;
                    damage = realDamage(damage);
                    //then calculate the remaining health of the opponent and keep it positive
                    afterHealth = foes[bossSelected].currenthp - damage;
                    afterHealth = foes[bossSelected].realHealth(afterHealth);
                    foes[bossSelected].currenthp = afterHealth;

                    totalDamage += damage;
                }

                else if(!foes[bossSelected].debuffSlot1.equals("none") || !foes[bossSelected].ailment.equals("healthy")){
                    //first calculate the actual damage dealt and convert to real numbers
                    damage = (this.atk * 2) - foes[bossSelected].def;
                    damage = realDamage(damage);
                    //then calculate the remaining health of the opponent and keep it positive
                    afterHealth = foes[bossSelected].currenthp - damage;
                    afterHealth = foes[bossSelected].realHealth(afterHealth);
                    foes[bossSelected].currenthp = afterHealth;

                    totalDamage += damage;
                }

                else{
                    //first calculate the actual damage dealt and convert to real numbers
                    damage = this.atk - foes[bossSelected].def;
                    damage = realDamage(damage);
                    //then calculate the remaining health of the opponent and keep it positive
                    afterHealth = foes[bossSelected].currenthp - damage;
                    afterHealth = foes[bossSelected].realHealth(afterHealth);
                    foes[bossSelected].currenthp = afterHealth;

                    totalDamage += damage;
                }
            }

            String[] pirate = {"HAA", "HA", "GRA", "HAR", "YAR"};
            for(int i = 0; i < 5; i++){
                applyBuff(pirate[i], 0);
            }

            display = name + " bursts into a fit of rage dealing " + totalDamage + " damage!";
        }

        else{
            display = name + " could not tap into her latent rage!";
        }

        return display;
    }
}