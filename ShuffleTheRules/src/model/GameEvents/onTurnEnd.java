package model.GameEvents;

import model.GameActions.GameAction;

import java.util.ArrayList;


public class onTurnEnd extends GameEvent {

    public onTurnEnd() {
        this.name = "onTurnEnd";
        this.description = "The turn is ending.";
    }

    @Override
    public void run(ArrayList<GameAction> actions) {

    }
}
