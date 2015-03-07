package hig.johanhugg.umldrawing.associations;

/**
 * Created by Johan on 2015-03-01.
 */
public class RawAssociation implements Association {
    private String name;

    public RawAssociation(String name) {
        this.name = name;
    }
	@Override
	public String getType() {
		return null;
	}
}
