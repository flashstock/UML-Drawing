package hig.johanhugg.umldrawing.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Johan on 2015-03-08.
 */
public enum Visibility {
	PRIVATE ("-"), PUBLIC("+"), PROTECTED("#"), DERIVED("#"), PACKAGE("~");
	private String symbol;

	Visibility(String c) {
		symbol = c;
	}

	@Override
	public String toString() {
		return name();
	}

	public String getSymbol() {
		return symbol;
	}

	public static List<Visibility> getSymbols() {
		List<Visibility> tmp = new ArrayList<>();
		tmp.add(Visibility.PRIVATE);
		tmp.add(Visibility.PUBLIC);
		tmp.add(Visibility.PROTECTED);
		tmp.add(Visibility.DERIVED);
		tmp.add(Visibility.PACKAGE);
		return tmp;
	}
}
