package hig.johanhugg.umldrawing;

import net.miginfocom.layout.CC;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Johan on 2015-02-25.
 */
public class UMLClassFrame extends JInternalFrame {
	private BoxLayout bottomLayout;
	private BorderLayout panelLayout;

	private JPanel parentPanel;
	private JLabel titleLabel;

	private UMLClass associatedClass;

	public UMLClassFrame(UMLClass associatedClass) {
		super(associatedClass.getName(), true, false, false, true);
		setFrameIcon(null);
        setIconifiable(false);
		setSize(300, 300);
		setLocation(100, 100);
		titleLabel = new JLabel(title);

		this.parentPanel = new JPanel();
		parentPanel.setBackground(Color.white);
		parentPanel.setLayout(new MigLayout("fillx"));
		CC componentConstraints = new CC();
		componentConstraints.alignX("center").spanX();
		titleLabel.setFont(titleLabel.getFont().deriveFont(18.0f));
		parentPanel.add(titleLabel, componentConstraints);
		parentPanel.add(new JLabel("Test"), "wrap");

		this.add(parentPanel);

	}


}
