package model.GameEvents;

import model.GameActions.GameAction;

import java.util.ArrayList;


public class OnCardPlayedEvent extends GameEvent {

    public OnCardPlayedEvent() {
        this.name = "OnCardPlayedEvent";
        this.description = "A specific card is played.";
    }

    @Override
    public void run(Object... args) {

    }
}
