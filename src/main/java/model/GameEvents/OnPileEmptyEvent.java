/*
* Requirements mandating inclusion:
* */
package model.GameEvents;


import model.GameRule;
import model.GameState;
import model.Piles.Pile;
import model.RuleInterpreter;


public class OnPileEmptyEvent extends GameEvent {

    public OnPileEmptyEvent() {
        this.className = "OnPileEmptyEvent";
        this.description = "Check if a specific pile is empty.";
    }

    @Override
    public void run() {
        GameState.getInstance().setCurrentRule(this);
        RuleInterpreter.launchPostRules(this);
    }
}
