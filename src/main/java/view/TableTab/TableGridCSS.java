/*
* Requirements mandating inclusion:
*
* 3.2.1.8.3.4 User can show the grid.
* 3.2.1.8.3.5 User can hide the grid.
* */

package view.TableTab;

public enum TableGridCSS {

    DEFAULT_GRID("-fx-background-color: white; -fx-padding: 10;"),
    ENABLE_GRID("-fx-background-color: black, green; -fx-background-insets: 0, 0 1 1 0;"),
    DISABLE_GRID("-fx-background-color: green;"),
    ENABLE_BACKGROUND_IMAGE("-fx-background-image: url('assets/background/green.jpg');"),
    ENABLE_BORDER_RED("-fx-border-color: red; -fx-border-width: 4;"),
    ENABLE_BORDER_BLUE("-fx-border-color: blue; -fx-border-width: 4;"),
    DISABLE_BORDER("-fx-background-color: black, green; -fx-background-insets: 0, 0 1 1 0;");

    private final String style;
    private TableGridCSS(String style) {
        this.style = style;
    }

    public String getStyle() {
        return this.style;
    }

    @Override
    public String toString() {
        return this.style.replaceAll(";", ";\n");
    }
}
