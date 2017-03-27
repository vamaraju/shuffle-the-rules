package model.GameEvents;

import model.GameActions.GameAction;

import java.util.ArrayList;


public class OnPileEmptyEvent extends GameEvent {

    public OnPileEmptyEvent() {
        this.name = "OnPileEmptyEvent";
        this.description = "A specific pile is empty.";
    }

    @Override
    public void run(ArrayList<GameAction> actions) {

    }
}
