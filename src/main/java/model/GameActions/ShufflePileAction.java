/*
* Requirements mandating inclusion:
*
* 3.2.1.6.3.2 User can create Game Rules by selecting Game Events and adding a Game Action to that event.
* 3.2.1.3.7.2 User can view Event actions and rules.
* 3.2.1.3.7.3 User can edit Event actions and rules.
* 3.2.2.5.3.1 Rule Interpreter will interpret and execute Game Rules for a Card Game.
* */
package model.GameActions;

import model.GameState;
import model.Piles.Pile;
import model.RuleInterpreter;


public class ShufflePileAction extends GameAction {

    private Pile pile;

    public ShufflePileAction() {
        this.className = "ShufflePileAction";
        this.description = "A pile is shuffled.";
    }

    @Override
    public void run() {
        GameState.getInstance().setCurrentRule(this);
        RuleInterpreter.launchPostRules(this);
    }

}
