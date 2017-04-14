/*
* Requirements mandating inclusion:
* */
package model;

import javafx.scene.shape.Line;
import view.RuleElementLine;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Serializable version of a JavaFX Line. RuleElementLines are converted to this upon serialization.
 */
public class RuleElementLineBlueprint implements Serializable {

    private double startX;
    private double startY;
    private double endX;
    private double endY;
    private ArrayList<Double> arrowheadPoints = new ArrayList<>();

    public RuleElementLineBlueprint() {

    }

    public RuleElementLineBlueprint(double startX, double startY, double endX, double endY) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;

        this.arrowheadPoints.add(endX-RuleElementLine.arrowheadSize);
        this.arrowheadPoints.add(endY-RuleElementLine.arrowheadSize);
        this.arrowheadPoints.add(endX);
        this.arrowheadPoints.add(endY);
        this.arrowheadPoints.add(endX+RuleElementLine.arrowheadSize);
        this.arrowheadPoints.add(endY-RuleElementLine.arrowheadSize);
    }

    public RuleElementLineBlueprint(RuleElementLine l) {
        this.startX = l.getStartX();
        this.startY = l.getStartY();
        this.endX = l.getEndX();
        this.endY = l.getEndY();

        this.arrowheadPoints.addAll(l.getArrowhead().getPoints());
    }

    public double getStartX() {
        return startX;
    }

    public void setStartX(double startX) {
        this.startX = startX;
    }

    public double getStartY() {
        return startY;
    }

    public void setStartY(double startY) {
        this.startY = startY;
    }

    public double getEndX() {
        return endX;
    }

    public void setEndX(double endX) {
        this.endX = endX;
    }

    public double getEndY() {
        return endY;
    }

    public void setEndY(double endY) {
        this.endY = endY;
    }

    public ArrayList<Double> getArrowheadPoints() {
        return arrowheadPoints;
    }

    public void setArrowheadPoints(ArrayList<Double> arrowheadPoints) {
        this.arrowheadPoints = arrowheadPoints;
    }
}
