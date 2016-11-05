
/**
 * This package contains all of the sender interfaces. These contain all of the 
 * current in-code options for the front-end to send state toward the back end.
 * Back-end Modules see this as methods that the front-end is allowed access to 
 * in order to update state.
 * <p>
 * DESIGN COMMENT:
 * While technically a back channel that, depending on who you ask, breaks the MVC
 * compound pattern. Shouldn't that information run through the the controller? 
 * Well it should. But that would lead to a very bloated controller. In this 
 * implementation, this back channel allows for a more intimate connection 
 * between front and back end related components without actually showing too 
 * much of the back end's relative information.
 * <p>
 * COMPLETENESS COMMENT:
 * Due to time constraints in development, the nice front back coordination 
 * features implemented were either buggy or uncomplete. In the current state
 * of the program, it would be wise to implement more features and building this 
 * package as a necessary step.
 * 
 * @author George Bernard
 */
package front_end.sender;