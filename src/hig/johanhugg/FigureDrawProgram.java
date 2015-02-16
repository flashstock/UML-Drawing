package hig.johanhugg;

import javax.swing.*;
import java.awt.*;

/**
 * Created by hugg on 16/02/15.
 */
public class FigureDrawProgram extends JFrame {
    public FigureDrawProgram() {
        FigureList figureList = new FigureList();
        figureList.add(new Circle(100, 100, 20));
        figureList.add(new Circle(200, 200, 20));
        figureList.add(new Circle(300, 300, 5));
        figureList.add(new Circle(400, 400, 2));
        FigureViewer figureViewer = new FigureViewer(figureList);
        this.setContentPane(figureViewer);
        setBounds(0, 0, 800, 600);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

}
