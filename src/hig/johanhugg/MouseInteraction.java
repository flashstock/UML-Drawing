package hig.johanhugg;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Observable;

/**
 * Created by hugg on 16/02/15.
 */
public class MouseInteraction extends Observable implements MouseMotionListener, MouseListener {
    private Figure selectedFigure;
    private FigureList figureList;

    public MouseInteraction(FigureList list) {
        this.figureList = list;
        this.addObserver(figureList);
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        for (Figure f : figureList)
            if (f.encloses(e.getX(), e.getY()))
                selectedFigure = f;

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (selectedFigure != null) {
            selectedFigure.moveTo(e.getX(), e.getY());
            notifyObservers();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
