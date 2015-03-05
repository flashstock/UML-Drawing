package hig.johanhugg.umldrawing;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
			default:
				System.out.println("Unknown Action");
				break;
		}
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
        if (classTitle.isEmpty())
            return;
        UMLClassFrame umlClassFrame = new UMLClassFrame(new UMLClass(classTitle));
        umlClassFrames.add(umlClassFrame);
        Command umlClassCommand = UMLCommandFactory.createNewUMLClassCommand(umlClassReceiver, umlClassFrame);
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
