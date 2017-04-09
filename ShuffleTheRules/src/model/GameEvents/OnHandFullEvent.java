package model.GameEvents;

import model.GameActions.GameAction;

import java.util.ArrayList;


public class OnHandFullEvent extends GameEvent {

    public OnHandFullEvent() {
        this.name = "OnHandFullEvent";
        this.description = "A player's hand is full.";
    }

    @Override
    public void run(Object... args) {

    }
}
