package hig.johanhugg.umldrawing.controller;

import hig.johanhugg.umldrawing.associations.Association;
import hig.johanhugg.umldrawing.associations.AssociationFactory;
import hig.johanhugg.umldrawing.classloader.UMLClassLoader;
import hig.johanhugg.umldrawing.commands.Command;
import hig.johanhugg.umldrawing.commands.UMLCommandFactory;
import hig.johanhugg.umldrawing.model.*;
import hig.johanhugg.umldrawing.view.UMLClassFrame;
import hig.johanhugg.umldrawing.view.UMLView;

import javax.swing.*;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Johan on 2015-02-25.
 */
public class UMLController implements ActionListener {

	private final UMLView umlView;
	private UMLClassReceiver umlClassReceiver;
    private LinkedList<UMLClassFrame> umlClassFrames;
    private UndoRedoStack undoRedoStack;

	public UMLController(UMLView umlView) {
		this.umlView = umlView;
        this.umlClassFrames = new LinkedList<>();
        this.undoRedoStack = new UndoRedoStack();
		this.umlClassReceiver = new UMLClassReceiver(umlView.getDesktopPane());

//		UMLClassFrame testFrame = new UMLClassFrame(new UMLClassLoader("hig.johanhugg.umldrawing.controller.UMLController").createUMLClassFromLoadedClass());
//		umlClassFrames.add(testFrame);
//		Command umlClassCommand = UMLCommandFactory.newUMLClassCommand(umlClassReceiver, testFrame);
//		undoRedoStack.redo(umlClassCommand);

		addActionListeners(this);
	}

	private void addActionListeners(ActionListener actionListener) {
		umlView.getNewUMLClass().addActionListener(actionListener);
        umlView.getUndoItem().addActionListener(actionListener);
        umlView.getRedoItem().addActionListener(actionListener);
        umlView.getAddAttributeItem().addActionListener(actionListener);
        umlView.getRemoveAttributeItem().addActionListener(actionListener);
        umlView.getAddAssociationItem().addActionListener(actionListener);
		umlView.getRemoveClassItem().addActionListener(actionListener);
        umlView.getEditAttributeItem().addActionListener(actionListener);
        umlView.getEditClassName().addActionListener(actionListener);
        umlView.getNewUMLClassFromCompiledSource().addActionListener(actionListener);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
        UMLClassFrame selectedFrame = getSelectedUMLClassFrame();
		String ac = event.getActionCommand();
		if (selectedFrame != null) {
			if (ac.equals(Constants.ACTION_COMMAND_ADDATTRIBUTE))
				addAttribute(selectedFrame);
			if (ac.equals(Constants.ACTION_COMMAND_REMOVEATTRIBUTE))
				removeAttribute(selectedFrame);
			if (ac.equals(Constants.ACTION_COMMAND_NEWASSOCIATION))
				createAssociation(selectedFrame);
			if (ac.equals(Constants.ACTION_COMMAND_REMOVECLASS))
				removeClass(selectedFrame);
            if (ac.equals(Constants.ACTION_COMMAND_EDITATTRIBUTE))
                editAttribute(selectedFrame);
            if (ac.equals(Constants.ACTION_COMMAND_EDITCLASSNAME))
                editClassName(selectedFrame);
		}

        if (ac.equals(Constants.ACTION_COMMAND_NEWUMLCLASS))
            newUMLClass();
        if (ac.equals(Constants.ACTION_COMMAND_UNDO))
            undoRedoStack.undo();
        if (ac.equals(Constants.ACTION_COMMAND_REDO))
            undoRedoStack.redo();
        if (ac.equals(Constants.ACTION_COMMAND_NEWUMLCLASSFROMCOMPILEDSOURCE))
            loadClassFromQualifiedName();
	}

    private void loadClassFromQualifiedName() {
        String name = JOptionPane.showInputDialog("Enter the fully-qualified name for the class you want to load");
        if (name == null)
            return;
        UMLClassLoader loader = new UMLClassLoader(name);
        UMLClassFrame frame = new UMLClassFrame(loader.createUMLClassFromLoadedClass());
        addInternalFrameListeners(frame);
        umlClassFrames.add(frame);

        Command umlClassCommand = UMLCommandFactory.newUMLClassCommand(umlClassReceiver, frame);
        undoRedoStack.redo(umlClassCommand);
    }

    private void editClassName(UMLClassFrame selectedFrame) {
        String newName = JOptionPane.showInputDialog("Input the new name for the class");
        if (newName == null || newName.length() == 0)
            return;
        undoRedoStack.redo(UMLCommandFactory.editClassName(selectedFrame, newName));
    }

    private void editAttribute(UMLClassFrame selectedFrame) {
        List<UMLAttribute> attributes = selectedFrame.getAttributes();
        UMLAttribute selectedAttribute = (UMLAttribute) JOptionPane.showInputDialog(
                selectedFrame,
                "Please choose the attribute you want to edit",
                "Edit attribute",
                JOptionPane.PLAIN_MESSAGE,
                null,
                attributes.toArray(),
                null
        );
        UMLAttribute createdAttrib;
        switch (selectedAttribute.getIdentifier()) {
            case Constants.ATTRIBUTE_CONSTRUCTOR:
                createdAttrib = createConstructorAttribute(selectedFrame);
                if (createdAttrib != null)
                    undoRedoStack.redo(UMLCommandFactory.editAttribute(selectedFrame, selectedAttribute, createdAttrib));
                break;
            case Constants.ATTRIBUTE_FIELD:
                createdAttrib = createFieldAttribute(selectedFrame);
                if (createdAttrib != null)
                    undoRedoStack.redo(UMLCommandFactory.editAttribute(selectedFrame, selectedAttribute, createdAttrib));
                    break;
            case Constants.ATTRIBUTE_METHOD:
                createdAttrib = createMethodAttribute(selectedFrame);
                if (createdAttrib != null)
                    undoRedoStack.redo(UMLCommandFactory.editAttribute(selectedFrame, selectedAttribute, createdAttrib));
                break;
        }

    }

    private void removeClass(UMLClassFrame selectedFrame) {
		Command removeClassCommand = UMLCommandFactory.removeClass(selectedFrame);
		undoRedoStack.redo(removeClassCommand);

	}

	private void createAssociation(UMLClassFrame selectedFrame) {
        UMLClassFrame otherFrame = (UMLClassFrame) JOptionPane.showInputDialog(
                selectedFrame,
                "Please choose the other class to create an association with",
                "Create Association",
                JOptionPane.PLAIN_MESSAGE,
                null,
                umlClassFrames.stream().filter(x -> !(x == selectedFrame)).collect(Collectors.toList()).toArray(),
                null
        );
		String selectedAssociationType = (String) JOptionPane.showInputDialog(
				selectedFrame,
				"Please choose the type of association you want",
				"Choose association type",
				JOptionPane.PLAIN_MESSAGE,
				null,
				AssociationFactory.getListOfAssociations().toArray(),
				null
		);
		Association type = AssociationFactory.createAssociationFromString(selectedAssociationType);
        Command addAssociationCommand = UMLCommandFactory.createAssociation(selectedFrame, otherFrame, type);
        undoRedoStack.redo(addAssociationCommand);
    }

    private void removeAttribute(UMLClassFrame selectedFrame) {
        List<UMLAttribute> umlAttributes = selectedFrame.getAttributes();
        if (umlAttributes.size() == 0)
            return;
        UMLAttribute selectedAttribute = (UMLAttribute) JOptionPane.showInputDialog(
                selectedFrame,
                "Please choose the attribute to remove",
                "Remove attribute",
                JOptionPane.PLAIN_MESSAGE,
                null,
                umlAttributes.toArray(),
                null
        );

		if (selectedAttribute == null)
			return;

        Command removeAttributeCommand = UMLCommandFactory.removeAttributeFromClass(selectedFrame, selectedAttribute);
        undoRedoStack.redo(removeAttributeCommand);
    }

    private void addAttribute(UMLClassFrame selectedFrame) {

		String selectedAttributeType = (String) JOptionPane.showInputDialog(
				selectedFrame,
				"Please choose the type of attribute you want",
				"Choose attribute type",
				JOptionPane.PLAIN_MESSAGE,
				null,
				UMLAttributeFactory.getListOfAttributes().toArray(),
				null
		);
        if (selectedAttributeType == null || selectedAttributeType.isEmpty())
            return;

		UMLAttribute selectedAttribute = selectAttributeCommand(selectedAttributeType, selectedFrame);

		if (selectedAttribute == null)
			return;
		Command addAttributeCommand = UMLCommandFactory.addAttributeToClass(selectedFrame, selectedAttribute);
        undoRedoStack.redo(addAttributeCommand);
    }

	private UMLAttribute selectAttributeCommand(String selectedAttributeType, UMLClassFrame selectedFrame) {
		switch(selectedAttributeType) {
			case Constants.ATTRIBUTE_FIELD:
				return createFieldAttribute(selectedFrame);
			case Constants.ATTRIBUTE_CONSTRUCTOR:
				return createConstructorAttribute(selectedFrame);
			case Constants.ATTRIBUTE_METHOD:
				return createMethodAttribute(selectedFrame);
			default:
				break;
		}
		return null;
	}

	private UMLAttribute createMethodAttribute(UMLClassFrame selectedFrame) {
		Visibility vis = showInputVisibilityDialog(selectedFrame);
		if (vis == null)
			return null;

		String methodName = JOptionPane.showInputDialog(selectedFrame, "Enter name for method");

		if (methodName == null || methodName.length() == 0)
			return null;

		String args = JOptionPane.showInputDialog(selectedFrame, "Enter args (e.g. \"String String int\")");

		if (args == null)
            args = "";

		String type = JOptionPane.showInputDialog(selectedFrame, "Enter return type");

		if (type == null || type.length() == 0)
			return null;

		return UMLAttributeFactory.createMethodAttribute(vis, methodName, args, type);
	}

	private UMLAttribute createConstructorAttribute(UMLClassFrame selectedFrame) {
		Visibility vis = showInputVisibilityDialog(selectedFrame);
		if (vis == null)
			return null;

		String constructorName = selectedFrame.getUMLClassName();

		String args = JOptionPane.showInputDialog(selectedFrame, "Enter args (e.g. \"String String int\")");
		if (args == null)
			args = "";

		return UMLAttributeFactory.createConstructorAttribute(vis, constructorName, args);

	}

	private Visibility showInputVisibilityDialog(UMLClassFrame selectedFrame) {
		Visibility vis = (Visibility) JOptionPane.showInputDialog(
				selectedFrame,
				"Please choose the visibility for the field",
				"Please choose visibility",
				JOptionPane.PLAIN_MESSAGE,
				null,
				Visibility.getSymbols().toArray(),
				null);
		return vis;
	}

	private UMLAttribute createFieldAttribute(UMLClassFrame selectedFrame) {
		Visibility vis = showInputVisibilityDialog(selectedFrame);
		if (vis == null)
			return null;

		String fieldName = JOptionPane.showInputDialog(selectedFrame, "Enter field name");
		if (fieldName == null || fieldName.length() == 0)
			return null;

		String typeName = JOptionPane.showInputDialog(selectedFrame, "Enter type");
		if (typeName == null || typeName.length() == 0)
			return null;

		return UMLAttributeFactory.createFieldAttribute(vis, fieldName, typeName);
	}

	private void newUMLClass() {
        String classTitle = JOptionPane.showInputDialog("Input Class Name");
        if (classTitle == null || classTitle.isEmpty())
            return;
        UMLClassFrame umlClassFrame = new UMLClassFrame(new UMLClass(classTitle));
        addInternalFrameListeners(umlClassFrame);
        umlClassFrames.add(umlClassFrame);
        Command umlClassCommand = UMLCommandFactory.newUMLClassCommand(umlClassReceiver, umlClassFrame);
        undoRedoStack.redo(umlClassCommand);
    }

    private void addInternalFrameListeners(UMLClassFrame umlClassFrame) {
        umlClassFrame.addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameActivated(InternalFrameEvent e) {
                super.internalFrameActivated(e);
                umlView.getEditMenu().setEnabled(true);
            }

            @Override
            public void internalFrameDeactivated(InternalFrameEvent e) {
                super.internalFrameDeactivated(e);
                umlView.getEditMenu().setEnabled(false);
            }

        });
        umlClassFrame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentMoved(ComponentEvent e) {
                super.componentMoved(e);
                umlClassFrame.getDesktopPane().repaint();
                //We need this so that the desktoppane repaints itself every time the component is moved, otherwise lines will look weird.
                //This also saves performance since we only repaint when the component is moved, not all the time the line is drawn.
            }

            @Override
            public void componentHidden(ComponentEvent e) {
                super.componentHidden(e);
                umlView.getEditMenu().setEnabled(false);
            }
        });
    }

    private UMLClassFrame getSelectedUMLClassFrame() {
        return (UMLClassFrame) umlView.getDesktopPane().getSelectedFrame();
    }

}
