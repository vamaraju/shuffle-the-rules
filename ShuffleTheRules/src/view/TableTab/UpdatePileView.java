package view.TableTab;


import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class UpdatePileView extends VBox {

    public UpdatePileView() {

        Label name = new Label("Name:");
        Label type = new Label("Type:");
        Label numCards = new Label("Number Cards");
        Label minCards = new Label("min:");
        Label maxCards = new Label("max:");
        Label gridCoordinates = new Label("Coordinates");
        Label xCoord = new Label("x:");
        Label yCoord = new Label("y:");
        Label playerAssociation = new Label("Player Association");
        Label viewable = new Label("Viewable By:");
        Label orientation = new Label("Orientation");
        Button updatePileButton = new Button("Update Pile");
        this.getChildren().addAll(name, type, numCards, minCards, maxCards, gridCoordinates, xCoord, yCoord, playerAssociation, viewable, orientation, updatePileButton);
    }
}
