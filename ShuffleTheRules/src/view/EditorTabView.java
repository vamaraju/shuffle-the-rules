package view;


import controller.EditorTabController;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class EditorTabView extends Tab {

    private EditorTabController controller;
    private BorderPane window;
    private GridPane editorGrid;
    private Accordion accordion;
    private TitledPane eventsPane;
    private TitledPane actionsPane;
    private GridPane eventsGrid;
    private GridPane actionsGrid;
    private ComboBox eventComboBox;
    private ComboBox actionComboBox;
    private Button addEventButton;
    private Button addActionButton;
    private TextField eventIdTextField;
    private TextField actionIdTextField;

    public EditorTabView(){

        this.setText("Editor");
        controller = new EditorTabController(this);
        window = new BorderPane(); // Represents entire contents of editor tab
        editorGrid = new GridPane(); // main (left/center) portion of editor window where rule tree appears
        accordion = new Accordion(); // right portion of editor window with accordion with Events and Actions tabs
        eventsPane = new TitledPane(); // "Events" tab on right-hand side in the accordion
        eventsGrid = new GridPane(); // Grid in the "Events" tab
        actionsPane = new TitledPane(); // "Actions" tab on right-hand side in the accordion
        actionsGrid = new GridPane(); // Grid in the "Actions" tab
        addEventButton = new Button("Add Event"); // "Add Event" button in "Events" tab
        addActionButton = new Button("Add Action"); // "Add Action" button in "Actions" tab
        eventIdTextField = new TextField(); // ID text field for Events ("Events" tab)
        actionIdTextField = new TextField(); // ID text field for Actions ("Actions" tab)
        eventComboBox = new ComboBox(); // Drop-down menu of Game Events
        actionComboBox = new ComboBox(); // Drop-down menu of Game Actions

        window.setCenter(editorGrid);
        window.setRight(accordion);
        addEventButton.setOnAction(controller::onAddEventButtonClick);
        addActionButton.setOnAction(controller::onAddActionButtonClick);
        initEventComboBox();
        initActionComboBox();
        initEventsGrid();
        initActionsGrid();

        eventsPane.setText("Events");
        eventsPane.setContent(eventsGrid);

        actionsPane.setText("Actions");
        actionsPane.setContent(actionsGrid);

        accordion.getPanes().addAll(eventsPane, actionsPane);

        this.setContent(window);
    }

    public Tab getTab(){
        return this;
    }

    public BorderPane getWindow() {
        return this.window;
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

    public Accordion getAccordion() {
        return this.accordion;
    }

    public void initEventsGrid() {
        this.eventsGrid.setVgap(4);
        this.eventsGrid.setHgap(10);
        this.eventsGrid.setPadding(new Insets(5, 5, 5, 5));

        this.eventsGrid.add(eventComboBox, 0, 0);
        this.eventsGrid.add(new Label("ID: "), 1, 0);
        this.eventsGrid.add(eventIdTextField, 2, 0);
        this.eventsGrid.add(addEventButton, 3, 0);
    }

    public void initActionsGrid() {
        this.actionsGrid.setVgap(4);
        this.actionsGrid.setHgap(10);
        this.actionsGrid.setPadding(new Insets(5, 5, 5, 5));

        this.actionsGrid.add(actionComboBox, 0, 0);
        this.actionsGrid.add(new Label("ID: "), 1, 0);
        this.actionsGrid.add(actionIdTextField, 2, 0);
        this.actionsGrid.add(addActionButton, 3, 0);
    }

    public void initEventComboBox() {
        this.eventComboBox.getItems().addAll(controller.getEventList());
        this.eventComboBox.setPromptText("Select a Game Event");
        this.eventComboBox.setEditable(false);
    }

    public void initActionComboBox() {
        this.actionComboBox.getItems().addAll(controller.getActionList());
        this.actionComboBox.setPromptText("Select a Game Action");
        this.actionComboBox.setEditable(false);
    }
}
