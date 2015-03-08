package hig.johanhugg.umldrawing.associations;

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

        return associations;
    }

	public static Association createAssociationFromString(String type) {
		switch (type) {
			case Constants.ASSOCIATION_RAWASSOCIATION:
				return createRawAssociation();
			default:
				return null;
		}
	}



}
