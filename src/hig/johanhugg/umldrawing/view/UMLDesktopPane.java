package hig.johanhugg.umldrawing.view;

import javax.swing.*;
import java.awt.*;

/**
 * Created by hugg on 26/02/15.
 */
public class UMLDesktopPane extends JDesktopPane {

    public UMLDesktopPane() {
        super();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawRect(0, 0, 100, 100);

    }
}
