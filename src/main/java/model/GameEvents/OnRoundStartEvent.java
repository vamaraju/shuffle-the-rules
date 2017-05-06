/*
* Requirements mandating inclusion:
* */
package model.GameEvents;


import model.*;
import view.Gameplay.GameplayViewUpdater;


public class OnRoundStartEvent extends GameEvent {

    public OnRoundStartEvent() {
        this.className = "OnRoundStartEvent";
        this.description = "The round is starting.";
    }

    @Override
    public void run() {
        GameState.getInstance().setCurrentRule(this);
        if (RuleInterpreter.gameCompleted()) {return;}

        GameplayViewUpdater.postGameplayMessage(GameplayMessageType.EVENT, RuleInterpreter.defaultGameplayMessage() + " -- Resetting to first player.");

        GameState.getInstance().setCurrentPlayer(null);

        // If there is an active player, continue the game. Otherwise, end the game.
        for (Player p : GameCreation.getInstance().getPlayers()) {
            if (!p.isInactive()) {
                RuleInterpreter.launchPostRules(this);
                return;
            }
        }
        GameplayViewUpdater.postGameplayMessage(GameplayMessageType.ALERT, "All players are set to inactive. Ending game execution.");
        GameplayViewUpdater.disableAllButtons();
    }
}
