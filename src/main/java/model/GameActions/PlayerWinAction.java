/*
* Requirements mandating inclusion:
* */
package model.GameActions;

import model.GameState;
import model.GameplayMessageType;
import model.Player;
import view.Gameplay.GameplayViewUpdater;


public class PlayerWinAction extends GameAction {

    private Player player;

    public PlayerWinAction() {
        this.className = "PlayerWinAction";
        this.description = "A player wins.";
    }

    @Override
    public void run() {
        Player currentPlayer = GameState.getInstance().getActivePlayer();
        GameplayViewUpdater.postGameplayMessage(GameplayMessageType.ACTION, defaultGameplayMessage() + " --- Player " + currentPlayer.getName() + " has won!");
        GameplayViewUpdater.showPlayerWinAlert(currentPlayer);

        currentPlayer.setInactive(true);
        GameplayViewUpdater.postGameplayMessage(GameplayMessageType.INFO, "Winning player has been set to inactive.");
    }

}
