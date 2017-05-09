/*
* Requirements mandating inclusion:
*
* 3.2.1.6.3.2 User can create Game Rules by selecting Game Events and adding a Game Action to that event.
* 3.2.1.3.7.2 User can view Event actions and rules.
* 3.2.1.3.7.3 User can edit Event actions and rules.
* 3.2.2.5.3.1 Rule Interpreter will interpret and execute Game Rules for a Card Game.
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
