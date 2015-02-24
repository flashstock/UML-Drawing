package hig.johanhugg;

import java.awt.*;

/**
 * Created by hugg on 16/02/15.
 */
public abstract class Figure implements Drawable {
    private Color color;

    public Figure(Color color) {
        this.color = color;
    }

    public Figure() {
        this(Color.black);
    }

    @Override
    public final void draw(Graphics g) {
        Color prevColor = g.getColor();
        if (color != null)
            g.setColor(color);
        drawSpecific(g);
        g.setColor(prevColor);
    }
    //Template method pattern
    protected abstract void drawSpecific(Graphics g);

    public abstract void move(int dx, int dy);
    public abstract boolean encloses(int x, int y);
    public abstract void moveTo(int x, int y);
}
