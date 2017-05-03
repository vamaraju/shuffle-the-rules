package view.Gameplay;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;


import java.io.Serializable;

public class GameplayMessage extends HBox implements Serializable {

    private String type;
    private String message;

    private Label content = new Label();

    public GameplayMessage(String type, String message){
        setMessageContent(type,message);
        this.getChildren().addAll(content);
    }

    public String getMessage(){
        return this.message;
    }

    public String getType(){
        return this.type;
    }

    public GameplayMessage getGameplayMessage(){
        return this;
    }

    public void setMessageContent(String type, String message){
        this.type = type;
        this.message = message;
        this.content.setText("[" + type + "]" + " - " + message);
    }
}
