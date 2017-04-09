package view.Gameplay;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import model.GameplayMessage;

public class GameplayMessageView extends Pane{

    private ObservableList<GameplayMessage> gameplayMessages = FXCollections.observableArrayList();
    private ListView<GameplayMessage> gameplayMessageListView;

    public GameplayMessageView(){
        gameplayMessages = FXCollections.observableArrayList();
        gameplayMessageListView = new ListView<>(gameplayMessages);

        this.getChildren().addAll(gameplayMessageListView);

    }

    public void addMessage(GameplayMessage message){
        this.gameplayMessages.add(message);
    }
}
