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

import java.util.List;

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
        GameplayViewUpdater.postGameplayMessage(GameplayMessageType.ACTION, defaultGameplayMessage());
        GameplayViewUpdater.postGameplayMessage(GameplayMessageType.INSTRUCTION, "Please select " + getNumCards() + " card(s) from your hand to place in pile " + getPile().getName() + ". Then, click the PLAY button.");
        GameplayViewUpdater.postGameplayMessage(GameplayMessageType.INSTRUCTION, "The card(s) must be of suit '" + getCardSuit() + "' and of value '" + getCardValue() + "'. " +
                "If you cannot play these cards, you can click the 'Skip Action' button to proceed to a different action.");

        GameState.getInstance().getLock().lock();

        if (playerInactive() || skipAction()) {
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
        if (getCardValue().equals("*Any*") && getCardSuit().equals("*Any*")) {
            for (Card c : clickedCards) {
                currentHand.remove(c);
                getPile().add(c);
            }
        }

        GameplayViewUpdater.updateSelectedPile(currentHand);
        GameplayViewUpdater.postGameplayMessage(GameplayMessageType.INFO, finishedGameplayMessage());
        launchPostRules();
    }

}
