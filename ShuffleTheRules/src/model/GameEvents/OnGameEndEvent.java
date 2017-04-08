package model.GameEvents;

import model.GameActions.GameAction;

import java.util.ArrayList;


public class OnGameEndEvent extends GameEvent {

    public OnGameEndEvent() {
        this.name = "OnGameEndEvent";
        this.description = "The game is ending.";
    }

    @Override
    public void run(Object... args) {

    }
}
