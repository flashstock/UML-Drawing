package hig.johanhugg.umldrawing;

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
