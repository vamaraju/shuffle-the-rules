package controller.TableTab;


import javafx.event.Event;
import model.CardSettings;
import view.TableTab.CardRestrictionsMenuView;

import java.util.List;

public class CardRestrictionsMenuController {

    private CardRestrictionsMenuView cardRestrictionsMenuView;
    private CardSettings cardSettings;

    public CardRestrictionsMenuController(CardRestrictionsMenuView view){
        cardRestrictionsMenuView = view;
        cardSettings = new CardSettings();
        cardRestrictionsMenuView.getUpdateButton().setOnAction(this::onUpdateButtonClick);
        updateCardList();
        /* change to use observer/ observable */
    }

    public List<String> getCardList(){
        return cardSettings.getCardList();
    }

    public void updateCardList(){
        cardRestrictionsMenuView.updateCardList(cardSettings.getCardList());
    }

    public void onUpdateButtonClick(Event event){
        System.out.println("Update pressed - Card Restrictions Menu ");

        /* Get input from view. convert counts from strings to ints */

        /* validate */
        /* update model */
        /* update view */
        /* */
    }
}
