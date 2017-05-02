/*
* Requirements mandating inclusion:
* */
package model.GameEvents;


import model.GameRule;
import java.io.Serializable;



public abstract class GameEvent extends GameRule implements Serializable {

    public GameEvent() {
        this.className = "GameEvent";
        this.description = "A generic game event.";
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

}
