package model;

public class RuleInterpreter {

    public static void execute(RuleGraph ruleGraph) {
        ruleGraph.getRoot().run();
    }

}
