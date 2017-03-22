package model;

public class Player {
    private int handId;
    private int playerID;
    private boolean status;
    private int turnOrder;

    public Player(){

    }

    public int getHandId() {
        return handId;
    }

    public void setHandId(int handId) {
        this.handId = handId;
    }

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getTurnOrder() {
        return turnOrder;
    }

    public void setTurnOrder(int turnOrder) {
        this.turnOrder = turnOrder;
    }
}
