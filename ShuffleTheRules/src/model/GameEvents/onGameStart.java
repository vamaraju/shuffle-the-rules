package model.GameEvents;

import model.GameActions.GameAction;

import java.util.ArrayList;


public class onGameStart extends GameEvent {

    public onGameStart() {
        this.name = "onGameStart";
        this.description = "The game is starting.";
    }

    @Override
    public void run(ArrayList<GameAction> actions) {

    }
}
