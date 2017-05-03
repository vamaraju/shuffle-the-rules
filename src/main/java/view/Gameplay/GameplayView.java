/*
* Requirements mandating inclusion:
*
* Need to update with anything Gameplay related that this class takes care of (showing)
* */
package view.Gameplay;


import controller.GameplayMode.GameplayController;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import model.GameCreation;
import model.GameView;
import model.Piles.Pile;
import model.TableGrid;


public class GameplayView extends BorderPane {

    private GameplayController controller;

    private GameplayTableGridView tableView;
    private GameplayMessageView gameplayMessageView;
    private PileView selectedPileView;


    public GameplayView() {
        initialize();
    }


    public void initialize() {
        controller = new GameplayController(this);
        GameView.getInstance().setGameplayView(this);

        tableView = new GameplayTableGridView((TableGrid) GameCreation.getInstance().getTableGrid().copy());
        populatePiles();


        this.setCenter(tableView);


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


    public void populatePiles() {
        // Regenerate the card pool in GameCreation using the latest CardSettings.
        // Then, populate all the piles using this card pool.
        GameCreation.getInstance().setCardPool(GameCreation.getInstance().getCardSettings().generateCardPool());
        for (Pile p : tableView.getTableGrid().getPiles()) {
            p.populate(GameCreation.getInstance().getCardPool());
        }
    }

    public GameplayMessageView getGameplayMessageView(){
        return this.gameplayMessageView;
    }

    public GameplayTableGridView getTableView() {
        return tableView;
    }

    public PileView getSelectedPileView() {
        return selectedPileView;
    }

    public GameplayController getController() {
        return controller;
    }


}
