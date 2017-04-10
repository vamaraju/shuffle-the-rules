package model.GameEvents;

import model.GameActions.GameAction;
import model.GameRule;

import java.util.ArrayList;


public class OnTurnEndEvent extends GameEvent {

    public OnTurnEndEvent() {
        this.name = "OnTurnEndEvent";
        this.description = "The turn is ending.";
    }

    @Override
    public void run(Object... args) {
        for (int i = 0; i < args.length; i++) {
            GameRule rule = (GameRule) args[i];
            rule.run(args);
        }
    }
}
