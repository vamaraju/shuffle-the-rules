/*
* Requirements mandating inclusion:
* */
package model.GameActions;

import model.Card;
import model.GameRule;
import model.Piles.Pile;

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
    }

}
