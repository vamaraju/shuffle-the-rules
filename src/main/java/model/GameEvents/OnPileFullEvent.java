/*
* Requirements mandating inclusion:
* */
package model.GameEvents;


import model.GameRule;
import model.Piles.Pile;


public class OnPileFullEvent extends GameEvent {

    public OnPileFullEvent() {
        this.className = "OnPileFullEvent";
        this.description = "Check if a specific pile is full.";
    }

    @Override
    public void run() {

    }
}
