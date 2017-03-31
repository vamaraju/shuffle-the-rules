package view.TableTab;

import controller.TableTab.CardRestrictionsMenuController;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
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

    public List<String> getCardList() {
        return cardList;
    }

    public ChoiceBox getAvailableCards() {
        return availableCards;
    }
    private Button updateButton;



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
        Canvas suitCanvas = new Canvas(60, 60);
        GraphicsContext graphicsContext = suitCanvas.getGraphicsContext2D();

        graphicsContext.setFill(Color.BLACK);
        graphicsContext.setStroke(Color.BLACK);
        graphicsContext.setLineWidth(2);
        /* club */
        graphicsContext.fillOval(19,13,20,20);
        graphicsContext.fillOval(10,26,20,20);
        graphicsContext.fillOval(30,26,20,20);

        graphicsContext.fillRect(26,33,8,22);

        cardRestrictionMenuContent.getChildren().addAll(cardSelection, suitCanvas);
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

    public void drawSpade(){
        /* two diagonal lines, two circles and a rectangle. fill in with black*/
    }

    public void drawClub(){
        /* three circles and a rectangle. fill in with black*/

        Canvas suitCanvas = new Canvas(100, 100);
        GraphicsContext graphicsContext = suitCanvas.getGraphicsContext2D();

        graphicsContext.setFill(Color.BLACK);
        graphicsContext.setStroke(Color.BLACK);
        graphicsContext.setLineWidth(2);
        /* club */
        graphicsContext.fillOval(19,13,20,20);
        graphicsContext.fillOval(10,26,20,20);
        graphicsContext.fillOval(30,26,20,20);

        graphicsContext.fillRect(26,33,8,22);


    }

    public void drawDiamond(){
        /* four diagonal lines. fill in with red*/
    }

    public void updateCardList(List<String> list){
        cardList = list;
        /* should probably change to use a listener */
        this.availableCards.getItems().addAll(cardList);
    }

    public Button getUpdateButton() {
        return updateButton;
    }
}
