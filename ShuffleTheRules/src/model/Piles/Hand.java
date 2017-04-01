package model.Piles;


public class Hand extends Pile {
    private int handID; /* TODO needs to be set */

    public Hand(String name, int minSize, int maxSize) {
        super(name, minSize, maxSize);
    }

    public void sort(){

    }

    public int getHandID() {
        return handID;
    }

    public void setHandID(int handID) {
        this.handID = handID;
    }
}
