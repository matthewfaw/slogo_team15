package back_end.model.node;

/**
 * An enum that represents the possible states of evalutation for branching nodes
 * 
 * @author matthewfaw
 *
 */
public enum EvaluationState {
	UNEVALUATED,
	EVALUATED,
	EVALUATING_INPUTS,
	EVALUATING_BRANCH,
}
