package model.GameEvents;

import model.GameActions.GameAction;

import java.util.ArrayList;


public class OnRoundEndEvent extends GameEvent {

    public OnRoundEndEvent() {
        this.name = "OnRoundEndEvent";
        this.description = "The round is ending.";
    }

    @Override
    public void run(Object... args) {

    }
}
