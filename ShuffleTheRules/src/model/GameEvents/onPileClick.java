package model.GameEvents;

import model.GameActions.GameAction;

import java.util.ArrayList;


public class onPileClick extends GameEvent {

    public onPileClick() {
        this.name = "onPileClick";
        this.description = "A specific pile is clicked.";
    }

    @Override
    public void run(ArrayList<GameAction> actions) {

    }
}
