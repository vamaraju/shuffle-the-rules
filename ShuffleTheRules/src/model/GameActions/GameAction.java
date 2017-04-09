package model.GameActions;

import model.GameEvents.GameEvent;
import model.GameRule;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class GameAction extends GameRule implements Serializable {

    public GameAction() {
        this.name = "GameAction";
        this.description = "A generic game action.";
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
