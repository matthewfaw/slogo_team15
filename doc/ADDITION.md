
## SLogo Addition -- mcf28 ##

Since I was on backend for SLogo, I'll be implementing the two new SLogo commands -- `STAMP` and `CLEARSTAMPS`

**Estimation**: before looking at the old code

- how long do you think it will take you to complete this new feature?

> I estimate that I'll likely need about 4 hours to fully complete my feature
> In my opinion, this is a bit of an over-estimation, as these features, ideally, should not require very many changes to the code.  I hope these features take me no more than an hour or two to implement. However, it could be possible that my additions won't go as smoothly as I expect, so I allocate a couple more hours to be safe

- how many files will you need to add or update? Why?

> I'll first have to add the two new function names to the Resource files (there's either one or two resource files I need to edit, but I can't currently remember if it is one or two).  I'll then have to create the command classes corresponding to STAMP and CLEARSTAMPS.  I'll also have to create a StampManager class to keep track of which stamps are currently in place, and provide a means for adding and removing stamps.  The big question, then, is where to place this object.  My goal with adding these commands is to change as little code as possible.  We decided to make all of the Command objects have the same constructors so that constructing the objects using reflection would be simple.  Thus, the new command objects cannot be constructed with the StampManager object directly.  I believe the Command objects are constructed with a scope object.  It seems natural for that object to have a StampManager, so I could slightly modify that object's interface to add a way to add a stamp and remove stamps.
>
>If the "stamps" are represented on the backend as Machine objects that are never actually stored in the TurtleManager, then we can easily send these objects to the frontend through the router, allowing me to not change any external APIs.
>
>I'm thinking of adding a new interface to Machines called Cloneable that allows the turtles to be easily cloned whenever stamp is called.  This would require making a small modification to the Turtle class to allow an easy copy of the turtle's current state to be made.  This is a simple usage of the Protoype pattern.  OODesign describes this pattern's intent is to "creat[e] new objects by copying [a] prototype," and is useful when [i]t is more convenient to copy an existing instance than to create a new one" (http://www.oodesign.com/prototype-pattern.html) Since these commands call for copying the currently existing object, this design pattern seems to fit perfectly.
>
>**So I will need to change:**
> 
> - 1-2 Resource files
>- The scope manager object, to construct the StampManager and give commands access to this
>- The machine class to implement the Clonable interface
>
>**I'll have to create:**
>
>- The StampManager class
>- The two new command objects
>- The Cloneable interface

**Review**: after completing the feature

- how long did it take you to complete this new feature?
- how many files did you need to add or update? Why?
- did you get it completely right on the first try?

**Analysis**: what do you feel this exercise reveals about your project's design and documentation?

- was it as good (or bad) as you remembered?
- what could be improved?
- what would it have been like if you were not familiar with the code at all?