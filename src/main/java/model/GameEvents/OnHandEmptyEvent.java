package model.GameEvents;

import model.*;
import view.Gameplay.GameplayViewUpdater;


public class OnHandEmptyEvent extends GameEvent {

    public OnHandEmptyEvent() {
        this.className = "OnHandEmptyEvent";
        this.description = "Check if player's hand is empty.";
    }

    @Override
    public void run() {
        if (gameCompleted()) {return;}

        GameplayViewUpdater.postGameplayMessage(GameplayMessageType.EVENT, defaultGameplayMessage());

        Player currentPlayer = GameState.getInstance().getCurrentPlayer();
        if (currentPlayer.getHand().isEmpty()) {
            launchPostRules();
        }
    }
}