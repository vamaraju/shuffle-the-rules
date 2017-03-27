package model.GameEvents;

import model.GameActions.GameAction;
import model.GameRule;

import java.util.ArrayList;


public abstract class GameEvent extends GameRule {

    public GameEvent() {
        this.name = "GameEvent";
        this.description = "A generic game event.";
    }

    /* actions can contain any object that implements the GameAction interface,
    *  it will accept all our our Actions which implement GameAction */
    public abstract void run(ArrayList<GameAction> actions);

}
