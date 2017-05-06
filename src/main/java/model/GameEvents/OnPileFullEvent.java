/*
* Requirements mandating inclusion:
* */
package model.GameEvents;


import model.GameRule;
import model.GameState;
import model.Piles.Pile;
import model.RuleInterpreter;


public class OnPileFullEvent extends GameEvent {

    public OnPileFullEvent() {
        this.className = "OnPileFullEvent";
        this.description = "Check if a specific pile is full.";
    }

    @Override
    public void run() {
        GameState.getInstance().setCurrentRule(this);
        RuleInterpreter.launchPostRules(this);
    }
}
