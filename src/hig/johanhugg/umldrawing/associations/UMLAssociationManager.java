package hig.johanhugg.umldrawing.associations;

import hig.johanhugg.umldrawing.model.UMLClass;
import org.javatuples.Pair;
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
    private boolean changed = false;

	public void makeAssociation(UMLClass first, UMLClass second, Association associationType) {
		associations.add(Triplet.with(first, second, associationType));
	}

	public List<Pair<UMLClass, Association>> relatedWith(UMLClass umlClass) {
		ArrayList<Pair<UMLClass, Association>> foundRelations = new ArrayList<>();
        if (associations.size() == 0)
            return foundRelations;
		for (Tuple t : associations) {
			if (t.contains(umlClass)) {
				if (t.indexOf(umlClass) == 0) {
					foundRelations.add(Pair.with((UMLClass) t.getValue(1), (Association) t.getValue(2)));
				}
			}
		}
        if (foundRelations.size() > 0) {
            //System.out.println(umlClass.getName() + " is related with: ");
            //for (UMLClass found : foundRelations)
            //    System.out.println(found.getName());

        }
        return foundRelations;
	}

    public void removeAssociation(UMLClass first, UMLClass second) {
        Triplet toRemove = null;
        for (Triplet t : associations) {
            if (t.contains(first) && t.contains(second)) {
                toRemove = t;
            }
        }
        if (toRemove != null)
            associations.remove(toRemove);
    }
}
