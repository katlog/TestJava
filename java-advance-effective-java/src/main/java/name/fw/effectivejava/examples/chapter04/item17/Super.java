package name.fw.effectivejava.examples.chapter04.item17;

public class Super {
	// Broken - constructor invokes an overridable _03method
	public Super() {
		overrideMe();
	}

	public void overrideMe() {
	}
}
