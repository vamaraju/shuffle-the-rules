package model;

public class RuleInterpreter {

    public static void execute(RuleGraph ruleGraph) {
        if (ruleGraph != null && ruleGraph.getRoot() != null) {
            ruleGraph.getRoot().run();
        }
    }

}
