package model.GameEvents;

import model.GameActions.GameAction;

import java.util.ArrayList;


public class onGameEnd extends GameEvent {

    public onGameEnd() {
        this.name = "onGameEnd";
        this.description = "The game is ending.";
    }

    @Override
    public void run(ArrayList<GameAction> actions) {

    }
}
