package model.GameEvents;

import model.GameActions.GameAction;
import model.GameRule;

import java.util.ArrayList;


public class OnGameEndEvent extends GameEvent {

    public OnGameEndEvent() {
        this.name = "OnGameEndEvent";
        this.description = "The game is ending.";
    }

    @Override
    public void run(Object... args) {
        for (int i = 0; i < args.length; i++) {
            GameRule rule = (GameRule) args[i];
            rule.run(args);
        }
    }
}
