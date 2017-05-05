/*
* Requirements mandating inclusion:
* */
package model.GameActions;

import model.GameRule;

public class StartTurnAction extends GameAction {

    public StartTurnAction() {
        this.className = "StartTurnAction";
        this.description = "A turn is started.";
    }

    @Override
    public void run() {

    }
}
