/*
* Requirements mandating inclusion:
* */
package model.GameEvents;


import model.GameRule;
import model.GameState;
import model.Player;
import model.RuleInterpreter;


public class OnPlayerTurnEvent extends GameEvent {

    public OnPlayerTurnEvent() {
        this.className = "OnPlayerTurnEvent";
        this.description = "Check if it a specific player's turn.";
    }

    @Override
    public void run() {
        GameState.getInstance().setCurrentRule(this);
        RuleInterpreter.launchPostRules(this);
    }
}
