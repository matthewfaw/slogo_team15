package integration.drawing;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Kayla Schulz
 * @author George Bernard
 * 
 * Special thanks to Stack Overflow:
 * http://stackoverflow.com/questions/9662170/override-valueof-and-tostring-in-java-enum
 *
 */
public enum LineStyleSpec {
	SOLID("Solid"),
	DASH("Dash"),
	DOT("Dot"),
	DASH_DOT("Dash Dot");
    
    private String value;
    
    LineStyleSpec(String value) {
        this.value = value;
    }
    
    public String getValue() {
        return value;
    }
    
    @Override
    public String toString() {
        return this.getValue();
    }
    
    public static List<Object> getMyLineStyles() {
        List<Object> myList = new ArrayList<Object>();
        for(LineStyleSpec n: values())
            myList.add(n.value);
        return myList;
    }
    
}
