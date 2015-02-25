package hig.refactoring;

/**
 * Created by Johan on 2015-02-25.
 */
public class NewUMLClassCommand implements Command {

	private UMLClassReceiver umlClassReceiver;
	private UMLClassFrame umlClassFrame;
	private String title;

	public NewUMLClassCommand(UMLClassReceiver umlClassReceiver, UMLClassFrame umlClassFrame) {
		this.umlClassReceiver = umlClassReceiver;
		this.umlClassFrame = umlClassFrame;
		this.title = title;
	}

	@Override
	public void execute() {
		umlClassReceiver.addUMLClass(umlClassFrame);
	}
}
