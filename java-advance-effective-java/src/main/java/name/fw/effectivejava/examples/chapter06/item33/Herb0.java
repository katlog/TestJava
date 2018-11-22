// Using an EnumMap to associate data with an enum - Pages 161-162
package name.fw.effectivejava.examples.chapter06.item33;

import java.util.HashSet;
import java.util.Set;

// Simplistic class representing a culinary herb - Page 161
public class Herb0 {
	public enum Type {
		ANNUAL, PERENNIAL, BIENNIAL
	}

	private final String name;
	private final Type type;

	Herb0(String name, Type type) {
		this.name = name;
		this.type = type;
	}

	@Override
	public String toString() {
		return name;
	}

	public static void main(String[] args) {
		Herb0[] garden = { new Herb0("Basil", Type.ANNUAL),
				new Herb0("Carroway", Type.BIENNIAL),
				new Herb0("Dill", Type.ANNUAL),
				new Herb0("Lavendar", Type.PERENNIAL),
				new Herb0("Parsley", Type.BIENNIAL),
				new Herb0("Rosemary", Type.PERENNIAL) };

		// Using ordinal() to index an array - DON'T DO THIS!
		Set<Herb0>[] herbsByType = (Set<Herb0>[])new Set[Type.values().length];
		for (int i = 0; i < herbsByType.length; i++) {
		    herbsByType[i] = new HashSet<Herb0>();
        }
		for (Herb0 herb0 : garden) {
            herbsByType[herb0.type.ordinal()].add(herb0);
        }
		
		for (int i = 0; i < herbsByType.length; i++) {
            Set<Herb0> set = herbsByType[i];
            System.out.printf("%s:%s%n", Type.values()[i],herbsByType[i]);
        }
	}
}
