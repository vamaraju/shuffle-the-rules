/*
* Requirements mandating inclusion:
* */
package model.GameActions;

import model.Card;
import model.GameRule;
import model.GameState;
import model.Piles.Pile;
import model.RuleInterpreter;

public class MoveCardAction extends GameAction {

    private Pile fromPile;
    private Pile toPile;
    private Card card;

    public MoveCardAction() {
        this.className = "MoveCardAction";
        this.description = "A card (or cards) is moved.";
    }

    @Override
    public void run() {
        GameState.getInstance().setCurrentRule(this);
        RuleInterpreter.launchPostRules(this);
    }

}
