package hig.refactoring;

import javax.swing.*;

/**
 * Created by Johan on 2015-02-25.
 */
public class UMLClassFrame extends JInternalFrame {
	public UMLClassFrame(String title) {
		super(title, true, false, false, true);
		setFrameIcon(null);
		setSize(300, 300);
		setLocation(100, 100);
	}
}
