/*
* Requirements mandating inclusion:
* */
package model.GameActions;

import model.Player;


public class PlayerWinAction extends GameAction {

    private Player player;

    public PlayerWinAction() {
        this.className = "PlayerWinAction";
        this.description = "A player wins.";
    }

    public PlayerWinAction(Player player) {
        this();
        this.player = player;
    }

    @Override
    public void run(Object... args) {

    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
