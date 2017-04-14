/*
* Requirements mandating inclusion:
* */
package model.GameEvents;


import model.GameRule;
import model.Piles.Pile;



public class OnPileClickEvent extends GameEvent {

    public OnPileClickEvent() {
        this.name = "OnPileClickEvent";
        this.description = "A specific pile is clicked.";
    }

    @Override
    public void run(Object... args) {
        Pile pile = (Pile) args[0];

        if (pile.isClicked()) {
            for (int i = 1; i < args.length; i++) {
                GameRule rule = (GameRule) args[i];
                rule.run(args);
            }
        }
    }
}
