/*
* Requirements mandating inclusion:
* */
package model.GameActions;

import model.GameRule;

public class EndGameAction extends GameAction {

    public EndGameAction() {
        this.className = "EndGameAction";
        this.description = "The game ends.";
    }

    @Override
    public void run(Object... args) {
        for (int i = 0; i < args.length; i++) {
            GameRule rule = (GameRule) args[i];
            rule.run(args);
        }
    }
}
