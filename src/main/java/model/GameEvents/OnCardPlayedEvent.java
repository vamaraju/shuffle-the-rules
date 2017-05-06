package model.GameEvents;

import model.Card;
import model.GameRule;


public class OnCardPlayedEvent extends GameEvent {

    private Card expectedCard;

    public OnCardPlayedEvent() {
        this.className = "OnCardPlayedEvent";
        this.description = "Check if a specific card is played.";
    }

    @Override
    public void run() {
    }

}
