/*
* Requirements mandating inclusion:
* 3.2.1.8.3.3 User can place Pile Objects on the grid.
* 3.2.1.1.3.1 User can place Piles on Table.
*/
package model;

import java.io.Serializable;

public class TableGridPosition implements Serializable, Comparable<TableGridPosition> {

    private int x;
    private int y;

    public TableGridPosition() {

    }

    public TableGridPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean at(int x, int y) {
        return (this.x == x) && (this.y == y);
    }

    public boolean at(TableGridPosition pos) {
        return (this.x == pos.getX()) && (this.y == pos.getY());
    }

    public boolean containedWithin(TableGridPosition otherPosition) {
        return (this.x <= otherPosition.getX()) && (this.y <= otherPosition.getY());
    }

    @Override
    public int compareTo(TableGridPosition otherPosition) {
        if (this.equals(otherPosition)) {return 0;}
        if (this.x == otherPosition.getX() && this.y == otherPosition.getY()) {return 0;}
        if (this.x <= otherPosition.getX() && this.y <= otherPosition.getY()) {return -1;}
        else {return 1;}
    }

    @Override
    public int hashCode() {
        return (this.x*17) + (this.y*21);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {return true;}
        if (obj == null) {return false;}
        if (!(obj instanceof TableGridPosition)) {return false;}

        TableGridPosition otherPos = (TableGridPosition) obj;
        return at(otherPos);
    }

    @Override
    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }
}
