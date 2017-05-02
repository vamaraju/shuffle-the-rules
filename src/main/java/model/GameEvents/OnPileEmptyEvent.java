/*
* Requirements mandating inclusion:
* */
package model.GameEvents;


import model.GameRule;
import model.Piles.Pile;



public class OnPileEmptyEvent extends GameEvent {

    public OnPileEmptyEvent() {
        this.className = "OnPileEmptyEvent";
        this.description = "A specific pile is empty.";
    }

    @Override
    public void run(Object... args) {
        Pile pile = (Pile) args[0];

        if (pile.isEmpty()) {
            for (int i = 1; i < args.length; i++) {
                GameRule rule = (GameRule) args[i];
                rule.run(args);
            }
        }
    }
}
