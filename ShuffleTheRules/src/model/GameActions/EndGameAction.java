package model.GameActions;

import model.GameRule;

import java.util.ArrayList;

public class EndGameAction extends GameAction {

    public EndGameAction() {
        this.name = "EndGameAction";
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
