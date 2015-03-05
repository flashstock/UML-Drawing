package hig.johanhugg.umldrawing.commands;

import hig.johanhugg.umldrawing.model.UMLAttribute;
import hig.johanhugg.umldrawing.view.UMLClassFrame;

/**
 * Created by hugg on 02/03/15.
 */
public class RemoveAttributeCommand implements Command {

    private final UMLClassFrame frame;
    private final UMLAttribute attribute;

    public RemoveAttributeCommand(UMLClassFrame frame, UMLAttribute attribute) {
        this.frame = frame;
        this.attribute = attribute;
    }

    @Override
    public void execute() {
        frame.removeAttribute(attribute);
    }

    @Override
    public void undo() {
        frame.addAttribute(attribute);
    }
}
