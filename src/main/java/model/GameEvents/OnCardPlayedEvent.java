package model.GameEvents;

import model.Card;
import model.GameRule;
import model.GameState;
import model.RuleInterpreter;


public class OnCardPlayedEvent extends GameEvent {

    private Card expectedCard;

    public OnCardPlayedEvent() {
        this.className = "OnCardPlayedEvent";
        this.description = "Check if a specific card is played.";
    }

    @Override
    public void run() {
        GameState.getInstance().setCurrentRule(this);
        RuleInterpreter.launchPostRules(this);
    }

}
