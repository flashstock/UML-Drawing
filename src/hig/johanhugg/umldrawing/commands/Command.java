package hig.johanhugg.umldrawing.commands;

/**
 * Created by Johan on 2015-02-25.
 */
public interface Command {
	public void execute();
	public void undo();
}
