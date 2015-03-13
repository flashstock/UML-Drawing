package hig.johanhugg.umldrawing.classloader;

/**
 * Created by hugg on 13/03/15.
 */
public class ClassNotLoadedException extends RuntimeException {
    public ClassNotLoadedException() {
        super("Class is not loaded!");
    }
}
