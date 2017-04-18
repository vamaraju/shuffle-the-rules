/*
* Requirements mandating inclusion:
*
* Need to update with anything Gameplay related that this class takes care of (showing)
* */
package view.Gameplay;


import controller.GameplayController;
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

    /* views */
    private GameplayMessageView gameplayMessageView;
    private TableGridView table; /* TODO update in near future to use values from TableGrid in GameCreation*/

    /* controllers */
    private GameplayController gameplayController = new GameplayController(this);


    public GameplayView(){
        initialize();
    }

    public void initialize(){


        /* centre pane will be the table */
        /* need to pass in TableGrid values*/
        table = new TableGridView();
        table.enableBackgroundImage();
        this.setCenter(table);


        /* right pane will be where text is displayed for Events and Actions */
        gameplayMessageView = new GameplayMessageView();
        this.setRight(gameplayMessageView);


        /* bottom pane will be where hand and buttons are displayed */
        HBox selectedPileAndButtons = new HBox();

        VBox pileArea = new VBox();
        Label handSelectionMessage = new Label("Control click to select and deselect multiple cards");
        PileView selectedPileView = new PileView(Orientation.HORIZONTAL);

        pileArea.getChildren().addAll(handSelectionMessage, new Separator(), selectedPileView);

        VBox gameplayButtons = new VBox(15);

        endTurnButton.setPrefWidth(150);
        endGameButton.setPrefWidth(150);
        showHandButton.setPrefWidth(150);
        sortHandButton.setPrefWidth(150);

        gameplayButtons.getChildren().addAll(endTurnButton, showHandButton, sortHandButton, endGameButton);


        /* buttons will be disabled unless condition satisified */
        endTurnButton.setDisable(true);
        sortHandButton.setDisable(true);

        selectedPileAndButtons.setHgrow(selectedPileView, Priority.ALWAYS);
        selectedPileAndButtons.setHgrow(handSelectionMessage, Priority.ALWAYS);
        selectedPileAndButtons.setMaxHeight(300);
        selectedPileAndButtons.getChildren().addAll(pileArea, new Separator(Orientation.VERTICAL), gameplayButtons);

        this.setBottom(selectedPileAndButtons);

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
}
