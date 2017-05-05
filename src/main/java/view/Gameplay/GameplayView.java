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
import model.*;
import model.Piles.Pile;


public class GameplayView extends BorderPane {

    private GameplayController controller;

    private GameplayTableGridView tableView;
    private GameplayMessageView gameplayMessageView;
    private PileView selectedPileView;
    private GameplayButtonView gameplayButtons;
    private VBox selectedPileVBox;
    private HBox selectedPileAndButtons;
    private Label pileViewWarningMessage = new Label("Control-click to select and deselect multiple cards");

    public GameplayView() {
        initialize();
    }


    public void initialize() {
        controller = new GameplayController(this);
        GameView.getInstance().setGameplayView(this);

        tableView = new GameplayTableGridView(GameCreation.getInstance().getTableGrid());
        populatePiles();

        gameplayMessageView = new GameplayMessageView();

        selectedPileView = new PileView();
        gameplayButtons = new GameplayButtonView();

        selectedPileVBox = new VBox();
        selectedPileVBox.getChildren().addAll(pileViewWarningMessage, new Separator(), selectedPileView);
        pileViewWarningMessage.setStyle("-fx-font-weight: bold");

        /* bottom pane will be where hand and buttons are displayed */
        selectedPileAndButtons = new HBox();
        selectedPileAndButtons.setHgrow(selectedPileVBox, Priority.ALWAYS);
        selectedPileAndButtons.setMaxHeight(300);
        selectedPileAndButtons.getChildren().addAll(selectedPileVBox, new Separator(Orientation.VERTICAL), gameplayButtons);

        this.setCenter(tableView);
        this.setRight(gameplayMessageView);
        this.setBottom(selectedPileAndButtons);

        gameplayButtons.disableSkipActionButton();
//        gameplayButtons.disableSwapCardsButton();
        gameplayButtons.disableEndTurnButton();

        GameState.getInstance().getLock().unlock();
        GameState.getInstance().getLock().lock();
        RuleInterpreter.execute(GameCreation.getInstance().getRuleGraph());
    }


    public void populatePiles() {
        // Regenerate the card pool in GameCreation using the latest CardSettings.
        // Then, populate all the piles using this card pool.
        GameCreation.getInstance().setCardPool(GameCreation.getInstance().getCardSettings().generateCardPool());
        for (Pile p : tableView.getTableGrid().getPiles()) {
            p.populate(GameCreation.getInstance().getCardPool());
        }
        for (Player p : GameCreation.getInstance().getPlayers()) {
            p.getHand().populate(GameCreation.getInstance().getCardPool());
        }
    }

    public GameplayController getController() {
        return controller;
    }

    public GameplayMessageView getGameplayMessageView(){
        return this.gameplayMessageView;
    }

    public GameplayButtonView getGameplayButtonView() {
        return this.gameplayButtons;
    }

    public GameplayTableGridView getTableView() {
        return tableView;
    }

    public PileView getSelectedPileView() {
        return selectedPileView;
    }
}
