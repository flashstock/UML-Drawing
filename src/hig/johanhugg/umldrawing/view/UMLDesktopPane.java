package hig.johanhugg.umldrawing.view;

import hig.johanhugg.umldrawing.associations.UMLAssociationManager;
import hig.johanhugg.umldrawing.model.UMLClass;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by hugg on 26/02/15.
 */
public class UMLDesktopPane extends JDesktopPane {

    private UMLAssociationManager manager;

    public UMLDesktopPane(UMLAssociationManager manager) {
        super();
        this.manager = manager;
    }


    public List<UMLClassFrame> getAllUMLFrames() {
        ArrayList<UMLClassFrame> internalFrames = new ArrayList<>();
        Arrays.asList(this.getAllFrames()).forEach(x -> internalFrames.add((UMLClassFrame) x));
        return internalFrames;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawAssociations(g);
    }

    private void drawAssociations(Graphics g) {
        ArrayList<UMLClassFrame> internalFrames = new ArrayList<>();
        Arrays.asList(this.getAllFrames()).forEach(x -> internalFrames.add((UMLClassFrame) x));

        for (UMLClassFrame frame : internalFrames) {
            List<UMLClass> relatedWith = manager.relatedWith(frame.getAssociatedClass());
            for (UMLClass umlClass : relatedWith) {
                UMLClassFrame classFrame = getAssociatedFrame(umlClass);
                g.drawLine(frame.getX(), frame.getY(), classFrame.getX(), classFrame.getY());
                //System.out.printf("Frame x: %s, Frame y: %s, Frame2 x: %s, Frame2 y: %s \n", frame.getX(), frame.getY(), classFrame.getX(), classFrame.getY());
            }
        }
        //this.repaint();

    }

    private UMLClassFrame getAssociatedFrame(UMLClass umlClass) {
        for (UMLClassFrame tmp : getAllUMLFrames()) {
            if (tmp.getAssociatedClass().equals(umlClass))
                return tmp;
        }
        return null;
    }

    public UMLAssociationManager getAssociationManager() {
        return manager;
    }
}
