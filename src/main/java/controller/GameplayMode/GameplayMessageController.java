/*
* Requirements mandating inclusion:
*
* 3.2.2.3.3.1 Player will be notified when their Turn begins.
* 3.2.2.3.3.2 Player will be notified when their Turn ends.
* 3.2.2.3.3.2 Player’s Turn will be skipped if Player is inactive.
*
* Indirectly relates to requirements related to Card Actions, the interpreter and validation.
* */
package controller.GameplayMode;

import model.GameplayMessageType;
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

    public void addMessage(GameplayMessageType type, String message){
        GameplayMessage gameplayMessage = new GameplayMessage(type, message);
        this.addMessage(gameplayMessage);
    }

}
