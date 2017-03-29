package controller.TableTab;


import model.CardSettings;
import view.TableTab.CardRestrictionsMenuView;

import java.util.List;

public class CardRestrictionsMenuController {

    private CardRestrictionsMenuView cardRestrictionsMenuView;
    private CardSettings cardSettings;

    public CardRestrictionsMenuController(CardRestrictionsMenuView view, CardSettings cardSettingsModel){
        cardRestrictionsMenuView = view;
        cardSettings = cardSettingsModel;
        updateCardList();
    }

    public List<String> getCardList(){
        return cardSettings.getCardList();
    }

    public void updateCardList(){
        cardRestrictionsMenuView.updateCardList(cardSettings.getCardList());
    }
}
