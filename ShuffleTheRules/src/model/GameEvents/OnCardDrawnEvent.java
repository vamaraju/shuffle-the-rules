package model.GameEvents;

import model.Card;
import model.GameRule;

import java.util.ArrayList;


public class OnCardDrawnEvent extends GameEvent {

    private Card expectedCard;

    public OnCardDrawnEvent() {
        this.name = "OnCardDrawnEvent";
        this.description = "A specific card is drawn.";
    }

    public OnCardDrawnEvent(Card expectedCard) {
        this();
        this.expectedCard = expectedCard;
    }

    @Override
    public void run(Object... args) {
        Card drawnCard = (Card) args[0];

        if (drawnCard.equals(expectedCard)) {
            for (int i = 1; i < args.length; i++) {
                GameRule rule = (GameRule) args[i];
                rule.run(args);
            }
        }
    }

    public Card getExpectedCard() {
        return this.expectedCard;
    }

    public void setExpectedCard(Card expectedCard) {
        this.expectedCard = expectedCard;
    }

}
