package model.GameEvents;

import model.*;
import view.Gameplay.GameplayButtonView;
import view.Gameplay.GameplayView;
import view.Gameplay.GameplayViewUpdater;


public class OnGameStartEvent extends GameEvent {

    public OnGameStartEvent() {
        this.name = "Game Start";
        this.className = "OnGameStartEvent";
        this.description = "The game is starting.";
    }

    @Override
    public void run() {
        GameState.getInstance().setCurrentRule(this);
        GameplayViewUpdater.postGameplayMessage(GameplayMessageType.EVENT, RuleInterpreter.defaultGameplayMessage());
        RuleInterpreter.launchPostRules(this);
    }
}
