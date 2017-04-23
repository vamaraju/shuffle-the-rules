package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;

public class CardTest {
    @Test
    public void testConstructors() {
        Card card1 = new Card();
        Card card2 = new Card(PlayingCard.ACE, Suit.DIAMOND);
        Card card3 = new Card(PlayingCard.FIVE, Suit.CLUB, CardOrientation.DOWN);

        assertNull(card1.getValue());
        assertNull(card1.getSuit());
        assert(card2.getValue() == PlayingCard.ACE);
        assert(card2.getSuit() == Suit.DIAMOND);
        assert(card3.getValue() == PlayingCard.FIVE);
        assert(card3.getSuit() == Suit.CLUB);
        assert(card3.getOrientation() == CardOrientation.DOWN);
    }

    @Test
    public void testGetValue() throws NoSuchFieldException, IllegalAccessException {
        Card card1 = new Card();
        Card card2 = new Card(PlayingCard.ACE, Suit.DIAMOND);

        Field field = card1.getClass().getDeclaredField("value");
        field.setAccessible(true);
        field.set(card1, PlayingCard.SIX);
        field.set(card2, PlayingCard.SEVEN);

        assert(card1.getValue() == PlayingCard.SIX);
        assert(card2.getValue() == PlayingCard.SEVEN);
    }

    @Test
    public void testSetValue() throws NoSuchFieldException, IllegalAccessException {
        Card card1 = new Card();
        Card card2 = new Card(PlayingCard.ACE, Suit.DIAMOND);

        card1.setValue(PlayingCard.QUEEN);
        card2.setValue(PlayingCard.TWO);

        Field field = card1.getClass().getDeclaredField("value");
        field.setAccessible(true);

        assert(field.get(card1) == PlayingCard.QUEEN);
        assert(field.get(card2) == PlayingCard.TWO);
    }
}