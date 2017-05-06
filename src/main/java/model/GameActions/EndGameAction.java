/*
* Requirements mandating inclusion:
* */
package model.GameActions;

import model.GameRule;
import model.GameState;
import model.RuleInterpreter;

public class EndGameAction extends GameAction {

    public EndGameAction() {
        this.className = "EndGameAction";
        this.description = "The game ends.";
    }

    @Override
    public void run() {
        GameState.getInstance().setCurrentRule(this);
        RuleInterpreter.launchPostRules(this);
    }
}
