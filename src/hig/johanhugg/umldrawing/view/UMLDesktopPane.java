package hig.johanhugg.umldrawing.view;

import hig.johanhugg.umldrawing.associations.Association;
import hig.johanhugg.umldrawing.associations.UMLAssociationManager;
import hig.johanhugg.umldrawing.model.Constants;
import hig.johanhugg.umldrawing.model.UMLClass;
import org.javatuples.Pair;

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
	private Graphics g;

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
		this.g = g;
        //drawCoolBackground(g);
        drawAssociations();
    }

	/*
	Taken from http://www.bytemycode.com/snippets/snippet/82/
	 */
	private void drawArrow(int x, int y, int xx, int yy )
	{
		float arrowWidth = 10.0f ;
		float theta = 0.423f ;
		int[] xPoints = new int[ 3 ] ;
		int[] yPoints = new int[ 3 ] ;
		float[] vecLine = new float[ 2 ] ;
		float[] vecLeft = new float[ 2 ] ;
		float fLength;
		float th;
		float ta;
		float baseX, baseY ;

		xPoints[ 0 ] = xx ;
		yPoints[ 0 ] = yy ;

		// build the line vector
		vecLine[ 0 ] = (float)xPoints[ 0 ] - x ;
		vecLine[ 1 ] = (float)yPoints[ 0 ] - y ;

		// build the arrow base vector - normal to the line
		vecLeft[ 0 ] = -vecLine[ 1 ] ;
		vecLeft[ 1 ] = vecLine[ 0 ] ;

		// setup length parameters
		fLength = (float)Math.sqrt( vecLine[0] * vecLine[0] + vecLine[1] * vecLine[1] ) ;
		th = arrowWidth / ( 2.0f * fLength ) ;
		ta = arrowWidth / ( 2.0f * ( (float)Math.tan( theta ) / 2.0f ) * fLength ) ;

		// find the base of the arrow
		baseX = ( (float)xPoints[ 0 ] - ta * vecLine[0]);
		baseY = ( (float)yPoints[ 0 ] - ta * vecLine[1]);

		// build the points on the sides of the arrow
		xPoints[ 1 ] = (int)( baseX + th * vecLeft[0] );
		yPoints[ 1 ] = (int)( baseY + th * vecLeft[1] );
		xPoints[ 2 ] = (int)( baseX - th * vecLeft[0] );
		yPoints[ 2 ] = (int)( baseY - th * vecLeft[1] );

		g.drawLine( x, y, (int)baseX, (int)baseY ) ;
		g.fillPolygon( xPoints, yPoints, 3 ) ;
	}

    private void drawCoolBackground() {
        g.drawImage(img, (this.getWidth() - img.getWidth(null)) / 2, (this.getHeight() - img.getHeight(null)) / 2, null);
        //TODO: ?
    }

    private void drawAssociations() {
        ArrayList<UMLClassFrame> internalFrames = new ArrayList<>();
        Arrays.asList(this.getAllFrames()).forEach(x -> internalFrames.add((UMLClassFrame) x));

        for (UMLClassFrame frame : internalFrames) {
			List<Pair<UMLClass, Association>> relatedWith = manager.relatedWith(frame.getAssociatedClass());
            for (Pair<UMLClass, Association> umlClassPair : relatedWith) {
                UMLClassFrame classFrame = getAssociatedFrame(umlClassPair.getValue0());
				drawAssociationShape(frame, classFrame, (Association) umlClassPair.getValue(1));
            }
        }

    }

	private void drawAssociationShape(UMLClassFrame first, UMLClassFrame second, Association value) {
		switch(value.getType()) {
			case Constants.ASSOCIATION_RAWASSOCIATION:
				drawLineBetweenClasses(first, second);
				break;
			case Constants.ASSOCIATION_AGGREGATION:
				break;
			case Constants.ASSOCIATION_COMPOSITION:
				break;
			case Constants.ASSOCIATION_GENERALIZATION:
				drawGeneralization(first, second);
				break;
		}
	}

	private void drawGeneralization(UMLClassFrame first, UMLClassFrame second) {
		drawArrow(first.getX(), first.getY(), second.getX(), second.getY());
	}

	private void drawLineBetweenClasses(UMLClassFrame first, UMLClassFrame second) {
		g.drawLine(first.getX(), first.getY(), second.getX(), second.getY());
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
