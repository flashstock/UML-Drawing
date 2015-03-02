package hig.johanhugg.umldrawing;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;

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
		switch(event.getActionCommand()) {

            case Constants.ACTION_COMMAND_NEWUMLCLASS:
				String classTitle = JOptionPane.showInputDialog("Input Class Name");
				UMLClassFrame umlClassFrame = new UMLClassFrame(new UMLClass(classTitle));
                umlClassFrames.add(umlClassFrame);
                Command umlClassCommand = UMLCommandFactory.createNewUMLClassCommand(umlClassReceiver, umlClassFrame);
				undoRedoStack.redo(umlClassCommand);
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
                System.out.println("ADDATTRIB");
                if (selectedFrame != null) {
                    Command addAttributeCommand = UMLCommandFactory.addAttributeToClass(selectedFrame, new UMLAttribute("Asd"));
                    undoRedoStack.redo(addAttributeCommand);
                }
                else
                    System.out.println("No frame selected");
                break;
            case Constants.ACTION_COMMAND_REMOVEATTRIBUTE:
                System.out.println("Remove Attrib");
                if (selectedFrame != null) {
                    //Command removeAttributeCommand = UMLCommandFactory.removeAttributeFromClass()
                }
                break;
			default:
				System.out.println("Unknown Action");
				break;
		}
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
