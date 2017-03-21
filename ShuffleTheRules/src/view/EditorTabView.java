package view;


import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class EditorTabView {
    private Tab editorTab = new Tab("Editor");

    public EditorTabView(){

        BorderPane editorTabBorderPane = new BorderPane();

        /* center (main portion) will be a grid */
        GridPane editorGridPane = new GridPane();
        editorTabBorderPane.setCenter(editorGridPane);

        /* right side will contain menus */
        TitledPane eventsMenu = new TitledPane("Events", new Button("Add Event"));
        TitledPane actionsMenu = new TitledPane("Actions", new Button("Add Action"));

        Accordion editorTabAccordian = new Accordion();
        editorTabAccordian.getPanes().addAll(eventsMenu, actionsMenu);

        editorTabBorderPane.setRight(editorTabAccordian);
        editorTab.setContent(editorTabBorderPane);

    }
    public Tab getTab(){
        return this.editorTab;
    }
}
