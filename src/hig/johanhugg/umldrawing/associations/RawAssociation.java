package hig.johanhugg.umldrawing.associations;

import hig.johanhugg.umldrawing.model.Constants;

/**
 * Created by Johan on 2015-03-01.
 */
public class RawAssociation implements Association {
    private String name;
	private static final String typeName = Constants.ASSOCIATION_RAWASSOCIATION;

	@Override
	public String getType() {
		return typeName;
	}

	@Override
	public String toString() {
		return getType();
	}
}
