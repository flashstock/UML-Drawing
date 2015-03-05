package hig.johanhugg.umldrawing.model;

/**
 * Created by Johan on 2015-03-01.
 */
public class UMLAttribute {
	private String name;
	public UMLAttribute(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}

}
