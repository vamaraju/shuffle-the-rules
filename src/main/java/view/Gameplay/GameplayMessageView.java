/*
* Requirements mandating inclusion:
*
* 3.2.2.3.3.1 Player will be notified when their Turn begins.
* 3.2.2.3.3.2 Player will be notified when their Turn ends.
* 3.2.2.3.3.2 Player's Turn will be skipped if Player is inactive.
* */
package view.Gameplay;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import model.GameplayMessageType;


public class GameplayMessageView extends ListView{

    private ObservableList<GameplayMessage> gameplayMessages;

    public GameplayMessageView() {
        gameplayMessages = FXCollections.observableArrayList();
        this.setItems(gameplayMessages);
    }

    public void addMessage(GameplayMessage message) {
        this.gameplayMessages.add(message);
    }

    public void addMessage(GameplayMessageType messageType, String message) {
        addMessage(new GameplayMessage(messageType, message));
    }
}
