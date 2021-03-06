/*
* Requirements mandating inclusion:
*
* This class is a singleton that stores crucial game information, and is responsible for saving/loading games.
* It is both directly and indirectly related to almost all of our requirements.
* */

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
import view.TableTab.TableGridPropertiesView;
import view.TableTab.TableGridView;

import java.io.*;
import java.util.*;

/**
 * Singleton that stores information regarding creation of the current game.
 */
public class GameCreation implements Serializable {

    private static GameCreation instance = new GameCreation();

    private ArrayList<GameEvent> gameEvents;
    private ArrayList<GameAction> gameActions;
    private ArrayList<Player> players;
    private CardSettings cardSettings;
    private GameSettings gameSettings;
    private List<Card> cardPool;

    private transient ArrayList<RuleElementRectangleBlueprint> rectangleBlueprints;
    private TableGrid tableGrid;
    private RuleGraph ruleGraph;

    /**
     * Private constructor to block anyone from creating a new instance of this class.
     */
    private GameCreation() {
        /* initialize */
        gameEvents = new ArrayList<>();
        gameActions = new ArrayList<>();
        players = new ArrayList<>();
        cardSettings = new CardSettings();
        gameSettings = new GameSettings();
        cardPool = getDefaultCardPool();
        rectangleBlueprints = new ArrayList<>();
        tableGrid = new TableGrid();
        ruleGraph = null;
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
        instance.players.clear();
        instance.cardSettings.reset();
        instance.gameSettings.reset();
        instance.rectangleBlueprints.clear();
        instance.tableGrid.reset();
    }


    public static void resetViews() {
        GameView.getInstance().getEditorTab().clearAllInputs();
        GameView.getInstance().getTableTab().getTableGridPropertiesView().resetInputsToDefaults();
        GameView.getInstance().getTableTab().getTableGridView().resetGrid();
        GameView.getInstance().getTableTab().getGeneralSettingsMenu().clearAllInputs();
        GameView.getInstance().getTableTab().getPileSettingsMenu().clearAllInputs();
        GameView.getInstance().getTableTab().getCardRestrictionSettingsMenu().clearAllInputs();
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

    public Player getPlayerFromPlayerName(String playerName) {
        for (Player p : players) {
            if (p.getName().equals(playerName)) {
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

    public List<Card> getCardPool() {
        return cardPool;
    }

    public void setCardPool(List<Card> cardPool) {
        this.cardPool = cardPool;
    }

    public List<Card> getDefaultCardPool() {
        List<Card> cardPool = new LinkedList<>();
        for (PlayingCard c : PlayingCard.values()) {
            for (Suit s : Suit.values()) {
                cardPool.add(new Card(c, s, CardOrientation.DOWN));
            }
        }
        Collections.shuffle(cardPool);
        return cardPool;
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
    *   RuleGraph
    *
    * ************************************************************************************************************* */

    public RuleGraph getRuleGraph() {
        return ruleGraph;
    }

    public void setRuleGraph(RuleGraph ruleGraph) {
        this.ruleGraph = ruleGraph;
    }

    /* *************************************************************************************************************
    *   Serialization (Saving and Loading)
    *
    * ************************************************************************************************************* */


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
            if (r.getGameRuleClassName().equals("OnGameStartEvent")) {
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
        drawingPane.extendToFit();
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
        view.setGridLines(this.tableGrid.isHideGrid());
    }

    private void loadTableGridProperties() {
        TableGridPropertiesView view = GameView.getInstance().getTableTab().getTableGridPropertiesView();
        view.getGridWidthTextField().setText(Integer.toString(this.tableGrid.getNumCols()));
        view.getGridHeightTextField().setText(Integer.toString(this.tableGrid.getNumRows()));
        view.getGridCellWidthTextField().setText(Double.toString(this.tableGrid.getCellWidth()));
        view.getGridHideCheckBox().setSelected(this.tableGrid.isHideGrid());
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
        loadTableGridProperties();
        loadTableGrid();
    }


}
