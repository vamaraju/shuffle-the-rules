/*
* Requirements mandating inclusion:
*
* Need to update with anything Gameplay related that this class takes care of (showing)
* */
package view.Gameplay;


import controller.GameplayMode.GameplayController;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import model.GameCreation;
import model.GameView;
import view.TableTab.TableGridView;



public class GameplayView extends BorderPane {

    private TableGridView table;
    private GameplayMessageView gameplayMessageView;
    private PileView selectedPileView;

    ScrollPane tableScrollPane;

    private GameplayController gameplayController = new GameplayController(this);


    public GameplayView(){
        initialize();
    }

    public void initialize(){

        /*
        * Things that need to be done
        *       Create Card objects (based on CardSettings)
        *       Distribute Card objects into Piles that are in TableGrid
        *
        *
        * */
        /* centre pane will be the table */
        /* TODO on clicks in Gameplay mode will be different than onclicks in GameCreation mode.
        *  TODO either make another controller OR make the tableGridController an interface (with the onCLick needed to be implemented by each)? */
        table = new TableGridView(GameCreation.getInstance().getTableGrid());

        tableScrollPane = new ScrollPane(table);
        tableScrollPane.setFitToWidth(true);
        tableScrollPane.setFitToHeight(true);
        tableScrollPane.setStyle("-fx-focus-color: transparent;");

        table.enableBackgroundImage();
        this.setCenter(tableScrollPane);


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


//        VBox gameplayButtons = new VBox(15, showHandButton, sortHandButton, swapCardsButton, playButton,endTurnButton, endGameButton, becomeInactiveButton);
        /* TODO Will change to GridPane for formatting */
        GridPane gameplayButtons = new GameplayButtonView();
//        gameplayButtons.setPadding(new Insets(10, 20, 10, 20));




        displayedPileAndButtons.setHgrow(displayedPile, Priority.ALWAYS);
        displayedPileAndButtons.setMaxHeight(300);
        displayedPileAndButtons.getChildren().addAll(displayedPile, new Separator(Orientation.VERTICAL), gameplayButtons);

        this.setBottom(displayedPileAndButtons);



    }



    public GameplayMessageView getGameplayMessageView(){
        return this.gameplayMessageView;
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
