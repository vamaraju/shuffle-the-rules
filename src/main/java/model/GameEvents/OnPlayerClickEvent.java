/*
* Requirements mandating inclusion:
* */
package model.GameEvents;


import model.GameRule;
import model.GameState;
import model.Player;
import model.RuleInterpreter;


public class OnPlayerClickEvent extends GameEvent {

    public OnPlayerClickEvent() {
        this.className = "OnPlayerClickEvent";
        this.description = "Check if a specific player is clicked.";
    }

    @Override
    public void run() {
        GameState.getInstance().setCurrentRule(this);
        RuleInterpreter.launchPostRules(this);
    }
}
