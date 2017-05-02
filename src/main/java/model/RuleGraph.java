package model;

import java.util.HashSet;

public class RuleGraph {

    private GameRule root;
    private GameRule roundStart;
    private GameRule turnStart;
    private GameRule currentRule;

    public RuleGraph() {

    }

    public RuleGraph(GameRule root, GameRule roundStart, GameRule turnStart) {
        this.root = root;
        this.roundStart = roundStart;
        this.turnStart = turnStart;
    }

    public GameRule getRoot() {
        return root;
    }

    public void setRoot(GameRule root) {
        this.root = root;
    }

    public GameRule getRoundStart() {
        return roundStart;
    }

    public void setRoundStart(GameRule roundStart) {
        this.roundStart = roundStart;
    }

    public GameRule getTurnStart() {
        return turnStart;
    }

    public void setTurnStart(GameRule turnStart) {
        this.turnStart = turnStart;
    }

    public GameRule getCurrentRule() {
        return currentRule;
    }

    public void setCurrentRule(GameRule currentRule) {
        this.currentRule = currentRule;
    }

    public String prettyPrintout() {
        return printNodeAndAllChildren(root);
    }

    private String printNode(GameRule g) {
        String printout = "";
        printout += "-Parent: " + g.getName() + "\n";
        for (GameRule postRule : g.getPostRules()) {
            printout += "---Child: " + postRule.getName() + "\n";
        }
        return printout;
    }

    private String printNodeAndAllChildren(GameRule g) {
        HashSet<GameRule> printed = new HashSet<>();
        String printout = printNode(g);
        printed.add(g);
        for (GameRule postRule : g.getPostRules()) {
            printout += printNodeAndAllChildren(postRule, printed);
        }
        return printout;
    }

    private String printNodeAndAllChildren(GameRule g, HashSet<GameRule> printed) {
        String printout = printNode(g);
        printed.add(g);
        for (GameRule postRule : g.getPostRules()) {
            if (!printed.contains(postRule)) {
                printout += printNodeAndAllChildren(postRule, printed);
            }
        }
        return printout;
    }
}
