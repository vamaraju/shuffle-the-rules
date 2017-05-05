/*
* Requirements mandating inclusion:
* */
package model.GameActions;

import model.GameRule;
import model.Player;


public class SkipTurnAction extends GameAction {

    private Player player;

    public SkipTurnAction() {
        this.className = "SkipTurnAction";
        this.description = "A turn is skipped.";
    }


    @Override
    public void run() {
    }

}
