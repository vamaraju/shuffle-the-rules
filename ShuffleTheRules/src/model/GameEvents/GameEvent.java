package model.GameEvents;

import model.GameActions.GameAction;

import java.util.ArrayList;

/**
 * Created by kirsten on 2017-03-18.
 */
public interface GameEvent {

    /* TODO it complains when this isn't initialized. this may not be what we want*/
    //ArrayList<GameAction> gameActions = new ArrayList<GameAction>();

    /* actions can contain any object that implements the GameAction interface,
    *  it will accept all our our Actions which implement GameAction */
    public void run(ArrayList<GameAction> actions);

}
