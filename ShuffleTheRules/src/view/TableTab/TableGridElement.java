/*
* Requirements mandating inclusion:
* */
package view.TableTab;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import model.CardOrientation;
import model.Piles.Pile;
import model.Piles.PileType;
import model.TableGridPosition;

public class TableGridElement extends Pane {

    private TableGridPosition position;
    private Pile pile;
    private CardOrientation cardOrientation;

    public TableGridElement() {

    }

    public TableGridElement(int x, int y) {
        this.position = new TableGridPosition(x, y);
    }

    public TableGridElement(int x, int y, double width, double height) {
        this.position = new TableGridPosition(x, y);
        this.setWidth(width);
        this.setHeight(height);
    }

    public TableGridElement(TableGridPosition position) {
        this.position = position;
    }

    public TableGridElement(TableGridPosition position, double width, double height) {
        this.position = position;
        this.setWidth(width);
        this.setHeight(height);
    }

    public boolean isPosition(int x, int y) {
        return position.at(x, y);
    }

    public boolean isPosition(TableGridPosition pos) {
        return position.equals(pos);
    }

    public void updatePile(Pile p) {
        updatePile(p, p.getCardOrientation());
    }

    private void updatePile(Pile p, CardOrientation cardOrientation) {
        this.pile = p;
        this.cardOrientation = cardOrientation;

        ImageView im = new ImageView(new Image(cardOrientation.getAssetLocation()));
        im.setFitHeight(this.getHeight());
        im.setFitWidth(this.getWidth());

        this.getChildren().clear();
        this.getChildren().add(im);
    }

    public boolean hasPile() {
        return (pile != null);
    }

    public String getPileName() {
        return pile.getName();
    }

    public PileType getPileType() {
        return pile.getPileType();
    }

    public int getPileMinSize() {
        return pile.getMinSize();
    }

    public int getPileMaxSize() {
        return pile.getMaxSize();
    }

    public int getPileStartingSize() {
        return pile.getStartingSize();
    }

    public int getX() {
        return position.getX();
    }

    public void setX(int x) {
        position.setX(x);
    }

    public int getY() {
        return position.getY();
    }

    public void setY(int y) {
        position.setY(y);
    }

    public Pile getPile() {
        return pile;
    }

    public CardOrientation getCardOrientation() {
        return cardOrientation;
    }

    public void setCardOrientation(CardOrientation cardOrientation) {
        this.cardOrientation = cardOrientation;
    }

    public TableGridPosition getPosition() {
        return position;
    }

    public void setPosition(TableGridPosition position) {
        this.position = position;
    }
}
