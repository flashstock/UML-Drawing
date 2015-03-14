package hig.johanhugg.umldrawing.commands;

import hig.johanhugg.umldrawing.associations.Association;
import hig.johanhugg.umldrawing.controller.UMLClassReceiver;
import hig.johanhugg.umldrawing.model.UMLAttribute;
import hig.johanhugg.umldrawing.view.UMLClassFrame;

import javax.swing.*;

/**
 * Created by Johan on 2015-03-01.
 */
public class UMLCommandFactory {
	public static Command newUMLClassCommand(UMLClassReceiver receiver, JComponent frame) {
		return new NewUMLClassCommand(receiver, frame);
	}

    public static Command addAttributeToClass(UMLClassFrame frame, UMLAttribute attribute) {
        return new AddAttributeCommand(frame, attribute);
    }

    public static Command removeAttributeFromClass(UMLClassFrame frame, UMLAttribute attribute) {
        return new RemoveAttributeCommand(frame, attribute);
    }

    public static Command createAssociation(UMLClassFrame selectedFrame, UMLClassFrame otherFrame, Association type) {
        return new CreateAssociationCommand(selectedFrame, otherFrame, type);
    }

    public static Command editAttribute(UMLClassFrame frame, UMLAttribute toEdit, UMLAttribute newValue) {
        return new EditAttributeCommand(frame, toEdit, newValue);
    }

	public static Command removeClass(UMLClassFrame selectedFrame) {
		return new RemoveClassCommand(selectedFrame);
	}

    public static EditClassNameCommand editClassName(UMLClassFrame selectedFrame, String newName) {
        return new EditClassNameCommand(selectedFrame, newName);
    }
}
