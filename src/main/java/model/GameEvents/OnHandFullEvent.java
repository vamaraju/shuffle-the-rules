package model.GameEvents;

import model.GameRule;
import model.GameState;
import model.Piles.Pile;
import model.RuleInterpreter;


public class OnHandFullEvent extends GameEvent {

    public OnHandFullEvent() {
        this.className = "OnHandFullEvent";
        this.description = "Check if player's hand is full.";
    }

    @Override
    public void run() {
        GameState.getInstance().setCurrentRule(this);
        RuleInterpreter.launchPostRules(this);
    }
}
