package hig.johanhugg.umldrawing.classloader;

import hig.johanhugg.umldrawing.model.UMLAttribute;
import hig.johanhugg.umldrawing.model.UMLAttributeFactory;
import hig.johanhugg.umldrawing.model.UMLClass;
import hig.johanhugg.umldrawing.model.Visibility;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Arrays;
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

	public List<UMLAttribute> getMethods() throws ClassNotLoadedException {
		if (loadedClass == null)
			throw new ClassNotLoadedException();

		ArrayList<UMLAttribute> umlAttributes = new ArrayList<>();

        Arrays.stream(loadedClass.getDeclaredMethods()).forEach(method -> {
            ArrayList<String> typeNames = new ArrayList<>();

            Arrays.stream(method.getParameterTypes()).forEach(type -> typeNames.add(type.getSimpleName()));

            Class<?> returnType = method.getReturnType();

            umlAttributes.add(UMLAttributeFactory.createMethodAttribute(Visibility.getVisibilityFromModifier(method.getModifiers()),
                    method.getName(), typeNames.stream().map(Object::toString).collect(Collectors.joining(" ")), returnType.getSimpleName()));
        });

		return umlAttributes;
	}

	public List<UMLAttribute> getFields() throws ClassNotLoadedException {
		if (loadedClass == null)
            throw new ClassNotLoadedException();

		ArrayList<UMLAttribute> umlFields = new ArrayList<>();

        Arrays.stream(loadedClass.getDeclaredFields()).forEach(attr -> {
            umlFields.add(UMLAttributeFactory.createFieldAttribute(Visibility.getVisibilityFromModifier(attr.getModifiers()),
                    attr.getName(), attr.getType().getSimpleName()));
        });

		return umlFields;

	}

	public List<UMLAttribute> getConstructors() throws ClassNotLoadedException {
		if (loadedClass == null)
            throw new ClassNotLoadedException();

		ArrayList<UMLAttribute> umlConstructors = new ArrayList<>();

        Arrays.stream(loadedClass.getConstructors()).forEach(cons -> {
            Class[] typeParameters = cons.getParameterTypes();
            ArrayList<String> typeNames = new ArrayList<>();

            Arrays.stream(typeParameters).forEach(x -> typeNames.add(x.getSimpleName()));

            umlConstructors.add(UMLAttributeFactory.createConstructorAttribute(Visibility.getVisibilityFromModifier(cons.getModifiers()),
                    loadedClass.getSimpleName(), typeNames.stream().map(Object::toString).collect(Collectors.joining(" "))));
        });

		return umlConstructors;
	}

	public UMLClass createUMLClassFromLoadedClass() throws ClassNotLoadedException {
		if (loadedClass == null)
            throw new ClassNotLoadedException();
		UMLClass generatedClass = new UMLClass(loadedClass.getSimpleName());

        getConstructors().forEach(x -> generatedClass.addAttribute(x));
		getFields().forEach(x -> generatedClass.addAttribute(x));
		getMethods().forEach(x -> generatedClass.addAttribute(x));

		return generatedClass;
	}

	@Override
	public String toString() {
		List<UMLAttribute> allAttributes = new LinkedList<>();
        allAttributes.addAll(getConstructors());
        allAttributes.addAll(getFields());
        allAttributes.addAll(getMethods());

        return allAttributes.stream().map(Object::toString).collect(Collectors.joining("\n"));
	}

}
