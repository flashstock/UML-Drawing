package hig.johanhugg;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by hugg on 16/02/15.
 */
public class FigureViewer extends JPanel implements Observer {
    protected FigureList figureList;

    public FigureViewer(FigureList figureList) {
        this.figureList = figureList;
        figureList.addObserver(this);
        setBackground(Color.white);
    }

    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) { //Called by Swing-thread
        super.paintComponent(g);
        for (Figure f : figureList)
            f.draw(g);
    }
}
