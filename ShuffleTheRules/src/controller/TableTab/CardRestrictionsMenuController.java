package controller.TableTab;


import model.CardSettings;
import view.TableTab.CardRestrictionsMenuView;

import java.util.List;

public class CardRestrictionsMenuController {

    private CardRestrictionsMenuView cardRestrictionsMenuView;
    private CardSettings cardSettings;

    public CardRestrictionsMenuController(CardRestrictionsMenuView view){
        cardRestrictionsMenuView = view;
        cardSettings = new CardSettings();
        updateCardList();
        /* change to use observer/ observable */
    }

    public List<String> getCardList(){
        return cardSettings.getCardList();
    }

    public void updateCardList(){
        cardRestrictionsMenuView.updateCardList(cardSettings.getCardList());
    }
}
