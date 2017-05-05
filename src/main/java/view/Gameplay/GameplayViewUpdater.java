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
