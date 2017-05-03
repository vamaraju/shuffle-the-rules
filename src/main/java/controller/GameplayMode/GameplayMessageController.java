/*
* Requirements mandating inclusion:
* */
package controller.GameplayMode;

import view.Gameplay.GameplayMessage;
import view.Gameplay.GameplayMessageView;


public class GameplayMessageController {

    private GameplayMessageView gameplayMessageView;

    public GameplayMessageController(GameplayMessageView view){
        this. gameplayMessageView = view;
    }

    public void addMessage(GameplayMessage gameplayMessage){
        gameplayMessageView.addMessage(gameplayMessage);
    }

    public void addMessage(String type, String message){
        GameplayMessage gameplayMessage = new GameplayMessage(type, message);
        this.addMessage(gameplayMessage);
    }

}
