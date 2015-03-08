package hig.johanhugg.umldrawing.controller;

import hig.johanhugg.umldrawing.associations.Association;
import hig.johanhugg.umldrawing.associations.AssociationFactory;
import hig.johanhugg.umldrawing.model.Constants;
import hig.johanhugg.umldrawing.model.UndoRedoStack;
import hig.johanhugg.umldrawing.commands.Command;
import hig.johanhugg.umldrawing.commands.UMLCommandFactory;
import hig.johanhugg.umldrawing.model.UMLAttribute;
import hig.johanhugg.umldrawing.model.UMLClass;
import hig.johanhugg.umldrawing.model.UMLModel;
import hig.johanhugg.umldrawing.view.UMLClassFrame;
import hig.johanhugg.umldrawing.view.UMLView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Johan on 2015-02-25.
 */
public class UMLController implements MouseListener, ActionListener {

	private final UMLView umlView;
	private final UMLModel umlModel;
	private UMLClassReceiver umlClassReceiver;
    private LinkedList<UMLClassFrame> umlClassFrames;
    private UndoRedoStack undoRedoStack;

	public UMLController(UMLView umlView, UMLModel umlModel) {
		this.umlView = umlView;
		this.umlModel = umlModel;
        this.umlClassFrames = new LinkedList<>();
        this.undoRedoStack = new UndoRedoStack();
		this.umlClassReceiver = new UMLClassReceiver(umlView.getDesktopPane());

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
	}


	@Override
	public void actionPerformed(ActionEvent event) {
        UMLClassFrame selectedFrame = getSelectedUMLClassFrame();
        if (selectedFrame == null && umlClassFrames.size() != 0)
            return;
		switch(event.getActionCommand()) {

            case Constants.ACTION_COMMAND_NEWUMLCLASS:
                newUMLClass();
				break;
            case Constants.ACTION_COMMAND_UNDO:
                System.out.println("Undo");
                undoRedoStack.undo();
                break;
            case Constants.ACTION_COMMAND_REDO:
                System.out.println("Redo");
                undoRedoStack.redo();
                break;
            case Constants.ACTION_COMMAND_ADDATTRIBUTE:
                addAttribute(selectedFrame);
                break;
            case Constants.ACTION_COMMAND_REMOVEATTRIBUTE:
                removeAttribute(selectedFrame);
                break;
            case Constants.ACTION_COMMAND_NEWASSOCIATION:
                createAssociation(selectedFrame);
                break;
			case Constants.ACTION_COMMAND_REMOVECLASS:
				removeClass(selectedFrame);
				break;
			default:
				System.out.println("Unknown Action");
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
                umlClassFrames.stream().filter(x -> !x.equals(selectedFrame)).collect(Collectors.toList()).toArray(),
                null
        );
		String selectedAssociationType = (String) JOptionPane.showInputDialog(
				selectedFrame,
				"Please choose the type of association you want",
				"Choose association type",
				JOptionPane.PLAIN_MESSAGE,
				null,
				AssociationFactory.getListOfAssocations().toArray(),
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
        Command removeAttributeCommand = UMLCommandFactory.removeAttributeFromClass(selectedFrame, selectedAttribute);
        undoRedoStack.redo(removeAttributeCommand);
    }

    private void addAttribute(UMLClassFrame selectedFrame) {
        String attributeName = JOptionPane.showInputDialog("Please enter attribute name");
        if (attributeName.isEmpty())
            return;
        Command addAttributeCommand = UMLCommandFactory.addAttributeToClass(selectedFrame, new UMLAttribute(attributeName));
        undoRedoStack.redo(addAttributeCommand);
    }

    private void newUMLClass() {
        String classTitle = JOptionPane.showInputDialog("Input Class Name");
        if (classTitle == null || classTitle.isEmpty())
            return;
        UMLClassFrame umlClassFrame = new UMLClassFrame(new UMLClass(classTitle));
        umlClassFrames.add(umlClassFrame);
        Command umlClassCommand = UMLCommandFactory.newUMLClassCommand(umlClassReceiver, umlClassFrame);
        undoRedoStack.redo(umlClassCommand);
    }

    private UMLClassFrame getSelectedUMLClassFrame() {
        return (UMLClassFrame) umlView.getDesktopPane().getSelectedFrame();
    }

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
}
