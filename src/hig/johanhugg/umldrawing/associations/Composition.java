package hig.johanhugg.umldrawing.associations;

import hig.johanhugg.umldrawing.model.Constants;

/**
 * Created by Johan on 2015-03-12.
 */
public class Composition extends Association {
	private static final String type = Constants.ASSOCIATION_COMPOSITION;

	@Override
	public String getType() {
		return type;
	}

	@Override
	public String toString() {
		return getType();
	}
}
