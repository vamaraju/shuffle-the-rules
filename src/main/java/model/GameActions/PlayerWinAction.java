/*
* Requirements mandating inclusion:
* */
package model.GameActions;

import model.GameState;
import model.GameplayMessageType;
import model.Player;
import model.RuleInterpreter;
import view.Gameplay.GameplayViewUpdater;


public class PlayerWinAction extends GameAction {

    private Player player;

    public PlayerWinAction() {
        this.className = "PlayerWinAction";
        this.description = "A player wins.";
    }

    @Override
    public void run() {
        GameState.getInstance().setCurrentRule(this);
        Player currentPlayer = GameState.getInstance().getCurrentPlayer();
        GameplayViewUpdater.postGameplayMessage(GameplayMessageType.ACTION, RuleInterpreter.defaultGameplayMessage() + " --- Player " + currentPlayer.getName() + " has won!");
        GameplayViewUpdater.showPlayerWinAlert(currentPlayer);

        currentPlayer.setInactive(true);
        GameState.getInstance().setGameCompleted(true);
        GameplayViewUpdater.postGameplayMessage(GameplayMessageType.INFO, "Winning player has been set to inactive.");
        RuleInterpreter.launchPostRules(this);
    }

}
