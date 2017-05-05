/*
* Requirements mandating inclusion:
* */
package model.GameEvents;


import model.GameRule;
import model.Piles.Pile;



public class OnPileEmptyEvent extends GameEvent {

    public OnPileEmptyEvent() {
        this.className = "OnPileEmptyEvent";
        this.description = "A specific pile is empty.";
    }

    @Override
    public void run() {

    }
}
