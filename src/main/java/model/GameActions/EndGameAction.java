/*
* Requirements mandating inclusion:
* */
package model.GameActions;

import model.GameRule;

public class EndGameAction extends GameAction {

    public EndGameAction() {
        this.className = "EndGameAction";
        this.description = "The game ends.";
    }

    @Override
    public void run() {
    }
}
