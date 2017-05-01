/*
* Requirements mandating inclusion:
* */
package view.EditorTab;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.GameRule;
import model.GameRuleType;
import model.RuleElementLineBlueprint;
import model.RuleElementRectangleBlueprint;

import java.util.ArrayList;

public class RuleElementRectangle extends Rectangle {

    private Text text = new Text("");
    private GameRule gameRule;
    private Paint defaultBorderColor;
    private boolean clicked = false;
    private ArrayList<RuleElementRectangle> preRules = new ArrayList<>();
    private ArrayList<RuleElementRectangle> postRules = new ArrayList<>();
    private ArrayList<RuleElementLine> outLines = new ArrayList<>();
    private ArrayList<RuleElementLine> inLines = new ArrayList<>();
    private GameRuleType ruleType;

    private double dragStartX;
    private double dragStartY;

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
        this.text = t;

        double textWidth = t.getLayoutBounds().getWidth();
        t.setX(x+(width-textWidth)/2);
        double textHeight = t.getLayoutBounds().getHeight();
        t.setY(y+(height-textHeight)/2);

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
        this.text = t;

        double rectWidth = calculateRectWidth();
        double rectHeight = calculateRectHeight();

        t.setWrappingWidth(rectWidth-20);

        this.setX(x, true);
        this.setY(y, true);

        this.setWidth(rectWidth);
        this.setHeight(rectHeight);
        this.setListeners();
    }


    /**
     * Dynamically generates the rectangle width and height based on (to fit) the given text.
     * Accepts a ruleType parameter (an event or action), and sets the rectangle border accordingly.
     *
     * @param x Rectangle starting (top-left) x coordinate.
     * @param y Rectangle starting (top-left) y coordinate.
     * @param text Text that goes inside the rectangle.
     * @param ruleType Either GameRuleType.EVENT or GameRuleType.ACTION. Used to set the rectangle border color.
     */
    public RuleElementRectangle(double x, double y, String text, GameRuleType ruleType) {
        this(x, y, text);
        this.ruleType = ruleType;

        this.setFill(Color.WHITE);
        this.setStrokeWidth(2);

        switch (ruleType) {
            case EVENT:
                this.setStroke(Color.BLUE);
                this.defaultBorderColor = Color.BLUE;
                break;
            case ACTION:
                this.setStroke(Color.RED);
                this.defaultBorderColor = Color.RED;
                break;
        }
    }


    /**
     * Constructs the rectangle from the given blueprint.
     *
     * @param blueprint A RuleElementRectangleBlueprint that will be used to construct this rectangle.
     */
    public RuleElementRectangle(RuleElementRectangleBlueprint blueprint) {
        this();
        this.constructFromBlueprint(blueprint);
    }


    /**
     * Constructs (sets the fields) of this rectangle given its blueprint.
     *
     * @param blueprint A RuleElementRectangleBlueprint that will be used to set all the fields of this rectangle.
     */
    public void constructFromBlueprint(RuleElementRectangleBlueprint blueprint) {
        this.setWidth(blueprint.getWidth());
        this.setHeight(blueprint.getHeight());
        this.setGameRule(blueprint.getGameRule());
        this.setRuleType(blueprint.getRuleType());
        this.setDefaultBorderColor(Color.valueOf(blueprint.getDefaultBorderColor()));

        Text t = new Text(blueprint.getText());
        t.setFont(new Font(15));
        this.text = t;
        t.setWrappingWidth(blueprint.getWidth()-20);

        this.setX(blueprint.getX(), true);
        this.setY(blueprint.getY(), true);

        this.setFill(Color.WHITE);
        this.setStrokeWidth(2);

        switch (this.ruleType) {
            case EVENT:
                this.setStroke(Color.BLUE);
                break;
            case ACTION:
                this.setStroke(Color.RED);
                break;
        }

        for (RuleElementRectangleBlueprint preRuleBlueprint : blueprint.getPreRules()) {
            this.preRules.add(new RuleElementRectangle(preRuleBlueprint));
        }

        for (RuleElementRectangleBlueprint postRuleBlueprint : blueprint.getPostRules()) {
            this.postRules.add(new RuleElementRectangle(postRuleBlueprint));
        }

        for (RuleElementLineBlueprint inLineBlueprint : blueprint.getInLines()) {
            this.inLines.add(new RuleElementLine(inLineBlueprint));
        }

        for (RuleElementLineBlueprint outLineBlueprint : blueprint.getOutLines()) {
            this.outLines.add(new RuleElementLine(outLineBlueprint));
        }

        this.setListeners();
    }


    /**
     * Returns the expected/desired width of the rectangle, based on the text inside of it.
     *
     * @return The proper width of the rectangle.
     */
    public double calculateRectWidth() {
        double textWidth = text.getLayoutBounds().getWidth();
        return textWidth + 40;
    }


    /**
     * Returns the expected/desired height of the rectangle, based on the text inside of it.
     *
     * @return The proper height of the rectangle.
     */
    public double calculateRectHeight() {
        double textHeight = text.getLayoutBounds().getHeight();
        return textHeight + 40;
    }


    /**
     * Sets the x-coordinate of the Rectangle and sets its text accordingly, if setText is provided (true).
     *
     * @param x The x-coordinate to which the Rectangle will be set and the text will be levelled with.
     * @param setText Boolean that determines whether the Rectangle text position will be adjusted.
     */
    public void setX(double x, boolean setText) {
        this.setX(x);
        if (setText) {this.text.setX(x+20);}
    }


    /**
     * Sets the y-coordinate of the Rectangle and sets its text accordingly, if setText is provided (true).
     *
     * @param y The y-coordinate to which the Rectangle will be set and the text will be levelled with.
     * @param setText Boolean that determines whether the Rectangle text position will be adjusted.
     */
    public void setY(double y, boolean setText) {
        this.setY(y);
        if (setText) {this.text.setY(y+33);}
    }


    /**
     * Sets the x-coordinate of the Rectangle, its text, and lines, if setText and setLines are provided (true).
     *
     * @param x The x-coordinate to which the Rectangle will be set. The text will be centered inside this Rectangle
     *          position, and the lines will be centered on its border.
     * @param setText Boolean that determines whether the Rectangle text position will be adjusted.
     * @param setLines Boolean that determines whether the Rectangle line positions will be adjusted.
     */
    public void setX(double x, boolean setText, boolean setLines) {
        this.setX(x, setText);
        if (setLines) {
            if (this.inLines.size() > 0) {
                for (RuleElementLine l : this.inLines) {
                    l.setEndX(this.getCenterX(), true);
                }
            }
            if (this.outLines.size() > 0) {
                for (RuleElementLine l : this.outLines) {
                    l.setStartX(this.getCenterX());
                }
            }
        }
    }


    /**
     * Sets the y-coordinate of the Rectangle, its text, and lines, if setText and setLines are provided (true).
     *
     * @param y The y-coordinate to which the Rectangle will be set. The text will be centered inside this Rectangle
     *          position, and the lines will be centered on its border.
     * @param setText Boolean that determines whether the Rectangle text position will be adjusted.
     * @param setLines Boolean that determines whether the Rectangle line positions will be adjusted.
     */
    public void setY(double y, boolean setText, boolean setLines) {
        this.setY(y, setText);
        if (setLines) {
            if (this.inLines.size() > 0) {
                for (RuleElementLine l : this.inLines) {
                    l.setEndY(this.getY(), true);
                }
            }
            if (this.outLines.size() > 0) {
                for (RuleElementLine l : this.outLines) {
                    l.setStartY(this.getEndY());
                }
            }
        }
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
     * Returns the x-coordinate of the end of the rectangle.
     *
     * @return End x-coordinate of rectangle.
     */
    public double getEndX() {
        return this.getX()+(this.getWidth());
    }


    /**
     * Returns the y-coordinate of the end of the rectangle.
     *
     * @return End y-coordinate of rectangle.
     */
    public double getEndY() {
        return this.getY()+(this.getHeight());
    }


    /**
     * Returns the x-coordinate of the Rectangle assuming its center at the specified value.
     *
     * @return The x-coordinate of the Rectangle given its center.
     */
    public double getXForCenter(double centerX) {
        return centerX-(this.getWidth()/2);
    }


    /**
     * Returns the y-coordinate of the Rectangle assuming its center at the specified value.
     *
     * @return The y-coordinate of the Rectangle given its center.
     */
    public double getYForCenter(double centerY) {
        return centerY-(this.getHeight()/2);
    }


    /**
     * Sets the x-coordinate of the center of the rectangle to the specified x value.
     */
    public void setCenterX(double centerX) {
        double x = this.getXForCenter(centerX);
        this.setX(x, true);
    }


    /**
     * Sets the y-coordinate of the center of the rectangle to the specified y value.
     */
    public void setCenterY(double centerY) {
        double y = this.getYForCenter(centerY);
        this.setY(y, true);
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
        this.text.setWrappingWidth(0.0);
        this.setWidth(calculateRectWidth());
        this.setHeight(calculateRectHeight());
        this.text.setWrappingWidth(this.getWidth()-20);

        this.setX(this.getX(), true, true);
        this.setY(this.getY(), true, true);
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
     * Returns this.preRules; an ArrayList of Rectangles that immediately precede this Rectangle.
     *
     * @return ArrayList of Game Rules that immediately precede this Game Rule
     */
    public ArrayList<RuleElementRectangle> getPreRules() {
        return this.preRules;
    }


    /**
     * Sets this.preRules, given an ArrayList of Rectangles that immediately precede this Rectangle.
     *
     * @param preRules ArrayList of Rectangles that immediately precede this Rectangle
     */
    public void setPreRules(ArrayList<RuleElementRectangle> preRules) {
        this.preRules = preRules;
    }


    /**
     * Adds a rectangle to the list of pre-rules - rules that immediately precede this rectangle in the rule tree.
     *
     * @param preRule A RuleElementRectangle that immediately precedes this rectangle in the rule tree.
     */
    public void addPreRule(RuleElementRectangle preRule) {
        this.preRules.add(preRule);
    }


    /**
     * Returns this.postRules; an ArrayList of Rectangles that immediately proceed this Rectangle.
     *
     * @return ArrayList of Game Rules that immediately follow this Game Rule
     */
    public ArrayList<RuleElementRectangle> getPostRules() {
        return this.postRules;
    }


    /**
     * Sets this.postRules, given an ArrayList of Rectangles that immediately proceed this Rectangle.
     *
     * @param postRules ArrayList of Rectangles that immediately follow this Rectangle
     */
    public void setPostRules(ArrayList<RuleElementRectangle> postRules) {
        this.postRules = postRules;
    }


    /**
     * Adds a rectangle to the list of post-rules - rules that immediately proceed this rectangle in the rule tree.
     *
     * @param postRule A RuleElementRectangle that immediately proceeds this rectangle in the rule tree.
     */
    public void addPostRule(RuleElementRectangle postRule) {
        this.postRules.add(postRule);
    }


    /**
     * Returns an ArrayList of Lines that are exiting from this Rectangle.
     *
     * @return this.outLines; ArrayList of Lines that exit from this Rectangle
     */
    public ArrayList<RuleElementLine> getOutLines() {
        return this.outLines;
    }


    /**
     * Sets this.outLines; an ArrayList of Lines that are exiting from this Rectangle.
     *
     * @param outLines An ArrayList of Lines that are exiting from this Rectangle.
     */
    public void setOutLines(ArrayList<RuleElementLine> outLines) {
        this.outLines = outLines;
    }


    /**
     * Returns an ArrayList of Lines that are entering this Rectangle.
     *
     * @return this.inLines; ArrayList of Lines that enter this Rectangle
     */
    public ArrayList<RuleElementLine> getInLines() {
        return this.inLines;
    }


    /**
     * Sets this.inLines; an ArrayList of Lines that are entering this Rectangle.
     *
     * @param inLines An ArrayList of Lines that are entering this Rectangle.
     */
    public void setInLines(ArrayList<RuleElementLine> inLines) {
        this.inLines = inLines;
    }


    /**
     * Returns the GameRuleType (ACTION or EVENT) associated with this rectangle.
     *
     * @return The type of game rule (event or action) that this rectangle represents.
     */
    public GameRuleType getRuleType() {
        return this.ruleType;
    }


    /**
     * Sets this.ruleType; the GameRuleType associated with this rectangle.
     *
     * @param ruleType The type of game rule (event or action) of this rectangle.
     */
    public void setRuleType(GameRuleType ruleType) {
        this.ruleType = ruleType;
    }


    /**
     * Listener for the mouse entering the rectangle. Changes the border color, but saves the old (original) one.
     *
     * @param e MouseEvent
     */
    public void onMouseEntered(MouseEvent e) {
        if (!this.clicked) {
            if (this.defaultBorderColor == null) {
                this.defaultBorderColor = this.getStroke();
            }
            this.setStroke(Color.GAINSBORO);
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
     * Listener for the mouse being pressed on this rectangle. Used to log the starting mouse-drag position.
     *
     * @param e MouseEvent
     */
    public void onMousePressed(MouseEvent e) {
        this.dragStartX = e.getX();
        this.dragStartY = e.getY();
    }


    /**
     * Listener for the mouse being dragged (pressed and moved) while on this rectangle.
     * Moves the Rectangle, text, and lines according to the drag movement.
     *
     * @param e MouseEvent
     */
    public void onMouseDragged(MouseEvent e) {
        double offsetX = e.getX()-this.dragStartX;
        double offsetY = e.getY()-this.dragStartY;

        this.setX(this.getX()+offsetX > 0 ? this.getX()+offsetX : 0, true, true);
        this.setY(this.getY()+offsetY > 0 ? this.getY()+offsetY : 0, true, true);

        this.dragStartX = e.getX();
        this.dragStartY = e.getY();
    }


    /**
     * Resets the border (stroke) color of the rectangle to its default (original) one.
     */
    public void resetBorder() {
        if (this.defaultBorderColor != null) {
            this.setStroke(this.defaultBorderColor);
        } else {
            this.defaultBorderColor = this.getStroke();
        }
    }


    @Override
    public int hashCode() {
        return super.hashCode() + this.text.hashCode();
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == this) {return true;}
        if (obj == null) {return false;}
        if (!(obj instanceof RuleElementRectangle)) {return false;}

        RuleElementRectangle otherRect = (RuleElementRectangle) obj;
        return (this.getX() == otherRect.getX()) &&
                (this.getY() == otherRect.getY()) &&
                (this.getWidth() == otherRect.getWidth()) &&
                (this.getHeight() == otherRect.getHeight()) &&
                (this.getText().equals(otherRect.getText())) &&
                (this.getRuleType() == otherRect.getRuleType()) &&
                (this.getGameRuleName().equals(otherRect.getGameRuleName()));
    }


    /**
     * Sets listeners for this Rectangle.
     */
    public void setListeners() {
        this.setOnMouseEntered(this::onMouseEntered);
        this.setOnMouseExited(this::onMouseExited);
        this.setOnMousePressed(this::onMousePressed);
        this.setOnMouseDragged(this::onMouseDragged);
        this.text.setOnMouseEntered(this::onMouseEntered);
        this.text.setOnMouseExited(this::onMouseExited);
        this.text.setOnMousePressed(this::onMousePressed);
        this.text.setOnMouseDragged(this::onMouseDragged);
    }
}
