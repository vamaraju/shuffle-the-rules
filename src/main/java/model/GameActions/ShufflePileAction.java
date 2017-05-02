/*
* Requirements mandating inclusion:
* */
package model.GameActions;

import model.GameRule;
import model.Piles.Pile;


public class ShufflePileAction extends GameAction {

    private Pile pile;

    public ShufflePileAction() {
        this.className = "ShufflePileAction";
        this.description = "A pile is shuffled.";
    }

    public ShufflePileAction(Pile pile) {
        this();
        this.pile = pile;
    }

    @Override
    public void run(Object... args) {
        pile = (args[0] != null ? (Pile) args[0] : pile);

        pile.shuffle();

        for (int i = 1; i < args.length; i++) {
            GameRule rule = (GameRule) args[i];
            rule.run(args);
        }
    }

    public Pile getPile() {
        return pile;
    }

    public void setPile(Pile pile) {
        this.pile = pile;
    }
}
