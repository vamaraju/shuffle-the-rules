package model.GameActions;

import model.GameEvents.GameEvent;

import java.util.ArrayList;

public abstract class GameAction {

    ArrayList<GameAction> postActions = new ArrayList<GameAction>();
    ArrayList<GameEvent> postEvents = new ArrayList<GameEvent>();

    /* Needs ArrayList argument */
    public abstract void run();
}
