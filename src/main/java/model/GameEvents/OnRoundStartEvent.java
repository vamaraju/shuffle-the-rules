/*
* Requirements mandating inclusion:
*
* 3.2.1.6.3.2 User can create Game Rules by selecting Game Events and adding a Game Action to that event.
* 3.2.1.3.7.2 User can view Event actions and rules.
* 3.2.1.3.7.3 User can edit Event actions and rules.
* 3.2.2.5.3.1 Rule Interpreter will interpret and execute Game Rules for a Card Game.
* */
package model.GameEvents;


import model.*;
import view.Gameplay.GameplayViewUpdater;


public class OnRoundStartEvent extends GameEvent {

    public OnRoundStartEvent() {
        this.className = "OnRoundStartEvent";
        this.description = "The round is starting.";
    }

    @Override
    public void run() {
        GameState.getInstance().setCurrentRule(this);
        if (RuleInterpreter.gameCompleted()) {return;}

        GameplayViewUpdater.postGameplayMessage(GameplayMessageType.EVENT, RuleInterpreter.defaultGameplayMessage() + " -- Resetting to first player.");

        GameState.getInstance().setCurrentPlayer(null);

        // If there is an active player, continue the game. Otherwise, end the game.
        for (Player p : GameCreation.getInstance().getPlayers()) {
            if (!p.isInactive()) {
                RuleInterpreter.launchPostRules(this);
                return;
            }
        }
        GameplayViewUpdater.postGameplayMessage(GameplayMessageType.ALERT, "All players are set to inactive. Ending game execution.");
        GameplayViewUpdater.disableAllButtons();
    }
}
