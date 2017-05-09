/*
* Requirements mandating inclusion:
*
* 3.2.1.8.3.1 User can set the dimensions of the grid.
* 3.2.1.8.3.2 User can scale the size of the grid blocks.
* 3.2.1.8.3.3 User can place Pile Objects on the grid.
* 3.2.1.8.3.4 User can show the grid.
* 3.2.1.8.3.5 User can hide the grid.
* 3.2.1.1.3.1 User can place Piles on Table.
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
        this.pile = p;

        ImageView im = new ImageView(new Image(p.getCardOrientation().getAssetLocation()));
        im.setFitHeight(this.getHeight());
        im.setFitWidth(this.getWidth());

        this.getChildren().clear();
        this.getChildren().add(im);
    }

    public void removePile() {
        this.getChildren().clear();
        this.pile = null;
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

    public CardOrientation getPileCardOrientation() {
        return pile.getCardOrientation();
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

    public TableGridPosition getPosition() {
        return position;
    }

    public void setPosition(TableGridPosition position) {
        this.position = position;
    }

    public void addStyle(String cssStyle) {
        this.setStyle(this.getStyle() + cssStyle);
    }

    public void removeStyle(String cssStyle) {
        this.setStyle(this.getStyle().replaceFirst(cssStyle, ""));
    }
}
