/*
* Requirements mandating inclusion:
*
* 3.2.1.1.3.2 User can specify number of Cards in a Pile (max/min).
* 3.2.1.1.3.8 User can specify a name for the Pile.
* */
package model.Piles;


public class BasicPile extends Pile {

    public BasicPile(String name, int minSize, int maxSize) {
        super(name, minSize, maxSize);
    }


}
