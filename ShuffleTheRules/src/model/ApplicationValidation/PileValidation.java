package model.ApplicationValidation;


import model.Piles.Pile;

import java.util.ArrayList;

public class PileValidation {
    /*
    * ** COULD FIGURE OUT textbox validation if know CSS resource thing
    *
    * Table Tab validation
    *
    * Pile Settings
    *
    * no empty fields

    * # cards: min, max must be non-negative integers; max must be >= min
    * coordinates - must be non-negative integers; pile must not currently exist there
    *
    * Card Restrictions
    * not empty? (will need defaults and listeners)
    * card values for suits - non- negative integers
    *
    * General Game Settings
    * fields not empty
    * min, max # players must be non-negative integers; max >= min
    * player turn order?
    * */

    /* Validate a list of Piles (BasicPiles and Hands) */
    public Boolean validateMultiplePiles(ArrayList<Pile> piles){
        return false;
    }

    /* Validate a Pile (BasicPile or Hand */
    public Boolean validatePile(Pile pile){
        return false;
    }


    /* Piles must have unique names */
    public Boolean uniqueNameCheck(String newPileName, ArrayList<Pile> piles){
        return false;
    }

    /* min number of Cards in Pile must be <= max number of Cards */
    public Boolean maxMinCheck(String min, String max){
        int minInt = Integer.parseInt(min);
        int maxInt = Integer.parseInt(max);
        return minInt <= maxInt;
    }
}
