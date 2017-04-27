/*
* Requirements mandating inclusion:
* */
package model;

import view.EditorTabView;
import view.TableTab.TableTabView;

/**
 * Singleton that stores the current views for global access.
 */
public class GameView {

    private static GameView instance = new GameView();

    private EditorTabView editorTab;
    private TableTabView tableTab;

    /**
     * Private constructor to block anyone from creating a new instance of this class.
     */
    private GameView() {
    }


    /**
     * Accessor that returns the global (static) instance of GameCreation.
     *
     * @return Current version of GameCreation.
     */
    public static GameView getInstance() {
        return instance;
    }


    public EditorTabView getEditorTab() {
        return editorTab;
    }

    public void setEditorTab(EditorTabView editorTab) {
        this.editorTab = editorTab;
    }

    public TableTabView getTableTab() {
        return tableTab;
    }

    public void setTableTab(TableTabView tableTab) {
        this.tableTab = tableTab;
    }
}