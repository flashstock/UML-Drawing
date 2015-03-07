package hig.johanhugg.umldrawing.view;

import hig.johanhugg.umldrawing.associations.RawAssociation;
import hig.johanhugg.umldrawing.associations.UMLAssociationManager;
import hig.johanhugg.umldrawing.model.Constants;
import hig.johanhugg.umldrawing.model.UMLClass;

import javax.swing.*;

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
    private JMenuBar menubar;
	private JMenu fileMenu;
	private JMenu newMenu;
	private JMenuItem newUMLClass;
	private UMLDesktopPane desktopPane;

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

//		UMLAssociationManager associationManager = new UMLAssociationManager();
//		UMLClass class1 = new UMLClass("Test");
//		UMLClass class2 = new UMLClass("Testes");
//
//		UMLClass class3 = new UMLClass("Test123");
//
//		associationManager.makeAssociation(class1, class3, new RawAssociation());
//		associationManager.makeAssociation(class1, class2, new RawAssociation());
//		associationManager.relatedWith(class1);
//		associationManager.relatedWith(class2);


        menubar = new JMenuBar();

		fileMenu = new JMenu("File");
		menubar.add(fileMenu);

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

		editMenu = new JMenu("Edit");
		menubar.add(editMenu);

		addAttributeItem = new JMenuItem("Add Attribute");
        addAttributeItem.setActionCommand(Constants.ACTION_COMMAND_ADDATTRIBUTE);
		editMenu.add(addAttributeItem);

        removeAttributeItem = new JMenuItem("Remove Attribute");
        removeAttributeItem.setActionCommand(Constants.ACTION_COMMAND_REMOVEATTRIBUTE);
        editMenu.add(removeAttributeItem);

        addAssociationItem = new JMenuItem("Add Association");
        addAssociationItem.setActionCommand(Constants.ACTION_COMMAND_NEWASSOCIATION);
        editMenu.add(addAssociationItem);



		this.setJMenuBar(menubar);

		desktopPane = new UMLDesktopPane(new UMLAssociationManager());


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
