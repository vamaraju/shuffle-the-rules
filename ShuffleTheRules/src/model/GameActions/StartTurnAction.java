package model.GameActions;

import java.util.ArrayList;

/**
 * Created by kirsten on 2017-03-18.
 */
public class StartTurnAction extends GameAction {

    public StartTurnAction() {
        this.name = "StartTurnAction";
        this.description = "A turn is started.";
    }

    @Override
    public void run(Object... obj) {

    }
}
