package view;


import controller.EditorTabController;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class EditorTabView extends Tab {

    private EditorTabController controller;
    private Tab editorTab;
    private Button addEventButton;
    private Button addActionButton;
    private BorderPane pane;
    private GridPane editorGrid;
    private TextField eventIdTextField;
    private TextField actionIdTextField;

    public EditorTabView(){

        controller = new EditorTabController(this);
        editorTab = new Tab("Editor");
        addEventButton = new Button("Add Event");
        addEventButton.setOnAction(controller::onAddEventButtonClick);
        addActionButton = new Button("Add Action");
        addActionButton.setOnAction(controller::onAddActionButtonClick);
        eventIdTextField = new TextField();
        actionIdTextField = new TextField();

        // Represents entire contents of editor tab
        pane = new BorderPane();

         /* center (main portion) will be a grid */
        editorGrid = new GridPane();
        pane.setCenter(editorGrid);

        /* right side will contain menus */
        TitledPane eventsPane = new TitledPane();
        TitledPane actionsPane = new TitledPane();

        GridPane eventsPaneGrid = new GridPane();
        eventsPaneGrid.setVgap(4);
        eventsPaneGrid.setHgap(10);
        eventsPaneGrid.setPadding(new Insets(5, 5, 5, 5));

        ComboBox gameEventComboBox = new ComboBox();
        gameEventComboBox.getItems().addAll(controller.getGameEventList());
        gameEventComboBox.setPromptText("Select a Game Event");
        gameEventComboBox.setEditable(false);

        eventsPaneGrid.add(gameEventComboBox, 0, 0);
        eventsPaneGrid.add(new Label("ID: "), 1, 0);
        eventsPaneGrid.add(eventIdTextField, 2, 0);
        eventsPaneGrid.add(addEventButton, 3, 0);

        GridPane actionsPaneGrid = new GridPane();
        actionsPaneGrid.setVgap(4);
        actionsPaneGrid.setHgap(10);
        actionsPaneGrid.setPadding(new Insets(5, 5, 5, 5));

        ComboBox gameActionComboBox = new ComboBox();
        gameActionComboBox.getItems().addAll(controller.getGameActionList());
        gameActionComboBox.setPromptText("Select a Game Action");
        gameActionComboBox.setEditable(false);

        actionsPaneGrid.add(gameActionComboBox, 0, 0);
        actionsPaneGrid.add(new Label("ID: "), 1, 0);
        actionsPaneGrid.add(actionIdTextField, 2, 0);
        actionsPaneGrid.add(addActionButton, 3, 0);

        eventsPane.setText("Events");
        eventsPane.setContent(eventsPaneGrid);

        actionsPane.setText("Actions");
        actionsPane.setContent(actionsPaneGrid);

        Accordion editorAccordion = new Accordion();
        editorAccordion.getPanes().addAll(eventsPane, actionsPane);

        pane.setRight(editorAccordion);

        editorTab.setContent(pane);

    }

    public Tab getTab(){
        return this.editorTab;
    }

    public BorderPane getPane() {
        return this.pane;
    }

    public GridPane getEditorGrid() {
        return this.editorGrid;
    }

    public TextField getEventIdTextField() {
        return this.eventIdTextField;
    }

    public TextField getActionIdTextField() {
        return this.actionIdTextField;
    }
}
