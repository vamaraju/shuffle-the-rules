package model.GameEvents;

import model.GameActions.GameAction;

import java.util.ArrayList;


public class OnRoundStartEvent extends GameEvent {

    public OnRoundStartEvent() {
        this.name = "OnRoundStartEvent";
        this.description = "The round is starting.";
    }

    @Override
    public void run(ArrayList<GameAction> actions) {

    }
}
