/*
* Requirements mandating inclusion:
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
            GameCreation.getInstance().getRuleGraph().getRoundStart().run();
        } else {
            currentPlayer = GameCreation.getInstance().getPlayers().get(currentPlayer.getPlayerNum());
            GameState.getInstance().setCurrentPlayer(currentPlayer);
            GameCreation.getInstance().getRuleGraph().getTurnStart().run();
        }
    }
}
