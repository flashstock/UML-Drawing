package hig.johanhugg;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by hugg on 16/02/15.
 */
public class FigureList extends Observable implements Iterable<Figure>, Observer {
    protected LinkedList<Figure> figures = new LinkedList<Figure>();

    public void add(Figure figure) {
        figures.add(figure);
        setChanged();
        notifyObservers();
    }

    public void remove(Figure figure) {
        figures.remove(figure);
        setChanged();
        notifyObservers();
    }

    public Iterator<Figure> iterator() {
        return figures.iterator();
    }

    @Override
    public void update(Observable o, Object arg) {
        setChanged();
        this.notifyObservers();
    }


}
