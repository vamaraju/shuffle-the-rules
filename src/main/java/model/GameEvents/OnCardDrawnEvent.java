package model.GameEvents;

import model.Card;
import model.GameRule;


public class OnCardDrawnEvent extends GameEvent {

    private Card expectedCard;

    public OnCardDrawnEvent() {
        this.className = "OnCardDrawnEvent";
        this.description = "Check if a specific card is drawn.";
    }

    @Override
    public void run() {
    }

}
