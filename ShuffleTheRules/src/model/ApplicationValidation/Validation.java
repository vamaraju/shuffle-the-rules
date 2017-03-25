package model.ApplicationValidation;

/* Game File Master Validation
*  This class will validate information the User has entered into the Game Creation interface
*  to check for errors in game logic settings values. */
public class Validation {


    /* Master validate method.  Will run a series of checks
    *  on the logic and settings of a user's file.  If all checks
    *  pass, will return true.  If any check fails, will return false.
    *  Results of the validation will be written to a file which the user can view
    *  to make the necessary changes to their file.*/
    // TODO specify whether to write to bottom of screen or file?
    public Boolean validate(){
        return false;

    }

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
}
