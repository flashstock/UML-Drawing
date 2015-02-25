package hig.refactoring;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Johan on 2015-02-25.
 */
public class UMLController implements MouseListener, ActionListener {

	private final UMLView umlView;
	private final UMLModel umlModel;

	public UMLController(UMLView umlView, UMLModel umlModel) {
		this.umlView = umlView;
		this.umlModel = umlModel;
		addActionListeners();
	}

	private void addActionListeners() {

	}


	@Override
	public void actionPerformed(ActionEvent e) {

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
