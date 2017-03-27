package model.GameEvents;

import model.GameActions.GameAction;

import java.util.ArrayList;


public class onHandEmpty extends GameEvent {

    public onHandEmpty() {
        this.name = "onHandEmpty";
        this.description = "A player's hand is empty.";
    }

    @Override
    public void run(ArrayList<GameAction> actions) {

    }
}