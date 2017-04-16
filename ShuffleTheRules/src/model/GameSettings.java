/*
* Requirements mandating inclusion:
* */
package model;


import java.io.Serializable;

public class GameSettings implements Serializable {
    int minPlayers;
    int maxPlayers;

    public GameSettings(){

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
