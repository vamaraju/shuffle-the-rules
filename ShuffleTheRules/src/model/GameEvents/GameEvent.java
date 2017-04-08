package model.GameEvents;

import model.GameActions.GameAction;
import model.GameRule;

import java.io.Serializable;
import java.util.ArrayList;


public abstract class GameEvent extends GameRule implements Serializable {

    public GameEvent() {
        this.name = "GameEvent";
        this.description = "A generic game event.";
    }

}
