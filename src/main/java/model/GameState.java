/*
* Requirements mandating inclusion:
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

    private Player activePlayer;
    private TableGrid currentTableGrid;
    private GameLock lock = new GameLock();
    private Pile selectedPile;
    private List<Card> clickedCards = new ArrayList<>();
    private boolean skipActionClicked = false;

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


    public Player getActivePlayer() {
        return activePlayer;
    }

    public void setActivePlayer(Player activePlayer) {
        this.activePlayer = activePlayer;
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
}