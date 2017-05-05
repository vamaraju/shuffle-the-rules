/*
* Requirements mandating inclusion:
* */
package model;

import model.Piles.Hand;

import java.io.Serializable;

public class Player implements Serializable {

    private int playerNum;
    private String name;
    private Hand hand;
    private boolean status;
    private boolean clicked;
    private boolean skipFlag = false;
    private boolean inactive = false;

    public Player() {

    }

    public Player(int playerNum) {
        this.playerNum = playerNum;
        this.name = "Player " + playerNum;
    }

    public int getTurnNum() {
        return playerNum;
    }

    public void setTurnNum(int turnNum) {
        this.playerNum = turnNum;
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
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

    public boolean isSkipFlag() {
        return skipFlag;
    }

    public void setSkipFlag(boolean skipFlag) {
        this.skipFlag = skipFlag;
    }

    public int getPlayerNum() {
        return playerNum;
    }

    public void setPlayerNum(int playerNum) {
        this.playerNum = playerNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isInactive() {
        return inactive;
    }

    public void setInactive(boolean inactive) {
        this.inactive = inactive;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
