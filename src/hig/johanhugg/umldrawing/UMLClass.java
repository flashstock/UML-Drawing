package hig.johanhugg.umldrawing;

import java.util.ArrayList;

/**
 * Created by Johan on 2015-02-25.
 */
public class UMLClass {
	private String name;
	private ArrayList<UMLAttribute> umlAttributes;

	public UMLClass(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void addAttribute(UMLAttribute attribute) {
		umlAttributes.add(attribute);
	}
}
