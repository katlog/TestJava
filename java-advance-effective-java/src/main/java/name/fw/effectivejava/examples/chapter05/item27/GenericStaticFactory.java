// Generic static factory _03method - Pages 130-131
package name.fw.effectivejava.examples.chapter05.item27;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenericStaticFactory {
	// TestGenericMethodReflect static factory _03method
	public static <K, V> HashMap<K, V> newHashMap() {
		return new HashMap<K, V>();
	}

	public static void main(String[] args) {
		// Parameterized type instance creation with static factory
		Map<String, List<String>> anagrams = newHashMap();
		
      // Parameterized type instance creation with constructor
        Map<String, List<String>> anagrams1 = new HashMap<String, List<String>>();

	}
}
