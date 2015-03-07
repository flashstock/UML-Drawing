package hig.johanhugg.umldrawing.associations;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by hugg on 07/03/15.
 */
public class AssociationFactory {
    public static Association createRawAssociation(String name) {
        return new RawAssociation(name);
    }

    public List<Association> getListOfAssocations() {
        List<Association> tmp = new LinkedList<>();

        tmp.add(createRawAssociation("Sample"));

        return tmp;
    }
}
