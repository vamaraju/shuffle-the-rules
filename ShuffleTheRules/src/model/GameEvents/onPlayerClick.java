package model.GameEvents;

import model.GameActions.GameAction;

import java.util.ArrayList;


public class onPlayerClick extends GameEvent {

    public onPlayerClick() {
        this.name = "onPlayerClick";
        this.description = "A specific player is clicked.";
    }

    @Override
    public void run(ArrayList<GameAction> actions) {

    }
}
