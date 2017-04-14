/*
* Requirements mandating inclusion:
* */
package model.GameEvents;


import model.GameRule;



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
