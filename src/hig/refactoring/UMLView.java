package hig.refactoring;

import javax.swing.*;
import java.beans.PropertyVetoException;

/**
 * Created by Johan on 2015-02-25.
 */
public class UMLView extends JFrame {
	private JMenuBar jMenuBar;
	private JMenu fileMenu;


	public UMLView() {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}


		jMenuBar = new JMenuBar();
		jMenuBar.add(fileMenu = new JMenu("File"));
		this.setJMenuBar(jMenuBar);



		JDesktopPane desktop = new JDesktopPane();
		UMLClassFrame umlClassFrame = new UMLClassFrame();
		umlClassFrame.setVisible(true);
		desktop.add(umlClassFrame);
		try {
			umlClassFrame.setSelected(true);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}

		setContentPane(desktop);
		//desktop.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);


		setBounds(0, 0, 1024, 768);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setVisible(true);
	}
}
