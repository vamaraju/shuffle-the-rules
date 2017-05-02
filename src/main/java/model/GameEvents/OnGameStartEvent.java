package model.GameEvents;

import model.GameRule;


public class OnGameStartEvent extends GameEvent {

    public OnGameStartEvent() {
        this.name = "Game Start";
        this.className = "OnGameStartEvent";
        this.description = "The game is starting.";
    }

    @Override
    public void run(Object... args) {
        for (int i = 0; i < args.length; i++) {
            GameRule rule = (GameRule) args[i];
            rule.run(args);
        }
    }
}
