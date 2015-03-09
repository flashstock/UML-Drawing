package hig.johanhugg.umldrawing.model;

import java.util.Formatter;

/**
 * Created by Johan on 2015-03-09.
 */
public class MethodAttribute extends UMLAttribute {
	private final Visibility vis;
	private final String methodName;
	private final String args;
	private final String type;

	public MethodAttribute(Visibility vis, String methodName, String args, String type) {
		super(Constants.ATTRIBUTE_METHOD);

		this.vis = vis;
		this.methodName = methodName;
		this.args = args;
		this.type = type;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		Formatter formatter = new Formatter(sb);
		formatter.format("%s %s (%s) : %s", vis.getSymbol(), methodName, args, type);
		return sb.toString();
	}
}
