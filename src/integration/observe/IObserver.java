package integration.observe;

/**
 *  This interface specifies the Observer property
 *  
 *  Design Pattern - Observer:
 *  http://www.oodesign.com/observer-pattern.html
 *  
 *  It must implement update(), which ~updates~ the object with this property
 *  
 * @author George Bernard, Hannah Fuchsuber
 */
public interface IObserver {

    /**
     * this method is called whenever the observer must be updated
     */
    void update ();

}
