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
        figureList.add(new Quadrangle(500, 200, 200, 80));
        figureList.add(new UMLClass(200, 200, new JLabel("Den bästa klassen i hela världen")));
        figureList.add(new UMLClass(400, 400, new JLabel("Den näst bästa klassen i hela världen")));

        FigureViewer figureViewer = new FigureViewer(figureList);

        setBounds(0, 0, 800, 600);
        MouseInteraction mi = new MouseInteraction(figureList);

        figureViewer.addMouseListener(mi);
        figureViewer.addMouseMotionListener(mi);

        this.setContentPane(figureViewer);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JMenuBar jMenuBar = new JMenuBar();
        JMenu menu = new JMenu("File");
        menu.setBackground(Color.gray);
        jMenuBar.add(menu);
        jMenuBar.setBackground(Color.gray);
        this.setJMenuBar(jMenuBar);
        setVisible(true);
    }

}
