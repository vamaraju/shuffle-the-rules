package model.Piles;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import model.Card;
import model.CardOrientation;
import model.PlayingCard;
import model.Suit;

public class PileTest {
    @Test
    public void testConstructors() {
        Pile pile1 = new Pile();
        Pile pile2 = new Pile(2, 6);
        Pile pile3 = new Pile(10, 52, 11);
        Pile pile4 = new Pile("deck", 0, 52);
        Pile pile5 = new Pile("deck", 0, 52, 52);
        Pile pile6 = new Pile("smallpile", 2, 4, 3, CardOrientation.DOWN);
        Pile pile7 = new Pile("faceuppile", 2, 4, 3, CardOrientation.UP, "All");

        assertNull(pile1.getName());
        assert(pile1.getMinSize() == 0);
        assert(pile1.getMaxSize() == 0);
        assert(pile1.getStartingSize() == 0);
        assertNull(pile1.getCardOrientation());
        assertNull(pile1.getViewablePlayers());

        assertNull(pile2.getName());
        assert(pile2.getMinSize() == 2);
        assert(pile2.getMaxSize() == 6);
        assert(pile2.getStartingSize() == pile2.getMinSize());
        assertNotNull(pile2.getCardOrientation());
        assert(pile2.getCardOrientation() == CardOrientation.EITHER);
        assertNull(pile2.getViewablePlayers());

        assertNull(pile3.getName());
        assert(pile3.getMinSize() == 10);
        assert(pile3.getMaxSize() == 52);
        assert(pile3.getStartingSize() == 11);
        assertNotNull(pile3.getCardOrientation());
        assert(pile3.getCardOrientation() == CardOrientation.EITHER);
        assertNull(pile3.getViewablePlayers());

        assert(pile4.getName().equals("deck"));
        assert(pile4.getMinSize() == 0);
        assert(pile4.getMaxSize() == 52);
        assert(pile4.getStartingSize() == 0);
        assertNotNull(pile4.getCardOrientation());
        assert(pile4.getCardOrientation() == CardOrientation.EITHER);
        assertNull(pile4.getViewablePlayers());

        assert(pile5.getName().equals("deck"));
        assert(pile5.getMinSize() == 0);
        assert(pile5.getMaxSize() == 52);
        assert(pile5.getStartingSize() == 52);
        assertNotNull(pile5.getCardOrientation());
        assert(pile5.getCardOrientation() == CardOrientation.EITHER);
        assertNull(pile5.getViewablePlayers());

        assert(pile6.getName().equals("smallpile"));
        assert(pile6.getMinSize() == 2);
        assert(pile6.getMaxSize() == 4);
        assert(pile6.getStartingSize() == 3);
        assert(pile6.getCardOrientation() == CardOrientation.DOWN);
        assertNull(pile6.getViewablePlayers());

        assert(pile7.getName().equals("faceuppile"));
        assert(pile7.getMinSize() == 2);
        assert(pile7.getMaxSize() == 4);
        assert(pile7.getStartingSize() == 3);
        assert(pile7.getCardOrientation() == CardOrientation.UP);
        assert(pile7.getName().equals("faceuppile"));
    }

    @Test
    public void testAddToBottom() {
        Pile pile1 = new Pile();

        pile1.addToBottom(new Card(PlayingCard.ACE, Suit.DIAMOND));
        pile1.addToBottom(new Card(PlayingCard.FOUR, Suit.SPADE));
        pile1.addToBottom(new Card(PlayingCard.THREE, Suit.HEART));
        assert(pile1.getBottomCard().equals(new Card(PlayingCard.THREE, Suit.HEART)));
    }

    @Test
    public void testAddToTop() {
        Pile pile1 = new Pile();

        pile1.addToTop(new Card(PlayingCard.ACE, Suit.DIAMOND));
        pile1.addToTop(new Card(PlayingCard.FOUR, Suit.SPADE));
        pile1.addToTop(new Card(PlayingCard.FIVE, Suit.CLUB));
        assert(pile1.getTopCard().equals(new Card(PlayingCard.FIVE, Suit.CLUB)));
    }
}