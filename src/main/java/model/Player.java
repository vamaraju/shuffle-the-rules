/*
* Requirements mandating inclusion:
*
* 3.2.2.3.3.1 Player will be notified when their Turn begins.
* 3.2.2.3.3.2 Player will be notified when their Turn ends.
* 3.2.2.3.3.2 Player's Turn will be skipped if Player is inactive.
* 3.2.2.4.3.1 Player can end game.
* 3.2.1.5.3.1 Player Objects can be set to active or inactive.
* 3.2.1.5.3.2 Player Objects can store player-specific information for gameplay.
* 3.2.1.1.3.5 User can specify the Players that are allowed to view a Pile.
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
