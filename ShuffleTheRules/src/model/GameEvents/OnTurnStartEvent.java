package model.GameEvents;

import model.GameActions.GameAction;

import java.util.ArrayList;


public class OnTurnStartEvent extends GameEvent {

    public OnTurnStartEvent() {
        this.name = "OnTurnStartEvent";
        this.description = "The turn is starting.";
    }

    @Override
    public void run(ArrayList<GameAction> actions) {

    }
}
