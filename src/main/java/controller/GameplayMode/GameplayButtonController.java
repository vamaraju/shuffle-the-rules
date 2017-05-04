package controller.GameplayMode;

import javafx.event.ActionEvent;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Toggle;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;
import model.*;
import view.Gameplay.GameplayButtonView;
import view.Gameplay.SortDialog;

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
            if (sortDialog.getSelectedToggle() == null) {return;}
            SortType sortSelection = (SortType) sortDialog.getSelectedToggle().getUserData();
            switch (sortSelection) {
                case ASCENDING:
                    if (sortDialog.getSuitCheckBox().isSelected()) {
                        System.out.println("Sort ascending, with suits separated");
                    } else {
                        System.out.println("Sort ascending, ignore suits");
                    }
                    break;
                case DESCENDING:
                    if (sortDialog.getSuitCheckBox().isSelected()) {
                        System.out.println("Sort descending, with suits separated");
                    } else {
                        System.out.println("Sort descending, ignore suits");
                    }
                    break;
            }
        }
    }


    public void onSwapCardsButtonClick(ActionEvent e) {

    }


    public void onEndTurnButtonClick(ActionEvent e) {

    }


    public void onEndGameButtonClick(ActionEvent e) {

    }


    public void onBecomeInactiveButtonClick(ActionEvent e) {

    }
}
