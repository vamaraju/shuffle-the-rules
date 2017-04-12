package controller.TableTab;


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

        /* change to use observer/ observable */
    }

    public void onUpdateButtonClick(Event event){
        System.out.println("Update pressed - Card Restrictions Menu ");

        /* Get input from view. convert counts from strings to ints */
        String heartCount = cardRestrictionsMenuView.getHeartCount();
        String spadeCount = cardRestrictionsMenuView.getSpadeCount();
        String clubCount = cardRestrictionsMenuView.getClubCount();
        String diamondCount = cardRestrictionsMenuView.getDiamondCount();


        /* validate */
        /* update model */
        /* update view */
        /* */
    }

    public void updateSuitCounts(PlayingCard card){
        cardRestrictionsMenuView.setHeartCount(Integer.toString(cardSettings.getSuitCount(card, Suit.HEART)));
        cardRestrictionsMenuView.setSpadeCount(Integer.toString(cardSettings.getSuitCount(card, Suit.SPADE)));
        cardRestrictionsMenuView.setClubCount(Integer.toString(cardSettings.getSuitCount(card, Suit.CLUB)));
        cardRestrictionsMenuView.setDiamondCount(Integer.toString(cardSettings.getSuitCount(card, Suit.DIAMOND)));
    }
}
