package model;


import model.GameActions.GameAction;
import model.GameEvents.GameEvent;
import model.Piles.Pile;

import java.util.ArrayList;

public class GameCreation {
    ArrayList<GameEvent> gameEvents;
    ArrayList<GameAction> gameActions;
    ArrayList<Pile> piles;
    ArrayList<Player> players;
    CardSettings cardSettings;
    GameSettings gameSettings;

    public GameCreation(){

    }
}
