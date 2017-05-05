package model.GameEvents;

import model.GameRule;
import model.Piles.Pile;


public class OnHandEmptyEvent extends GameEvent {

    public OnHandEmptyEvent() {
        this.className = "OnHandEmptyEvent";
        this.description = "A player's hand is empty.";
    }

    @Override
    public void run() {

    }
}