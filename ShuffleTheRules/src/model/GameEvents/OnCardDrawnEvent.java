package model.GameEvents;

import model.Card;
import model.GameActions.GameAction;

import java.util.ArrayList;


public class OnCardDrawnEvent extends GameEvent {

    public OnCardDrawnEvent() {
        this.name = "OnCardDrawnEvent";
        this.description = "A specific card is drawn.";
    }

    @Override
    public void run(Object... args) {

    }
}
