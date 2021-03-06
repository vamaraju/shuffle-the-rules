/*
* Requirements mandating inclusion:
*
* 3.2.1.6.3.1 User can specify Game Rules associated with particular Cards.
* 3.2.1.6.3.2 User can create Game Rules by selecting Game Events and adding a Game Action to that event.
* 3.2.1.6.3.3 User can create a Game Flow Sequence by linking Game Rules.
* 3.2.1.3.7.1 Load Event settings.
* 3.2.1.3.7.2 User can view Event actions and rules.
* 3.2.1.3.7.3 User can edit Event actions and rules.
* */

package view.EditorTab;


import controller.EditorTab.EditorTabController;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import model.GameRuleType;
import model.TripleHashMap;

public class EditorTabView extends Tab {

    private EditorTabController controller;
    private BorderPane window;
    private ScrollPane scrollPane;
    private DrawingPane drawingPane;
    private Accordion accordion;

    private TitledPane eventsPane;
    private GridPane eventsGrid;
    private TripleHashMap<String, Node, Node> eventsGridElements = new TripleHashMap<>();

    private TitledPane actionsPane;
    private GridPane actionsGrid;
    private TripleHashMap<String, Node, Node> actionsGridElements = new TripleHashMap<>();

    private Button addEventButton = new Button("Add Event");
    private Button updateEventButton = new Button("Update Event");
    private Button deleteEventButton = new Button("Delete Event");

    private Button addActionButton = new Button("Add Action");
    private Button updateActionButton = new Button("Update Action");
    private Button deleteActionButton = new Button("Delete Action");

    private RuleElementRectangle clickedRectangle;

    public EditorTabView() {

        this.setText("Editor");
        controller = new EditorTabController(this);
        window = new BorderPane(); // Represents entire contents of editor tab
        drawingPane = new DrawingPane(); // main portion in which rule elements will appear (and be stored)
        scrollPane = new ScrollPane(this.drawingPane); // the drawing pane of the window will sit within this
        accordion = new Accordion(); // right portion of editor window with accordion with Events and Actions tabs

        eventsPane = new TitledPane(); // "Events" tab on right-hand side in the accordion
        eventsGrid = new GridPane(); // Grid in the "Events" tab
        eventsGrid.setHgap(2);
        eventsGrid.setVgap(4);

        actionsPane = new TitledPane(); // "Actions" tab on right-hand side in the accordion
        actionsGrid = new GridPane(); // Grid in the "Actions" tab
        actionsGrid.setHgap(2);
        actionsGrid.setVgap(4);

        populateGridElements(eventsGridElements, GameRuleType.EVENT);
        populateGridElements(actionsGridElements, GameRuleType.ACTION);

        boldAllHeaders(eventsGridElements);
        boldAllHeaders(actionsGridElements);

        setInputPrompts(eventsGridElements);
        setInputPrompts(actionsGridElements);

        initGrid(eventsGrid, eventsGridElements, GameRuleType.EVENT);
        initGrid(actionsGrid, actionsGridElements, GameRuleType.ACTION);

        disableAllEventSpecificInputs();
        disableAllActionSpecificInputs();

        addEventButton.setDisable(false);
        updateEventButton.setDisable(true);
        deleteEventButton.setDisable(true);
        addActionButton.setDisable(false);
        updateActionButton.setDisable(true);
        deleteActionButton.setDisable(true);

        window.setCenter(scrollPane);
        window.setRight(accordion);

        this.selectedProperty().addListener(controller::onTabSelected);
        getEventTypeComboBox().valueProperty().addListener(controller::onEventTypeChanged);
        getActionTypeComboBox().valueProperty().addListener(controller::onActionTypeChanged);
        addEventButton.setOnAction(controller::onAddButtonClick);
        addActionButton.setOnAction(controller::onAddButtonClick);
        updateEventButton.setOnAction(controller::onUpdateButtonClick);
        updateActionButton.setOnAction(controller::onUpdateButtonClick);
        deleteEventButton.setOnAction(controller::onDeleteButtonClick);
        deleteActionButton.setOnAction(controller::onDeleteButtonClick);
        drawingPane.setOnMouseDragged(controller::drawingPaneOnMouseDragged);
        controller.addOnGameStart();

        controller.initEventTypeComboBox();
        controller.initActionTypeComboBox();
        controller.initCardValueComboBoxes();
        controller.initCardSuitComboBoxes();

        initEditorScrollPane();

        eventsPane.setText("Events");
        eventsPane.setContent(eventsGrid);
        eventsPane.expandedProperty().addListener(controller::onEventsExpanded);

        actionsPane.setText("Actions");
        actionsPane.setContent(actionsGrid);
        actionsPane.expandedProperty().addListener(controller::onActionsExpanded);

        accordion.getPanes().addAll(eventsPane, actionsPane);

        this.setContent(window);
    }


    private void populateGridElements(TripleHashMap<String, Node, Node> gridElements, GameRuleType ruleType) {
        gridElements.put("generalHeader", new Label("General"), null);
        gridElements.put("type", new Label(ruleType + " Type: "), new ComboBox());
        gridElements.put("name", new Label(ruleType + " Name: "), new TextField());
        gridElements.put("description", new Label(ruleType + " Description: "), new TextField());
        gridElements.put("previous", new Label("Previous Rule: "), new TextField());

        gridElements.put("specificHeader", new Label(ruleType + "-Specific"), null);
        gridElements.put("pile", new Label("Pile"), new ComboBox());
        gridElements.put("numberOfCards", new Label("Number of Cards"), new TextField());
        gridElements.put("cardValue", new Label("Card Value"), new ComboBox());
        gridElements.put("cardSuit", new Label("Card Suit"), new ComboBox());
        gridElements.put("player", new Label("Player(s)"), new ComboBox());
        gridElements.put("priority", new Label("Priority"), new TextField());
    }

    public void clearAllInputs() {
        clearPaneInputs(eventsGridElements);
        clearPaneInputs(actionsGridElements);
    }

    public void clearPaneInputs(TripleHashMap<String, Node, Node> gridElements) {
        getTypeComboBox(gridElements).setValue(null);
        getNameTextField(gridElements).clear();
        getDescriptionTextField(gridElements).clear();
        getPreviousRuleTextField(gridElements).clear();

        getPileComboBox(gridElements).setValue(null);
        getNumCardsTextField(gridElements).clear();
        getCardValueComboBox(gridElements).setValue(null);
        getCardSuitComboBox(gridElements).setValue(null);
        getPlayerComboBox(gridElements).setValue(null);
        getPriorityTextField(gridElements).clear();
    }


    private void boldAllHeaders(TripleHashMap<String, Node, Node> gridElements) {
        for (String key : gridElements.keySet()) {
            if (key.contains("Header")) {
                gridElements.getValue1(key).setStyle("-fx-font-weight: bold");
            }
        }
    }

    private void setInputPrompts(TripleHashMap<String, Node, Node> gridElements) {
        setTextFieldPrompts(gridElements);
//        setComboBoxPrompts(gridElements);
    }

    private void setTextFieldPrompts(TripleHashMap<String, Node, Node> gridElements) {
        ((TextField) gridElements.getValue2("name")).setPromptText("Enter a Name");
        ((TextField) gridElements.getValue2("description")).setPromptText("Enter a Description");
        ((TextField) gridElements.getValue2("previous")).setPromptText("Enter the Previous Rule");
        ((TextField) gridElements.getValue2("numberOfCards")).setPromptText("Enter the Number of Cards");
        ((TextField) gridElements.getValue2("priority")).setPromptText("Enter the Priority (Number)");
    }

    private void setComboBoxPrompts(TripleHashMap<String, Node, Node> gridElements) {
        ((ComboBox) gridElements.getValue2("pile")).setPromptText("Deck");
        ((ComboBox) gridElements.getValue2("cardValue")).setPromptText("*Any*");
        ((ComboBox) gridElements.getValue2("cardSuit")).setPromptText("*Any*");
        ((ComboBox) gridElements.getValue2("player")).setPromptText("*All*");
    }

    private void initGrid(GridPane grid, TripleHashMap<String, Node, Node> gridElements, GameRuleType ruleType) {
        int row = 0;
        for (String key : gridElements.keySet()) {
            if (gridElements.getValue2(key) == null) {
                if (row != 0) {
                    grid.add(new Separator(), 0, row++);
                }
                grid.add(gridElements.getValue1(key), 0, row++);
            } else {
                grid.add(gridElements.getValue1(key), 0, row);
                grid.add(gridElements.getValue2(key), 1, row++);
            }
        }

        if (ruleType == GameRuleType.EVENT) {
            grid.add(deleteEventButton, 0, row);
            grid.add(addEventButton, 1, row);
            grid.add(updateEventButton, 2, row++);
        } else if (ruleType == GameRuleType.ACTION) {
            grid.add(deleteActionButton, 0, row);
            grid.add(addActionButton, 1, row);
            grid.add(updateActionButton, 2, row++);
        }
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

    public EditorTabController getController() {
        return controller;
    }

    public ScrollPane getEditorScrollPane() {
        return this.scrollPane;
    }

    public DrawingPane getEditorDrawingPane() {
        return this.drawingPane;
    }

    public void setEditorDrawingPane(DrawingPane drawingPane) {
        this.drawingPane = drawingPane;
        this.scrollPane.setContent(this.drawingPane);
    }

    public void clearEditorDrawingPane() {
        this.drawingPane.clear();
        controller.addOnGameStart();
    }

    public void addToEditorDrawingPane(Node element) {
        this.drawingPane.add(element);
    }

    public void addAllToEditorDrawingPane(Node... elements) {
        this.drawingPane.addAll(elements);
    }

    public void removeFromEditorDrawingPane(Node element) {
        this.drawingPane.remove(element);
    }

    public void disableEventPileComboBox() {
        getEventPileComboBox().setDisable(true);
    }

    public void disableEventNumCardsTextField() {
        getEventNumCardsTextField().setDisable(true);
    }

    public void disableEventCardValueComboBox() {
        getEventCardValueComboBox().setDisable(true);
    }

    public void disableEventCardSuitComboBox() {
        getEventCardSuitComboBox().setDisable(true);
    }

    public void disableEventPlayerComboBox() {
        getEventPlayerComboBox().setDisable(true);
    }

    public void disableEventPriorityTextField() {
        getEventPriorityTextField().setDisable(true);
    }

    public void disableActionPileComboBox() {
        getActionPileComboBox().setDisable(true);
    }

    public void disableActionNumCardsTextField() {
        getActionNumCardsTextField().setDisable(true);
    }

    public void disableActionCardValueComboBox() {
        getActionCardValueComboBox().setDisable(true);
    }

    public void disableActionCardSuitComboBox() {
        getActionCardSuitComboBox().setDisable(true);
    }

    public void disableActionPlayerComboBox() {
        getActionPlayerComboBox().setDisable(true);
    }

    public void disableActionPriorityTextField() {
        getActionPriorityTextField().setDisable(true);
    }

    public void enableEventPileComboBox() {
        getEventPileComboBox().setDisable(false);
    }

    public void enableEventNumCardsTextField() {
        getEventNumCardsTextField().setDisable(false);
    }

    public void enableEventCardValueComboBox() {
        getEventCardValueComboBox().setDisable(false);
    }

    public void enableEventCardSuitComboBox() {
        getEventCardSuitComboBox().setDisable(false);
    }

    public void enableEventPlayerComboBox() {
        getEventPlayerComboBox().setDisable(false);
    }

    public void enableEventPriorityTextField() {
        getEventPriorityTextField().setDisable(false);
    }

    public void enableActionPileComboBox() {
        getActionPileComboBox().setDisable(false);
    }

    public void enableActionNumCardsTextField() {
        getActionNumCardsTextField().setDisable(false);
    }

    public void enableActionCardValueComboBox() {
        getActionCardValueComboBox().setDisable(false);
    }

    public void enableActionCardSuitComboBox() {
        getActionCardSuitComboBox().setDisable(false);
    }

    public void enableActionPlayerComboBox() {
        getActionPlayerComboBox().setDisable(false);
    }

    public void enableActionPriorityTextField() {
        getActionPriorityTextField().setDisable(false);
    }

    public void disableAllEventSpecificInputs() {
        disableEventPileComboBox();
        disableEventNumCardsTextField();
        disableEventCardValueComboBox();
        disableEventCardSuitComboBox();
        disableEventPlayerComboBox();
        disableEventPriorityTextField();
    }

    public void disableAllActionSpecificInputs() {
        disableActionPileComboBox();
        disableActionNumCardsTextField();
        disableActionCardValueComboBox();
        disableActionCardSuitComboBox();
        disableActionPlayerComboBox();
        disableActionPriorityTextField();
    }

    public void enableAllEventSpecificInputs() {
        enableEventPileComboBox();
        enableEventNumCardsTextField();
        enableEventCardValueComboBox();
        enableEventCardSuitComboBox();
        enableEventPlayerComboBox();
        enableEventPriorityTextField();
    }

    public void enableAllActionSpecificInputs() {
        enableActionPileComboBox();
        enableActionNumCardsTextField();
        enableActionCardValueComboBox();
        enableActionCardSuitComboBox();
        enableActionPlayerComboBox();
        enableActionPriorityTextField();
    }

    public TripleHashMap<String, Node, Node> getEventsGridElements() {
        return eventsGridElements;
    }

    public TripleHashMap<String, Node, Node> getActionsGridElements() {
        return actionsGridElements;
    }

    public Accordion getAccordion() {
        return this.accordion;
    }

    public TitledPane getEventsPane() {
        return eventsPane;
    }

    public TitledPane getActionsPane() {
        return actionsPane;
    }

    public GridPane getEventsGrid() {
        return this.eventsGrid;
    }

    public TextField getNameTextField(TripleHashMap<String, Node, Node> gridElements) {
        return (TextField) gridElements.getValue2("name");
    }

    public TextField getEventNameTextField() {
        return getNameTextField(eventsGridElements);
    }

    public TextField getActionNameTextField() {
        return getNameTextField(actionsGridElements);
    }

    public ComboBox getTypeComboBox(TripleHashMap<String, Node, Node> gridElements) {
        return (ComboBox) gridElements.getValue2("type");
    }

    public ComboBox getEventTypeComboBox() {
        return getTypeComboBox(eventsGridElements);
    }

    public ComboBox getActionTypeComboBox() {
        return getTypeComboBox(actionsGridElements);
    }

    public TextField getPreviousRuleTextField(TripleHashMap<String, Node, Node> gridElements) {
        return (TextField) gridElements.getValue2("previous");
    }

    public TextField getEventPreviousRuleTextField() {
        return getPreviousRuleTextField(eventsGridElements);
    }

    public TextField getActionPreviousRuleTextField() {
        return getPreviousRuleTextField(actionsGridElements);
    }

    public TextField getDescriptionTextField(TripleHashMap<String, Node, Node> gridElements) {
        return (TextField) gridElements.getValue2("description");
    }

    public TextField getEventDescriptionTextField() {
        return getDescriptionTextField(eventsGridElements);
    }

    public TextField getActionDescriptionTextField() {
        return getDescriptionTextField(actionsGridElements);
    }

    public ComboBox getPileComboBox(TripleHashMap<String, Node, Node> gridElements) {
        return (ComboBox) gridElements.getValue2("pile");
    }

    public TextField getNumCardsTextField(TripleHashMap<String, Node, Node> gridElements) {
        return (TextField) gridElements.getValue2("numberOfCards");
    }

    public ComboBox getCardValueComboBox(TripleHashMap<String, Node, Node> gridElements) {
        return (ComboBox) gridElements.getValue2("cardValue");
    }

    public ComboBox getCardSuitComboBox(TripleHashMap<String, Node, Node> gridElements) {
        return (ComboBox) gridElements.getValue2("cardSuit");
    }

    public ComboBox getPlayerComboBox(TripleHashMap<String, Node, Node> gridElements) {
        return (ComboBox) gridElements.getValue2("player");
    }

    public TextField getPriorityTextField(TripleHashMap<String, Node, Node> gridElements) {
        return (TextField) gridElements.getValue2("priority");
    }

    public ComboBox getEventPileComboBox() {
        return getPileComboBox(eventsGridElements);
    }

    public TextField getEventNumCardsTextField() {
        return getNumCardsTextField(eventsGridElements);
    }

    public ComboBox getEventCardValueComboBox() {
        return getCardValueComboBox(eventsGridElements);
    }

    public ComboBox getEventCardSuitComboBox() {
        return getCardSuitComboBox(eventsGridElements);
    }

    public ComboBox getEventPlayerComboBox() {
        return getPlayerComboBox(eventsGridElements);
    }

    public TextField getEventPriorityTextField() {
        return getPriorityTextField(eventsGridElements);
    }

    public ComboBox getActionPileComboBox() {
        return getPileComboBox(actionsGridElements);
    }

    public TextField getActionNumCardsTextField() {
        return getNumCardsTextField(actionsGridElements);
    }

    public ComboBox getActionCardValueComboBox() {
        return getCardValueComboBox(actionsGridElements);
    }

    public ComboBox getActionCardSuitComboBox() {
        return getCardSuitComboBox(actionsGridElements);
    }

    public ComboBox getActionPlayerComboBox() {
        return getPlayerComboBox(actionsGridElements);
    }

    public TextField getActionPriorityTextField() {
        return getPriorityTextField(actionsGridElements);
    }

    public Button getAddEventButton() {
        return addEventButton;
    }

    public Button getUpdateEventButton() {
        return updateEventButton;
    }

    public Button getDeleteEventButton() {
        return deleteEventButton;
    }

    public Button getAddActionButton() {
        return addActionButton;
    }

    public Button getUpdateActionButton() {
        return updateActionButton;
    }

    public Button getDeleteActionButton() {
        return deleteActionButton;
    }

    public void enableAddButtons() {
        getAddEventButton().setDisable(false);
        getUpdateEventButton().setDisable(true);
        getDeleteEventButton().setDisable(true);
        getAddActionButton().setDisable(false);
        getUpdateActionButton().setDisable(true);
        getDeleteActionButton().setDisable(true);
    }

    public RuleElementRectangle getClickedRectangle() {
        return clickedRectangle;
    }

    public void setClickedRectangle(RuleElementRectangle clickedRectangle) {
        this.clickedRectangle = clickedRectangle;
    }
}
