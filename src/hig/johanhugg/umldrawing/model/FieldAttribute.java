package hig.johanhugg.umldrawing.model;

/**
 * Created by Johan on 2015-03-08.
 */
public class FieldAttribute extends UMLAttribute {
	private Visibility vis;
	private String attribute;
	private String type;


	public FieldAttribute(Visibility vis, String attribute, String type) {
		super(Constants.ATTRIBUTE_FIELD);

		this.vis = vis;
		this.attribute = attribute;
		this.type = type;
	}

	@Override
	public String toString() {
		return (vis.getSymbol() + " " + attribute + " : " + type);
	}


}
