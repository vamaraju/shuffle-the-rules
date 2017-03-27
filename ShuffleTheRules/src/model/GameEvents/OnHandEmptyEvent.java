package model.GameEvents;

import model.GameActions.GameAction;

import java.util.ArrayList;


public class OnHandEmptyEvent extends GameEvent {

    public OnHandEmptyEvent() {
        this.name = "OnHandEmptyEvent";
        this.description = "A player's hand is empty.";
    }

    @Override
    public void run(ArrayList<GameAction> actions) {

    }
}