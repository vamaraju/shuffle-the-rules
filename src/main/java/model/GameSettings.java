/*
* Requirements mandating inclusion:
*
* 3.2.1.9.3.1 User can set minimum and maximum number of Players in game.
* */
package model;


import java.io.Serializable;

public class GameSettings implements Serializable {
    private int minPlayers;
    private int maxPlayers;

    public GameSettings() {

    }

    public void reset() {
        minPlayers = 0;
        maxPlayers = 0;
    }

    public int getMinPlayers() {
        return minPlayers;
    }

    public void setMinPlayers(int minPlayers) {
        this.minPlayers = minPlayers;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }
}
