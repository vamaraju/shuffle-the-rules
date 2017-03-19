package model.GameActions;

import java.util.ArrayList;

/**
 * Created by kirsten on 2017-03-18.
 */
public interface GameAction {
    /* actions can contain any object that implements the GameAction interface,
    *  it will accept all our our Actions which implement GameAction */
    public void run(ArrayList<GameAction> actions);
}
