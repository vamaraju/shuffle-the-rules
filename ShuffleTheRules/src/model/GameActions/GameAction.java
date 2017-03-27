package model.GameActions;

import model.GameEvents.GameEvent;
import model.GameRule;

import java.util.ArrayList;

public abstract class GameAction extends GameRule {

    public GameAction() {
        this.name = "GameAction";
        this.description = "A generic game action.";
    }

    /* Needs ArrayList argument */
    public abstract void run();

}
