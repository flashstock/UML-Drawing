package hig.johanhugg.umldrawing.associations;

import hig.johanhugg.umldrawing.model.Constants;

/**
 * Created by Johan on 2015-03-11.
 */
public class Generalization extends Association {
	private static final String type = Constants.ASSOCIATION_GENERALIZATION;
	@Override
	public String getType() {
		return type;
	}

	@Override
	public String toString() {
		return getType();
	}
}
