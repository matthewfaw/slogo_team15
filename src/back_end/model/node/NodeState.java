package back_end.model.node;

public enum NodeState {
	VISITED,
	EVALUATING_INPUTS,
	EVALUATING_BRANCH,
	UNREACHABLE,
	EVALUATED,
	AVAILABLE
}
