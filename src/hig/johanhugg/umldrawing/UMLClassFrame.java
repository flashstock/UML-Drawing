package hig.johanhugg.umldrawing;

import net.miginfocom.layout.CC;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by Johan on 2015-02-25.
 */
public class UMLClassFrame extends JInternalFrame {
	private JPanel parentPanel;
	private JLabel titleLabel;

	private UMLClass associatedClass;
    private Dictionary<UMLAttribute, JLabel> attributeDict;

	public UMLClassFrame(UMLClass associatedClass) {
		super(associatedClass.getName(), true, false, false, true);
		setFrameIcon(null);
        setIconifiable(false);
		setSize(300, 300);
		setLocation(100, 100);
		titleLabel = new JLabel(title);
        this.associatedClass = associatedClass;
        this.attributeDict = new Hashtable<>();

		this.parentPanel = new JPanel();
		parentPanel.setBackground(Color.white);
		parentPanel.setLayout(new MigLayout("fillx"));
		CC componentConstraints = new CC();
		componentConstraints.alignX("center").spanX();
		titleLabel.setFont(titleLabel.getFont().deriveFont(18.0f));
		parentPanel.add(titleLabel, componentConstraints);

		this.add(parentPanel);

	}

    public void addAttribute(UMLAttribute attribute) {
        JLabel addedAttr;
        this.attributeDict.put(attribute, addedAttr = new JLabel(attribute.toString()));
        parentPanel.add(addedAttr, "wrap");
        associatedClass.addAttribute(attribute);
        update();
    }

    public void removeAttribute(UMLAttribute attribute) {
        JLabel associatedLabel = attributeDict.get(attribute);
        parentPanel.remove(associatedLabel);
        attributeDict.remove(attribute);
        associatedClass.removeAttribute(attribute);
        update();
    }

    public List<UMLAttribute> getAttributes() {
        return associatedClass.getAttributes();
    }

    private void update() {
        this.revalidate();
        this.repaint();
    }



}
