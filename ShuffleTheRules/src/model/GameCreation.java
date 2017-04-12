package model;

import javafx.scene.Node;
import javafx.scene.shape.Line;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import model.GameActions.GameAction;
import model.GameEvents.GameEvent;
import model.Piles.Pile;
import view.RuleElementRectangle;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Singleton that stores information regarding creation of the current game.
 */
public class GameCreation implements Serializable {

    private static GameCreation instance = new GameCreation();

    private ArrayList<GameEvent> gameEvents = new ArrayList<>();
    private ArrayList<GameAction> gameActions = new ArrayList<>();
    //private ArrayList<Pile> piles = new ArrayList<>();
    private HashMap<String, Pile> piles;
    private ArrayList<Player> players = new ArrayList<>();
    private CardSettings cardSettings;
    private GameSettings gameSettings;

    private transient ArrayList<RuleElementRectangleBlueprint> rectangleBlueprints = new ArrayList<>();

    /**
     * Private constructor to block anyone from creating a new instance of this class.
     */
    private GameCreation() {
        /* initialize */
        cardSettings = new CardSettings();
        gameSettings = new GameSettings();
        piles = new HashMap<>();
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
     * Resets/Clears the current instance of GameCreation to a new copy. Re-instantiates GameCreation.
     */
    public static void resetInstance() {
        instance = new GameCreation();
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
            GameCreation g = (GameCreation) oIn.readObject();
            GameCreation.instance = g;
            oIn.close();
            fIn.close();
        } catch (StreamCorruptedException e) {
            String errorMsg = "The selected file is not a valid Shuffle The Rules game file: \n" + filename + "\nLoad aborted.";
            Alert alert = new Alert(Alert.AlertType.WARNING, errorMsg);
            alert.setTitle("Load Game Error");
            alert.setHeaderText("Invalid Game File");
            alert.showAndWait();
        } catch(IOException e) {
            System.out.println("Error while de-serializing GameCreation from file: " + filename);
            e.printStackTrace();
        } catch(ClassNotFoundException e) {
            System.out.println("GameCreation class not found - error while de-serializing from file: " + filename);
            e.printStackTrace();
        }
    }

    /* ************************************************************************************************************
    *   Events
    *
    * *********************************************************************************************************** */

    public ArrayList<GameEvent> getGameEvents() {
        return gameEvents;
    }


    public void setGameEvents(ArrayList<GameEvent> gameEvents) {
        this.gameEvents = gameEvents;
    }


    /* ************************************************************************************************************
    *   Actions
    *
    * *********************************************************************************************************** */

    public ArrayList<GameAction> getGameActions() {
        return gameActions;
    }


    public void setGameActions(ArrayList<GameAction> gameActions) {
        this.gameActions = gameActions;
    }


    /* ************************************************************************************************************
    *   Piles
    *
    * ************************************************************************************************************ */

    public HashMap<String, Pile> getPilesHashMap() {
        return piles;
    }

    public void setPilesHashMap(HashMap<String, Pile> piles) {
        this.piles = piles;
    }

    public List<String> getNamesOfAllPiles(){
        return new ArrayList<>(piles.keySet());
    }
    /*
    public List<Pile> getAllPiles(){

    }
    */
    public Pile getPileByName(String pileName){
        if(piles.containsKey(pileName)) {
            return piles.get(pileName);
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING, "The Pile " + pileName + "does not exist.");
            alert.setTitle("Pile Missing Error");
            alert.setHeaderText("Error - Pile does not exist");
            alert.showAndWait();
            return null;
        }
    }

    public void addPile(Pile pile){
        if(!piles.containsKey(pile.getName())) {
            piles.put(pile.getName(), pile);
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING, "The Pile " + pile.getName() + "cannot be added because a pile with that name already exists. Please rename the pile.");
            alert.setTitle("Pile Already Exists Error");
            alert.setHeaderText("Error - Cannot Create Pile");
            alert.showAndWait();
        }
    }

    public void removePile(String pileName){
        if (piles.containsKey(pileName)) {
            piles.remove(pileName);
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING, "The Pile you are trying to remove does not exist.");
            alert.setTitle("Pile Does Not Exist Error");
            alert.setHeaderText("Error - Cannot Remove Pile");
            alert.showAndWait();
        }

    }

    /* *************************************************************************************************************
    *   Players
    *
    * ************************************************************************************************************* */

    public ArrayList<Player> getPlayers() {
        return players;
    }


    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    /* *************************************************************************************************************
    *   CardSettings
    *
    * ************************************************************************************************************* */
    public CardSettings getCardSettings() {
        return cardSettings;
    }


    public void setCardSettings(CardSettings cardSettings) {
        this.cardSettings = cardSettings;
    }

    /* *************************************************************************************************************
    *   GameSettings
    *
    * ************************************************************************************************************* */

    public GameSettings getGameSettings() {
        return gameSettings;
    }


    public void setGameSettings(GameSettings gameSettings) {
        this.gameSettings = gameSettings;
    }


    private void updateRectangleAssociations(ArrayList<RuleElementRectangle> rectangles) {
        updateRectangleAssociations(rectangles, rectangles.get(0));
    }

    private void updateRectangleAssociations(ArrayList<RuleElementRectangle> rectangles, RuleElementRectangle currentRect) {
        for (int i = 0; i < currentRect.getPostRules().size(); i++) {
            for (RuleElementRectangle rect : rectangles) {
                if (currentRect.getPostRules().get(i).equals(rect)) {currentRect.getPostRules().set(i, rect); break;}
            }
            RuleElementRectangle postRule = currentRect.getPostRules().get(i);
            updateLineAssociations(currentRect, postRule);
            updateRectangleAssociations(rectangles, postRule);
        }
    }

    private void updateLineAssociations(RuleElementRectangle currentRect, RuleElementRectangle postRule) {
        for (Line outLine : currentRect.getOutLines()) {
            for (int i = 0; i < postRule.getInLines().size(); i++) {
                if (equalLines(outLine, postRule.getInLines().get(i))) {
                    postRule.getInLines().set(i, outLine);
                }
            }
        }
    }

    private boolean equalLines(Line l1, Line l2) {
        return (l1.getStartX() == l2.getStartX()) && (l1.getStartY() == l2.getStartY()) && (l1.getEndX() == l2.getEndX()) && (l1.getEndY() == l2.getEndY());
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();

        this.rectangleBlueprints = new ArrayList<>();
        Pane drawingPane = GameView.getInstance().getEditorTab().getEditorDrawingPane();
        for (Node child : drawingPane.getChildren()) {
            if (child instanceof RuleElementRectangle) {
                this.rectangleBlueprints.add(new RuleElementRectangleBlueprint((RuleElementRectangle)child));
            }
        }

        out.writeObject(this.rectangleBlueprints);
    }


    private void readObject(ObjectInputStream in) throws ClassNotFoundException, IOException {
        in.defaultReadObject();

        this.rectangleBlueprints = (ArrayList<RuleElementRectangleBlueprint>) in.readObject();
        Pane drawingPane = new Pane();
        drawingPane.setOnMouseDragged(GameView.getInstance().getEditorTab().getController()::drawingPaneOnMouseDragged);

        ArrayList<RuleElementRectangle> rectangles = new ArrayList<>();
        for (RuleElementRectangleBlueprint rectangleBlueprint : this.rectangleBlueprints) {
            RuleElementRectangle r = new RuleElementRectangle(rectangleBlueprint);
            if (r.getGameRuleName().equals("OnGameStartEvent")) {
                rectangles.add(0, r);
            } else {
                rectangles.add(r);
            }

            drawingPane.getChildren().add(r);
            drawingPane.getChildren().add(r.getTextObj());
            for (Line l : r.getOutLines()) {
                drawingPane.getChildren().add(l);
            }

            r.setOnMouseClicked(GameView.getInstance().getEditorTab().getController()::onRectangleClicked);
            r.getTextObj().setOnMouseClicked(GameView.getInstance().getEditorTab().getController()::onRectangleClicked);
        }

        updateRectangleAssociations(rectangles);
        GameView.getInstance().getEditorTab().setEditorDrawingPane(drawingPane);
    }

}
