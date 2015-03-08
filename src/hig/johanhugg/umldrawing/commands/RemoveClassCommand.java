package hig.johanhugg.umldrawing.commands;

import hig.johanhugg.umldrawing.view.UMLClassFrame;
import hig.johanhugg.umldrawing.view.UMLDesktopPane;

/**
 * Created by Johan on 2015-03-08.
 */
public class RemoveClassCommand implements Command {
	private UMLDesktopPane pane;
	private UMLClassFrame frame;
	public RemoveClassCommand(UMLClassFrame selectedFrame) {
		pane = (UMLDesktopPane) selectedFrame.getDesktopPane();
		frame = selectedFrame;
	}

	@Override
	public void execute() {
		pane.remove(frame);
		pane.repaint();
	}

	@Override
	public void undo() {
		pane.add(frame);
		pane.repaint();
	}
}
