package hig.refactoring;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyVetoException;

/**
 * Created by Johan on 2015-02-25.
 */
public class UMLController implements MouseListener, ActionListener {

	private final UMLView umlView;
	private final UMLModel umlModel;
	private JDesktopPane desktopPane;
	private JMenuItem newUMLClass;
	private UMLClassInvoker umlClassInvoker;
	private UMLClassReceiver umlClassReceiver;
	public UMLController(UMLView umlView, UMLModel umlModel) {
		this.umlView = umlView;
		this.umlModel = umlModel;
		this.umlClassInvoker = new UMLClassInvoker();
		this.umlClassReceiver = new UMLClassReceiver(umlView.getDesktopPane());
		addActionListeners(this);
	}

	private void addActionListeners(ActionListener actionListener) {
		umlView.getNewUMLClass().addActionListener(actionListener);

	}


	@Override
	public void actionPerformed(ActionEvent event) {
		switch(event.getActionCommand()) {
			case Constants.ACTION_COMMAND_NEWUMLCLASS:
				System.out.println("HELLO?");
				umlClassInvoker.setCommand(new NewUMLClassCommand(umlClassReceiver, new UMLClassFrame("TEST")));
				umlClassInvoker.executeCommand();
				break;
			default:
				System.out.println("Unknown Action");
				break;
		}
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
