package hig.johanhugg.umldrawing;

/**
 * Created by Johan on 2015-02-25.
 */
public class UMLClassInvoker {
	private Command command;

	public void setCommand(Command command) {
		this.command = command;
	}

	public void executeCommand() {
		command.execute();
	}

}
