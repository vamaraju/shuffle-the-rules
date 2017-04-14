/*
* Requirements mandating inclusion:
* */
package model.GameActions;


import model.GameRule;
import java.io.Serializable;


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
