package hig.refactoring;

import java.util.Stack;

/**
 * Created by hugg on 26/02/15.
 */
public class UndoRedoStack {
    private Stack<Command> redoStack = new Stack<Command>();
    private Stack<Command> undoStack = new Stack<Command>();

    public void redo(Command cmd) {
        cmd.execute();
        undoStack.push(cmd);
    }

    public void undo(Command cmd) {
        cmd.undo();
        redoStack.push(cmd);
    }

    public void undo() {
        if (!undoStack.isEmpty())
            undo(undoStack.pop());
    }

    public void redo() {
        if (!redoStack.isEmpty())
            redo(redoStack.pop());
    }
}
