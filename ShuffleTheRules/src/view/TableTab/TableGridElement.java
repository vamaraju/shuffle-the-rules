package view.TableTab;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import model.CardOrientation;
import model.Piles.Pile;

public class TableGridElement extends Pane {

    private int x;
    private int y;
    private double width;
    private double height;
    private Pile pile;
    private CardOrientation orientation;

    public TableGridElement() {

    }

    public TableGridElement(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public TableGridElement(int x, int y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public boolean atPosition(int x, int y) {
        return (this.x == x) && (this.y == y);
    }

    public void addPile(Pile p, CardOrientation orientation) {
        this.pile = p;
        this.orientation = orientation;

        ImageView im = new ImageView(new Image(orientation.getAssetLocation()));
        im.setFitHeight(height);
        im.setFitWidth(width);

        this.getChildren().clear();
        this.getChildren().add(im);
    }
}
