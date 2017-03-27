package model.GameEvents;

import model.GameActions.GameAction;

import java.util.ArrayList;


public class onRoundEnd extends GameEvent {

    public onRoundEnd() {
        this.name = "onRoundEnd";
        this.description = "The round is ending.";
    }

    @Override
    public void run(ArrayList<GameAction> actions) {

    }
}
