/*
* Requirements mandating inclusion:
* */
package model.GameActions;

import model.GameRule;
import model.Player;


public class SkipTurnAction extends GameAction {

    private Player player;

    public SkipTurnAction() {
        this.name = "SkipTurnAction";
        this.description = "A turn is skipped.";
    }

    public SkipTurnAction(Player player) {
        this();
        this.player = player;
    }

    @Override
    public void run(Object... args) {
        player = (args[0] != null ? (Player) args[0] : player);

        player.setSkipFlag(true);

        for (int i = 1; i < args.length; i++) {
            GameRule rule = (GameRule) args[i];
            rule.run(args);
        }
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
