package controller.GameplayMode;

import javafx.event.ActionEvent;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Toggle;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;
import model.*;
import model.Piles.Hand;
import view.Gameplay.GameplayButtonView;
import view.Gameplay.SortDialog;
import view.Gameplay.SwapDialog;

import java.util.Optional;

public class GameplayButtonController {

    private GameplayButtonView view;


    public GameplayButtonController(GameplayButtonView view) {
        this.view = view;
    }


    public void onPlayButtonClick(ActionEvent e) {

    }


    public void onShowHandButtonClick(ActionEvent e) {
        Player currentPlayer = GameState.getInstance().getActivePlayer();
        if (GameState.getInstance().getActivePlayer() == null) {
            return;
        }
        GameView.getInstance().getGameplayView().getSelectedPileView().updatePile(currentPlayer.getHand());
    }


    public void onSortHandButtonClick(ActionEvent e) {
        SortDialog sortDialog = new SortDialog();
        Optional<ButtonType> buttonClickResult = sortDialog.showAndWait();

        if (buttonClickResult.isPresent() && buttonClickResult.get() == ButtonType.APPLY) {
            SortType sortSelection = null;
            if (sortDialog.getSelectedToggle() != null) {
                sortSelection = (SortType) sortDialog.getSelectedToggle().getUserData();
            }

            Player currentPlayer = GameState.getInstance().getActivePlayer();
            currentPlayer.getHand().sort(sortSelection, sortDialog.getSuitCheckBox().isSelected());
            GameView.getInstance().getGameplayView().getSelectedPileView().updatePile(currentPlayer.getHand());
        }
    }


    public void onSwapCardsButtonClick(ActionEvent e) {
        SwapDialog swapDialog = new SwapDialog();
        Optional<ButtonType> buttonClickResult = swapDialog.showAndWait();

    }


    public void onEndTurnButtonClick(ActionEvent e) {

    }


    public void onEndGameButtonClick(ActionEvent e) {

    }


    public void onBecomeInactiveButtonClick(ActionEvent e) {

    }
}
