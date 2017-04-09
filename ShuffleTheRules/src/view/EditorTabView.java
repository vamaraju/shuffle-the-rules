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

import javax.xml.soap.Text;

public class EditorTabView extends Tab {

    private EditorTabController controller;
    private BorderPane window;
    private ScrollPane scrollPane;
    private Pane drawingPane;
    private Accordion accordion;

    private TitledPane eventsPane;
    private GridPane eventsGrid;
    private ComboBox eventComboBox;
    private TextField eventNameTextField;
    private TextField eventDescriptionTextField;
    private TextField eventPreviousRuleTextField;
    private Button addEventButton;

    private TitledPane actionsPane;
    private GridPane actionsGrid;
    private ComboBox actionComboBox;
    private TextField actionNameTextField;
    private TextField actionDescriptionTextField;
    private TextField actionPreviousRuleTextField;
    private Button addActionButton;

    private RuleElementRectangle clickedRectangle;

    private Label clickedEventTypeHeader;
    private Label clickedEventTypeValue;
    private Label clickedEventNameHeader;
    private Label clickedEventNameValue;
    private Label clickedEventPreviousEventHeader;
    private Label clickedEventPreviousEventValue;

    private Label clickedActionTypeHeader;
    private Label clickedActionTypeValue;
    private Label clickedActionNameHeader;
    private Label clickedActionNameValue;
    private Label clickedActionPreviousActionHeader;
    private Label clickedActionPreviousActionValue;

    public EditorTabView() {

        this.setText("Editor");
        controller = new EditorTabController(this);
        window = new BorderPane(); // Represents entire contents of editor tab
        drawingPane = new Pane(); // main portion in which rule elements will appear (and be stored)
        scrollPane = new ScrollPane(this.drawingPane); // the drawing pane of the window will sit within this
        accordion = new Accordion(); // right portion of editor window with accordion with Events and Actions tabs

        eventsPane = new TitledPane(); // "Events" tab on right-hand side in the accordion
        eventsGrid = new GridPane(); // Grid in the "Events" tab
        eventComboBox = new ComboBox(); // Drop-down menu of Game Events
        eventNameTextField = new TextField(); // ID text field for Events ("Events" tab)
        eventDescriptionTextField = new TextField(); // Description text field for Events ("Events" tab)
        eventPreviousRuleTextField = new TextField(); // Text field to specify the previous rule ("Events" tab)
        addEventButton = new Button("Add Event"); // "Add Event" button in "Events" tab

        actionsPane = new TitledPane(); // "Actions" tab on right-hand side in the accordion
        actionsGrid = new GridPane(); // Grid in the "Actions" tab
        actionComboBox = new ComboBox(); // Drop-down menu of Game Actions
        actionNameTextField = new TextField(); // ID text field for Actions ("Actions" tab)
        actionDescriptionTextField = new TextField(); // Description text field for Actions ("Action" tab)
        actionPreviousRuleTextField = new TextField(); // Text field to specify the previous rule ("Actions" tab)
        addActionButton = new Button("Add Action"); // "Add Action" button in "Actions" tab

        clickedEventTypeHeader = new Label("Event Type:");
        clickedEventTypeValue = new Label();
        clickedEventNameHeader = new Label("Event Name:");
        clickedEventNameValue = new Label();
        clickedEventPreviousEventHeader = new Label("Previous Rule(s):");
        clickedEventPreviousEventValue = new Label();

        clickedActionTypeHeader = new Label("Action Type:");
        clickedActionTypeValue = new Label();
        clickedActionNameHeader = new Label("Action Name:");
        clickedActionNameValue = new Label();
        clickedActionPreviousActionHeader = new Label("Previous Rule(s):");
        clickedActionPreviousActionValue = new Label();

        window.setCenter(scrollPane);
        window.setRight(accordion);
        addEventButton.setOnAction(controller::onAddEventButtonClick);
        addActionButton.setOnAction(controller::onAddActionButtonClick);
        drawingPane.setOnMouseDragged(controller::drawingPaneOnMouseDragged);
        controller.addOnGameStart(drawingPane);

        initEventComboBox();
        initActionComboBox();
        initGrid("Event");
        initGrid("Action");
        initEditorDrawingPane();
        initEditorScrollPane();

        eventsPane.setText("Events");
        eventsPane.setContent(eventsGrid);

        actionsPane.setText("Actions");
        actionsPane.setContent(actionsGrid);

        accordion.getPanes().addAll(eventsPane, actionsPane);

        this.setContent(window);
    }


    private void initGrid(String ruleType) {
        GridPane grid = null;
        TextField nameTextField = null;
        TextField descriptionTextField = null;
        TextField previousRuleTextField = null;
        ComboBox comboBox = null;
        Button addButton = null;
        Label clickedRuleTypeHeader = null;
        Label clickedRuleNameHeader = null;
        Label clickedPreviousRuleHeader = null;
        Label clickedRuleTypeValue = null;
        Label clickedRuleNameValue = null;
        Label clickedPreviousRuleValue = null;

        if (ruleType.equals("Event")) {
            grid = this.eventsGrid;
            nameTextField = this.eventNameTextField;
            descriptionTextField = this.eventDescriptionTextField;
            previousRuleTextField = this.eventPreviousRuleTextField;
            comboBox = this.eventComboBox;
            addButton = this.addEventButton;
            clickedRuleTypeHeader = this.clickedEventTypeHeader;
            clickedRuleNameHeader = this.clickedEventNameHeader;
            clickedPreviousRuleHeader = this.clickedEventPreviousEventHeader;
            clickedRuleTypeValue = this.clickedEventTypeValue;
            clickedRuleNameValue = this.clickedEventNameValue;
            clickedPreviousRuleValue = this.clickedEventPreviousEventValue;
        } else if (ruleType.equals("Action")) {
            grid = this.actionsGrid;
            nameTextField = this.actionNameTextField;
            descriptionTextField = this.actionDescriptionTextField;
            previousRuleTextField = this.actionPreviousRuleTextField;
            comboBox = this.actionComboBox;
            addButton = this.addActionButton;
            clickedRuleTypeHeader = this.clickedActionTypeHeader;
            clickedRuleNameHeader = this.clickedActionNameHeader;
            clickedPreviousRuleHeader = this.clickedActionPreviousActionHeader;
            clickedRuleTypeValue = this.clickedActionTypeValue;
            clickedRuleNameValue = this.clickedActionNameValue;
            clickedPreviousRuleValue = this.clickedActionPreviousActionValue;
        }

        grid.setVgap(4);
        grid.setHgap(10);
        grid.setPadding(new Insets(5, 5, 5, 5));

        nameTextField.setPromptText("Enter a Name");
        descriptionTextField.setPromptText("Enter a Description");
        previousRuleTextField.setPromptText("Enter the Previous Rule");
        previousRuleTextField.setText("Game Start");
        grid.addRow(0, new Label(ruleType + " Type: "), comboBox);
        grid.addRow(1, new Label(ruleType + " Name: "), nameTextField);
        grid.addRow(2, new Label(ruleType + " Description: "), descriptionTextField);
        grid.addRow(3, new Label("Previous Rule: "), previousRuleTextField);
        grid.add(addButton, 0, 4);
        grid.add(new Separator(), 0, 5);
        grid.add(new Label("Clicked " + ruleType + ":"), 0, 6);
        grid.addRow(7, clickedRuleTypeHeader, clickedRuleTypeValue);
        grid.addRow(8, clickedRuleNameHeader, clickedRuleNameValue);
        grid.addRow(9, clickedPreviousRuleHeader, clickedPreviousRuleValue);

        clickedRuleTypeHeader.setVisible(false);
        clickedRuleNameHeader.setVisible(false);
        clickedPreviousRuleHeader.setVisible(false);
        clickedRuleTypeValue.setVisible(false);
        clickedRuleNameValue.setVisible(false);
        clickedPreviousRuleValue.setVisible(false);
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

    public TextField getEventDescriptionTextField() {
        return eventDescriptionTextField;
    }

    public TextField getActionDescriptionTextField() {
        return actionDescriptionTextField;
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

    public Label getClickedActionTypeHeader() {
        return clickedActionTypeHeader;
    }

    public Label getClickedActionTypeValue() {
        return clickedActionTypeValue;
    }

    public Label getClickedActionNameHeader() {
        return clickedActionNameHeader;
    }

    public Label getClickedActionNameValue() {
        return clickedActionNameValue;
    }

    public Label getClickedActionPreviousActionHeader() {
        return clickedActionPreviousActionHeader;
    }

    public Label getClickedActionPreviousActionValue() {
        return clickedActionPreviousActionValue;
    }

    public RuleElementRectangle getClickedRectangle() {
        return clickedRectangle;
    }

    public void setClickedRectangle(RuleElementRectangle clickedRectangle) {
        this.clickedRectangle = clickedRectangle;
    }
}
