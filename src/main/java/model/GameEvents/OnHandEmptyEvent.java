package model.GameEvents;

import model.*;
import model.Piles.Pile;
import view.Gameplay.GameplayViewUpdater;


public class OnHandEmptyEvent extends GameEvent {

    public OnHandEmptyEvent() {
        this.className = "OnHandEmptyEvent";
        this.description = "A player's hand is empty.";
    }

    @Override
    public void run() {
        GameplayViewUpdater.postGameplayMessage(GameplayMessageType.EVENT, defaultGameplayMessage());

        Player currentPlayer = GameState.getInstance().getActivePlayer();
        if (currentPlayer.getHand().isEmpty()) {
            launchPostRules();
        }
    }
}