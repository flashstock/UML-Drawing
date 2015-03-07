package hig.johanhugg.umldrawing.commands;

import hig.johanhugg.umldrawing.associations.Association;
import hig.johanhugg.umldrawing.associations.UMLAssociationManager;
import hig.johanhugg.umldrawing.view.UMLClassFrame;
import hig.johanhugg.umldrawing.view.UMLDesktopPane;

import javax.swing.*;

/**
 * Created by hugg on 07/03/15.
 */
public class CreateAssociationCommand implements Command {
    private final UMLAssociationManager manager;
    private UMLClassFrame selectedFrame;
    private UMLClassFrame otherFrame;
    private Association type;

    public CreateAssociationCommand(UMLClassFrame selectedFrame, UMLClassFrame otherFrame, Association type) {
        this.selectedFrame = selectedFrame;
        this.otherFrame = otherFrame;
        this.type = type;
        this.manager = ((UMLDesktopPane) selectedFrame.getDesktopPane()).getAssociationManager();

    }

    @Override
    public void execute() {
        manager.makeAssociation(selectedFrame.getAssociatedClass(), otherFrame.getAssociatedClass(), type);
    }

    @Override
    public void undo() {
        manager.removeAssociation(selectedFrame.getAssociatedClass(), otherFrame.getAssociatedClass());
    }
}
