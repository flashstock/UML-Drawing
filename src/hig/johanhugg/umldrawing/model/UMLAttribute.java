package hig.johanhugg.umldrawing.model;

/**
 * Created by Johan on 2015-03-01.
 */
public class UMLAttribute {
	private String identifier;
	public UMLAttribute(String identifier) {
        this.identifier = identifier;
    }

	@Override
	public String toString() {
		return identifier;
	}

    public String getIdentifier() {
        return identifier;
    }

}
