package hig.johanhugg;

import java.awt.*;

/**
 * Created by Johan on 2015-02-22.
 */
public class Quadrangle extends Point {
    protected int width;
    protected int height;
    protected Rectangle rect;
	public Quadrangle(int x, int y, int width, int height) {
		super(x, y);
        this.width = width;
        this.height = height;
        rect = new Rectangle();
	}


    @Override
    protected void drawSpecific(Graphics g) {
        rect.setRect(x, y, width, height);
        g.drawRect((int) rect.getX(), (int) rect.getY(), (int) rect.getWidth(), (int) rect.getHeight());
    }

    @Override
    public boolean encloses(int x, int y) {
        return rect.contains(x, y);
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
