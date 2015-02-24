package hig.johanhugg;

import javax.swing.*;
import java.awt.*;

/**
 * Created by hugg on 24/02/15.
 */
public class UMLClass extends Figure {
    private Quadrangle topBox;
    private Quadrangle bodyBox;
    private JLabel title;
    private int x;
    private int y;
    private Dimension titleDimension;

    public UMLClass(int x, int y, JLabel title) {
        super();
        this.x = x;
        this.y = y;
        this.title = title;
        titleDimension = title.getPreferredSize();
        topBox = new Quadrangle(x, y, (int) titleDimension.getWidth() * 2, (int) titleDimension.getHeight());
        bodyBox = new Quadrangle(x, y, (int) titleDimension.getWidth() * 2, 200);
    }

    @Override
    public void drawSpecific(Graphics g) {
        topBox.drawSpecific(g);
        bodyBox.drawSpecific(g);
        g.drawChars(title.getText().toCharArray(), 0, title.getText().length(), x + (int) titleDimension.getWidth() / 2, y + (int) titleDimension.getHeight() - 3);
    }

    @Override
    public void move(int dx, int dy) {
        x += dx;
        y += dy;
    }

    @Override
    public boolean encloses(int x, int y) {
        return (topBox.encloses(x, y) || bodyBox.encloses(x, y));
    }

    @Override
    public void moveTo(int x, int y) {
        this.x = x;
        this.y = y;
        topBox.x = x;
        topBox.y = y;
        bodyBox.x = x;
        bodyBox.y = this.y + topBox.height;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }
}
