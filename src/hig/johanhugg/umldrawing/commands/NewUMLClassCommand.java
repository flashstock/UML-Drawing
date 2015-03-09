package hig.johanhugg.umldrawing.commands;

import hig.johanhugg.umldrawing.view.UMLClassFrame;
import hig.johanhugg.umldrawing.controller.UMLClassReceiver;

import javax.swing.*;

/**
 * Created by Johan on 2015-02-25.
 */
public class NewUMLClassCommand implements Command {

	private UMLClassReceiver umlClassReceiver;
	private JComponent umlClassFrame;

	public NewUMLClassCommand(UMLClassReceiver umlClassReceiver, JComponent umlClassFrame) {
		this.umlClassReceiver = umlClassReceiver;
		this.umlClassFrame = umlClassFrame;
	}

	@Override
	public void execute() {
		umlClassReceiver.addUMLClass(umlClassFrame);
	}

	@Override
	public void undo() {
		umlClassReceiver.removeUMLClass(umlClassFrame);
	}


}
