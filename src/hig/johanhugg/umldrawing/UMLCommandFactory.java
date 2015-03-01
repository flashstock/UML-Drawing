package hig.johanhugg.umldrawing;

/**
 * Created by Johan on 2015-03-01.
 */
public class UMLCommandFactory {
	static Command createNewUMLClassCommand(UMLClassReceiver receiver, UMLClassFrame frame) {
		return new NewUMLClassCommand(receiver, frame);
	}
}
