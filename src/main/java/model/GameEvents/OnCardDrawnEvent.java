package model.GameEvents;

import model.Card;
import model.GameRule;
import model.GameState;
import model.RuleInterpreter;


public class OnCardDrawnEvent extends GameEvent {

    private Card expectedCard;

    public OnCardDrawnEvent() {
        this.className = "OnCardDrawnEvent";
        this.description = "Check if a specific card is drawn.";
    }

    @Override
    public void run() {
        GameState.getInstance().setCurrentRule(this);
        RuleInterpreter.launchPostRules(this);
    }

}
