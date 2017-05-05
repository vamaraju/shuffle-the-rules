/*
* Requirements mandating inclusion:
* */
package model.GameEvents;


import model.GameRule;
import model.Player;



public class OnPlayerClickEvent extends GameEvent {

    public OnPlayerClickEvent() {
        this.className = "OnPlayerClickEvent";
        this.description = "A specific player is clicked.";
    }

    @Override
    public void run() {

    }
}
