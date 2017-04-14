/*
* Requirements mandating inclusion:
* */
package view.TableTab;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import model.CardOrientation;
import model.Piles.Pile;

public class TableGridElement extends Pane {

    private int x;
    private int y;
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
        this.setWidth(width);
        this.setHeight(height);
    }

    public boolean isPosition(int x, int y) {
        return (this.x == x) && (this.y == y);
    }

    public void addPile(Pile p, CardOrientation orientation) {
        this.pile = p;
        this.orientation = orientation;

        ImageView im = new ImageView(new Image(orientation.getAssetLocation()));
        im.setFitHeight(this.getHeight());
        im.setFitWidth(this.getWidth());

        this.getChildren().clear();
        this.getChildren().add(im);
    }

    public boolean hasPile() {
        return (pile != null);
    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Pile getPile() {
        return pile;
    }

    public void setPile(Pile pile) {
        this.pile = pile;
    }

    public CardOrientation getOrientation() {
        return orientation;
    }

    public void setOrientation(CardOrientation orientation) {
        this.orientation = orientation;
    }
}
