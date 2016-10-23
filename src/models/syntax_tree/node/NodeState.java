package models.syntax_tree.node;

public enum NodeState {
	VISITED,
	EVALUATING_CONDITION,
	EVALUATING_BRANCH,
	UNREACHABLE,
	EVALUATED,
	AVAILABLE
}
