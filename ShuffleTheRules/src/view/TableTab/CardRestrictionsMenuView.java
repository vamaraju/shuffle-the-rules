package view.TableTab;

import controller.TableTab.CardRestrictionsMenuController;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class CardRestrictionsMenuView extends TitledPane{
    private List<String> cardList = new ArrayList<String>();
    private ChoiceBox availableCards;

    public CardRestrictionsMenuView(){
        initialize();
    }

    private Button updateButton;

    private ImageView heartImageView = new ImageView(getClass().getResource("../../assets/playing_cards/suits/heart.png").toExternalForm());
    private ImageView spadeImageView = new ImageView(getClass().getResource("../../assets/playing_cards/suits/spade.png").toExternalForm());
    private ImageView clubImageView = new ImageView(getClass().getResource("../../assets/playing_cards/suits/club.png").toExternalForm());
    private ImageView diamondImageView = new ImageView(getClass().getResource("../../assets/playing_cards/suits/diamond.png").toExternalForm());

    public void initialize(){
        this.setText("Card Restrictions");

        VBox cardRestrictionMenuContent = new VBox();

        String cssLayout = "-fx-border-color: rgba(0,0,0,0.32);\n" +
                "-fx-border-insets: 0;\n" +
                "-fx-border-width: 1;\n" +
                "-fx-border-style: solid;\n";

        VBox yourBox = new VBox();
        cardRestrictionMenuContent.setStyle(cssLayout);

        HBox cardSelection = new HBox(10);
        Label selectCard = new Label("Select Card");
        availableCards = new ChoiceBox();
        availableCards.getItems().addAll(cardList);
        cardSelection.getChildren().addAll(selectCard, availableCards);

        drawCardSettingsDisplay();

        cardRestrictionMenuContent.getChildren().addAll(cardSelection, heartImageView,spadeImageView, clubImageView, diamondImageView);
        this.setContent(cardRestrictionMenuContent);
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


    public void updateCardList(List<String> list){
        cardList = list;
        /* should probably change to use a listener */
        this.availableCards.getItems().addAll(cardList);
    }

    public Button getUpdateButton() {
        return updateButton;
    }


    public List<String> getCardList() {
        return cardList;
    }

    public ChoiceBox getAvailableCards() {
        return availableCards;
    }

    public class CardSettingsDisplay{
        
    }

}


