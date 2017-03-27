package model.GameEvents;

import model.GameActions.GameAction;

import java.util.ArrayList;


public class OnPlayerTurnEvent extends GameEvent {

    public OnPlayerTurnEvent() {
        this.name = "OnPlayerTurnEvent";
        this.description = "A specific player's turn is starting.";
    }

    @Override
    public void run(ArrayList<GameAction> actions) {

    }
}
