package model.GameEvents;

import model.GameActions.GameAction;

import java.util.ArrayList;


public class onTurnStart extends GameEvent {

    public onTurnStart() {
        this.name = "onTurnStart";
        this.description = "The turn is starting.";
    }

    @Override
    public void run(ArrayList<GameAction> actions) {

    }
}
