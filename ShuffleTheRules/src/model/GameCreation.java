package model;

import model.GameActions.GameAction;
import model.GameEvents.GameEvent;
import model.Piles.Pile;

import java.io.*;
import java.util.ArrayList;

/**
 * Singleton that stores information regarding creation of the current game.
 */
public class GameCreation implements Serializable {

    private static GameCreation instance = new GameCreation();

    private ArrayList<GameEvent> gameEvents = new ArrayList<>();
    private ArrayList<GameAction> gameActions = new ArrayList<>();
    private ArrayList<Pile> piles = new ArrayList<>();
    private ArrayList<Player> players = new ArrayList<>();
    private CardSettings cardSettings;
    private GameSettings gameSettings;


    /**
     * Private constructor to block anyone from creating a new instance of this class.
     */
    private GameCreation() {

    }


    /**
     * Accessor that returns the global (static) instance of GameCreation.
     *
     * @return Current version of GameCreation.
     */
    public static GameCreation getInstance() {
        return instance;
    }


    /**
     * Static method to serialize (save) the current GameCreation instance to file.
     *
     * @param filename File name to which the current instance of GameCreation will be serialized.
     */
    public static void serializeToFile(String filename) {
        try {
            FileOutputStream fOut = new FileOutputStream(filename);
            ObjectOutputStream oOut = new ObjectOutputStream(fOut);
            oOut.writeObject(GameCreation.getInstance());
            oOut.close();
            fOut.close();
        } catch(IOException e) {
            System.out.println("Error while serializing GameCreation to file: " + filename);
            e.printStackTrace();
        }
    }


    /**
     * Static method to de-serialize (load) an instance of GameCreation from file and store it as the current version.
     * Note that the current instance of GameCreation will be lost and updated with the loaded version.
     *
     * @param filename File name from which the GameCreation instance will be de-serialized.
     */
    public static void deserializeFromFile(String filename) {
        try {
            FileInputStream fIn = new FileInputStream(filename);
            ObjectInputStream oIn = new ObjectInputStream(fIn);
            GameCreation.instance = (GameCreation) oIn.readObject();
            oIn.close();
            fIn.close();
        } catch(IOException e) {
            System.out.println("Error while de-serializing GameCreation from file: " + filename);
            e.printStackTrace();
        } catch(ClassNotFoundException e) {
            System.out.println("GameCreation class not found - error while de-serializing from file: " + filename);
            e.printStackTrace();
        }
    }


    public ArrayList<GameEvent> getGameEvents() {
        return gameEvents;
    }


    public void setGameEvents(ArrayList<GameEvent> gameEvents) {
        this.gameEvents = gameEvents;
    }


    public ArrayList<GameAction> getGameActions() {
        return gameActions;
    }


    public void setGameActions(ArrayList<GameAction> gameActions) {
        this.gameActions = gameActions;
    }


    public ArrayList<Pile> getPiles() {
        return piles;
    }


    public void setPiles(ArrayList<Pile> piles) {
        this.piles = piles;
    }


    public ArrayList<Player> getPlayers() {
        return players;
    }


    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }


    public CardSettings getCardSettings() {
        return cardSettings;
    }


    public void setCardSettings(CardSettings cardSettings) {
        this.cardSettings = cardSettings;
    }


    public GameSettings getGameSettings() {
        return gameSettings;
    }


    public void setGameSettings(GameSettings gameSettings) {
        this.gameSettings = gameSettings;
    }
}
