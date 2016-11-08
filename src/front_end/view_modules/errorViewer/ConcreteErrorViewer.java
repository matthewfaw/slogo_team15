package front_end.view_modules.errorViewer;

import back_end.model.exception.IExceptionDebugger;
import front_end.view_modules.textEditor.ITextEditor;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;


/**
 * This class creates the concrete implementation of the error viewer. It implements the
 * error viewer interface and sets the specifics for how the error viewer will be laid out.
 * 
 * @author George Bernard
 * @author Kayla Schulz
 *
 */
class ConcreteErrorViewer implements IErrorViewer {

    private ScrollPane myErrorScroller;
    private ITextEditor myTextEditor;
    private IExceptionDebugger myError;
    private Label myErrorLabel;

    private static final int ErrorRowHeight = 30;

    ConcreteErrorViewer (int aWidth, int aHeight, ITextEditor aTextEditor) {
        myTextEditor = aTextEditor;

        myErrorScroller = new ScrollPane();
        myErrorScroller.setHbarPolicy(ScrollBarPolicy.NEVER);
        myErrorScroller.setVbarPolicy(ScrollBarPolicy.ALWAYS);
        myErrorScroller.setMinSize(aWidth, aHeight);
        myErrorScroller.setMaxSize(aWidth, aHeight);

        myErrorLabel = new Label("No errors... yet!");
        myErrorLabel.setWrapText(true);

        HBox box = new HBox(0);
        box.getChildren().add(myErrorLabel);

        myErrorScroller.setContent(box);

    }

    @Override
    public void reset () {
        // TODO Implement
    }

    @Override
    public Node getInstanceAsNode () {
        return myErrorScroller;
    }

    @Override
    public void giveError (IExceptionDebugger aError) {
        myError =  aError;
        myErrorLabel.setText(aError.getErrorMessage());
        myTextEditor.highlightLine(Color.RED, aError.getErrorLineNumber());
    }

}
