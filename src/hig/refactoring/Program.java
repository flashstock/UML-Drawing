package hig.refactoring;

import javax.swing.*;

/**
 * Created by Johan on 2015-02-25.
 */
public class Program {
	public static void main(String[] args) {
		System.out.println("Hello");
		SwingUtilities.invokeLater(() -> new UMLController(new UMLView(), new UMLModel()));
	}
}
