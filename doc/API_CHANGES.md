# API - Changes (Team 15)


## What changes that have been made to the APIs


### Internal
Front-End
Interface “inheritance” hierarchies to reduce redefining the same API’s
Factory class for each view component/box


### External
MVC vs. MVA
MVA is a superfluous level of indirection
MVC eliminates indirection with the Controller
Observer Observable
We wanted to implement this, but we didn’t fully understand the intricacies of this
Further, the java standard implementation didn’t have the abstraction we required




## If those changes are major or minor (justify your distinction based on how much they affected your team mate's code)


## If those changes are for better or worse (if for the worse, is there a way to improve it or was the original API overly optimistic)


Most if not all changes have been for better


## If you foresee any significant changes coming in the next few days as you finish the project (based on your experience and the fact that you now know all the features to be implemented)


### Front
Setting background color as an external API
Getting more info from IViewRobot (i.e. color, current shape)
More ViewModules


### Back
The AST operations on different Nodes needs to separated out into Node classes -that’s why we have the hierarchy in the first place! 
Potentially: separate out into creating the tree and reading the tree (Visitor)


Node classes should handle more work and all the constructors of the node classes are going to be the same.


Scope was not in original API and it’s something that is very Bad currently needs to be refactored. Standardized API for accessing Scope


NodeFactory and CommandFactory - all nodes and commads should have same constructor so we can use reflection. Awesome one line method. Because right now the factories are HUGE if -trees.


Add In: 
Add a SceneState class - to represent color


TurtleController to deal with multiple turtles - implement Robot interface so that we don’t have to modify the commands (Composite Pattern)


New Commands and get rid of bugs


Potential Collision detection for Turtles








