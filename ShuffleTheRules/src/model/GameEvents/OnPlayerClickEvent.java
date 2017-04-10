package model.GameEvents;

import model.GameActions.GameAction;
import model.GameRule;
import model.Player;

import java.util.ArrayList;


public class OnPlayerClickEvent extends GameEvent {

    public OnPlayerClickEvent() {
        this.name = "OnPlayerClickEvent";
        this.description = "A specific player is clicked.";
    }

    @Override
    public void run(Object... args) {
        Player player = (Player) args[0];

        if (player.isClicked()) {
            for (int i = 1; i < args.length; i++) {
                GameRule rule = (GameRule) args[i];
                rule.run(args);
            }
        }
    }
}
