/*
* Requirements mandating inclusion:
* */
package model.GameActions;

import model.Card;
import model.GameRule;
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

    public PlaceCardAction(Hand fromHand, Pile toPile, Card card) {
        this();
        this.fromHand = fromHand;
        this.toPile = toPile;
        this.card = card;
    }

    @Override
    public void run(Object... args) {

    }

    public Hand getFromHand() {
        return fromHand;
    }

    public void setFromHand(Hand fromHand) {
        this.fromHand = fromHand;
    }

    public Pile getToPile() {
        return toPile;
    }

    public void setToPile(Pile toPile) {
        this.toPile = toPile;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }
}
