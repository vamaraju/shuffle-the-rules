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
import model.Piles.Pile;
import view.Gameplay.GameplayViewUpdater;

public class DrawCardAction extends GameAction {

    private Pile drawingPile;
    private int numCards;
    private Player player;

    public DrawCardAction() {
        this.className = "DrawCardAction";
        this.description = "A card (or cards) is drawn.";
    }

    @Override
    public void run() {
        GameState.getInstance().setCurrentRule(this);
        if (RuleInterpreter.gameCompleted()) {return;}
        if (RuleInterpreter.actionPhaseCompleted()) {return;}

        GameplayViewUpdater.postGameplayMessage(GameplayMessageType.ACTION, RuleInterpreter.defaultGameplayMessage());
        GameplayViewUpdater.postGameplayMessage(GameplayMessageType.INSTRUCTION, "Please click the PLAY button to draw " + getNumCards() + " card(s) from pile " + getPile().getName() + ".");
        GameplayViewUpdater.postGameplayMessage(GameplayMessageType.INSTRUCTION, "The card(s) must be of suit '" + getCardSuit() + "' and of value '" + getCardValue() + "'. " +
                "If the pile does not contain these cards, the draw will be unsuccessful.");
        GameplayViewUpdater.postGameplayMessage(GameplayMessageType.INSTRUCTION, "You can click the 'Skip Action' button to proceed to a different action. If you are unsure, click the PLAY button, and read the messages that proceed.");

        // Wait for user input and/or for the user to click the necessary objects in the GUI.
        GameState.getInstance().getLock().lock();

        if (RuleInterpreter.playerInactive()) {
            return;
        }

        if (RuleInterpreter.skipTurn()) {
            return;
        }

        if (RuleInterpreter.skipAction()) {
            // The player needs to take an action this turn; restart the the action list if this is the last action in the list.
            if (isFinalAction()) {
                RuleInterpreter.launchPostRules(getParentRule());
            }
            return;
        }

        Player currentPlayer = GameState.getInstance().getCurrentPlayer();

        if (currentPlayer.getHand().isFull() || (currentPlayer.getHand().size() + getNumCards() > currentPlayer.getHand().getMaxSize())) {
            GameplayViewUpdater.postGameplayMessage(GameplayMessageType.ALERT, "The hand will be too full if " + getNumCards() + " cards are drawn. Cards cannot be drawn. Please skip this action by clicking 'Skip Action'.");
            run();
            return;
        }

        if (getPile().isMin() || (getPile().getCards().size() - getNumCards() < getPile().getMinSize())) {
            GameplayViewUpdater.postGameplayMessage(GameplayMessageType.ALERT, "The pile '" + getPile().getName() + "' would be below its minimum size if drawn from. Please skip this action by clicking 'Skip Action'.");
            run();
            return;
        }

        if (getCardValue().equals("*Any*") && getCardSuit().equals("*Any*")) {
            for (int i = 0; i < getNumCards(); i++) {
                currentPlayer.getHand().add(getPile().draw());
            }
        }

        GameplayViewUpdater.updateSelectedPile(currentPlayer.getHand());
        GameplayViewUpdater.postGameplayMessage(GameplayMessageType.INFO, RuleInterpreter.finishedGameplayMessage());

        GameState.getInstance().setActionPhaseCompleted(true);
        GameplayViewUpdater.disableAllButtonsExceptEndTurn();
        GameState.getInstance().getLock().lock();
        RuleInterpreter.launchPostRules(this);
    }

}
