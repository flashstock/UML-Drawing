package hig.johanhugg.umldrawing.model;

/**
 * Created by Johan on 2015-03-01.
 */
public class UMLAttribute {
	private String type;
	public UMLAttribute(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return type;
	}

}
