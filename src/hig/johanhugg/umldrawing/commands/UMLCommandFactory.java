package hig.johanhugg.umldrawing.commands;

import hig.johanhugg.umldrawing.model.UMLAttribute;
import hig.johanhugg.umldrawing.view.UMLClassFrame;
import hig.johanhugg.umldrawing.controller.UMLClassReceiver;

/**
 * Created by Johan on 2015-03-01.
 */
public class UMLCommandFactory {
	public static Command newUMLClassCommand(UMLClassReceiver receiver, UMLClassFrame frame) {
		return new NewUMLClassCommand(receiver, frame);
	}

    public static Command addAttributeToClass(UMLClassFrame frame, UMLAttribute attribute) {
        return new AddAttributeCommand(frame, attribute);
    }

    public static Command removeAttributeFromClass(UMLClassFrame frame, UMLAttribute attribute) {
        return new RemoveAttributeCommand(frame, attribute);
    }
}
