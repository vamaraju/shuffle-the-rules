/*
* Requirements mandating inclusion:
*
* 3.2.2.1.3.1 User can main.java.view their Hand.
* 3.2.2.1.3.2 User can sort their Hand.
* 3.2.2.2.3.1 User can select Card(s) from Hand to play.
* 3.2.2.2.3.2 User can select Card(s) from Hand to discard.
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
