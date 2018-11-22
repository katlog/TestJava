// Abuse of ordinal to derive an associated value - don't do this
package name.fw.effectivejava.examples.chapter06.item31;

public enum Ensemble0 {
	SOLO, DUET, TRIO, QUARTET, QUINTET, 
	SEXTET, SEPTET, OCTET, NONET, DECTET ;

	public int numberOfMusicians() {
		return ordinal() +1;
	}
}
