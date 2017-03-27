package model.GameEvents;

import model.GameActions.GameAction;

import java.util.ArrayList;


public class OnGameStartEvent extends GameEvent {

    public OnGameStartEvent() {
        this.name = "OnGameStartEvent";
        this.description = "The game is starting.";
    }

    @Override
    public void run(ArrayList<GameAction> actions) {

    }
}
