package hig.johanhugg.umldrawing;

import net.miginfocom.layout.CC;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Created by Johan on 2015-02-25.
 */
public class UMLClassFrame extends JInternalFrame {
	private BoxLayout bottomLayout;
	private BorderLayout panelLayout;

	private JPanel parentPanel;
	private JPanel titlePanel;
	private JPanel bottomPanel;

	private JLabel titleLabel;

	public UMLClassFrame(String title) {
		super(title, true, false, false, true);
		setFrameIcon(null);
        setIconifiable(false);
		setSize(300, 300);
		setLocation(100, 100);
		titleLabel = new JLabel(title);

		this.parentPanel = new JPanel();
		parentPanel.setLayout(new MigLayout("fillx, debug"));
		CC componentConstraints = new CC();
		componentConstraints.alignX("center").spanX();
		titleLabel.setFont(titleLabel.getFont().deriveFont(18.0f));
		parentPanel.add(titleLabel, componentConstraints);
		parentPanel.add(new JLabel("Test"), "wrap");



		this.add(parentPanel);




	}
}
