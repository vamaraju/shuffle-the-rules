/*
* Requirements mandating inclusion:
* */
package model.GameActions;

import model.GameRule;

public class EndTurnAction extends GameAction  {

    public EndTurnAction() {
        this.name = "EndTurnAction";
        this.description = "The turn ends.";
    }

    @Override
    public void run(Object... args) {
        for (int i = 0; i < args.length; i++) {
            GameRule rule = (GameRule) args[i];
            rule.run(args);
        }
    }
}
