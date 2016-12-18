
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
> It took me 2 hours to finish implementing the feature, and an extra hour to refine the copying method to use reflection.  I wanted to add this capability, since if new fields are added to the Turtle object, it is not ideal for one to have to modify the copy method. Thus, using reflection, copying is quite maintainable

- how many files did you need to add or update? Why?
>  - I had to modify one line of the ModelController to give the Environment object access to the Router. I find the way we distributed objects to the frontend through the router pretty messy.  It seems that we typically tried to make the ModelController the only class that used the Router.  This controller was an observer, listening for the robot controller to signal a new Turtle has been created, and then it had to distribute the new turtle. It would have been much simpler if we had just given the objects that create turtles the router, so that the router could be used immediately when the object was created--removing the unnecessary and convoluted layer of indirection.
>  - I had to modify the CommandTypes property file to specify that Stamp and ClearStamps were InputCommands so that the factory would know what type of command to construct.  I had to modify English.properties so that the command was recognized in English (I could have modified all the language files if I wanted support in multiple languages).  I had to modify the NumberOfInputs properties file to specify the number of expected inputs for these commands.  After that, I had to add the ClearStampsCommand and the StampCommand so that, when the user types these commands, something would happen.
>  - I created a cloneable interface to allow the turtles to be cloned so that they could be stamped. I added this interface to the IRobot interface. Surprisingly, the Turtle didn't actually implement the IRobot interface, so I changed the Turtle so that it shared that interface.  I then implemented the clone method in the Turtle and in the RobotController.
>  - I made the RobotStampManager class to maintain which stamps have been placed, and to remove them when requested.  Since the stamps are part of the user's environment, I added this manager to the Environment class.  This was a convenient place to put it, since all Commands have access to the Environment class.  Thus, I was able to add the new commands without having to change anything about the CommandFactory, which is a sign that our design was good.
>  We did not set up any infrastructure for removing Turtles during our project, and thus there is no way with our current API to remove turtles from the frontend.  This could be easily added by adding a detach() method to the IObserver interface, so that the observers could know to destroy themselves whenever the Observable disappears. However, this would require changing some frontend code, so I decided not to fully implement this. It is implemented on the backend, however.
>  - I also modified the Router slightly, by separating some of its methods into more interfaces (IRobotRouter and IErrorRouter). This allowed me to pass the router to the RobotStampManager without revealing all of the interface the IRouter provides.
>  I added a new ReflectionException error message to be thrown if the Turtle's copy method fails
>  - In total, I changed 22 files, with 351 additions and 27 deletions.

- did you get it completely right on the first try?
> - My original approach worked without fail (besides a small bug when distributing the turtle to the frontend, but I just needed to call notifyObservers() after passing the object to the frontend to make sure the stamp was in the proper location).
> - I finished the feature more quickly than I expected, as it only took me 2 hours to implement and an additional hour to clean up object copying by using reflection.  I found this addition necessary to ensure maintainability of the copy method.

**Analysis**: what do you feel this exercise reveals about your project's design and documentation?

- was it as good (or bad) as you remembered? What could be improved?
> I think that overall, our design is one that is easy to work with and add new features to.  Adding new methods is quite easy, as I only had to change a few resource files and implement the commands themselves.  I could very easily leverage our old code to add new features.  It took very little effort for me to dive back into the code and add new features to it.
>
>I do not like how we currently distribute Turtles from the backend to the frontend.  This, I think, is one of the most confusing parts of our code, since it has a completely unnecessary layer of indirection that obfuscates the control flow, and is almost impossible to follow unless you are in debug mode. I am a huge fan of the Router class -- I think that class provides a fantastic and quite easy to use interface for notifying the frontend of new backend objects.  However, the way we used it was flawed.  I suppose we wanted to keep its use restricted to the ModelController, and as such, made the ModelController and observer of the RobotController, which would be notified whenever a new Robot was created. However, I find this observer/observable relationship quite confusing, as there is no clear guarantee that, when a Turtle is created, it will be passed to the frontend.  I think it would have been better design to give the classes that construct Robots direct access to the Router, so the Robot is distributed to the fronted whenever it is created -- this would result in a much more understandable distribution process.  This layer of indirection actually confused me when I went back to look at the code, and certainly slowed down adding the feature.  I gave the RobotStampManager direct access to the pertinent routers for exactly this reason.
>
>Another potential issue in our code is the uniformity we forced on each Command object.  This greatly helped with keeping the factory clean and simplifying reflection. However, if a new parameter needed to be added to a command at some point in the future, it would be quite difficult.  I suppose the Environment object provides a nice middleground for allowing flexibity in what Command objects have access to while keeping the uniform Command constructors.  However, as the Commands become more diverse, that Environment object could grow undesirably large. 
>In VOOGASalad, we made a factory (ComponentFactory.java) that was slightly more flexible than this CommandFactory by passing a list of objects that all implement the same interface (ISystem),  and using reflection on the type of each parameter of the constructors to see if that type matched a type in the ISystem list.  This worked quite well for us.  Thus, we could perhaps have had an IEnvironment interface, and had lots of classes that implemented this interface, and the CommandFactory could just choose which of the IEnvironment objects it wanted for a particular Command.  This would allow increased flexibility in Command constructors, decrease the lines of code in a single Environment object, while still not loosing the benefits of using Reflection in the Command Factory.

- what would it have been like if you were not familiar with the code at all?
>The code was not quite as heavily documented, so that could have made the code somewhat challenging to tackle without having any knowledge of the codebase.
>However, I think the control flow of our code was quite understandable, and the dependencies were quite clear.  There is a clean divide between frontend and backend, and adding new Commands is quite simple
>The code was written to be fairly readable, with all methods being given descriptive names.
>Towards the end of our project, some of the members of the team decided to make an integration package, which ended up being a somewhat disorganized collection of files that had some relevance to frontend and some to backend.  Perhaps this organizational structure could have been somewhat confusing to someone unfamiliar with the code. However, the rest of our project is divied into a package structure that makes sense organizationally. This structure makes the project easy to navigate, even for an outsider.