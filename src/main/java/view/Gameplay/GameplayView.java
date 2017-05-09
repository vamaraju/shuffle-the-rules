/*
* Requirements mandating inclusion:
*
* 3.2.2.1.3.1 User can view their Hand.
* 3.2.2.1.3.2 User can sort their Hand.
* 3.2.2.2.3.1 User can select Card(s) from Hand to play.
* 3.2.2.2.3.2 User can select Card(s) from Hand to discard.
* 3.2.2.2.3.3 User can choose Card orientation (face up, face down).
* 3.2.2.2.3.4 User can select Card(s) to swap between Piles.
* 3.2.2.2.3.5 User can draw Card(s) from a Pile.
* 3.2.2.2.3.6 User can place Card(s) on a Pile.
* 3.2.2.3.3.1 Player will be notified when their Turn begins.
* 3.2.2.3.3.2 Player will be notified when their Turn ends.
* 3.2.2.3.3.2 Player's Turn will be skipped if Player is inactive.
* 3.2.2.4.3.1 Player can end game.
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
        gameplayMessageView.setMinWidth(420);

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
