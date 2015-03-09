package hig.johanhugg.umldrawing.view;

import hig.johanhugg.umldrawing.associations.UMLAssociationManager;
import hig.johanhugg.umldrawing.model.UMLClass;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

/**
 * Created by hugg on 26/02/15.
 */
public class UMLDesktopPane extends JDesktopPane {

    private UMLAssociationManager manager;
    private Image img;

    public UMLDesktopPane(UMLAssociationManager manager) {
        super();
        this.manager = manager;
        try {
            img = ImageIO.read(new File("UML_logo.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public List<UMLClassFrame> getAllUMLFrames() {
        ArrayList<UMLClassFrame> internalFrames = new ArrayList<>();
        Arrays.asList(this.getAllFrames()).forEach(x -> internalFrames.add((UMLClassFrame) x));
        return internalFrames;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //drawCoolBackground(g);
        drawAssociations(g);
    }

    private void drawCoolBackground(Graphics g) {
        g.drawImage(img, (this.getWidth() - img.getWidth(null)) / 2, (this.getHeight() - img.getHeight(null)) / 2, null);
        //TODO: ?
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
