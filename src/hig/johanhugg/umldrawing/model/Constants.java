package hig.johanhugg.umldrawing.model;

/**
 * Created by Johan on 2015-02-25.
 */
public class Constants {
    /*
    Action commands for listeners
     */
	public static final String ACTION_COMMAND_NEWUMLCLASS =	"New UML Class";
    public static final String ACTION_COMMAND_UNDO = "Undo";
    public static final String ACTION_COMMAND_REDO = "Redo";
    public static final String ACTION_COMMAND_ADDATTRIBUTE = "Add Attribute";
    public static final String ACTION_COMMAND_REMOVEATTRIBUTE = "Remove Attribute";
    public static final String ACTION_COMMAND_NEWASSOCIATION = "Create Association";
    public static final String ACTION_COMMAND_REMOVECLASS = "Remove Class";
    public static final String ACTION_COMMAND_EDITATTRIBUTE = "Edit Attribute";
    public static final String ACTION_COMMAND_EDITCLASSNAME = "Edit Class Name";
    public static final String ACTION_COMMAND_NEWUMLCLASSFROMCOMPILEDSOURCE = "New UML Class From Compiled Source";


    /*
    Association types
     */
	public static final String ASSOCIATION_RAWASSOCIATION = "Raw Association";
	public static final String ASSOCIATION_AGGREGATION = "Aggregation";
	public static final String ASSOCIATION_COMPOSITION = "Composition";
	public static final String ASSOCIATION_GENERALIZATION = "Generalization";

    /*
    Attribute types
     */
	public static final String ATTRIBUTE_CONSTRUCTOR = "Constructor";
	public static final String ATTRIBUTE_METHOD = "Method";
	public static final String ATTRIBUTE_FIELD = "Field";


}
