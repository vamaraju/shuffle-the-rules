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

    @Override
    public void run() {

    }

}
