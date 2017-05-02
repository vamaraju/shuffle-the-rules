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

    public MoveCardAction(Pile fromPile, Pile toPile, Card card) {
        this();
        this.fromPile = fromPile;
        this.toPile = toPile;
        this.card = card;
    }

    @Override
    public void run(Object... args) {
        fromPile = (args[0] != null ? (Pile) args[0] : fromPile);
        toPile = (args[1] != null ? (Pile) args[1] : toPile);
        card = (args[2] != null ? (Card) args[2] : card);

        fromPile.remove(card);
        toPile.add(card);

        for (int i = 3; i < args.length; i++) {
            GameRule rule = (GameRule) args[i];
            rule.run(args);
        }
    }

    public Pile getFromPile() {
        return fromPile;
    }

    public void setFromPile(Pile fromPile) {
        this.fromPile = fromPile;
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
