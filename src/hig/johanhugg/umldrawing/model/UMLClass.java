package hig.johanhugg.umldrawing.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Johan on 2015-02-25.
 */
public class UMLClass {
	private String name;
	private ArrayList<UMLAttribute> umlAttributes;

	public UMLClass(String name) {
		this.name = name;
        this.umlAttributes = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

    public List<UMLAttribute> getAttributes() {
        return umlAttributes;
    }

	public void addAttribute(UMLAttribute attribute) {
		umlAttributes.add(attribute);
	}

	public void addAttribute(UMLAttribute attribute, int pos) {
		umlAttributes.add(pos, attribute);
	}

    public void removeAttribute(UMLAttribute attribute) {
        umlAttributes.remove(attribute);
    }

    public void setName(String name) {
        this.name = name;
    }
}
