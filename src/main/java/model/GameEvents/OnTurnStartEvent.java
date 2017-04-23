/*
* Requirements mandating inclusion:
* */
package model.GameEvents;


import model.GameRule;


public class OnTurnStartEvent extends GameEvent {

    public OnTurnStartEvent() {
        this.name = "OnTurnStartEvent";
        this.description = "The turn is starting.";
    }

    @Override
    public void run(Object... args) {
        for (int i = 0; i < args.length; i++) {
            GameRule rule = (GameRule) args[i];
            rule.run(args);
        }
    }
}
