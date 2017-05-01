/*
* Requirements mandating inclusion:
* */
package model.GameActions;

import model.GameRule;
import model.Player;


public class PlayerWinAction extends GameAction {

    private Player player;

    public PlayerWinAction() {
        this.name = "PlayerWinAction";
        this.description = "A player wins.";
    }

    public PlayerWinAction(Player player) {
        this();
        this.player = player;
    }

    @Override
    public void run(Object... args) {
        player = (args[0] != null ? (Player) args[0] : player);
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
