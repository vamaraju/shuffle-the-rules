/*
* Requirements mandating inclusion:
* */
package model.GameActions;

import javafx.application.Platform;
import model.*;
import model.GameEvents.OnTurnEndEvent;
import model.Piles.Hand;
import model.Piles.Pile;
import view.Gameplay.GameplayViewUpdater;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class PlaceCardAction extends GameAction {

    private Hand fromHand;
    private Pile toPile;
    private Card card;

    public PlaceCardAction() {
        this.className = "PlaceCardAction";
        this.description = "A card (or cards) is placed.";
    }

    @Override
    public void run() {
        GameState.getInstance().setCurrentRule(this);
        if (RuleInterpreter.gameCompleted()) {return;}
        if (RuleInterpreter.actionPhaseCompleted()) {return;}

        GameplayViewUpdater.postGameplayMessage(GameplayMessageType.ACTION, RuleInterpreter.defaultGameplayMessage());
        GameplayViewUpdater.postGameplayMessage(GameplayMessageType.INSTRUCTION, "Please select " + getNumCards() + " card(s) from your hand to place in pile " + getPile().getName() + ". Then, click the PLAY button.");
        GameplayViewUpdater.postGameplayMessage(GameplayMessageType.INSTRUCTION, "The card(s) must be of suit '" + getCardSuit() + "' and of value '" + getCardValue() + "'. " +
                "If you cannot play these cards, you can click the 'Skip Action' button to proceed to a different action.");

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

        List<Card> clickedCards = GameState.getInstance().getClickedCards();

        if (!(GameState.getInstance().getSelectedPile() instanceof Hand)) {
            GameplayViewUpdater.postGameplayMessage(GameplayMessageType.ALERT, "The selected pile is not your hand. Please click \"Show Hand\" to select cards from your hand.");
            run(); // run this action again (try again for proper inputs)
            return;
        }

        if (clickedCards.size() != getNumCards()) {
            GameplayViewUpdater.postGameplayMessage(GameplayMessageType.ALERT, "Invalid number of selected cards! Expected " + getNumCards() + " card(s) to place in " + getPile().getName() +
                    " pile. Currently, " + clickedCards.size() + " cards are selected.");
            run(); // run this action again (try again for proper inputs)
            return;
        }

        Pile currentHand = GameState.getInstance().getSelectedPile();
        Card topCard = getPile().getTopCard();
        String matchRequirementsStr = "Please select cards that match the requirements, or click the 'Skip Action' button if you do not have the necessary cards.";

        // Check card value and suit conditions. Check if the clicked cards match the required value/suit.
        // If there is any mismatch, post a message and re-run the action (exit).
        for (Card c : clickedCards) {
            if (getCardValue().equals("*Match Pile*") && c.getValue() != topCard.getValue()) {
                GameplayViewUpdater.postGameplayMessage(GameplayMessageType.ALERT, "Selected card value (" + c.getValue() +
                        ") does not match the top card value (" + topCard.getValue() + ") of pile " + getPile().getName() + ".");
                GameplayViewUpdater.postGameplayMessage(GameplayMessageType.ALERT, matchRequirementsStr);
                run();
                return;
            } else if (getCardSuit().equals("*Match Pile*") && c.getSuit() != topCard.getSuit()) {
                GameplayViewUpdater.postGameplayMessage(GameplayMessageType.ALERT, "Selected card suit (" + c.getSuit() +
                        ") does not match the top card suit (" + topCard.getSuit() + ") of pile " + getPile().getName() + ".");
                GameplayViewUpdater.postGameplayMessage(GameplayMessageType.ALERT, matchRequirementsStr);
                run();
                return;
            } else if (!getCardValue().equals("*Any*") && !getCardValue().equals("*Match Pile*") && c.getValue() != PlayingCard.valueOf(getCardValue().toUpperCase())) {
                GameplayViewUpdater.postGameplayMessage(GameplayMessageType.ALERT, "Selected card value (" + c.getValue() +
                        ") does not match the required value (" + getCardValue() + ").");
                GameplayViewUpdater.postGameplayMessage(GameplayMessageType.ALERT, matchRequirementsStr);
                run();
                return;
            } else if (!getCardSuit().equals("*Any*") && !getCardSuit().equals("*Match Pile*") && c.getSuit() != Suit.valueOf(getCardSuit().toUpperCase())) {
                GameplayViewUpdater.postGameplayMessage(GameplayMessageType.ALERT, "Selected card suit (" + c.getSuit() +
                        ") does not match the required suit (" + getCardSuit() + ").");
                GameplayViewUpdater.postGameplayMessage(GameplayMessageType.ALERT, matchRequirementsStr);
                run();
                return;
            }
        }

        // Launch the Place Card dialog and determine what Card Orientation to place the cards.
        // Sets the CardOrientation for each card automatically according to the results of the dialog.
        // The pile setting for CardOrientation will override anything set in this dialog.
        // This will only work (have any effect) for piles the are set to CardOrientation.EITHER.
        Platform.runLater(() -> RuleInterpreter.runPlaceCardDialog());

        // Wait for the result of the dialog before continuing. The dialog will unlock.
        GameState.getInstance().getLock().lock();

        // All the requirements have been met. Remove clicked cards from hand and add them to pile.
        for (Card c : clickedCards) {
            currentHand.remove(c);
            getPile().addToTop(c);
        }

        GameplayViewUpdater.updateSelectedPile(currentHand);
        GameplayViewUpdater.postGameplayMessage(GameplayMessageType.INFO, RuleInterpreter.finishedGameplayMessage());

        GameState.getInstance().setActionPhaseCompleted(true);
        GameplayViewUpdater.disableAllButtonsExceptEndTurn();
        GameState.getInstance().getLock().lock();
        RuleInterpreter.launchPostRules(this);
    }

}
