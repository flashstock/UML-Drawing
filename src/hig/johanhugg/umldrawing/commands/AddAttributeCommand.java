package hig.johanhugg.umldrawing.commands;

import hig.johanhugg.umldrawing.model.UMLAttribute;
import hig.johanhugg.umldrawing.view.UMLClassFrame;

/**
 * Created by hugg on 02/03/15.
 */
public class AddAttributeCommand implements Command {

    private UMLClassFrame frame;
    private UMLAttribute attribute;

    public AddAttributeCommand(UMLClassFrame frame, UMLAttribute attribute) {
        this.frame = frame;
        this.attribute = attribute;
    }

    @Override
    public void execute() {
        frame.addAttribute(attribute);
    }

    @Override
    public void undo() {
        frame.removeAttribute(attribute);
    }
}
