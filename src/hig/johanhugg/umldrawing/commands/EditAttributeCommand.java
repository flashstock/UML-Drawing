package hig.johanhugg.umldrawing.commands;

import hig.johanhugg.umldrawing.model.UMLAttribute;
import hig.johanhugg.umldrawing.view.UMLClassFrame;

import java.util.List;

/**
 * Created by hugg on 14/03/15.
 */
public class EditAttributeCommand implements Command {
    private final UMLClassFrame frame;
    private final UMLAttribute toEdit;
    private final UMLAttribute newValue;

    public EditAttributeCommand(UMLClassFrame frame, UMLAttribute toEdit, UMLAttribute newValue) {
        this.frame = frame;
        this.toEdit = toEdit;
        this.newValue = newValue;
    }

    @Override
    public void execute() {
        List<UMLAttribute> attributes = frame.getAssociatedClass().getAttributes();
        attributes.set(attributes.indexOf(toEdit), newValue);
        frame.updateFields();
    }

    @Override
    public void undo() {
        List<UMLAttribute> attributes = frame.getAssociatedClass().getAttributes();
        attributes.set(attributes.indexOf(newValue), toEdit);
        frame.updateFields();
    }
}
