package model;

import java.io.Serializable;
import java.util.HashSet;

public class RuleGraph implements Serializable {

    private GameRule root;
    private GameRule roundStart;
    private GameRule turnStart;
    private GameRule turnEnd;
    private GameRule currentRule;

    public RuleGraph() {

    }

    public RuleGraph(GameRule root, GameRule roundStart, GameRule turnStart, GameRule turnEnd) {
        this.root = root;
        this.roundStart = roundStart;
        this.turnStart = turnStart;
        this.turnEnd = turnEnd;
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

    public GameRule getTurnEnd() {
        return turnEnd;
    }

    public void setTurnEnd(GameRule turnEnd) {
        this.turnEnd = turnEnd;
    }

    public GameRule getCurrentRule() {
        return currentRule;
    }

    public void setCurrentRule(GameRule currentRule) {
        this.currentRule = currentRule;
    }

    public GameRule getRuleByClass(Class<? extends Object> c) {
        return getRuleByClass(c, root);
    }

    private GameRule getRuleByClass(Class<? extends Object> c, GameRule rule) {
        GameRule foundRule;
        for (GameRule postRule : rule.getPostRules()) {
            if (c.isInstance(postRule)) {
                return postRule;
            } else {
                foundRule = getRuleByClass(c, postRule);
                if (foundRule != null) {return foundRule;}
            }
        }
        return null;
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
