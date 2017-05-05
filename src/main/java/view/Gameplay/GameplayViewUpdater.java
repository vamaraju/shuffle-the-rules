package view.Gameplay;

import javafx.application.Platform;
import model.GameView;
import model.GameplayMessageType;
import model.Piles.Pile;

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
}
