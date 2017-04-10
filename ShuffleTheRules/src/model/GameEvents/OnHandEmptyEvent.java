package model.GameEvents;

import model.GameActions.GameAction;
import model.GameRule;
import model.Piles.Pile;

import java.util.ArrayList;


public class OnHandEmptyEvent extends GameEvent {

    public OnHandEmptyEvent() {
        this.name = "OnHandEmptyEvent";
        this.description = "A player's hand is empty.";
    }

    @Override
    public void run(Object... args) {
        Pile hand = (Pile) args[0];

        if (hand.isEmpty()) {
            for (int i = 1; i < args.length; i++) {
                GameRule rule = (GameRule) args[i];
                rule.run(args);
            }
        }
    }
}