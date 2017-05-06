/*
* Requirements mandating inclusion:
* */
package model.GameActions;

import model.GameRule;
import model.GameState;
import model.RuleInterpreter;

public class StartTurnAction extends GameAction {

    public StartTurnAction() {
        this.className = "StartTurnAction";
        this.description = "A turn is started.";
    }

    @Override
    public void run() {
        GameState.getInstance().setCurrentRule(this);
        RuleInterpreter.launchPostRules(this);
    }
}
