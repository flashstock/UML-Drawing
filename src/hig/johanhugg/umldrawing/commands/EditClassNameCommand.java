package hig.johanhugg.umldrawing.commands;

import hig.johanhugg.umldrawing.view.UMLClassFrame;

/**
 * Created by hugg on 14/03/15.
 */
public class EditClassNameCommand implements Command {
    private final UMLClassFrame selectedFrame;
    private final String newName;
    private final String oldName;

    public EditClassNameCommand(UMLClassFrame selectedFrame, String newName) {
        this.selectedFrame = selectedFrame;
        this.newName = newName;
        this.oldName = selectedFrame.getUMLClassName();
    }

    @Override
    public void execute() {
        selectedFrame.setUMLClassName(newName);
    }

    @Override
    public void undo() {
        selectedFrame.setUMLClassName(oldName);
    }
}
