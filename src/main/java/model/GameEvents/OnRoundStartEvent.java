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
        GameplayViewUpdater.postGameplayMessage(GameplayMessageType.EVENT, defaultGameplayMessage() + " -- Resetting to first player.");

        GameState.getInstance().setActivePlayer(null);

        launchPostRules();
    }
}
