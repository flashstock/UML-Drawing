package hig.johanhugg.umldrawing.view;

import hig.johanhugg.umldrawing.model.UMLAttribute;
import hig.johanhugg.umldrawing.model.UMLClass;
import net.miginfocom.layout.CC;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.beans.PropertyVetoException;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by Johan on 2015-02-25.
 */
public class UMLClassFrame extends JInternalFrame {
	private JPanel parentPanel;
	private JLabel titleLabel;
	private String name;
	private Font fieldFont;

	private UMLClass associatedClass;
    private Dictionary<UMLAttribute, JLabel> attributeDict;

	public UMLClassFrame(UMLClass associatedClass) {
		super(null, true, false, false, true);
		setFrameIcon(null);
        setIconifiable(false);
		fieldFont = new Font("Monospaced", Font.PLAIN, 12);
		setLocation(100, 100);
		titleLabel = new JLabel(associatedClass.getName());
		this.name = associatedClass.getName();
        this.associatedClass = associatedClass;
        this.attributeDict = new Hashtable<>();
        setSize(new Dimension(200, 200));

		this.parentPanel = new JPanel();
		parentPanel.setBackground(Color.white);
		parentPanel.setLayout(new MigLayout("fillx"));
		CC componentConstraints = new CC();
		componentConstraints.alignX("center").spanX();
		titleLabel.setFont(titleLabel.getFont().deriveFont(18.0f));
		parentPanel.add(titleLabel, componentConstraints);



		if (associatedClass.getAttributes().size() > 0)
			loadExistingAttributes();

		this.add(parentPanel);
        try {
            this.setSelected(true);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        this.pack();

    }

	private void loadExistingAttributes() {
		for (UMLAttribute attribute : associatedClass.getAttributes()) {
			JLabel addedAttr;
			this.attributeDict.put(attribute, addedAttr = new JLabel(attribute.toString()));
			addedAttr.setFont(fieldFont);
			parentPanel.add(addedAttr, "wrap");
		}
		updateFields();
	}

    public void addAttribute(UMLAttribute attribute) {
        addAttribute(attribute, -1);
    }

	public void addAttribute(UMLAttribute attribute, int pos) {
		JLabel addedAttr;
		this.attributeDict.put(attribute, addedAttr = new JLabel(attribute.toString()));
		parentPanel.add(addedAttr, "wrap");
		addedAttr.setFont(fieldFont);
		if (pos == -1) {
			associatedClass.addAttribute(attribute);
		} else
			associatedClass.addAttribute(attribute, pos);
		updateFields();
	}

    public void removeAttribute(UMLAttribute attribute) {
        JLabel associatedLabel = attributeDict.get(attribute);
        parentPanel.remove(associatedLabel);
        attributeDict.remove(attribute);
        associatedClass.removeAttribute(attribute);
        updateFields();
    }

    public void updateFields() {
        Enumeration<JLabel> elements = attributeDict.elements();
        while (elements.hasMoreElements())
            parentPanel.remove(elements.nextElement());

        this.attributeDict = new Hashtable<>();
        associatedClass.getAttributes().stream().forEach(x -> {
			JLabel addedAttr;
			attributeDict.put(x, addedAttr = new JLabel(x.toString()));
			addedAttr.setFont(fieldFont);
			parentPanel.add(addedAttr, "wrap");
		});
        update();
    }

    public void setUMLClassName(String name) {
        associatedClass.setName(name);
        titleLabel.setText(name);
    }

    public List<UMLAttribute> getAttributes() {
        return associatedClass.getAttributes();
    }

    private void update() {
        this.revalidate();
        this.repaint();
    }

    public UMLClass getAssociatedClass() {
        return associatedClass;
    }

    @Override
    public String toString() {
        return associatedClass.getName();
    }


	public String getUMLClassName() {
        return associatedClass.getName();
    }
}
