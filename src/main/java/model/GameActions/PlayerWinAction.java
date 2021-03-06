/*
* Requirements mandating inclusion:
*
* 3.2.1.6.3.2 User can create Game Rules by selecting Game Events and adding a Game Action to that event.
* 3.2.1.3.7.2 User can view Event actions and rules.
* 3.2.1.3.7.3 User can edit Event actions and rules.
* 3.2.2.5.3.1 Rule Interpreter will interpret and execute Game Rules for a Card Game.
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
