package model;

import model.Piles.Hand;

import java.io.Serializable;

public class Player implements Serializable {
    private int handId;
    private Hand hand;
    private int playerID;
    private boolean status;
    private int playerNum;
    private boolean clicked;
    private boolean skipFlag = false;

    public Player(){

    }

    public int getHandId() {
        return handId;
    }

    public void setHandId(int handId) {
        this.handId = handId;
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isClicked() {
        return clicked;
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }

    public int getPlayerNum() {
        return playerNum;
    }

    public void setPlayerNum(int playerNum) {
        this.playerNum = playerNum;
    }

    public boolean isSkipFlag() {
        return skipFlag;
    }

    public void setSkipFlag(boolean skipFlag) {
        this.skipFlag = skipFlag;
    }
}
