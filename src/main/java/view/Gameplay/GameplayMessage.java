package view.Gameplay;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import model.GameplayMessageType;


import java.io.Serializable;

public class GameplayMessage extends HBox {

    private GameplayMessageType type;
    private String message;

    private Label content = new Label();

    public GameplayMessage(GameplayMessageType type, String message) {
        setMessageContent(type, message);
        this.getChildren().addAll(content);
    }

    public String getMessage() {
        return this.message;
    }

    public GameplayMessageType getType() {
        return this.type;
    }

    public GameplayMessage getGameplayMessage() {
        return this;
    }

    public void setMessageContent(GameplayMessageType type, String message) {
        this.type = type;
        this.message = message;
        this.content.setText("[" + type + "]" + " - " + message);
        this.content.setTextFill(Color.valueOf(type.getColor()));
    }
}
