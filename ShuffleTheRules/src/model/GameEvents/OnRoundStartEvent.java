package model.GameEvents;

import model.GameActions.GameAction;
import model.GameRule;

import java.util.ArrayList;


public class OnRoundStartEvent extends GameEvent {

    public OnRoundStartEvent() {
        this.name = "OnRoundStartEvent";
        this.description = "The round is starting.";
    }

    @Override
    public void run(Object... args) {
        for (int i = 0; i < args.length; i++) {
            GameRule rule = (GameRule) args[i];
            rule.run(args);
        }
    }
}
