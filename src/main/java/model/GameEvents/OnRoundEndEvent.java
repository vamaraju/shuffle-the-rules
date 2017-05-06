/*
* Requirements mandating inclusion:
* */
package model.GameEvents;


import model.GameRule;
import model.GameState;
import model.RuleInterpreter;


public class OnRoundEndEvent extends GameEvent {

    public OnRoundEndEvent() {
        this.className = "OnRoundEndEvent";
        this.description = "The round is ending.";
    }

    @Override
    public void run() {
        GameState.getInstance().setCurrentRule(this);
        RuleInterpreter.launchPostRules(this);
    }
}
