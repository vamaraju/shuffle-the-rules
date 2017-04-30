package view;

import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import model.RuleElementLineBlueprint;

/**
 * Represents a line with a triangular arrowhead at its end. Used in Editor Tab.
 */
public class RuleElementLine extends Line {

    private Polygon arrowhead;
    public final static double arrowheadSize = 10;

    public RuleElementLine() {
        super();
    }

    public RuleElementLine(double startX, double startY, double endX, double endY) {
        super(startX, startY, endX, endY-arrowheadSize);
        arrowhead = new Polygon(endX-arrowheadSize, endY-arrowheadSize, endX, endY, endX+arrowheadSize, endY-arrowheadSize);
    }

    public RuleElementLine(RuleElementLineBlueprint blueprint) {
        super(blueprint.getStartX(), blueprint.getStartY(), blueprint.getEndX(), blueprint.getEndY());
        arrowhead = new Polygon();
        arrowhead.getPoints().addAll(blueprint.getArrowheadPoints());
    }

    public void setEndX(double endX, boolean setArrowhead) {
        this.setEndX(endX);
        if (setArrowhead) {
            arrowhead.getPoints().set(0, endX-arrowheadSize);
            arrowhead.getPoints().set(2, endX);
            arrowhead.getPoints().set(4, endX+arrowheadSize);
        }
    }

    public void setEndY(double endY, boolean setArrowhead) {
        this.setEndY(endY-arrowheadSize);
        if (setArrowhead) {
            arrowhead.getPoints().set(1, endY-arrowheadSize);
            arrowhead.getPoints().set(3, endY);
            arrowhead.getPoints().set(5, endY-arrowheadSize);
        }
    }

    public Polygon getArrowhead() {
        return arrowhead;
    }

    public void setArrowhead(Polygon arrowhead) {
        this.arrowhead = arrowhead;
    }

    @Override
    public int hashCode() {
        return super.hashCode() + arrowhead.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {return true;}
        if (obj == null) {return false;}
        if (!(obj instanceof RuleElementLine)) {return false;}

        RuleElementLine otherLine = (RuleElementLine) obj;
        return (this.getStartX() == otherLine.getStartX()) &&
                (this.getStartY() == otherLine.getStartY()) &&
                (this.getEndX() == otherLine.getEndX()) &&
                (this.getEndY() == otherLine.getEndY());
    }
}
