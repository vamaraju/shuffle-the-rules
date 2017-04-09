package controller;

import javafx.event.Event;
import model.GameplayMessage;
import view.Gameplay.GameplayView;

/* Think that separate elements should have separate controllers.
*  For now, all controller functionality for Gameplay mode will be in this file.
 *
 * The table grid will have its own controller.
 *  */
public class GameplayController {

    private GameplayView gameplayView;

    public GameplayController(GameplayView view){
        this.gameplayView = view;

        gameplayView.getEndTurnButton().setOnAction(this::onEndTurnButtonClick);
        gameplayView.getSortHandButton().setOnAction(this::onSortHandButtonClick);

        addMessage(new GameplayMessage("Event", "onGameStart"));
        addMessage(new GameplayMessage("Action", "Deal 5 cards"));
        addMessage(new GameplayMessage("Turn", "Player 1 Turn Start"));
    }


    public void onEndTurnButtonClick(Event event){
        System.out.println("End Turn pressed - Gameplay View ");

    }

    public void onSortHandButtonClick(Event event){
        System.out.println("Sort Hand pressed - Gameplay View ");

    }

    public void addMessage(GameplayMessage gameplayMessage){
        gameplayView.getGameplayMessageView().addMessage(gameplayMessage);
    }

    public void addMessage(String type, String message){
        GameplayMessage gameplayMessage = new GameplayMessage(type, message);
        this.addMessage(gameplayMessage);
    }
}
