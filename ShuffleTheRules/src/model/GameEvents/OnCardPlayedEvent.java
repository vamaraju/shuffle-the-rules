package model.GameEvents;

import model.Card;
import model.GameActions.GameAction;
import model.GameRule;

import java.util.ArrayList;


public class OnCardPlayedEvent extends GameEvent {

    private Card expectedCard;

    public OnCardPlayedEvent() {
        this.name = "OnCardPlayedEvent";
        this.description = "A specific card is played.";
    }

    public OnCardPlayedEvent(Card expectedCard) {
        this();
        this.expectedCard = expectedCard;
    }

    @Override
    public void run(Object... args) {
        Card playedCard = (Card) args[0];

        if (playedCard.equals(expectedCard)) {
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
