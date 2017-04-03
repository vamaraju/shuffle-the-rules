package model;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class RuleElementRectangle extends Rectangle {

    private Text text = new Text("");
    private GameRule gameRule;
    private Paint defaultBorderColor;
    private boolean clicked = false;
    private ArrayList<RuleElementRectangle> postRules;
    private ArrayList<Line> outLines;

    public RuleElementRectangle() {
        super();
        this.setListeners();
    }

    public RuleElementRectangle(double width, double height) {
        super(width, height);
        this.setListeners();
    }

    public RuleElementRectangle(double x, double y, double width, double height) {
        super(x, y, width, height);
        this.setListeners();
    }

    public RuleElementRectangle(double width, double height, Paint fill) {
        super(width, height, fill);
        this.setListeners();
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
        this.setListeners();
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
        this.setListeners();
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
        t.setY(y+33);

        this.text = t;

        this.setWidth(rectWidth);
        this.setHeight(rectHeight);
        this.setListeners();
    }


    /**
     * Dynamically generates the rectangle width and height based on (to fit) the given text.
     * Accepts a ruleType parameter that can be either "event" or "action", and sets the rectangle border accordingly.
     *
     * @param x Rectangle starting (top-left) x coordinate.
     * @param y Rectangle starting (top-left) y coordinate.
     * @param text Text that goes inside the rectangle.
     * @param ruleType Either "event" or "action". Used to set the rectangle border color.
     */
    public RuleElementRectangle(double x, double y, String text, String ruleType) {
        super(x, y, 150, 75);

        Text t = new Text(text);
        t.setFont(new Font(15));

        double textWidth = t.getLayoutBounds().getWidth();
        double rectWidth = textWidth + 40;
        t.setWrappingWidth(rectWidth-20);
        t.setX(x+20);
        double textHeight = t.getLayoutBounds().getHeight();
        double rectHeight = textHeight + 40;
        t.setY(y+33);

        this.text = t;

        this.setWidth(rectWidth);
        this.setHeight(rectHeight);

        if (ruleType.equals("event")) {
            this.setFill(Color.WHITE);
            this.setStrokeWidth(2);
            this.setStroke(Color.BLUE);
        } else if (ruleType.equals("action")) {
            this.setFill(Color.WHITE);
            this.setStrokeWidth(2);
            this.setStroke(Color.RED);
        }
        this.setListeners();
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
     * Returns the name of the Game Rule (GameEvent or GameAction) associated with this rectangle.
     *
     * @return Game Rule name.
     */
    public String getGameRuleName() {
        return this.gameRule.getName();
    }


    /**
     * Sets the Game Rule (GameEvent or GameAction) associated with this rectangle.
     *
     * @param gameRule String representing a game rule (event or action).
     */
    public void setGameRule(GameRule gameRule) {
        this.gameRule = gameRule;
    }


    /**
     * Return the Color of this Rectangle's border/stroke.
     *
     * @return defaultBorderColor (color of this rectangle's border/stroke)
     */
    public Paint getDefaultBorderColor() {
        return defaultBorderColor;
    }


    /**
     * Set the Color of this Rectangle's border/stroke.
     *
     * @param defaultBorderColor Color to set the Rectangle border/stroke.
     */
    public void setDefaultBorderColor(Paint defaultBorderColor) {
        this.defaultBorderColor = defaultBorderColor;
    }


    /**
     * Returns the value of this.clicked. True if the Rectangle is currently clicked (the border is a different color).
     *
     * @return Returns true if this Rectangle is currently clicked (this.clicked == true).
     */
    public boolean isClicked() {
        return this.clicked;
    }


    /**
     * Sets the value of this.clicked; i.e., whether this Rectangle is currently clicked or not.
     * If it is clicked, the Rectangle border should be a different color.
     */
    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }


    /**
     * Listener for the mouse entering the rectangle. Changes the border color, but saves the old (original) one.
     *
     * @param e MouseEvent
     */
    public void onMouseEntered(MouseEvent e) {
        if (!this.clicked) {
            this.defaultBorderColor = this.getStroke();
            this.setStroke(Color.GREY);
        }
    }


    /**
     * Listener for the mouse exiting the rectangle. Changes the border color back to the original color.
     *
     * @param e MouseEvent
     */
    public void onMouseExited(MouseEvent e) {
        if (!this.clicked) {
            this.setStroke(this.defaultBorderColor);
        }
    }


    /**
     * Resets the border (stroke) colcor of the rectangle to its default (original) one.
     */
    public void resetBorder() {
        if (this.defaultBorderColor != null) {
            this.setStroke(this.defaultBorderColor);
        } else {
            this.defaultBorderColor = this.getStroke();
        }
    }


//    public boolean contains(double x, double y) {
//        return
//    }


    /**
     * Sets listeners for this Rectangle.
     */
    public void setListeners() {
        this.setOnMouseEntered(this::onMouseEntered);
        this.setOnMouseExited(this::onMouseExited);
        this.text.setOnMouseEntered(this::onMouseEntered);
        this.text.setOnMouseExited(this::onMouseExited);
    }
}
