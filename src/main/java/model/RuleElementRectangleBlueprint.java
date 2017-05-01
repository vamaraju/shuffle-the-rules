/*
* Requirements mandating inclusion:
* */
package model;

import view.EditorTab.RuleElementLine;
import view.EditorTab.RuleElementRectangle;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Serializable version of a JavaFX Rectangle. RuleElementRectangles are converted to this upon serialization.
 */
public class RuleElementRectangleBlueprint implements Serializable {

    private double x;
    private double y;
    private double width;
    private double height;

    private String text;
    private GameRule gameRule;
    private GameRuleType ruleType;
    private String defaultBorderColor;
    private ArrayList<RuleElementRectangleBlueprint> preRules = new ArrayList<>();
    private ArrayList<RuleElementRectangleBlueprint> postRules = new ArrayList<>();
    private ArrayList<RuleElementLineBlueprint> inLines = new ArrayList<>();
    private ArrayList<RuleElementLineBlueprint> outLines = new ArrayList<>();

    public RuleElementRectangleBlueprint() {

    }

    public RuleElementRectangleBlueprint(RuleElementRectangle r) {
        this.setX(r.getX());
        this.setY(r.getY());
        this.setWidth(r.getWidth());
        this.setHeight(r.getHeight());
        this.setText(r.getText());
        this.setGameRule(r.getGameRule());
        this.setRuleType(r.getRuleType());
        this.setDefaultBorderColor(r.getDefaultBorderColor().toString());

        for (RuleElementRectangle preRule : r.getPreRules()) {
            this.preRules.add(new RuleElementRectangleBlueprint(preRule.getX(), preRule.getY(), preRule.getWidth(), preRule.getHeight(), preRule.getText(), preRule.getGameRule(), preRule.getRuleType()));
        }

        for (RuleElementRectangle postRule : r.getPostRules()) {
            this.postRules.add(new RuleElementRectangleBlueprint(postRule.getX(), postRule.getY(), postRule.getWidth(), postRule.getHeight(), postRule.getText(), postRule.getGameRule(), postRule.getRuleType()));
        }

        for (RuleElementLine inLine : r.getInLines()) {
            this.inLines.add(new RuleElementLineBlueprint(inLine));
        }

        for (RuleElementLine outLine : r.getOutLines()) {
            this.outLines.add(new RuleElementLineBlueprint(outLine));
        }
    }

    public RuleElementRectangleBlueprint(double x, double y, double width, double height, String text, GameRule gameRule, GameRuleType ruleType) {
        this.setX(x);
        this.setY(y);
        this.setWidth(width);
        this.setHeight(height);
        this.setText(text);
        this.setGameRule(gameRule);
        this.setRuleType(ruleType);
        this.setDefaultBorderColor(ruleType.getColor());
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public GameRule getGameRule() {
        return gameRule;
    }

    public void setGameRule(GameRule gameRule) {
        this.gameRule = gameRule;
    }

    public String getDefaultBorderColor() {
        return defaultBorderColor;
    }

    public void setDefaultBorderColor(String defaultBorderColor) {
        this.defaultBorderColor = defaultBorderColor;
    }

    public ArrayList<RuleElementRectangleBlueprint> getPreRules() {
        return preRules;
    }

    public void setPreRules(ArrayList<RuleElementRectangleBlueprint> preRules) {
        this.preRules = preRules;
    }

    public ArrayList<RuleElementRectangleBlueprint> getPostRules() {
        return postRules;
    }

    public void setPostRules(ArrayList<RuleElementRectangleBlueprint> postRules) {
        this.postRules = postRules;
    }

    public ArrayList<RuleElementLineBlueprint> getOutLines() {
        return outLines;
    }

    public void setOutLines(ArrayList<RuleElementLineBlueprint> outLines) {
        this.outLines = outLines;
    }

    public ArrayList<RuleElementLineBlueprint> getInLines() {
        return inLines;
    }

    public void setInLines(ArrayList<RuleElementLineBlueprint> inLines) {
        this.inLines = inLines;
    }

    public GameRuleType getRuleType() {
        return ruleType;
    }

    public void setRuleType(GameRuleType ruleType) {
        this.ruleType = ruleType;
    }

    @Override
    public int hashCode() {
        return (int) (this.x +
                this.y +
                this.width +
                this.height +
                this.text.hashCode() +
                this.ruleType.getName().hashCode() +
                this.gameRule.getName().hashCode());
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == this) {return true;}
        if (obj == null) {return false;}
        if (!(obj instanceof RuleElementRectangleBlueprint)) {return false;}

        RuleElementRectangleBlueprint otherRect = (RuleElementRectangleBlueprint) obj;
        return (this.getX() == otherRect.getX()) &&
                (this.getY() == otherRect.getY()) &&
                (this.getWidth() == otherRect.getWidth()) &&
                (this.getHeight() == otherRect.getHeight()) &&
                (this.getText().equals(otherRect.getText())) &&
                (this.getRuleType() == otherRect.getRuleType()) &&
                (this.getGameRule().getName().equals(otherRect.getGameRule().getName()));
    }
}
