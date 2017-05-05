/*
* Requirements mandating inclusion:
* */
package model.GameActions;

import model.GameRule;

public class EndTurnAction extends GameAction  {

    public EndTurnAction() {
        this.className = "EndTurnAction";
        this.description = "The turn ends.";
    }

    @Override
    public void run() {

    }
}
