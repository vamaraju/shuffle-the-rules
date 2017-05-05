package model.GameEvents;

import model.GameRule;


public class OnGameEndEvent extends GameEvent {

    public OnGameEndEvent() {
        this.className = "OnGameEndEvent";
        this.description = "The game is ending.";
    }

    @Override
    public void run() {
    }
}
