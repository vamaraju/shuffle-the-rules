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
    * name - unique
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



    /* Piles must have unique names */
    public Boolean uniqueNameCheck(String newPileName, ArrayList<Pile> piles){

    }
    
    public Boolean maxMinCheck(int min, int max){
        return min <= max;
    }
}
