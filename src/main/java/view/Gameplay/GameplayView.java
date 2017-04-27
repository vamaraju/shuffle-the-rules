/*
* Requirements mandating inclusion:
*
* Need to update with anything Gameplay related that this class takes care of (showing)
* */
package view.Gameplay;


import controller.GameplayController;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.*;
import view.TableTab.TableGridView;



public class GameplayView extends BorderPane {


    private Button endTurnButton = new Button("End Turn");
    private Button sortHandButton = new Button("Sort Hand");
    private Button endGameButton = new Button("End Game");
    private Button showHandButton = new Button("Show Hand");

    private TableGridView table; /* TODO update in near future to use values from TableGrid in GameCreation*/
    private GameplayMessageView gameplayMessageView;
    private PileView selectedPileView;

    private GameplayController gameplayController = new GameplayController(this);


    public GameplayView(){
        initialize();
    }

    public void initialize(){

        /* centre pane will be the table */
        /* TODO need to pass in TableGrid values*/
        table = new TableGridView();
        table.enableBackgroundImage();
        this.setCenter(table);


        /* right pane will be where text is displayed for Events and Actions */
        gameplayMessageView = new GameplayMessageView();
        this.setRight(gameplayMessageView);


        /* bottom pane will be where hand and buttons are displayed */
        HBox displayedPileAndButtons = new HBox();

        selectedPileView = new PileView(Orientation.HORIZONTAL);

        VBox displayedPile = new VBox();
        Label pileWarningMessage = new Label("Control click to select and deselect multiple cards");
        pileWarningMessage.setStyle("-fx-font-weight: bold");

        displayedPile.getChildren().addAll(pileWarningMessage, new Separator(), selectedPileView);

        VBox gameplayButtons = new VBox(15, endTurnButton, showHandButton, sortHandButton, endGameButton);
        gameplayButtons.setPadding(new Insets(10, 20, 10, 20));
        endTurnButton.setMaxWidth(Double.MAX_VALUE);
        endGameButton.setMaxWidth(Double.MAX_VALUE);
        showHandButton.setMaxWidth(Double.MAX_VALUE);
        sortHandButton.setMaxWidth(Double.MAX_VALUE);



        /* buttons will be disabled unless condition satisified */
        gameplayController.disableEndTurnButton();

        displayedPileAndButtons.setHgrow(displayedPile, Priority.ALWAYS);
        displayedPileAndButtons.setMaxHeight(300);
        displayedPileAndButtons.getChildren().addAll(displayedPile, new Separator(Orientation.VERTICAL), gameplayButtons);

        this.setBottom(displayedPileAndButtons);

        endTurnButton.setOnAction(gameplayController::onEndTurnButtonClick);
        showHandButton.setOnAction(gameplayController::onShowHandButtonClick);
        sortHandButton.setOnAction(gameplayController::onSortHandButtonClick);
        endGameButton.setOnAction(gameplayController::onEndGameButtonClick);

    }

    public Button getEndTurnButton() {
        return endTurnButton;
    }

    public Button getSortHandButton() {
        return sortHandButton;
    }

    public Button getEndGameButton() {
        return endGameButton;
    }

    public GameplayMessageView getGameplayMessageView(){
        return this.gameplayMessageView;
    }

    public Button getShowHandButton() {
        return showHandButton;
    }

    public TableGridView getTable() {
        return table;
    }

    public PileView getSelectedPileView() {
        return selectedPileView;
    }

    public GameplayController getGameplayController() {
        return gameplayController;
    }
}