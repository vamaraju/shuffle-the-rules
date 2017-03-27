package model.GameEvents;

import model.GameActions.GameAction;

import java.util.ArrayList;


public class onHandFull extends GameEvent {

    public onHandFull() {
        this.name = "onHandFull";
        this.description = "A player's hand is full.";
    }

    @Override
    public void run(ArrayList<GameAction> actions) {

    }
}
