package model;

import javafx.scene.shape.Line;

import java.io.Serializable;

/**
 * Serializable version of a JavaFX Line. Lines are converted to Connectors upon serialization.
 */
public class Connector implements Serializable {

    private double startX;
    private double startY;
    private double endX;
    private double endY;

    public Connector() {

    }

    public Connector(double startX, double startY, double endX, double endY) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
    }

    public Connector(Line l) {
        this.startX = l.getStartX();
        this.startY = l.getStartY();
        this.endX = l.getEndX();
        this.endY = l.getEndY();
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
}
