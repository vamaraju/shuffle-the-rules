package model.GameActions;

import model.GameRule;

import java.util.ArrayList;

public class StartTurnAction extends GameAction {

    public StartTurnAction() {
        this.name = "StartTurnAction";
        this.description = "A turn is started.";
    }

    @Override
    public void run(Object... args) {
        for (int i = 0; i < args.length; i++) {
            GameRule rule = (GameRule) args[i];
            rule.run(args);
        }
    }
}
