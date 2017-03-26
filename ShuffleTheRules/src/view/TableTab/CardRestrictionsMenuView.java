package view.TableTab;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import model.CardSettings;

public class CardRestrictionsMenuView extends TitledPane{

    public CardRestrictionsMenuView(){
        this.setText("Card Restrictions");

        VBox cardRestrictionMenuContent = new VBox();

        Label selectCard = new Label("Select Card");
        ChoiceBox availableCards = new ChoiceBox();
        //availableCards.getItems().addAll(CardSettings.getCardList());
    }
}
