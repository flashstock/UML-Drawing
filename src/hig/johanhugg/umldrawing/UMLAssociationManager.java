package hig.johanhugg.umldrawing;

import org.javatuples.Triplet;
import org.javatuples.Tuple;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Johan on 2015-03-01.
 */
public class UMLAssociationManager {
	private ArrayList<Triplet<UMLClass, UMLClass, Association>> associations;
	public UMLAssociationManager() {
		associations = new ArrayList<>();
	}

	public void makeAssociation(UMLClass first, UMLClass second, Association associationType) {
		associations.add(Triplet.with(first, second, associationType));
	}

	public List<UMLClass> relatedWith(UMLClass umlClass) {
		ArrayList<UMLClass> foundRelations = new ArrayList<>();
		for (Tuple t : associations) {
			if (t.contains(umlClass)) {
				if (t.indexOf(umlClass) == 0)
					foundRelations.add((UMLClass) t.getValue(1));
				else
					foundRelations.add((UMLClass) t.getValue(0));
			}
		}
		System.out.println(umlClass.getName() + " is related with: ");
		for (UMLClass found : foundRelations)
			System.out.println(found.getName());
		return foundRelations;
	}

}
