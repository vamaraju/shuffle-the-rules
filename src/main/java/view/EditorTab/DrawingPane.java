package view.EditorTab;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import model.GameEvents.OnRoundStartEvent;
import model.GameEvents.OnTurnEndEvent;
import model.GameEvents.OnTurnStartEvent;
import model.GameRule;
import model.RuleGraph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class DrawingPane extends Pane {

    public static final double ROW_SEPARATION_DISTANCE = 70;
    public static final double COL_SEPARATION_DISTANCE = 40;
    public static final String ROOT_NAME = "Game Start";

    public DrawingPane() {
        this.setPrefSize(800, 800);
        this.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
    }

    public void extendToFitWidth(RuleElementRectangle r) {
        if (r.getEndX() > this.getMinWidth()-100) {
            this.setMinWidth(r.getEndX()+100);
        }
    }

    public void extendToFitHeight(RuleElementRectangle r) {
        if (r.getEndY() > this.getMinHeight()-100) {
            this.setMinHeight(r.getEndY()+100);
        }
    }

    public void extendToFit(RuleElementRectangle r) {
        extendToFitWidth(r);
        extendToFitHeight(r);
    }

    public void clear() {
        this.getChildren().clear();
    }

    public void add(Node element) {
        this.getChildren().add(element);
    }

    public void addAll(Node... elements) {
        this.getChildren().addAll(elements);
    }

    public void addRule(RuleElementRectangle r) {
        addAll(r, r.getTextObj());
    }

    public void addLine(RuleElementLine l) {
        addAll(l, l.getArrowhead());
    }

    public void addRuleAndLine(RuleElementRectangle r, RuleElementLine l) {
        addRule(r);
        addLine(l);
    }

    public void remove(Node element) {
        this.getChildren().remove(element);
    }

    public void removeAll(Node... elements) {
        this.getChildren().removeAll(elements);
    }

    public void removeRule(RuleElementRectangle r) {
        removeAll(r, r.getTextObj());
    }

    public void removeLine(RuleElementLine l) {
        removeAll(l, l.getArrowhead());
    }

    public void removeRuleAndLine(RuleElementRectangle r, RuleElementLine l) {
        removeRule(r);
        removeLine(l);
    }

    public RuleElementRectangle getRoot() {
        for (Object o : this.getChildren()) {
            if (o instanceof RuleElementRectangle) {
                RuleElementRectangle r = (RuleElementRectangle) o;
                if (r.getName().equals("Game Start")) {
                    return r;
                }
            }
        }
        return null;
    }

    public boolean isRoot(RuleElementRectangle r) {
        return r.getName().equals(ROOT_NAME);
    }

    public RuleElementRectangle getRectByCoordinates(double x, double y) {
        for (int i = this.getChildren().size()-1; i >= 0; i--) {
            Object o = this.getChildren().get(i);
            if (o instanceof RuleElementRectangle) {
                RuleElementRectangle r = (RuleElementRectangle) o;
                if (r.contains(x, y)) {
                    return r;
                }
            }
        }
        return null;
    }

    public RuleElementRectangle getRectByName(String name) {
        for (int i = this.getChildren().size()-1; i >= 0; i--) {
            Object o = this.getChildren().get(i);
            if (o instanceof RuleElementRectangle) {
                RuleElementRectangle r = (RuleElementRectangle) o;
                if (r.getName().equals(name)) {
                    return r;
                }
            }
        }
        return null;
    }

    public RuleElementRectangle getRectByClass(Class<? extends Object> c) {
        for (Object o : this.getChildren()) {
            if (o instanceof RuleElementRectangle) {
                RuleElementRectangle r = (RuleElementRectangle) o;
                if (c.isInstance(r.getGameRule())) {
                    return r;
                }
            }
        }
        return null;
    }

    public int getCountByClass(Class<? extends Object> c) {
        int count = 0;
        for (Object o : this.getChildren()) {
            if (o instanceof RuleElementRectangle) {
                RuleElementRectangle r = (RuleElementRectangle) o;
                if (c.isInstance(r.getGameRule())) {
                    count++;
                }
            }
        }
        return count;
    }

    public List<RuleElementRectangle> getRow(double y) {
        List<RuleElementRectangle> row = new ArrayList<>();

        for (Object o : this.getChildren()) {
            if (o instanceof RuleElementRectangle) {
                RuleElementRectangle r = (RuleElementRectangle) o;
                if (r.getY() == y) {
                    row.add(r);
                }
            }
        }

        return row;
    }

    public List<RuleElementRectangle> getRowSorted(double y) {
        List<RuleElementRectangle> row = getRow(y);
        row.sort(Comparator.comparingDouble((r) -> r.getX()));
        return row;
    }

    public RuleElementRectangle getLeftmostInRow(double y) {
        return getRowSorted(y).get(0);
    }

    public RuleElementRectangle getRightmostInRow(double y) {
        List<RuleElementRectangle> sortedRow = getRowSorted(y);
        return sortedRow.get(sortedRow.size()-1);
    }

    public void setRelativeXPlacement(RuleElementRectangle currentRect, RuleElementRectangle previousRect) {
        double currentRowY = previousRect.getEndY() + ROW_SEPARATION_DISTANCE;
        List<RuleElementRectangle> currentRow = getRowSorted(currentRowY);

        if (currentRow.size() > 0) {
            RuleElementRectangle minXRect = currentRow.get(0);
            RuleElementRectangle maxXRect = currentRow.get(currentRow.size()-1);

            if ((minXRect.getX() - COL_SEPARATION_DISTANCE - currentRect.getWidth() - 20 > 0) && (new Random().nextInt(10) < 5)) {
                currentRect.setX(minXRect.getX() - currentRect.getWidth() - COL_SEPARATION_DISTANCE, true, true);
            } else {
                currentRect.setX(maxXRect.getEndX() + COL_SEPARATION_DISTANCE, true, true);
            }

        } else { // current row is empty
            currentRect.setCenterX(previousRect.getCenterX());
        }
    }

    public void setRelativeYPlacement(RuleElementRectangle currentRect, RuleElementRectangle previousRect) {
        currentRect.setY(previousRect.getEndY() + ROW_SEPARATION_DISTANCE, true, true);
    }

    public void setRelativePlacement(RuleElementRectangle currentRect, RuleElementRectangle previousRect) {
        setRelativeXPlacement(currentRect, previousRect);
        setRelativeYPlacement(currentRect, previousRect);
    }

    public void placeRelative(RuleElementRectangle currentRect, RuleElementRectangle previousRect) {
        setRelativePlacement(currentRect, previousRect);
        addRule(currentRect);
    }

    public void extendToFit() {
        for (Object o : this.getChildren()) {
            if (o instanceof RuleElementRectangle) {
                RuleElementRectangle r = (RuleElementRectangle) o;
                extendToFit(r.getEndX(), r.getEndY());
            }
        }
    }

    public void extendToFit(double x, double y) {
        if (x > this.getMinWidth()-100) {
            this.setMinWidth(x+100);
        }

        if (y > this.getMinHeight()-100) {
            this.setMinHeight(y+100);
        }
    }

    private void addPostRules(RuleElementRectangle r) {
        r.getGameRule().getPostRules().clear();
        for (RuleElementRectangle postRule : r.getPostRules()) {
            r.getGameRule().getPostRules().add(postRule.getGameRule());
        }
    }

    public RuleGraph toRuleGraph() {
        GameRule root = getRoot().getGameRule();
        GameRule roundStart = getRectByClass(OnRoundStartEvent.class).getGameRule();
        GameRule turnStart = getRectByClass(OnTurnStartEvent.class).getGameRule();
        GameRule turnEnd = getRectByClass(OnTurnEndEvent.class).getGameRule();

        for (Object o : this.getChildren()) {
            if (o instanceof RuleElementRectangle) {
                RuleElementRectangle r = (RuleElementRectangle) o;
                addPostRules(r);
            }
        }

        RuleGraph graph = new RuleGraph(root, roundStart, turnStart, turnEnd);
        return graph;
    }
}


