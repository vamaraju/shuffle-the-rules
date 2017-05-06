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
        if (gameCompleted()) {return;}

        GameplayViewUpdater.postGameplayMessage(GameplayMessageType.EVENT, defaultGameplayMessage() + " -- Resetting to first player.");

        GameState.getInstance().setCurrentPlayer(null);

        // If there is an active player, continue the game. Otherwise, end the game.
        for (Player p : GameCreation.getInstance().getPlayers()) {
            if (!p.isInactive()) {
                launchPostRules();
                return;
            }
        }
        GameplayViewUpdater.postGameplayMessage(GameplayMessageType.ALERT, "All players are set to inactive. Ending game execution.");
    }
}
