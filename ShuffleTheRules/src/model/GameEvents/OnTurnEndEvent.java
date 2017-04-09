package model.GameEvents;

import model.GameActions.GameAction;

import java.util.ArrayList;


public class OnTurnEndEvent extends GameEvent {

    public OnTurnEndEvent() {
        this.name = "OnTurnEndEvent";
        this.description = "The turn is ending.";
    }

    @Override
    public void run(Object... args) {

    }
}
