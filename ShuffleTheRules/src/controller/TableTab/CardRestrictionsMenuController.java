package controller.TableTab;


import javafx.event.Event;
import model.CardSettings;
import model.GameCreation;
import view.TableTab.CardRestrictionsMenuView;

import java.util.List;

public class CardRestrictionsMenuController {

    private CardRestrictionsMenuView cardRestrictionsMenuView;
    

    public CardRestrictionsMenuController(CardRestrictionsMenuView view){
        cardRestrictionsMenuView = view;

        cardRestrictionsMenuView.getUpdateButton().setOnAction(this::onUpdateButtonClick);
        updateCardList();
        /* change to use observer/ observable */
    }

    public List<String> getCardList(){
        return GameCreation.getInstance().getCardSettings().getCardList();
    }

    public void updateCardList(){
        cardRestrictionsMenuView.updateCardList(GameCreation.getInstance().getCardSettings().getCardList());
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

    public void updateSuitCounts(String card){
        cardRestrictionsMenuView.setHeartCount(Integer.toString(GameCreation.getInstance().getCardSettings().getSuitCount(card,"heart")));
        cardRestrictionsMenuView.setSpadeCount(Integer.toString(GameCreation.getInstance().getCardSettings().getSuitCount(card,"spade")));
        cardRestrictionsMenuView.setClubCount(Integer.toString(GameCreation.getInstance().getCardSettings().getSuitCount(card,"club")));
        cardRestrictionsMenuView.setDiamondCount(Integer.toString(GameCreation.getInstance().getCardSettings().getSuitCount(card,"diamond")));
    }
}
