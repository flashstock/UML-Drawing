package hig.refactoring;

import javax.swing.*;

/**
 * Created by Johan on 2015-02-25.
 */
public class UMLClassReceiver {
	private JDesktopPane desktopPane;
	private UMLClassFrame umlClassFrame;

	public UMLClassReceiver(JDesktopPane desktopPane) {
		this.desktopPane = desktopPane;
	}

	public void addUMLClass(UMLClassFrame umlClassFrame) {
		desktopPane.add(umlClassFrame);
		umlClassFrame.setVisible(true);
	}

	public void removeUMLClass() {
		//desktopPane.remove(umlClassFrame);
	}
}
