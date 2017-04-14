/*
* Requirements mandating inclusion:
* */
package model.GameEvents;


import model.GameRule;
import model.Piles.Pile;


public class OnPileFullEvent extends GameEvent {

    public OnPileFullEvent() {
        this.name = "OnPileFullEvent";
        this.description = "A specific pile is full.";
    }

    @Override
    public void run(Object... args) {
        Pile pile = (Pile) args[0];

        if (pile.isFull()) {
            for (int i = 1; i < args.length; i++) {
                GameRule rule = (GameRule) args[i];
                rule.run(args);
            }
        }
    }
}
