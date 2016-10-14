


jmk77_mcf28
Matthew Faw
Joy Kim

**Part 1**

 1. What about your API/design is intended to be flexible?
	 - New commands should be easy to add, simply by subclassing the Command class.
	 - It should be easy to change what is drawn on the screen
 2. How is your API/design encapsulating your implementation decisions?
	 -  It should be easy to make new commands without changing code.  Each command should agree to a common interface so that the specific method implementation is abstracted away from others
	 - The complexity of the AST should be hidden away from the rest of the code. Executing the next instruction from the tree should be easy, and the tree should manage itself
 3. What exceptions (error cases) might occur in your part and how will you handle them (or not, by throwing)?
	 - Parsing errors may occur in the TextParser when the user enters invalid syntax, or InvalidMethodName errors would occur when a user enters a method name that is not defined
		 - Make custom error classes, make an ErrorController to send the errors to the frontend
 4. Why do you think your API/design is good (also define what your measure of good is)?
	 - I think an important measure of a "good" API is the degree to which it will need to be changed as new features are added.  I think that by establishing custom objects which will be used to pass data between frontend and backend, we will eliminate having to change APIs as the data being passed becomes more complex.  By establishing simple, encapsulating APIs for these data objects, we think that we will minimize the amount of change we will have to make in the future sprints.


**Part 2**

 1.  Come up with at least five use cases for your part (it is absolutely fine if they are useful for both teams).
	 - Variable scoping
		 - When a user defines a variable within a block and then references that variable outside of the block, that variable should take on the proper value in the scope, not the old value from the block
	 - Custom functions
		 - When a user-defined command is created, a user should be able to reference that command later.  
	 - Executing commands on-click
		 - The frontend should be able to easily select a particular function to be run (for example, a subtree in the AST), and the code should be successfully evaluated
	 - Turtle functions
		 - Commands, as they are executed, should modify the current state of the turtle and automatically update the turtle's info on the frontend
	 - For loops
		 - When evaluating the AST, the current line number should be maintained so that stepping through/debugging the code is possible 
 2. How do you think at least one of the "advanced" Java features will help you implement your design?
	 - Observer/observable pattern will allow us to easily update the location/orientation of the turtle
 3. What feature/design problem are you most excited to work on?
	 - Making sure that the AST Nodes are designed so that adding new syntax objects/new types of methods does not change the evaluation of the tree--i.e. making sure the Command objects are well encapsulated 
 4. What feature/design problem are you most worried about working on?
	 - Properly implementing the Observer/Observable pattern

