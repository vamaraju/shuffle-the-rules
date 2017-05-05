/*
* Requirements mandating inclusion:
* */
package model.GameEvents;


import model.*;
import view.Gameplay.GameplayViewUpdater;


public class OnTurnStartEvent extends GameEvent {

    public OnTurnStartEvent() {
        this.className = "OnTurnStartEvent";
        this.description = "The turn is starting.";
    }

    @Override
    public void run() {
        Player currentPlayer = GameState.getInstance().getCurrentPlayer();
        if (currentPlayer == null) { // This means that RoundStart was just run. It sets activePlayer to null.
            currentPlayer = GameCreation.getInstance().getPlayers().get(0); // Get the first player.
            GameState.getInstance().setCurrentPlayer(currentPlayer);
        }

        if (currentPlayer.isInactive()) {
            GameplayViewUpdater.postGameplayMessage(GameplayMessageType.INFO, "Player '"
                    + GameState.getInstance().getCurrentPlayer().getName() + "' has been set to inactive. Skipping turn.");
            GameCreation.getInstance().getRuleGraph().getRuleByClass(OnTurnEndEvent.class).run();
            return;
        }

        GameplayViewUpdater.postGameplayMessage(GameplayMessageType.EVENT, defaultGameplayMessage() + " -- Starting " + currentPlayer.getName() + "'s turn.");
        GameplayViewUpdater.updateSelectedPile(currentPlayer.getHand());
        GameState.getInstance().setSelectedPile(currentPlayer.getHand());

        launchPostRules();
    }
}
