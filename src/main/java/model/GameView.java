/*
* Requirements mandating inclusion:
*
* This class is a singleton that stores the global views (GUI objects) for the application, and is necessary
* for all requirements.
* */
package model;

import view.EditorTab.EditorTabView;
import view.Gameplay.GameplayView;
import view.TableTab.TableTabView;

/**
 * Singleton that stores the current views for global access.
 */
public class GameView {

    private static GameView instance = new GameView();

    private EditorTabView editorTab;
    private TableTabView tableTab;
    private GameplayView gameplayView;

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

    public GameplayView getGameplayView() {
        return gameplayView;
    }

    public void setGameplayView(GameplayView gameplayView) {
        this.gameplayView = gameplayView;
    }
}
