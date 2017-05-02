package model;

import javafx.scene.Node;
import javafx.scene.control.Alert;
import model.GameActions.GameAction;
import model.GameEvents.GameEvent;
import model.Piles.Pile;
import view.EditorTab.DrawingPane;
import view.EditorTab.RuleElementLine;
import view.EditorTab.RuleElementRectangle;
import view.TableTab.CardRestrictionsMenuView;
import view.TableTab.GeneralSettingsMenuView;
import view.TableTab.TableGridView;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Singleton that stores information regarding creation of the current game.
 */
public class GameCreation implements Serializable {

    private static GameCreation instance = new GameCreation();

    private ArrayList<GameEvent> gameEvents;
    private ArrayList<GameAction> gameActions;
    //private ArrayList<Pile> piles = new ArrayList<>();
    private HashMap<String, Pile> piles;
    private ArrayList<Player> players;
    private CardSettings cardSettings;
    private GameSettings gameSettings;

    private transient ArrayList<RuleElementRectangleBlueprint> rectangleBlueprints;
    private TableGrid tableGrid;

    /**
     * Private constructor to block anyone from creating a new instance of this class.
     */
    private GameCreation() {
        /* initialize */
        gameEvents = new ArrayList<>();
        gameActions = new ArrayList<>();
        piles = new HashMap<>();
        players = new ArrayList<>();
        cardSettings = new CardSettings();
        gameSettings = new GameSettings();
        rectangleBlueprints = new ArrayList<>();
        tableGrid = new TableGrid();
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
        instance.gameEvents.clear();
        instance.gameActions.clear();
        instance.piles.clear();
        instance.players.clear();
        instance.cardSettings.reset();
        instance.gameSettings.reset();
        instance.rectangleBlueprints.clear();
        instance.tableGrid = new TableGrid();
    }


    public static void resetViews() {
        GameView.getInstance().getEditorTab().clearAllInputs();
        GameView.getInstance().getTableTab().getTableGridPropertiesView().resetInputsToDefaults();
        GameView.getInstance().getTableTab().getGeneralSettingsMenu().clearAllInputs();
        GameView.getInstance().getTableTab().getPileSettingsMenu().clearAllInputs();
        GameView.getInstance().getTableTab().getCardRestrictionSettingsMenu().clearAllInputs();
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

    public Player getPlayerFromPlayerNum(int playerNum) {
        for (Player p : players) {
            if (p.getPlayerNum() == playerNum) {
                return p;
            }
        }
        return null;
    }

    public Player getNextPlayer(Player currentPlayer) {
        int nextPlayerIndex = players.indexOf(currentPlayer)+1 % players.size();
        return players.get(nextPlayerIndex);
    }

    public void resetPlayers() {
        players.clear();
    }

    public void resetPlayers(int numPlayers) {
        players.clear();
        for (int i = 0; i < numPlayers; i++) {
            players.add(new Player(i+1));
        }
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



    /* *************************************************************************************************************
    *   TableGrid
    *
    * ************************************************************************************************************* */

    public TableGrid getTableGrid() {
        return tableGrid;
    }

    public void setTableGrid(TableGrid tableGrid) {
        this.tableGrid = tableGrid;
    }



    /* *************************************************************************************************************
    *   Serialization (Saving and Loading)
    *
    * ************************************************************************************************************* */


    private void updateRectangleAssociations(ArrayList<RuleElementRectangle> rectangles) {
        updateRectangleAssociations(rectangles, rectangles.get(0));
    }

    private void updateRectangleAssociations(ArrayList<RuleElementRectangle> rectangles, RuleElementRectangle currentRect) {
        for (int i = 0; i < currentRect.getPostRules().size(); i++) {
            for (RuleElementRectangle rect : rectangles) {
                if (currentRect.getPostRules().get(i).equals(rect)) {
                    currentRect.getPostRules().set(i, rect);
                    rect.getPreRules().add(currentRect);
                    break;
                }
            }
            RuleElementRectangle postRule = currentRect.getPostRules().get(i);
            updateLineAssociations(currentRect, postRule);
            updateRectangleAssociations(rectangles, postRule);
        }
    }

    private void updateLineAssociations(RuleElementRectangle currentRect, RuleElementRectangle postRule) {
        for (RuleElementLine outLine : currentRect.getOutLines()) {
            for (int i = 0; i < postRule.getInLines().size(); i++) {
                if (outLine.equals(postRule.getInLines().get(i))) {
                    postRule.getInLines().set(i, outLine);
                }
            }
        }
    }


    private void saveDrawingPane() {
        this.rectangleBlueprints = new ArrayList<>();
        DrawingPane drawingPane = GameView.getInstance().getEditorTab().getEditorDrawingPane();
        for (Node child : drawingPane.getChildren()) {
            if (child instanceof RuleElementRectangle) {
                this.rectangleBlueprints.add(new RuleElementRectangleBlueprint((RuleElementRectangle)child));
            }
        }
    }

    private void loadDrawingPane() {
        DrawingPane drawingPane = new DrawingPane();
        drawingPane.setOnMouseDragged(GameView.getInstance().getEditorTab().getController()::drawingPaneOnMouseDragged);

        ArrayList<RuleElementRectangle> rectangles = new ArrayList<>();
        for (RuleElementRectangleBlueprint rectangleBlueprint : this.rectangleBlueprints) {
            RuleElementRectangle r = new RuleElementRectangle(rectangleBlueprint);
            if (r.getGameRuleName().equals("OnGameStartEvent")) {
                rectangles.add(0, r);
            } else {
                rectangles.add(r);
            }
            r.getPreRules().clear(); // pre-rules are populated in updateRectangleAssociations() with the correct rects

            drawingPane.addRule(r);
            for (RuleElementLine l : r.getOutLines()) {
                drawingPane.addLine(l);
            }

            r.setOnMouseClicked(GameView.getInstance().getEditorTab().getController()::onRectangleClicked);
            r.getTextObj().setOnMouseClicked(GameView.getInstance().getEditorTab().getController()::onRectangleClicked);
        }

        updateRectangleAssociations(rectangles);
        GameView.getInstance().getEditorTab().setEditorDrawingPane(drawingPane);
        GameView.getInstance().getEditorTab().setClickedRectangle(null);
        GameView.getInstance().getEditorTab().enableAddButtons();
    }

    private void loadGeneralSettings() {
        GeneralSettingsMenuView view = GameView.getInstance().getTableTab().getGeneralSettingsMenu();
        view.getController().setGameSettings(gameSettings);
        view.getController().setPlayers(players);

        if (gameSettings.getMinPlayers() != 0) {
            view.getMinPlayersTextField().setText(Integer.toString(gameSettings.getMinPlayers()));
        }

        if (gameSettings.getMaxPlayers() != 0) {
            view.getMaxPlayersTextField().setText(Integer.toString(gameSettings.getMaxPlayers())); // this may overwrite this.players
        }

        if (!players.isEmpty() && players.get(0).getHand() != null) {
            view.getMinHandSizeTextField().setText(Integer.toString(players.get(0).getHand().getMinSize()));
            view.getMaxHandSizeTextField().setText(Integer.toString(players.get(0).getHand().getMaxSize()));
            view.getStartingHandSizeTextField().setText(Integer.toString(players.get(0).getHand().getStartingSize()));
        }

        view.getPlayerComboBox().getItems().clear();
        view.getPlayerComboBox().getItems().addAll(players);
        view.clearPlayerSettingsInputs();
    }

    private void loadCardRestrictionSettings() {
        CardRestrictionsMenuView view = GameView.getInstance().getTableTab().getCardRestrictionSettingsMenu();
        view.clearAllInputs();
        view.getNumDecksTextField().setText(Integer.toString(cardSettings.getNumDecks()));
        view.getController().setCardSettings(cardSettings);
    }

    private void loadTableGrid() {
        TableGridView view = GameView.getInstance().getTableTab().getTableGridView();
        view.setTableGrid(this.tableGrid);
        view.drawGrid();
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        saveDrawingPane();
        out.writeObject(this.rectangleBlueprints);
    }

    private void readObject(ObjectInputStream in) throws ClassNotFoundException, IOException {
        in.defaultReadObject();
        this.rectangleBlueprints = (ArrayList<RuleElementRectangleBlueprint>) in.readObject();
        loadDrawingPane();
        loadGeneralSettings();
        loadCardRestrictionSettings();
        loadTableGrid();
    }


}
