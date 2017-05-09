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

import javafx.application.Platform;
import javafx.scene.control.Alert;
import model.GameState;
import model.GameView;
import model.GameplayMessageType;
import model.Piles.Pile;
import model.Player;

/**
 * This class exists to safely update the GameplayView UI upon receiving gameplay events and actions.
 * Game rules use this to safely interact with the Gameplay UI and run updates on the correct thread.
 * Platform.runLater() automatically runs the given code block on the UI thread when it is free.
 * Avoids IllegalStateException.
 */
public class GameplayViewUpdater {

    public static void updateSelectedPile(Pile pile) {
        Platform.runLater(() -> GameView.getInstance().getGameplayView().getSelectedPileView().updatePile(pile));
    }

    public static void postGameplayMessage(GameplayMessageType type, String message) {
        GameplayView game = GameView.getInstance().getGameplayView();
        Platform.runLater(() -> game.getGameplayMessageView().addMessage(type, message));
    }

    public static void closeGameplayWindow() {
        Platform.runLater(() -> GameView.getInstance().getGameplayView().getScene().getWindow().hide());
    }

    public static void disablePlayButton() {
        Platform.runLater(() -> GameView.getInstance().getGameplayView().getGameplayButtonView().disablePlayButton());
    }

    public static void enablePlayButton() {
        Platform.runLater(() -> GameView.getInstance().getGameplayView().getGameplayButtonView().enablePlayButton());
    }

    public static void disableSkipActionButton() {
        Platform.runLater(() -> GameView.getInstance().getGameplayView().getGameplayButtonView().disableSkipActionButton());
    }

    public static void enableSkipActionButton() {
        Platform.runLater(() -> GameView.getInstance().getGameplayView().getGameplayButtonView().enableSkipActionButton());
    }

    public static void disableShowHandButton() {
        Platform.runLater(() -> GameView.getInstance().getGameplayView().getGameplayButtonView().disableShowHandButton());
    }

    public static void enableShowHandButton() {
        Platform.runLater(() -> GameView.getInstance().getGameplayView().getGameplayButtonView().enableShowHandButton());
    }

    public static void disableSortHandButton() {
        Platform.runLater(() -> GameView.getInstance().getGameplayView().getGameplayButtonView().disableSortHandButton());
    }

    public static void enableSortHandButton() {
        Platform.runLater(() -> GameView.getInstance().getGameplayView().getGameplayButtonView().enableSortHandButton());
    }

    public static void disableSwapCardsButton() {
        Platform.runLater(() -> GameView.getInstance().getGameplayView().getGameplayButtonView().disableSwapCardsButton());
    }

    public static void enableSwapCardsButton() {
        Platform.runLater(() -> GameView.getInstance().getGameplayView().getGameplayButtonView().enableSwapCardsButton());
    }

    public static void disableEndTurnButton() {
        Platform.runLater(() -> GameView.getInstance().getGameplayView().getGameplayButtonView().disableEndTurnButton());
    }

    public static void enableEndTurnButton() {
        Platform.runLater(() -> GameView.getInstance().getGameplayView().getGameplayButtonView().enableEndTurnButton());
    }

    public static void disableEndGameButton() {
        Platform.runLater(() -> GameView.getInstance().getGameplayView().getGameplayButtonView().disableEndGameButton());
    }

    public static void enableEndGameButton() {
        Platform.runLater(() -> GameView.getInstance().getGameplayView().getGameplayButtonView().enableEndGameButton());
    }

    public static void disableBecomeInactiveButton() {
        Platform.runLater(() -> GameView.getInstance().getGameplayView().getGameplayButtonView().disableBecomeInactiveButton());
    }

    public static void enableBecomeInactiveButton() {
        Platform.runLater(() -> GameView.getInstance().getGameplayView().getGameplayButtonView().enableBecomeInactiveButton());
    }

    public static void disableAllButtons() {
        Platform.runLater(() -> GameView.getInstance().getGameplayView().getGameplayButtonView().disableAllButtons());
    }

    public static void enableAllButtons() {
        Platform.runLater(() -> GameView.getInstance().getGameplayView().getGameplayButtonView().enableAllButtons());
    }

    public static void enableDefaultButtons() {
        Platform.runLater(() -> GameView.getInstance().getGameplayView().getGameplayButtonView().enableDefaultButtons());
    }

    public static void disableAllButtonsExceptEndTurn() {
        Platform.runLater(() -> GameView.getInstance().getGameplayView().getGameplayButtonView().disableAllButtonsExceptEndTurn());
    }

    public static void showPlayerWinAlert(Player winner) {
        Platform.runLater(() -> showPlayerWinAlertBox(winner));
    }

    private static void showPlayerWinAlertBox(Player winner) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Congratulations " + winner.getName() + "! You have won the game!");
        alert.setTitle("Winner");
        alert.setHeaderText(winner.getName() + " (Player " + winner.getPlayerNum() + ") has won!");
        alert.showAndWait();
    }
}
