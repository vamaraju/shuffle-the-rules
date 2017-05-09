/*
* Requirements mandating inclusion:
*
* This class stores information about the current game/player state in Gameplay mode.
* It is needed for various requirements relating to Gameplay mode.
*
* 3.2.2.2.3.5 User can draw Card(s) from a Pile.
* 3.2.2.2.3.6 User can place Card(s) on a Pile.
* 3.2.2.3.3.1 Player will be notified when their Turn begins.
* 3.2.2.3.3.2 Player will be notified when their Turn ends.
* 3.2.2.3.3.2 Player's Turn will be skipped if Player is inactive.
* 3.2.2.4.3.1 Player can end game.
* 3.2.2.5.3.1 Rule Interpreter will interpret and execute Game Rules for a Card Game.
* */
package model;

import model.Piles.Pile;

import java.util.ArrayList;
import java.util.List;

/**
 * Singleton that stores information regarding the current state of the game (in Gameplay Mode).
 */
public class GameState {

    private static GameState instance = new GameState();

    private Player currentPlayer;
    private TableGrid currentTableGrid;
    private GameLock lock = new GameLock();
    private Pile selectedPile;
    private List<Card> clickedCards = new ArrayList<>();
    private boolean skipActionClicked = false;
    private boolean skipTurnClicked = false;
    private boolean actionPhaseCompleted = false;
    private boolean gameCompleted = false;
    private GameRule currentRule;

    /**
     * Private constructor to block anyone from creating a new instance of this class.
     */
    private GameState() {

    }


    /**
     * Accessor that returns the global (static) instance of GameCreation.
     *
     * @return Current version of GameCreation.
     */
    public static GameState getInstance() {
        return instance;
    }


    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public TableGrid getCurrentTableGrid() {
        return currentTableGrid;
    }

    public void setCurrentTableGrid(TableGrid currentTableGrid) {
        this.currentTableGrid = currentTableGrid;
    }

    public GameLock getLock() {
        return lock;
    }

    public List<Card> getClickedCards() {
        return clickedCards;
    }

    public void updateClickedCards(List<Card> cardList) {
        clickedCards.clear();
        for (Card c : cardList) {
            clickedCards.add(c);
        }
    }

    public Pile getSelectedPile() {
        return selectedPile;
    }

    public void setSelectedPile(Pile selectedPile) {
        this.selectedPile = selectedPile;
    }

    public boolean isSkipActionClicked() {
        return skipActionClicked;
    }

    public void setSkipActionClicked(boolean skipActionClicked) {
        this.skipActionClicked = skipActionClicked;
    }

    public boolean isSkipTurnClicked() {
        return skipTurnClicked;
    }

    public void setSkipTurnClicked(boolean skipTurnClicked) {
        this.skipTurnClicked = skipTurnClicked;
    }

    public boolean isActionPhaseCompleted() {
        return actionPhaseCompleted;
    }

    public void setActionPhaseCompleted(boolean actionPhaseCompleted) {
        this.actionPhaseCompleted = actionPhaseCompleted;
    }

    public boolean isGameCompleted() {
        return gameCompleted;
    }

    public void setGameCompleted(boolean gameCompleted) {
        this.gameCompleted = gameCompleted;
    }

    public GameRule getCurrentRule() {
        return currentRule;
    }

    public void setCurrentRule(GameRule currentRule) {
        this.currentRule = currentRule;
    }
}