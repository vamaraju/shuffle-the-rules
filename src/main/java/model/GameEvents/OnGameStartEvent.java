package model.GameEvents;

import model.GameRule;
import model.GameView;
import model.GameplayMessageType;
import view.Gameplay.GameplayButtonView;
import view.Gameplay.GameplayView;


public class OnGameStartEvent extends GameEvent {

    public OnGameStartEvent() {
        this.name = "Game Start";
        this.className = "OnGameStartEvent";
        this.description = "The game is starting.";
    }

    @Override
    public void run(Object... args) {
        postGameplayMessage(GameplayMessageType.EVENT);
        launchPostRules();
    }
}
