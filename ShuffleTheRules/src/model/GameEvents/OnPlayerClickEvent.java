package model.GameEvents;

import model.GameActions.GameAction;

import java.util.ArrayList;


public class OnPlayerClickEvent extends GameEvent {

    public OnPlayerClickEvent() {
        this.name = "OnPlayerClickEvent";
        this.description = "A specific player is clicked.";
    }

    @Override
    public void run(ArrayList<GameAction> actions) {

    }
}
