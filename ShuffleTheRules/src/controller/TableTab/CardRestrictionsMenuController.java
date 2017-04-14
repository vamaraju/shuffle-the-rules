/*
* Requirements mandating inclusion:
* */

package controller.TableTab;


import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import model.CardSettings;
import model.GameCreation;
import model.PlayingCard;
import model.Suit;
import view.TableTab.CardRestrictionsMenuView;


public class CardRestrictionsMenuController {

    private CardRestrictionsMenuView cardRestrictionsMenuView;
    private CardSettings cardSettings = GameCreation.getInstance().getCardSettings();

    public CardRestrictionsMenuController(CardRestrictionsMenuView view){
        cardRestrictionsMenuView = view;

    }

    public void onUpdateButtonClick(Event event){
        System.out.println("Update pressed - Card Restrictions Menu ");

        /* Get input from view. convert counts from strings to ints */
        String heartCount = cardRestrictionsMenuView.getHeartCount();
        String spadeCount = cardRestrictionsMenuView.getSpadeCount();
        String clubCount = cardRestrictionsMenuView.getClubCount();
        String diamondCount = cardRestrictionsMenuView.getDiamondCount();

        PlayingCard selectedCard = (PlayingCard) cardRestrictionsMenuView.getAvailableCards().getValue();
        System.out.println("card "+ selectedCard.getClass());

        /* validate */
        int heartCountInt = validateSuitCount(heartCount);
        int spadeCountInt = validateSuitCount(spadeCount);
        int clubCountInt = validateSuitCount(clubCount);
        int diamondCountInt = validateSuitCount(diamondCount);

        /* update model */
        updateSuitCount(selectedCard, Suit.HEART, heartCountInt);
        updateSuitCount(selectedCard, Suit.SPADE, spadeCountInt);
        updateSuitCount(selectedCard, Suit.CLUB, clubCountInt);
        updateSuitCount(selectedCard, Suit.DIAMOND, diamondCountInt);

        /* update view */
        updateDisplayedSuitCounts(selectedCard);

    }


    public void changeDisplayedSuitCounts(ObservableValue observable, Object oldValue, Object newValue){
            PlayingCard selectedCard = (PlayingCard) newValue;
            System.out.println("previous card " + oldValue + " new card " + selectedCard);

            /* update displayed values */
            updateDisplayedSuitCounts(selectedCard);

    }

    public void updateDisplayedSuitCounts(PlayingCard selectedCard){
        cardRestrictionsMenuView.setHeartCount(Integer.toString(cardSettings.getSuitCount(selectedCard,Suit.HEART)));
        cardRestrictionsMenuView.setSpadeCount(Integer.toString(cardSettings.getSuitCount(selectedCard,Suit.SPADE)));
        cardRestrictionsMenuView.setClubCount(Integer.toString(cardSettings.getSuitCount(selectedCard,Suit.CLUB)));
        cardRestrictionsMenuView.setDiamondCount(Integer.toString(cardSettings.getSuitCount(selectedCard,Suit.DIAMOND)));
    }

    public int validateSuitCount(String suitCountString){
        if(suitCountString.isEmpty()){
            /* if the user deleted the value, then we will set it to zero */
            return 0;
        }
        try{
            int suitCountInt = Integer.parseInt(suitCountString);

            if(suitCountInt < 0){
                /* no negative counts. will set to zero */
                return 0;
            }else{
                return suitCountInt;
            }

        }catch (Exception e){
            System.out.println("exception thrown while trying to parse an entered suit count into an integer.");
            return 0;
        }
    }

    public void updateSuitCount(PlayingCard selectedCard, Suit suit, int suitCount){
        cardSettings.updateSuit(selectedCard, suit, suitCount);
    }
}
