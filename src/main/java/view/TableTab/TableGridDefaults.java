package view.TableTab;

import model.PlayingCard;

public enum TableGridDefaults {

    NUM_COLUMNS(7.0),
    NUM_ROWS(4.0),
    WIDTH(7.0),
    HEIGHT(4.0),
    CELL_WIDTH(70.0),
    CELL_HEIGHT(70.0*PlayingCard.ASPECT_RATIO);

    private final double num;
    private TableGridDefaults(double num) {
        this.num = num;
    }

    public double getNum() {
        return this.num;
    }

    public int toInt() {
        return (int) this.num;
    }

    public double toDouble() {
        return this.num;
    }

    public String toIntStr() {
        return Integer.toString((int) this.num);
    }

    public String toDoubleStr() {
        return Double.toString(this.num);
    }

    @Override
    public String toString() {
        return Double.toString(this.num);
    }
}
