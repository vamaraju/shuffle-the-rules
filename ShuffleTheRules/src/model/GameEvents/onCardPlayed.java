package model.GameEvents;

import model.GameActions.GameAction;

import java.util.ArrayList;


public class onCardPlayed extends GameEvent {

    public onCardPlayed() {
        this.name = "onCardPlayed";
        this.description = "A specific card is played.";
    }

    @Override
    public void run(ArrayList<GameAction> actions) {

    }
}
