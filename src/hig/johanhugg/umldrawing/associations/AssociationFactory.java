package hig.johanhugg.umldrawing.associations;

import com.sun.org.apache.bcel.internal.classfile.ConstantNameAndType;
import hig.johanhugg.umldrawing.model.Constants;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by hugg on 07/03/15.
 */
public class AssociationFactory {
    public static Association createRawAssociation() {
        return new RawAssociation();
    }

    public static List<String> getListOfAssocations() {
		List<String> associations = new LinkedList<>();

		associations.add(Constants.ASSOCIATION_RAWASSOCIATION);
		associations.add(Constants.ASSOCIATION_AGGREGATION);
		associations.add(Constants.ASSOCIATION_COMPOSITION);
		associations.add(Constants.ASSOCIATION_GENERALIZATION);

        return associations;
    }

	public static Association createAssociationFromString(String type) {
		switch (type) {
			case Constants.ASSOCIATION_RAWASSOCIATION:
				return createRawAssociation();
			case Constants.ASSOCIATION_GENERALIZATION:
				return createGeneralization();
			default:
				return null;
		}
	}

	private static Association createGeneralization() {
		return new Generalization();
	}


}
