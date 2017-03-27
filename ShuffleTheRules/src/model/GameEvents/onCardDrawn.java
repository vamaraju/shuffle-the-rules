package model.GameEvents;

import model.GameActions.GameAction;

import java.util.ArrayList;


public class onCardDrawn extends GameEvent {

    public onCardDrawn() {
        this.name = "onCardDrawn";
        this.description = "A specific card is drawn.";
    }

    @Override
    public void run(ArrayList<GameAction> actions) {

    }
}
