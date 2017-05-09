/*
* Requirements mandating inclusion:
*
* 3.2.1.6.3.2 User can create Game Rules by selecting Game Events and adding a Game Action to that event.
* 3.2.1.3.7.2 User can view Event actions and rules.
* 3.2.1.3.7.3 User can edit Event actions and rules.
* 3.2.2.5.3.1 Rule Interpreter will interpret and execute Game Rules for a Card Game.
* */
package model.GameActions;

import model.*;
import model.GameEvents.OnTurnEndEvent;
import view.Gameplay.GameplayViewUpdater;

public class EndTurnAction extends GameAction  {

    public EndTurnAction() {
        this.className = "EndTurnAction";
        this.description = "The turn ends.";
    }

    @Override
    public void run() {
        GameState.getInstance().setCurrentRule(this);
        if (RuleInterpreter.gameCompleted()) {return;}
        if (RuleInterpreter.actionPhaseCompleted()) {return;}

        GameplayViewUpdater.postGameplayMessage(GameplayMessageType.ACTION, RuleInterpreter.defaultGameplayMessage());
        GameplayViewUpdater.postGameplayMessage(GameplayMessageType.INSTRUCTION, "EndTurnAction has been reached. This turn is ending.");
        GameplayViewUpdater.postGameplayMessage(GameplayMessageType.INSTRUCTION, "Please click the 'End Turn' button to proceed.");

        GameplayViewUpdater.disableAllButtonsExceptEndTurn();
        // Wait for user to click End Turn button.
        GameState.getInstance().getLock().lock();

        GameplayViewUpdater.postGameplayMessage(GameplayMessageType.INFO, RuleInterpreter.finishedGameplayMessage() + " ---Player " +
                GameState.getInstance().getCurrentPlayer().getName() + "'s turn has ended.");

        GameState.getInstance().setActionPhaseCompleted(true);
        RuleInterpreter.launchTurnEndEvent();
    }
}
