package model.GameEvents;

import model.GameRule;
import model.Piles.Pile;


public class OnHandFullEvent extends GameEvent {

    public OnHandFullEvent() {
        this.className = "OnHandFullEvent";
        this.description = "A player's hand is full.";
    }

    @Override
    public void run() {

    }
}
