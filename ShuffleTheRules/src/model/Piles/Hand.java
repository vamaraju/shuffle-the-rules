/*
* Requirements mandating inclusion:
* */
package model.Piles;


import model.Player;

public class Hand extends Pile {

    Player viewablePlayer;

    public Hand() {
        super();
        setPileType(PileType.HAND);
    }

    public Hand(int minSize, int maxSize) {
        super(minSize, maxSize);
        setPileType(PileType.HAND);
    }

    public Hand(int minSize, int maxSize, int startingNumCards) {
        super(minSize, maxSize, startingNumCards);
        setPileType(PileType.HAND);
    }

    public void sort(){

    }

}
