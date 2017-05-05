package controller.GameplayMode;

import javafx.event.ActionEvent;
import javafx.scene.control.ButtonType;
import model.*;
import view.Gameplay.GameplayButtonView;
import view.Gameplay.GameplayView;
import view.Gameplay.SortDialog;
import view.Gameplay.SwapDialog;

import java.util.List;
import java.util.Optional;

public class GameplayButtonController {

    private GameplayButtonView view;


    public GameplayButtonController(GameplayButtonView view) {
        this.view = view;
    }


    public void onPlayButtonClick(ActionEvent e) {
        List<Card> selectedCards = GameView.getInstance().getGameplayView().getSelectedPileView().getSelectedCards();
        GameState.getInstance().updateClickedCards(selectedCards);
        GameState.getInstance().getLock().unlock();
    }


    public void onSkipActionButtonClick(ActionEvent e) {
        GameState.getInstance().setSkipActionClicked(true);
        GameState.getInstance().getLock().unlock();
    }


    public void onShowHandButtonClick(ActionEvent e) {
        Player currentPlayer = GameState.getInstance().getActivePlayer();
        if (GameState.getInstance().getActivePlayer() == null) {
            return;
        }
        GameView.getInstance().getGameplayView().getSelectedPileView().updatePile(currentPlayer.getHand());
        GameState.getInstance().setSelectedPile(currentPlayer.getHand());
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
        GameState.getInstance().getLock().unlock();
    }


    public void onEndGameButtonClick(ActionEvent e) {

    }


    public void onBecomeInactiveButtonClick(ActionEvent e) {
        GameState.getInstance().getActivePlayer().setInactive(true);
        GameView.getInstance().getGameplayView().getGameplayMessageView().addMessage(GameplayMessageType.INFO, "Player '"
                + GameState.getInstance().getActivePlayer().getName() + "'has been set to inactive. Skipping turn.");

        GameView.getInstance().getGameplayView().getGameplayButtonView().disablePlayButton();
        GameView.getInstance().getGameplayView().getGameplayButtonView().disableSkipActionButton();
        GameView.getInstance().getGameplayView().getGameplayButtonView().disableShowHandButton();
        GameView.getInstance().getGameplayView().getGameplayButtonView().disableSortHandButton();
        GameView.getInstance().getGameplayView().getGameplayButtonView().disableSwapCardsButton();
        GameView.getInstance().getGameplayView().getGameplayButtonView().enableEndTurnButton();
        GameView.getInstance().getGameplayView().getGameplayButtonView().disableEndGameButton();
        GameView.getInstance().getGameplayView().getGameplayButtonView().disableBecomeInactiveButton();
    }
}
