package hig.johanhugg.umldrawing.model;

import java.util.Formatter;

/**
 * Created by Johan on 2015-03-08.
 */
public class ConstructorAttribute extends UMLAttribute {
	private final Visibility vis;
	private final String constructorName;
	private String args;

	public ConstructorAttribute(Visibility vis, String constructorName, String args) {
		super(Constants.ATTRIBUTE_CONSTRUCTOR);
		this.vis = vis;
		this.constructorName = constructorName;
		this.args = args;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		Formatter formatter = new Formatter(sb);
		formatter.format("%s %s (%s)", vis.getSymbol(), constructorName, args);
		return sb.toString();
	}
}
