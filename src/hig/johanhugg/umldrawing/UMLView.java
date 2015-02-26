package hig.johanhugg.umldrawing;

import javax.swing.*;

/**
 * Created by Johan on 2015-02-25.
 */
public class UMLView extends JFrame {
    private final JMenuItem undoItem;
    private final JMenuItem redoItem;
    private JMenuBar jMenuBar;
	private JMenu fileMenu;
	private JMenu newMenu;
	private JMenuItem newUMLClass;
	private JDesktopPane desktopPane;

	public JMenuItem getNewUMLClass() {
		return newUMLClass;
	}

	public JMenuBar getjMenuBar() {
		return jMenuBar;
	}

	public JMenu getFileMenu() {
		return fileMenu;
	}

	public JMenu getNewMenu() {
		return newMenu;
	}

	public JDesktopPane getDesktopPane() {
		return desktopPane;
	}

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

		fileMenu = new JMenu("File");
		jMenuBar.add(fileMenu);

		newMenu = new JMenu("New");
		fileMenu.add(newMenu);

		newUMLClass = new JMenuItem("UML Class");
		newUMLClass.setActionCommand(Constants.ACTION_COMMAND_NEWUMLCLASS);
		newMenu.add(newUMLClass);

        undoItem = new JMenuItem("Undo");
        undoItem.setActionCommand(Constants.ACTION_COMMAND_UNDO);
        fileMenu.add(undoItem);

        redoItem = new JMenuItem("Redo");
        redoItem.setActionCommand(Constants.ACTION_COMMAND_REDO);
        fileMenu.add(redoItem);


		this.setJMenuBar(jMenuBar);

		desktopPane = new JDesktopPane();


		setContentPane(desktopPane);
		//desktop.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);


		setBounds(0, 0, 1024, 768);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setVisible(true);
	}


    public JMenuItem getRedoItem() {
        return redoItem;
    }

    public JMenuItem getUndoItem() {
        return undoItem;
    }
}
