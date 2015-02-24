package hig.johanhugg;

import java.awt.*;

/**
 * Created by hugg on 16/02/15.
 */
public class Point extends Figure {
    protected int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    protected void drawSpecific(Graphics g) {
        g.drawOval(x, y, 0, 0);
    }

    @Override
    public void move(int dx, int dy) {
        x += dx;
        y += dy;
    }

    @Override
    public boolean encloses(int x, int y) {
        return this.x == x && this.y == y;
    }

    @Override
    public void moveTo(int x, int y) {
        this.x = x;
        this.y = y;
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
