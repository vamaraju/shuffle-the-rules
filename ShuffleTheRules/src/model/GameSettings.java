package model;


public class GameSettings {
    int minPlayers;
    int maxPlayers;

    public GameSettings(){

    }
    /* TODO we may want to restrict this to ASC/DESC, or move to gameplay. doesn't make sense being in GameCreation with Player objects and no people present */
    public void changeTurnOrder(Player player){

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