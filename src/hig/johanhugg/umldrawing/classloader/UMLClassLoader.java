package hig.johanhugg.umldrawing.classloader;

import hig.johanhugg.umldrawing.model.UMLAttribute;
import hig.johanhugg.umldrawing.model.UMLAttributeFactory;
import hig.johanhugg.umldrawing.model.UMLClass;
import hig.johanhugg.umldrawing.model.Visibility;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Johan on 2015-03-12.
 */
public class UMLClassLoader {
	private Class loadedClass;

	public UMLClassLoader(String classToLoad) {
		loadClass(classToLoad);
	}

	public UMLClassLoader() {

	}

	public void loadClass(String name) {

		try {
			loadedClass = Class.forName(name);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public List<UMLAttribute> getMethods() {
		if (loadedClass == null)
			return null;
		ArrayList<UMLAttribute> umlAttributes = new ArrayList<>();
		Method[] methods = loadedClass.getDeclaredMethods();
		for (int i = 0; i < methods.length; i++) {
			Type[] typeParameters = methods[i].getGenericParameterTypes();
			ArrayList<String> typeNames = new ArrayList<>();

			for (int n = 0; n < typeParameters.length; n++)
				typeNames.add(typeParameters[n].getTypeName());

			Class<?> returnType = methods[i].getReturnType();

			umlAttributes.add(UMLAttributeFactory.createMethodAttribute(Visibility.getVisibilityFromModifier(methods[i].getModifiers()),
					methods[i].getName(), typeNames.stream().map(Object::toString).collect(Collectors.joining(" ")), returnType.getName()));

		}
		return umlAttributes;
	}

	public List<UMLAttribute> getFields() {
		if (loadedClass == null)
			return null;
		ArrayList<UMLAttribute> umlFields = new ArrayList<>();
		Field[] attributes = loadedClass.getDeclaredFields();
		for (int i = 0; i < attributes.length; i++) {
			umlFields.add(UMLAttributeFactory.createFieldAttribute(Visibility.getVisibilityFromModifier(attributes[i].getModifiers()),
					attributes[i].getName(), attributes[i].getType().getSimpleName()));
		}
		return umlFields;

	}

	public List<UMLAttribute> getConstructors() {
		if (loadedClass == null)
			return null;
		ArrayList<UMLAttribute> umlConstructors = new ArrayList<>();
		Constructor[] constructors = loadedClass.getConstructors();
		for (int i = 0; i < constructors.length; i++) {
			Type[] typeParameters = constructors[i].getGenericParameterTypes();
			ArrayList<String> typeNames = new ArrayList<>();

			for (int n = 0; n < typeParameters.length; n++)
				typeNames.add(typeParameters[n].getTypeName());

			umlConstructors.add(UMLAttributeFactory.createConstructorAttribute(Visibility.getVisibilityFromModifier(constructors[i].getModifiers()),
					loadedClass.getSimpleName(), typeNames.stream().map(Object::toString).collect(Collectors.joining(" "))));
		}
		return umlConstructors;
	}

	public UMLClass createUMLClassFromLoadedClass() {
		if (loadedClass == null)
			return null;
		UMLClass generatedClass = new UMLClass(loadedClass.getSimpleName());
		getFields().forEach(x -> generatedClass.addAttribute(x));
		getMethods().forEach(x -> generatedClass.addAttribute(x));
		getConstructors().forEach(x -> generatedClass.addAttribute(x));
		return generatedClass;
	}

	@Override
	public String toString() {
		List<UMLAttribute> allAttributes = new LinkedList<>();
        allAttributes.addAll(getFields());
        allAttributes.addAll(getConstructors());
        allAttributes.addAll(getMethods());

        return allAttributes.stream().map(Object::toString).collect(Collectors.joining("\n"));
	}

}
