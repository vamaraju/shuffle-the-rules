package view.TableTab;

import controller.TableTab.CardRestrictionsMenuController;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
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

    public CardRestrictionsMenuView(){
        initialize();
    }

    public void initialize(){
        this.setText("Card Restrictions");

        VBox cardRestrictionMenuContent = new VBox();

        HBox cardSelection = new HBox(10);
        Label selectCard = new Label("Select Card");
        ChoiceBox availableCards = new ChoiceBox();
        availableCards.getItems().addAll(cardList);
        cardSelection.getChildren().addAll(selectCard, availableCards);

        Canvas suitCanvas = new Canvas(100, 100);
        GraphicsContext graphicsContext = suitCanvas.getGraphicsContext2D();

        graphicsContext.setFill(Color.BLACK);
        graphicsContext.setStroke(Color.BLACK);
        graphicsContext.setLineWidth(2);
        /* club */
        graphicsContext.fillOval(25,25,11,11);
        graphicsContext.fillOval(20,34,11,11);
        graphicsContext.fillOval(30,34,11,11);
        graphicsContext.fillRect(28,33,6,20);

        drawCardSettingsDisplay();

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

    }

    public void drawDiamond(){
        /* four diagonal lines. fill in with red*/
    }

    public void updateCardList(List<String> list){
        cardList = list;
    }
}
