/*
* Requirements mandating inclusion:
* */
package model.GameEvents;


import model.*;


public class OnTurnStartEvent extends GameEvent {

    public OnTurnStartEvent() {
        this.className = "OnTurnStartEvent";
        this.description = "The turn is starting.";
    }

    @Override
    public void run(Object... args) {
        Player currentPlayer = GameState.getInstance().getActivePlayer();
        if (currentPlayer == null) { // This means that RoundStart was just run. It sets activePlayer to null.
            currentPlayer = GameCreation.getInstance().getPlayers().get(0); // Get the first player.
            GameState.getInstance().setActivePlayer(currentPlayer);
        }

        postGameplayMessage(GameplayMessageType.EVENT, defaultGameplayMessage() + " -- Starting " + currentPlayer.getName() + "'s turn.");
        launchPostRules();
    }
}
