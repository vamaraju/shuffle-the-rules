/*
* Requirements mandating inclusion:
* */
package view.Gameplay;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;


public class GameplayMessageView extends ListView{

    private ObservableList<GameplayMessage> gameplayMessages;

    public GameplayMessageView(){
        gameplayMessages = FXCollections.observableArrayList();
        this.setItems(gameplayMessages);

    }

    public void addMessage(GameplayMessage message){
        this.gameplayMessages.add(message);
    }
}
