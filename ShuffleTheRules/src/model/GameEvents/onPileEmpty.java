package model.GameEvents;

import model.GameActions.GameAction;

import java.util.ArrayList;


public class onPileEmpty extends GameEvent {

    public onPileEmpty() {
        this.name = "onPileEmpty";
        this.description = "A specific pile is empty.";
    }

    @Override
    public void run(ArrayList<GameAction> actions) {

    }
}
