package hig.johanhugg.umldrawing.controller;

import hig.johanhugg.umldrawing.view.UMLClassFrame;

import javax.swing.*;
import java.util.LinkedList;

/**
 * Created by Johan on 2015-02-25.
 */
public class UMLClassReceiver {
	private JDesktopPane desktopPane;

	public UMLClassReceiver(JDesktopPane desktopPane) {
		this.desktopPane = desktopPane;
	}

	public void addUMLClass(JComponent umlClassFrame) {
		desktopPane.add(umlClassFrame);
		umlClassFrame.setVisible(true);
        umlClassFrame.requestFocus();
	}

	public void removeUMLClass(JComponent umlClassFrame) {
		desktopPane.remove(umlClassFrame);
        desktopPane.repaint();
	}
}
