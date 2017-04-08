package model.GameActions;

import java.util.ArrayList;

/**
 * Created by kirsten on 2017-03-18.
 */
public class EndTurnAction extends GameAction  {

    public EndTurnAction() {
        this.name = "EndTurnAction";
        this.description = "The turn ends.";
    }

    @Override
    public void run(Object... obj) {

    }
}
