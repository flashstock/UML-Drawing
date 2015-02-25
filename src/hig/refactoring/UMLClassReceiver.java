package hig.refactoring;

import javax.swing.*;
import java.util.LinkedList;

/**
 * Created by Johan on 2015-02-25.
 */
public class UMLClassReceiver {
	private JDesktopPane desktopPane;
	private UMLClassFrame umlClassFrame;
	private LinkedList<UMLClassFrame> umlClassFrames;

	public UMLClassReceiver(JDesktopPane desktopPane) {
		this.desktopPane = desktopPane;
		this.umlClassFrames = new LinkedList<>();
	}

	public void addUMLClass(UMLClassFrame umlClassFrame) {
		desktopPane.add(umlClassFrame);
		umlClassFrame.setVisible(true);
	}

	public void removeUMLClass(UMLClassFrame umlClassFrame) {
		desktopPane.remove(umlClassFrame);
	}
}
