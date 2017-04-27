/*
* Requirements mandating inclusion:
* */
package view;


import controller.EditorTabController;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import model.GameRule;
import model.GameRuleType;
import model.TripleHashMap;

public class EditorTabView extends Tab {

    private EditorTabController controller;
    private BorderPane window;
    private ScrollPane scrollPane;
    private Pane drawingPane;
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
        drawingPane = new Pane(); // main portion in which rule elements will appear (and be stored)
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

        setTextFieldPrompts(eventsGridElements);
        setTextFieldPrompts(actionsGridElements);

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

        addEventButton.setOnAction(controller::onAddEventButtonClick);
        addActionButton.setOnAction(controller::onAddActionButtonClick);
        drawingPane.setOnMouseDragged(controller::drawingPaneOnMouseDragged);
        controller.addOnGameStart(drawingPane);

        initEventComboBox();
        initActionComboBox();
        initEditorDrawingPane();
        initEditorScrollPane();

        eventsPane.setText("Events");
        eventsPane.setContent(eventsGrid);

        actionsPane.setText("Actions");
        actionsPane.setContent(actionsGrid);

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
    }


    private void boldAllHeaders(TripleHashMap<String, Node, Node> gridElements) {
        for (String key : gridElements.keySet()) {
            if (key.contains("Header")) {
                gridElements.getValue1(key).setStyle("-fx-font-weight: bold");
            }
        }
    }

    private void setTextFieldPrompts(TripleHashMap<String, Node, Node> gridElements) {
        ((TextField) gridElements.getValue2("name")).setPromptText("Enter a Name");
        ((TextField) gridElements.getValue2("description")).setPromptText("Enter a Description");
        ((TextField) gridElements.getValue2("previous")).setPromptText("Enter the Previous Rule");
        ((TextField) gridElements.getValue2("numberOfCards")).setPromptText("Enter the Number of Cards");
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


    public void initEventComboBox() {
        getEventComboBox().getItems().addAll(controller.getEventList());
        getEventComboBox().setPromptText("Select a Game Event");
        getEventComboBox().setEditable(false);
    }

    public void initActionComboBox() {
        getActionComboBox().getItems().addAll(controller.getActionList());
        getActionComboBox().setPromptText("Select a Game Action");
        getActionComboBox().setEditable(false);
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

    public EditorTabController getController() {
        return controller;
    }

    public ScrollPane getEditorScrollPane() {
        return this.scrollPane;
    }

    public Pane getEditorDrawingPane() {
        return this.drawingPane;
    }

    public void setEditorDrawingPane(Pane drawingPane) {
        this.drawingPane = drawingPane;
        this.scrollPane.setContent(this.drawingPane);
    }

    public void clearEditorDrawingPane() {
        this.drawingPane.getChildren().clear();
        controller.addOnGameStart(this.drawingPane);
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

    public void disableAllEventSpecificInputs() {
        disableEventPileComboBox();
        disableEventNumCardsTextField();
        disableEventCardValueComboBox();
        disableEventCardSuitComboBox();
        disableEventPlayerComboBox();
    }

    public void disableAllActionSpecificInputs() {
        disableActionPileComboBox();
        disableActionNumCardsTextField();
        disableActionCardValueComboBox();
        disableActionCardSuitComboBox();
        disableActionPlayerComboBox();
    }

    public void enableAllEventSpecificInputs() {
        enableEventPileComboBox();
        enableEventNumCardsTextField();
        enableEventCardValueComboBox();
        enableEventCardSuitComboBox();
        enableEventPlayerComboBox();
    }

    public void enableAllActionSpecificInputs() {
        enableActionPileComboBox();
        enableActionNumCardsTextField();
        enableActionCardValueComboBox();
        enableActionCardSuitComboBox();
        enableActionPlayerComboBox();
    }

    public TextField getEventNameTextField() {
        return (TextField) eventsGridElements.getValue2("name");
    }

    public TextField getActionNameTextField() {
        return (TextField) actionsGridElements.getValue2("name");
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

    public ComboBox getEventComboBox() {
        return (ComboBox) eventsGridElements.getValue2("type");
    }

    public ComboBox getActionComboBox() {
        return (ComboBox) actionsGridElements.getValue2("type");
    }

    public GridPane getEventsGrid() {
        return this.eventsGrid;
    }

    public TextField getEventPreviousRuleTextField() {
        return (TextField) eventsGridElements.getValue2("previous");
    }

    public TextField getActionPreviousRuleTextField() {
        return (TextField) actionsGridElements.getValue2("previous");
    }

    public TextField getEventDescriptionTextField() {
        return (TextField) eventsGridElements.getValue2("description");
    }

    public TextField getActionDescriptionTextField() {
        return (TextField) actionsGridElements.getValue2("description");
    }

    public ComboBox getEventPileComboBox() {
        return (ComboBox) eventsGridElements.getValue2("pile");
    }

    public TextField getEventNumCardsTextField() {
        return (TextField) eventsGridElements.getValue2("numberOfCards");
    }

    public ComboBox getEventCardValueComboBox() {
        return (ComboBox) eventsGridElements.getValue2("cardValue");
    }

    public ComboBox getEventCardSuitComboBox() {
        return (ComboBox) eventsGridElements.getValue2("cardSuit");
    }

    public ComboBox getEventPlayerComboBox() {
        return (ComboBox) eventsGridElements.getValue2("player");
    }

    public ComboBox getActionPileComboBox() {
        return (ComboBox) actionsGridElements.getValue2("pile");
    }

    public TextField getActionNumCardsTextField() {
        return (TextField) actionsGridElements.getValue2("numberOfCards");
    }

    public ComboBox getActionCardValueComboBox() {
        return (ComboBox) actionsGridElements.getValue2("cardValue");
    }

    public ComboBox getActionCardSuitComboBox() {
        return (ComboBox) actionsGridElements.getValue2("cardSuit");
    }

    public ComboBox getActionPlayerComboBox() {
        return (ComboBox) actionsGridElements.getValue2("player");
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

    public RuleElementRectangle getClickedRectangle() {
        return clickedRectangle;
    }

    public void setClickedRectangle(RuleElementRectangle clickedRectangle) {
        this.clickedRectangle = clickedRectangle;
    }
}
