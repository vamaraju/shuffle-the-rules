/*
* Requirements mandating inclusion:
* */
package model.GameActions;

import model.Card;
import model.GameRule;
import model.GameState;
import model.Piles.Hand;
import model.Piles.Pile;

public class PlaceCardAction extends GameAction {

    private Hand fromHand;
    private Pile toPile;
    private Card card;

    public PlaceCardAction() {
        this.className = "PlaceCardAction";
        this.description = "A card (or cards) is placed.";
    }

    @Override
    public void run() {

    }

}
