package model.GameEvents;

import model.GameActions.GameAction;

import java.util.ArrayList;


public class onRoundStart extends GameEvent {

    public onRoundStart() {
        this.name = "onRoundStart";
        this.description = "The round is starting.";
    }

    @Override
    public void run(ArrayList<GameAction> actions) {

    }
}
