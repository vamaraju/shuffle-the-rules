package controller.TableTab;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import model.CardSettings;
import model.GameCreation;
import model.PlayingCard;
import model.Suit;
import view.TableTab.CardRestrictionsMenuView;

import java.util.List;

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
        /* update model */
        /* update view */
        /* */
    }

    public void updateSuitCounts(ObservableValue observable, Object oldValue, Object newValue){

    }

    public void updateDisplayedSuitCounts(ObservableValue observable, PlayingCard previousCard, PlayingCard selectedCard){

            System.out.println("card " + selectedCard);
            cardRestrictionsMenuView.setHeartCount(Integer.toString(cardSettings.getSuitCount(selectedCard,Suit.HEART)));
            cardRestrictionsMenuView.setSpadeCount(Integer.toString(cardSettings.getSuitCount(selectedCard,Suit.SPADE)));
            cardRestrictionsMenuView.setClubCount(Integer.toString(cardSettings.getSuitCount(selectedCard,Suit.CLUB)));
            cardRestrictionsMenuView.setDiamondCount(Integer.toString(cardSettings.getSuitCount(selectedCard,Suit.DIAMOND)));
    }
}
