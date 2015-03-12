package hig.johanhugg.umldrawing.associations;

import hig.johanhugg.umldrawing.model.Constants;

/**
 * Created by Johan on 2015-03-12.
 */
public class Aggregation extends Association {
	private static final String type = Constants.ASSOCIATION_AGGREGATION;

	@Override
	public String getType() {
		return type;
	}

	@Override
	public String toString() {
		return getType();
	}
}
