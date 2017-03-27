package view.TableTab;

import controller.TableTab.CardRestrictionsMenuController;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;

import java.util.List;

public class CardRestrictionsMenuView extends TitledPane{
    private List<String> cardList;

    public CardRestrictionsMenuView(){
        initialize();
    }

    public void initialize(){
        this.setText("Card Restrictions");

        VBox cardRestrictionMenuContent = new VBox();

        Label selectCard = new Label("Select Card");
        ChoiceBox availableCards = new ChoiceBox();
        availableCards.getItems().addAll(cardList);

        drawCardSettingsDisplay();
    }

    public void drawCardSettingsDisplay(){

    }


    public void drawCardSettingsDisplay(String card){

    }

    public void loadCardSettings(){
        /* load settings for card chosen in ChoiceBox*/
    }

    public void drawHeart(){
        /* two diagonal lines, two circles. fill in with red*/
    }

    public void drawSpade(){
        /* two diagonal lines, two circles and a rectangle. fill in with black*/
    }

    public void drawClub(){
        /* three circles and a rectangle. fill in with black*/
    }

    public void drawDiamond(){
        /* four diagonal lines. fill in with red*/
    }

    public void updateCardList(List<String> list){
        cardList = list;
    }
}
