package hig.johanhugg.umldrawing;

/**
 * Created by Johan on 2015-03-01.
 */
public class UMLCommandFactory {
	static Command createNewUMLClassCommand(UMLClassReceiver receiver, UMLClassFrame frame) {
		return new NewUMLClassCommand(receiver, frame);
	}

    static Command addAttributeToClass(UMLClassFrame frame, UMLAttribute attribute) {
        return new AddAttributeCommand(frame, attribute);
    }

    static Command removeAttributeFromClass(UMLClassFrame frame, UMLAttribute attribute) {
        return new RemoveAttributeCommand(frame, attribute);
    }
}
