package hig.johanhugg.umldrawing.view;

import hig.johanhugg.umldrawing.associations.UMLAssociationManager;
import hig.johanhugg.umldrawing.classloader.UMLClassLoader;
import hig.johanhugg.umldrawing.model.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by Johan on 2015-02-25.
 */
public class UMLView extends JFrame {
    private final JMenuItem undoItem;
    private final JMenuItem redoItem;
	private final JMenu editMenu;
	private final JMenuItem addAttributeItem;
    private final JMenuItem removeAttributeItem;
    private final JMenuItem addAssociationItem;
	private final JMenuItem removeClassItem;
	private JMenuBar menubar;
	private JMenu fileMenu;
	private JMenu newMenu;
	private JMenuItem newUMLClass;
	private UMLDesktopPane desktopPane;

	public JMenuItem getRemoveClassItem() {
		return removeClassItem;
	}

    public JMenuItem getAddAssociationItem() {
        return addAssociationItem;
    }

	public JMenuItem getNewUMLClass() {
		return newUMLClass;
	}

	public JMenuBar getMenubar() {
		return menubar;
	}

	public JMenu getFileMenu() {
		return fileMenu;
	}

	public JMenu getNewMenu() {
		return newMenu;
	}

	public UMLDesktopPane getDesktopPane() {
		return desktopPane;
	}

	public UMLView() {
        try {
            // Set System L&F
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        }
        catch (UnsupportedLookAndFeelException e) {
            // handle exception
        }
        catch (ClassNotFoundException e) {
            // handle exception
        }
        catch (InstantiationException e) {
            // handle exception
        }
        catch (IllegalAccessException e) {
            // handle exception
        }

		UMLClassLoader loader = new UMLClassLoader();
		loader.loadClass("hig.johanhugg.umldrawing.testclasses.Main");

		loader.getMethods().forEach(System.out::println);
		loader.getFields().forEach(System.out::println);
		loader.getConstructors().forEach(System.out::println);



        menubar = new JMenuBar();

		fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);
		menubar.add(fileMenu);

		newMenu = new JMenu("New");
		newMenu.setMnemonic(KeyEvent.VK_N);
		fileMenu.add(newMenu);

		newUMLClass = new JMenuItem("UML Class");
		newUMLClass.setActionCommand(Constants.ACTION_COMMAND_NEWUMLCLASS);
		newUMLClass.setMnemonic(KeyEvent.VK_U);
        newUMLClass.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, Event.CTRL_MASK));
		newMenu.add(newUMLClass);

        undoItem = new JMenuItem("Undo");
        undoItem.setActionCommand(Constants.ACTION_COMMAND_UNDO);
		undoItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, Event.CTRL_MASK));
        fileMenu.add(undoItem);

        redoItem = new JMenuItem("Redo");
        redoItem.setActionCommand(Constants.ACTION_COMMAND_REDO);
		redoItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, Event.CTRL_MASK | Event.SHIFT_MASK));
        fileMenu.add(redoItem);

		editMenu = new JMenu("Edit");
		editMenu.setMnemonic(KeyEvent.VK_E);
		menubar.add(editMenu);

		addAttributeItem = new JMenuItem("Add Attribute");
        addAttributeItem.setActionCommand(Constants.ACTION_COMMAND_ADDATTRIBUTE);
		addAttributeItem.setMnemonic(KeyEvent.VK_A);
		editMenu.add(addAttributeItem);

        removeAttributeItem = new JMenuItem("Remove Attribute");
        removeAttributeItem.setActionCommand(Constants.ACTION_COMMAND_REMOVEATTRIBUTE);
		removeAttributeItem.setMnemonic(KeyEvent.VK_R);
        editMenu.add(removeAttributeItem);

        addAssociationItem = new JMenuItem("Add Association");
        addAssociationItem.setActionCommand(Constants.ACTION_COMMAND_NEWASSOCIATION);
		addAssociationItem.setMnemonic(KeyEvent.VK_S);
		addAssociationItem.setDisplayedMnemonicIndex(5);
        editMenu.add(addAssociationItem);

		removeClassItem = new JMenuItem("Remove this class");
		removeClassItem.setMnemonic(KeyEvent.VK_C);
		removeClassItem.setActionCommand(Constants.ACTION_COMMAND_REMOVECLASS);
		editMenu.add(removeClassItem);



		this.setJMenuBar(menubar);
		desktopPane = new UMLDesktopPane(new UMLAssociationManager());
        desktopPane.setDragMode(JDesktopPane.LIVE_DRAG_MODE);
        desktopPane.setBackground(Color.gray);

		setContentPane(desktopPane);


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

	public JMenu getEditMenu() {
		return editMenu;
	}

	public JMenuItem getAddAttributeItem() {
		return addAttributeItem;
	}

    public JMenuItem getRemoveAttributeItem() {
        return removeAttributeItem;
    }
}
