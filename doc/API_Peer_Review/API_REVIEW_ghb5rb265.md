API Design Review [rb265, ghb5]
===============================

# Part 1

## What about your API/design is intended to be flexible?

The Path and Instruction objects that are passed through the mediator are intended to be the most open to extension, while the exact methods getting and giving those are crystallized in the API. Changes in the number of turtles or on 

## How is your API/design encapsulating your implementation decisions?

The encapsulated components are very clearly in the model and view. But in this case, rather than using MVC, I suggested using MVA or Model View Adapter. Since the interaction between front and back end is so critical but also can be delegated to a specific interaction environment, the traditional controller is replaced by a mediator between Model and View. The Model and View only need to pass relevant instruction and path objects on a (code) line by line basis

## What exceptions (error cases) might occur in your part and how will you handle them (or not, by throwing)?

Error cases would happen in misformatted instructions thrown by the model, and thrown to the mediator to be displayed by the view. Any exceptions could be included as a ExceptionPacket within the PathPacket.

## Why do you think your API/design is good (also define what your measure of good is)?

I think this API is good at maintaining single responsibility of Model-View-Mediator however it leaves to be desired exactly how the controllers in MVC interact from within the GUI. 


# Part 2

## Come up with at least five use cases for your part (it is absolutely fine if they are useful for both teams).

1. User types invalid command
2. User types valid command but error (known only at runtime) in the back end
3. User wants to change pen color / background / turtle image using code command
4. User wants to see current user defined commands in the environment
5. User wants to see current instantiated variables in the environment

## How do you think at least one of the "advanced" Java features will help you implement your design?

For use case 4 and 5, using the observer pattern to update current user defined commands and variables.  

## What feature/design problem are you most excited to work on?

I'm most excited to work on coordinating both interaction with the back end and also coordinating interaction with the front end components (i.e. turtleBox, codeEditor)

## What feature/design problem are you most worried about working on?