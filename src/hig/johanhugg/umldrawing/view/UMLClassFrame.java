package hig.johanhugg.umldrawing.view;

import hig.johanhugg.umldrawing.model.UMLAttribute;
import hig.johanhugg.umldrawing.model.UMLClass;
import net.miginfocom.layout.CC;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.*;
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
		super(null, false, false, false, true);
		setFrameIcon(null);
        setIconifiable(false);
		fieldFont = new Font("Monospaced", Font.PLAIN, 12);
		setSize(300, 300);
		setLocation(100, 100);
		titleLabel = new JLabel(associatedClass.getName());
		this.name = associatedClass.getName();
        this.associatedClass = associatedClass;
        this.attributeDict = new Hashtable<>();

		this.parentPanel = new JPanel();
		parentPanel.setBackground(Color.white);
		parentPanel.setLayout(new MigLayout("fillx"));
		CC componentConstraints = new CC();
		componentConstraints.alignX("center").spanX();
		titleLabel.setFont(titleLabel.getFont().deriveFont(18.0f));
		parentPanel.add(titleLabel, componentConstraints);

        this.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentMoved(ComponentEvent e) {
				super.componentMoved(e);
				getDesktopPane().repaint();
				//We need this so that the desktoppane repaints itself everytime the component is moved, otherwise lines will look weird.
				//This also saves performance since we only repaint when the component is moved, not all the time the line is drawn.
			}
		});

		if (associatedClass.getAttributes().size() > 0)
			loadExistingAttributes();

		this.add(parentPanel);
        this.setVisible(true);

	}

	private void loadExistingAttributes() {
		for (UMLAttribute attribute : associatedClass.getAttributes()) {
			JLabel addedAttr;
			this.attributeDict.put(attribute, addedAttr = new JLabel(attribute.toString()));
			addedAttr.setFont(fieldFont);
			parentPanel.add(addedAttr, "wrap");
		}
		update();
	}

    public void addAttribute(UMLAttribute attribute) {
        JLabel addedAttr;
        this.attributeDict.put(attribute, addedAttr = new JLabel(attribute.toString()));
        parentPanel.add(addedAttr, "wrap");
		addedAttr.setFont(fieldFont);
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

    public UMLClass getAssociatedClass() {
        return associatedClass;
    }

    @Override
    public String toString() {
        return associatedClass.getName();
    }


	@Override
	public String getName() {
		return name;
	}
}
