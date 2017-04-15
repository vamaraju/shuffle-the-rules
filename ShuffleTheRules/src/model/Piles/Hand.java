/*
* Requirements mandating inclusion:
* */
package model.Piles;


public class Hand extends Pile {

    public Hand(int minSize, int maxSize) {
        super(minSize, maxSize);
    }

    public Hand(int minSize, int maxSize, int startingNumCards) {
        super(minSize, maxSize, startingNumCards);
    }

    public void sort(){

    }

}
