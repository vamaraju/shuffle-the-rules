package model.GameEvents;

import model.GameRule;
import view.Gameplay.GameplayViewUpdater;


public class OnGameEndEvent extends GameEvent {

    public OnGameEndEvent() {
        this.className = "OnGameEndEvent";
        this.description = "The game is ending.";
    }

    @Override
    public void run() {
        GameplayViewUpdater.closeGameplayWindow();
    }
}
