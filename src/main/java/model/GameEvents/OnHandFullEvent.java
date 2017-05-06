package model.GameEvents;

import model.GameRule;
import model.Piles.Pile;


public class OnHandFullEvent extends GameEvent {

    public OnHandFullEvent() {
        this.className = "OnHandFullEvent";
        this.description = "Check if player's hand is full.";
    }

    @Override
    public void run() {

    }
}
