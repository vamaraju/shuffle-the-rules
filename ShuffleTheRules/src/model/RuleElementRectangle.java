package model;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class RuleElementRectangle extends Rectangle {

    private Text text;
    private GameRule gameRule;

    public RuleElementRectangle() {
        super();
    }

    public RuleElementRectangle(double width, double height) {
        super(width, height);
    }

    public RuleElementRectangle(double x, double y, double width, double height) {
        super(x, y, width, height);
    }

    public RuleElementRectangle(double width, double height, Paint fill) {
        super(width, height, fill);
    }


    /**
     * Dynamically center the given text (string) in the rectangle, based on the given rectangle width and height.
     *
     * @param x Rectangle starting (top-left) x coordinate.
     * @param y Rectangle starting (top-left) y coordinate.
     * @param width Rectangle width.
     * @param height Rectangle height.
     * @param text Text (string) that goes inside the rectangle.
     */
    public RuleElementRectangle(double x, double y, double width, double height, String text) {
        super(x, y, width, height);

        Text t = new Text(text);
        t.setFont(new Font(15));
        t.setWrappingWidth(width-20);

        double textWidth = t.getLayoutBounds().getWidth();
        t.setX(x+(width-textWidth)/2);
        double textHeight = t.getLayoutBounds().getHeight();
        t.setY(y+(height-textHeight)/2);

        this.text = t;
    }


    /**
     * Dynamically center the given Text (object) in the rectangle, based on the given rectangle width and height.
     * Does not modify any of the properties in the given Text.
     *
     * @param x Rectangle starting (top-left) x coordinate.
     * @param y Rectangle starting (top-left) y coordinate.
     * @param width Rectangle width.
     * @param height Rectangle height.
     * @param text Text (JavaFX object) that goes inside the rectangle.
     */
    public RuleElementRectangle(double x, double y, double width, double height, Text text) {
        super(x, y, width, height);
        this.text = text;
    }


    /**
     * Dynamically generates the rectangle width and height based on (to fit) the given text.
     *
     * @param x Rectangle starting (top-left) x coordinate.
     * @param y Rectangle starting (top-left) y coordinate.
     * @param text Text that goes inside the rectangle.
     */
    public RuleElementRectangle(double x, double y, String text) {
        super(x, y, 150, 75);

        Text t = new Text(text);
        t.setFont(new Font(15));

        double textWidth = t.getLayoutBounds().getWidth();
        double rectWidth = textWidth + 40;
        t.setWrappingWidth(rectWidth-20);
        t.setX(x+20);
        double textHeight = t.getLayoutBounds().getHeight();
        double rectHeight = textHeight + 40;
        t.setY(y+30);

        this.text = t;

        this.setWidth(rectWidth);
        this.setHeight(rectHeight);
    }


    /**
     * Returns the x-coordinate of the center of the rectangle.
     * Formula: ([rectangle width]/2) + [starting x-coordinate of rectangle]
     *
     * @return Center x-coordinate of rectangle.
     */
    public double getCenterX() {
        return this.getX()+(this.getWidth()/2);
    }


    /**
     * Returns the y-coordinate of the center of the rectangle.
     * Formula: ([rectangle height]/2) + [starting y-coordinate of rectangle]
     *
     * @return Center y-coordinate of rectangle.
     */
    public double getCenterY() {
        return this.getY()+(this.getHeight()/2);
    }


    /**
     * Returns the text (string) within the rectangle.
     *
     * @return Rectangle text (string).
     */
    public String getText() {
        return this.text.getText();
    }


    /**
     * Sets the text (string) inside the rectangle. No resizing to the rectangle itself.
     * The text will automatically wrap within the rectangle if necessary.
     *
     * @param text New text (string) to update within the rectangle.
     */
    public void setText(String text) {
        this.text.setText(text);
    }


    /**
     * Returns the Text (JavaFX object) within the rectangle.
     *
     * @return Rectangle Text (JavaFX object).
     */
    public Text getTextObj() {
        return this.text;
    }


    /**
     * Sets the Text (JavaFX object) inside the rectangle. No resizing to the rectangle itself.
     *
     * @param text New Text (JavaFX object) to update within the rectangle.
     */
    public void setTextObj(Text text) {
        this.text = text;
    }


    /**
     * Returns the Game Rule (GameEvent or GameAction) associated with this rectangle.
     *
     * @return Game Rule.
     */
    public GameRule getGameRule() {
        return this.gameRule;
    }


    /**
     * Sets the Game Rule (GameEvent or GameAction) associated with this rectangle.
     *
     * @param gameRule String representing a game rule (event or action).
     */
    public void setGameRule(GameRule gameRule) {
        this.gameRule = gameRule;
    }
}
