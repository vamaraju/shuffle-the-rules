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


public class OnTurnEndEvent extends GameEvent {

    public OnTurnEndEvent() {
        this.className = "OnTurnEndEvent";
        this.description = "The player's turn is ending.";
    }

    @Override
    public void run() {
        GameState.getInstance().setCurrentRule(this);
        if (RuleInterpreter.gameCompleted()) {return;}

        Player currentPlayer = GameState.getInstance().getCurrentPlayer();
        GameplayViewUpdater.postGameplayMessage(GameplayMessageType.EVENT, RuleInterpreter.defaultGameplayMessage() + " -- Ending " + currentPlayer.getName() + "'s turn.");
        RuleInterpreter.launchPostRules(this);

        // If the current player is the last one in the round, restart the round.
        // Otherwise, update the current player to the next one in the sequence. Note that the playerNum of the
        // currentPlayer is always one higher than its index in getPlayers(). Also, the players in getPlayers() are
        // stored in the sequence (in order of turns).
        if (currentPlayer.getPlayerNum() == GameCreation.getInstance().getPlayers().size()) {
            RuleInterpreter.launchRoundStartEvent();
        } else {
            currentPlayer = GameCreation.getInstance().getPlayers().get(currentPlayer.getPlayerNum());
            GameState.getInstance().setCurrentPlayer(currentPlayer);
            RuleInterpreter.launchTurnStartEvent();
        }
    }
}
