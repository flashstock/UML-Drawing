package hig.johanhugg.umldrawing.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Johan on 2015-03-08.
 */
public class UMLAttributeFactory {
	public static UMLAttribute createConstructorAttribute(Visibility vis, String constructorName, String args) {
		return new ConstructorAttribute(vis, constructorName, args);
	}

	public static UMLAttribute createFieldAttribute(Visibility vis, String attribute, String type) {
		return new FieldAttribute(vis, attribute, type);
	}

	public static List<String> getListOfAttributes() {
		List<String> tmp = new ArrayList<>();
		tmp.add(Constants.ATTRIBUTE_CONSTRUCTOR);
		tmp.add(Constants.ATTRIBUTE_FIELD);
		tmp.add(Constants.ATTRIBUTE_METHOD);
		return tmp;
	}

	public static UMLAttribute createMethodAttribute(Visibility vis, String methodName, String args, String type) {
		return new MethodAttribute(vis, methodName, args, type);
	}
}
