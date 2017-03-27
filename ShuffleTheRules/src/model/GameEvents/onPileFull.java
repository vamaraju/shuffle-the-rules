package model.GameEvents;

import model.GameActions.GameAction;

import java.util.ArrayList;


public class onPileFull extends GameEvent {

    public onPileFull() {
        this.name = "onPileFull";
        this.description = "A specific pile is full.";
    }

    @Override
    public void run(ArrayList<GameAction> actions) {

    }
}
