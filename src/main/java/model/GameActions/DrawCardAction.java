/*
* Requirements mandating inclusion:
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
        if (gameCompleted()) {return;}

        GameplayViewUpdater.postGameplayMessage(GameplayMessageType.ACTION, defaultGameplayMessage());
        GameplayViewUpdater.postGameplayMessage(GameplayMessageType.INSTRUCTION, "Please click the PLAY button to draw " + getNumCards() + " card(s) from pile " + getPile().getName() + ".");
        GameplayViewUpdater.postGameplayMessage(GameplayMessageType.INSTRUCTION, "The card(s) must be of suit '" + getCardSuit() + "' and of value '" + getCardValue() + "'. " +
                "If the pile does not contain these cards, the draw will be unsuccessful.");
        GameplayViewUpdater.postGameplayMessage(GameplayMessageType.INSTRUCTION, "You can click the 'Skip Action' button to proceed to a different action. If you are unsure, click the PLAY button, and read the messages that proceed.");

        GameState.getInstance().getLock().lock();

        if (playerInactive() || skipAction()) {
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
        GameplayViewUpdater.postGameplayMessage(GameplayMessageType.INFO, finishedGameplayMessage());

        GameplayViewUpdater.disableAllButtonsExceptEndTurn();
        GameState.getInstance().getLock().lock();
        launchPostRules();
    }

}
