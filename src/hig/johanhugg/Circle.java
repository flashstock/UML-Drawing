package hig.johanhugg;

import java.awt.*;

/**
 * Created by hugg on 16/02/15.
 */
public class Circle extends Point {
    private int radius;

    public Circle(int x, int y, int radius) {
        super(x, y);
        this.radius = radius;
    }

    @Override
    protected void drawSpecific(Graphics g) {
        g.drawOval(x - radius, y - radius, 2 * radius, 2 * radius);
    }

    @Override
    public boolean encloses(int x, int y) {
        int dx = this.x - x;
        int dy = this.y - y;


        if (dx * dx + dy * dy <= radius * radius) return true;
        else return false;
    }

}
