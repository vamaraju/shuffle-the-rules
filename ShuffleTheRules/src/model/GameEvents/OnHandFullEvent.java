package model.GameEvents;

import model.GameActions.GameAction;
import model.GameRule;
import model.Piles.Pile;

import java.util.ArrayList;


public class OnHandFullEvent extends GameEvent {

    public OnHandFullEvent() {
        this.name = "OnHandFullEvent";
        this.description = "A player's hand is full.";
    }

    @Override
    public void run(Object... args) {
        Pile hand = (Pile) args[0];

        if (hand.isFull()) {
            for (int i = 1; i < args.length; i++) {
                GameRule rule = (GameRule) args[i];
                rule.run(args);
            }
        }
    }
}
