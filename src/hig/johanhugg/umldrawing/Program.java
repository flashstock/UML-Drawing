package hig.johanhugg.umldrawing;

import hig.johanhugg.umldrawing.controller.UMLController;
import hig.johanhugg.umldrawing.model.UMLModel;
import hig.johanhugg.umldrawing.view.UMLView;

import javax.swing.*;

/**
 * Created by Johan on 2015-02-25.
 */
public class Program {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new UMLController(new UMLView(), new UMLModel()));
	}
}
