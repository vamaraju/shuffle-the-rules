package view.TableTab;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import model.CardSettings;

public class CardRestrictionsMenuView extends TitledPane{
    controller = new EditorTabController(this);

    public CardRestrictionsMenuView(){
        initialize();
    }

    public void initialize(){
        this.setText("Card Restrictions");

        VBox cardRestrictionMenuContent = new VBox();

        Label selectCard = new Label("Select Card");
        ChoiceBox availableCards = new ChoiceBox();
        availableCards.getItems().addAll(TableTabController.getCardList());
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
}
