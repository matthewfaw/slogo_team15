/**
 * Provides interfaces through which the front end can 
 * recieve state from the back end.
 * <p>
 * The IAcceptor interface is how the backend "sees" front end
 * modules that are given state. Most, if not all, state is passed
 * to the front end as an observable object. But to minimize the knowledge
 * the back end has about the front end, the router (a back end object) can
 * send relevant states to the front end, knowing only that they are acceptors
 * of this object. 
 * 
 * <p>
 * Sender Acceptors:
 * SenderAcceptors are classes that pass forward interfaces of classes that need 
 * to have state sent back to them. These are established and added to the router
 * upon instantiation of it.
 * 
 * 
 * <p>
 * DESIGN CHOICE 1: Acceptors should be large scale "modules" within the front end.
 * Any object that needs a state object in order to exist should not implement
 * any of these interfaces. This would create a method of call dependency. Acceptors
 * should create lower level modules using the state object in a constructor.
 * 
 * <p>
 * DESIGN CHOICE 2: Objects that implement these interfaces may, but are not required to, 
 * implement the IObserver Interface, as the background objects are all observable. 
 * If you only need the state of the object once, then you don't need to, but if you want
 * the unique object to change in the backend and be updated on such changes, then also 
 * implement IObserver
 * 
 * @author George Bernard
 */
package front_end.acceptor;