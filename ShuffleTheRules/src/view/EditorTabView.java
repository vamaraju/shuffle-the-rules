package view;


import controller.EditorTabController;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import model.RuleElementRectangle;

public class EditorTabView extends Tab {

    private EditorTabController controller;
    private BorderPane window;
    private ScrollPane scrollPane;
    private Pane drawingPane;
    private Accordion accordion;
    private TitledPane eventsPane;
    private TitledPane actionsPane;
    private GridPane eventsGrid;
    private GridPane actionsGrid;
    private ComboBox eventComboBox;
    private ComboBox actionComboBox;
    private Button addEventButton;
    private Button addActionButton;
    private TextField eventNameTextField;
    private TextField actionNameTextField;
    private TextField eventPreviousRuleTextField;
    private TextField actionPreviousRuleTextField;

    private RuleElementRectangle clickedRectangle;

    private Label clickedEventTypeHeader;
    private Label clickedEventTypeValue;
    private Label clickedEventNameHeader;
    private Label clickedEventNameValue;
    private Label clickedEventPreviousEventHeader;
    private Label clickedEventPreviousEventValue;

    private Line curLine;

    public EditorTabView() {

        this.setText("Editor");
        controller = new EditorTabController(this);
        window = new BorderPane(); // Represents entire contents of editor tab
        drawingPane = new Pane(); // main portion in which rule elements will appear (and be stored)
        scrollPane = new ScrollPane(this.drawingPane); // the drawing pane of the window will sit within this
        accordion = new Accordion(); // right portion of editor window with accordion with Events and Actions tabs
        eventsPane = new TitledPane(); // "Events" tab on right-hand side in the accordion
        eventsGrid = new GridPane(); // Grid in the "Events" tab
        actionsPane = new TitledPane(); // "Actions" tab on right-hand side in the accordion
        actionsGrid = new GridPane(); // Grid in the "Actions" tab
        addEventButton = new Button("Add Event"); // "Add Event" button in "Events" tab
        addActionButton = new Button("Add Action"); // "Add Action" button in "Actions" tab
        eventNameTextField = new TextField(); // ID text field for Events ("Events" tab)
        actionNameTextField = new TextField(); // ID text field for Actions ("Actions" tab)
        eventPreviousRuleTextField = new TextField(); // Text field to specify the previous rule ("Events" tab)
        actionPreviousRuleTextField = new TextField(); // Text field to specify the previous rule ("Actions" tab)
        eventComboBox = new ComboBox(); // Drop-down menu of Game Events
        actionComboBox = new ComboBox(); // Drop-down menu of Game Actions

        clickedEventTypeHeader = new Label("Event Type:");
        clickedEventTypeValue = new Label();
        clickedEventNameHeader = new Label("Event Name:");
        clickedEventNameValue = new Label();
        clickedEventPreviousEventHeader = new Label("Previous Rule:");
        clickedEventPreviousEventValue = new Label();

        window.setCenter(scrollPane);
        window.setRight(accordion);
        addEventButton.setOnAction(controller::onAddEventButtonClick);
        addActionButton.setOnAction(controller::onAddActionButtonClick);
        drawingPane.setOnMouseDragged(controller::drawingPaneOnMouseDragged);
        controller.addOnGameStart(drawingPane);

        initEventComboBox();
        initActionComboBox();
        initEventsGrid();
        initActionsGrid();
        initEditorDrawingPane();
        initEditorScrollPane();

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

    public ScrollPane getEditorScrollPane() {
        return this.scrollPane;
    }

    public Pane getEditorDrawingPane() {
        return this.drawingPane;
    }

    public TextField getEventNameTextField() {
        return this.eventNameTextField;
    }

    public TextField getActionNameTextField() {
        return this.actionNameTextField;
    }

    public Accordion getAccordion() {
        return this.accordion;
    }

    public ComboBox getEventComboBox() {
        return this.eventComboBox;
    }

    public ComboBox getActionComboBox() {
        return this.actionComboBox;
    }

    public GridPane getEventsGrid() {
        return this.eventsGrid;
    }

    public TextField getEventPreviousRuleTextField() {
        return eventPreviousRuleTextField;
    }

    public TextField getActionPreviousRuleTextField() {
        return actionPreviousRuleTextField;
    }

    public Label getClickedEventTypeHeader() {
        return clickedEventTypeHeader;
    }

    public Label getClickedEventTypeValue() {
        return clickedEventTypeValue;
    }

    public Label getClickedEventNameHeader() {
        return clickedEventNameHeader;
    }

    public Label getClickedEventNameValue() {
        return clickedEventNameValue;
    }

    public Label getClickedEventPreviousEventHeader() {
        return clickedEventPreviousEventHeader;
    }

    public Label getClickedEventPreviousEventValue() {
        return clickedEventPreviousEventValue;
    }

    public RuleElementRectangle getClickedRectangle() {
        return clickedRectangle;
    }

    public void setClickedRectangle(RuleElementRectangle clickedRectangle) {
        this.clickedRectangle = clickedRectangle;
    }

    public void initEventsGrid() {
        this.eventsGrid.setVgap(4);
        this.eventsGrid.setHgap(10);
        this.eventsGrid.setPadding(new Insets(5, 5, 5, 5));

        eventNameTextField.setPromptText("Enter a Name");
        eventPreviousRuleTextField.setPromptText("Enter the Previous Rule");
        eventPreviousRuleTextField.setText("Game Start");
        this.eventsGrid.addRow(0, new Label("Event Type: "), eventComboBox);
        this.eventsGrid.addRow(1, new Label("Event Name: "), eventNameTextField);
        this.eventsGrid.addRow(2, new Label("Previous Rule: "), eventPreviousRuleTextField);
        this.eventsGrid.add(addEventButton, 0, 3);
        this.eventsGrid.add(new Separator(), 0, 4);
        this.eventsGrid.add(new Label("Clicked Event:"), 0, 5);

        clickedEventTypeHeader.setVisible(false);
        clickedEventNameHeader.setVisible(false);
        clickedEventPreviousEventHeader.setVisible(false);
        clickedEventTypeValue.setVisible(false);
        clickedEventNameValue.setVisible(false);
        clickedEventPreviousEventValue.setVisible(false);

        this.eventsGrid.addRow(6, clickedEventTypeHeader, clickedEventTypeValue);
        this.eventsGrid.addRow(7, clickedEventNameHeader, clickedEventNameValue);
        this.eventsGrid.addRow(8, clickedEventPreviousEventHeader, clickedEventPreviousEventValue);
    }

    public void initActionsGrid() {
        this.actionsGrid.setVgap(4);
        this.actionsGrid.setHgap(10);
        this.actionsGrid.setPadding(new Insets(5, 5, 5, 5));

        actionNameTextField.setPromptText("Enter a Rule Name");
        this.actionsGrid.add(actionComboBox, 0, 0);
        this.actionsGrid.add(new Label("Name: "), 1, 0);
        this.actionsGrid.add(actionNameTextField, 2, 0);
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

    public void initEditorDrawingPane() {
        this.drawingPane.setPrefSize(800, 800);
        this.drawingPane.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
    }

    public void initEditorScrollPane() {
        this.scrollPane.setPrefSize(300, 300);
        this.scrollPane.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        this.scrollPane.setFitToWidth(true);
        this.scrollPane.setFitToHeight(true);
        this.scrollPane.setStyle("-fx-focus-color: transparent;");
    }
}
