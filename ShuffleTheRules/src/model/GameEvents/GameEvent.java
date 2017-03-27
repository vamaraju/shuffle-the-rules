package model.GameEvents;

import model.GameActions.GameAction;

import java.util.ArrayList;


public abstract class GameEvent {

    ArrayList<GameAction> postActions = new ArrayList<GameAction>();
    ArrayList<GameEvent> postEvents = new ArrayList<GameEvent>();

    /* actions can contain any object that implements the GameAction interface,
    *  it will accept all our our Actions which implement GameAction */
    public abstract void run(ArrayList<GameAction> actions);

}
