/*
* Requirements mandating inclusion:
* */
package model.GameActions;

import model.GameRule;
import model.GameState;
import model.Player;
import model.RuleInterpreter;


public class SkipTurnAction extends GameAction {

    private Player player;

    public SkipTurnAction() {
        this.className = "SkipTurnAction";
        this.description = "A turn is skipped.";
    }


    @Override
    public void run() {
        GameState.getInstance().setCurrentRule(this);
        RuleInterpreter.launchPostRules(this);
    }

}
