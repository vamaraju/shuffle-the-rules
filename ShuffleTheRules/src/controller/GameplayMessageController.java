/*
* Requirements mandating inclusion:
* */
package controller;

import model.GameplayMessage;
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
