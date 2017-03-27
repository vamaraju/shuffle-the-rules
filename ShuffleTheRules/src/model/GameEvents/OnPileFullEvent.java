package model.GameEvents;

import model.GameActions.GameAction;

import java.util.ArrayList;


public class OnPileFullEvent extends GameEvent {

    public OnPileFullEvent() {
        this.name = "OnPileFullEvent";
        this.description = "A specific pile is full.";
    }

    @Override
    public void run(ArrayList<GameAction> actions) {

    }
}
