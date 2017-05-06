/*
* Requirements mandating inclusion:
* */
package model.GameActions;

import model.GameCreation;
import model.GameEvents.OnTurnEndEvent;
import model.GameRule;
import model.GameState;
import model.GameplayMessageType;
import view.Gameplay.GameplayViewUpdater;

public class EndTurnAction extends GameAction  {

    public EndTurnAction() {
        this.className = "EndTurnAction";
        this.description = "The turn ends.";
    }

    @Override
    public void run() {
        if (gameCompleted()) {return;}
        if (actionPhaseCompleted()) {return;}

        GameplayViewUpdater.postGameplayMessage(GameplayMessageType.ACTION, defaultGameplayMessage());
        GameplayViewUpdater.postGameplayMessage(GameplayMessageType.INSTRUCTION, "EndTurnAction has been reached. This turn is ending.");
        GameplayViewUpdater.postGameplayMessage(GameplayMessageType.INSTRUCTION, "Please click the 'End Turn' button to proceed.");

        GameplayViewUpdater.disableAllButtonsExceptEndTurn();
        // Wait for user to click End Turn button.
        GameState.getInstance().getLock().lock();

        GameplayViewUpdater.postGameplayMessage(GameplayMessageType.INFO, finishedGameplayMessage() + " ---Player " +
                GameState.getInstance().getCurrentPlayer().getName() + "'s turn has ended.");

        GameState.getInstance().setActionPhaseCompleted(true);
        GameCreation.getInstance().getRuleGraph().getRuleByClass(OnTurnEndEvent.class).run();
    }
}
