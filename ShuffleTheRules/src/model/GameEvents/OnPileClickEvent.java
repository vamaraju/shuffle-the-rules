package model.GameEvents;

import model.GameActions.GameAction;

import java.util.ArrayList;


public class OnPileClickEvent extends GameEvent {

    public OnPileClickEvent() {
        this.name = "OnPileClickEvent";
        this.description = "A specific pile is clicked.";
    }

    @Override
    public void run(Object... args) {

    }
}
