/*
* Requirements mandating inclusion:
* */
package model;

/**
 * Singleton that stores information regarding the current state of the game (in Gameplay Mode).
 */
public class GameState {

    private static GameState instance = new GameState();

    private Player activePlayer;
    private TableGrid currentTableGrid;

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
}