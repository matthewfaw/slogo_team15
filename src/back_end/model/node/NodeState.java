package back_end.model.node;

public enum NodeState {
                       VISITED,
                       EVALUATING_CONDITION,
                       EVALUATING_BRANCH,
                       UNREACHABLE,
                       EVALUATED,
                       AVAILABLE
}
