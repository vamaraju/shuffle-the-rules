package model.GameEvents;

import model.GameActions.GameAction;

import java.util.ArrayList;


public class onPlayerTurn extends GameEvent {

    public onPlayerTurn() {
        this.name = "onPlayerTurn";
        this.description = "A specific player's turn is starting.";
    }

    @Override
    public void run(ArrayList<GameAction> actions) {

    }
}
